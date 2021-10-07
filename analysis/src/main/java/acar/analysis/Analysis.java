package acar.analysis;

import acar.analysis.minicfg.*;
import acar.utils.Exporter;
import edu.uci.ics.jung.algorithms.filters.EdgePredicateFilter;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.objectweb.asm.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Analysis {

    public static Logger LOGGER = Logger.getLogger(Analysis.class);

    private static final char SEP = ',';

    public Map<Method,List<Node>> methodInstructions = new HashMap<>();
    public DirectedGraph<Class,SubtypeOf> subtypeGraph = new DirectedSparseGraph<>();
    public DirectedGraph<Class,Association> associationGraph = new DirectedSparseGraph<>();
    public DirectedGraph<Method,MethodRelationship> chaGraph = new DirectedSparseGraph<>();

    public Map<String, Class> classesByName = new HashMap<>();
    public Map<String, Method> methodsByName = new HashMap<>();

    public Set<String> unresolvableCallsites = new HashSet<>();
    public Set<String> namesOfClassesNotFound = new HashSet<>();

    public Set<Composite> composites = null;
    public Set<Method> entryPoints = new HashSet<>();
    public Set<Field> fields = new HashSet<>();
    private Map<String,Method> methodsByDoopName = new HashMap<>();



    public static void main (String[] args) throws Exception {

        File inputFile = new File("dataset/test-" + args[0]);
        File outputFolder = new File("out");
        new File("out").mkdirs();

        BasicConfigurator.configure();

        Analysis analysis = new Analysis();
        analysis.runAnalysis(inputFile, outputFolder);


        LOGGER.addAppender(
                new org.apache.log4j.FileAppender(
                        new org.apache.log4j.PatternLayout("%m%n"), "out/analysis_log.txt"
                )
        );


        Exporter.exportElements(new File(outputFolder,"fields.txt"), analysis.fields,
                e -> {
                    String s = "";
                    String header = "";
                    if (e.getParamTypes().size() == 0)
                        return "";
                    header = header + e.getOwner().getSootClassName() + ',';
                    header = header + e.getName() +  ',';
                    for(String type: e.getParamTypes()) {
                        if (type.length() == 1 || !type.contains("/")) {
                            type = "java/lang/Object";
                        }
                        s = s + header + type.replaceAll("/", ".") + '\n';
                    }
                    return s.substring(0, s.length()-1);
                }
        );

        Map<Method,List<Method>> callsInLoop = new HashMap<>();


        analysis.methodInstructions.keySet().stream().forEach(e -> {
            List<Method> methods = analysis.findIterativeCalls(e, analysis.methodInstructions.get(e));
            if (methods.size() != 0) {
                callsInLoop.put(e, methods);
            }

        });


        Exporter.exportElements(new File(outputFolder,"looped.txt"), callsInLoop.keySet(),
                e -> {

                    String s = "";
                    List<Method> methods = callsInLoop.get(e);
                    for(Method c: methods) {
                        s = s + (e.doopMethodName() + "\t" + c.doopMethodName()) + "\n";
                    }

                    return s.substring(0, s.length()-1);
                }
        , e -> analysis.methodInstructions.get(e).size() != 0);


    }

    public List<Method> findIterativeCalls(Method m, List<Node> ins) {
        List<Method> results = new ArrayList<>();

        CFGraph cg = new CFGraph(ins);
        if (m.toString().contains("copyData") && m.toString().contains("ProfileRed")) {
            System.err.println(m);
            System.err.println(ins);
            System.err.println(cg.toGraphViz());
        }

        for(Node i: ins) {
            if (i instanceof CallNode) {
                CallNode cn = (CallNode) i;
                if (cn.target.getName().equals(m.getName()) && !m.getName().equals("<init>") && cg.hasPath(i, i))
                    results.add(cn.target);
            }
        }


        return results;
    }

    public void runAnalysis(File inputFile,File outputFolder) throws Exception {
        // first pass: collect classes
        ClassVisitor visitor = new ClassVisitor(Opcodes.ASM5) {
            private Class klass = null;
            @java.lang.Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                // infer type from opCodes
                // note that annotations are also flagged as interfaces
                Class.Kind kind = null;
                if (checkFlag(access,Opcodes.ACC_ANNOTATION)) {
                    kind = Class.Kind.INTERFACE;
                }
                else if (checkFlag(access,Opcodes.ACC_INTERFACE)) {
                    kind = Class.Kind.INTERFACE;
                }
                else if (checkFlag(access,Opcodes.ACC_ENUM)) {
                    kind = Class.Kind.ENUM;
                }
                else {
                    kind = Class.Kind.CLASS;
                }

                klass = new Class(name,kind);
                classesByName.put(name,klass);
                subtypeGraph.addVertex(klass);
                associationGraph.addVertex(klass);
            }

            @java.lang.Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
                Method method = new Method(name,klass,desc,signature);

                if (name.equals("readObject") && desc.equals("(Ljava/io/ObjectInputStream;)V"))
                    entryPoints.add(method);

                chaGraph.addVertex(method);
                klass.addMethod(method);
                methodsByName.put(method.getId(),method);
                methodsByDoopName.put(method.doopMethodName(), method);
                return null;
            }

        };
        analyse(inputFile,visitor);

        // second pass: build subtype graph
        visitor = new ClassVisitor(Opcodes.ASM5) {
            private Class klass = null;
            @java.lang.Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                klass = classesByName.get(name);
                Class superKlass = lookup(classesByName,namesOfClassesNotFound,superName);
                if (superKlass!=null) {
                    subtypeGraph.addEdge(new SubtypeOf(SubtypeOf.Kind.EXTENDS), klass, superKlass);
                }
                for (String ifName:interfaces) {
                    Class ifClass = lookup(classesByName,namesOfClassesNotFound,ifName);
                    if (ifClass!=null) {
                        subtypeGraph.addEdge(new SubtypeOf(SubtypeOf.Kind.IMPLEMENTS), klass, ifClass);
                    }
                }
            }

            @java.lang.Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
                String id = Method.getId(klass.getName(), name, desc);
                Method method = methodsByName.get(id);
                assert method!=null;

                return new MethodVisitor(Opcodes.ASM5) {

                    public List<Node> getBlocks() {
                        List<Node> blocks = methodInstructions.get(method);

                        if (blocks == null) {
                            blocks =  new ArrayList<>();
                            methodInstructions.put(method, blocks);
                        }
                        return blocks;
                    }

                    @Override
                    public void visitLabel(Label l) {

                        List<Node> blocks = getBlocks();
                        blocks.add(new LabelNode(l));
                    }
                    @Override
                    public void visitJumpInsn(int opcode, Label l) {
                        List<Node> blocks = getBlocks();
                        blocks.add(new JumpNode(l, opcode));
                    }
                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                        String id = Method.getId(owner,name,desc);

                        Method invokedMethod = methodsByName.get(id);
                        if (invokedMethod != null) {
                            List<Node> blocks = getBlocks();
                            blocks.add(new CallNode(invokedMethod));
                        }

                        //assert invokedMethod != null;
                        if (invokedMethod != null) {
                            chaGraph.addEdge(new Invokes(), method, invokedMethod);

                        }
                        else {
                            String unresolvableCallsite = method.getId() + "#" + id;
                            unresolvableCallsites.add(unresolvableCallsite);
                        }
                    }
                };
            }

        };

        analyse(inputFile,visitor);

        // third pass: build association and cha graphs
        final Set<Class> containerTypes = getContainerTypes(subtypeGraph, classesByName);


        visitor = new ClassVisitor(Opcodes.ASM5) {
            private Class klass = null;
            @java.lang.Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                klass = classesByName.get(name);
            }

            @java.lang.Override
            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                boolean isStatic = checkFlag(access,Opcodes.ACC_STATIC);

                // do not track static fields !
                if (isStatic) {
                    return null;
                }

                boolean isArray = desc.startsWith("[");

                if (isArray) {
                    while (desc.startsWith("[")) {
                        desc = desc.substring(1);
                    }
                }
                if (desc.startsWith("L") && desc.endsWith(";")) {
                    // ref type, ignore primitive fields

                    Class refClass = classesByName.get(desc.substring(1, desc.length()-1));
                    Set<String> typeParamNames = null;
                    if (signature!=null) {
                        typeParamNames = findParamTypesInSignature(signature);
                        Field field = new Field(name, klass, desc, signature, typeParamNames);
                        if (typeParamNames.size() != 0) {
                            fields.add(field);
                        }
                    }

                    if (containerTypes.contains(refClass)) {
                        if (signature==null) {
                            refClass = classesByName.get("java/lang/Object");
                            HashSet<String> typeParams = new HashSet<String>();
                            typeParams.add("java/lang/Object");
                            Field field = new Field(name, klass, desc, signature, typeParams);
                            associationGraph.addEdge(new Association(Association.Kind.MANY),klass,refClass);
                        }
                        else {

                            for (String paramTypeName:typeParamNames) {
                                refClass = classesByName.get(paramTypeName);
                                if (refClass!=null) { // FIXME possibly a type variable
                                    associationGraph.addEdge(new Association(Association.Kind.MANY),klass,refClass);
                                }
                            }
                        }
                    }

                    else if (refClass!=null) {
                        if (typeParamNames!=null) {
                            for (String paramTypeName : typeParamNames) {
                                refClass = classesByName.get(paramTypeName);
                                if (refClass != null) {
                                    associationGraph.addEdge(new Association(isArray ? Association.Kind.MANY : Association.Kind.ONE), klass, refClass);
                                }
                            }
                        }
                        if (refClass != null) {
                            associationGraph.addEdge(new Association(isArray ? Association.Kind.MANY : Association.Kind.ONE), klass, refClass);
                        }
                    }


                }
                else {
                    assert desc.length()==1;
                }

                return null;
            }
        };
        analyse(inputFile,visitor);

        // add inherited methods
        for (Class klass : subtypeGraph.getVertices()) {
            for (Class superKlass:getSuperTypes(subtypeGraph,klass)) {
                // TODO improve precision by filtering for access modifiers
                for (Method method:superKlass.getMethods()) {
                    klass.addInheritedMethod(method);
                }
            }
        }

        // compute overrides
        // TODO optimise if slow, e.g. use cache for superTypes
        for (Method method : chaGraph.getVertices()) {
            Class defClass = method.getOwner();
            Set<Class> superTypes = getSuperTypes(subtypeGraph,defClass);
            for (Class superType:superTypes) {
                for (Method m:superType.getMethods()) {
                    if (method.getName().equals(m.getName()) && method.getDescriptor().equals(m.getDescriptor())) {
                        chaGraph.addEdge(new OverriddenBy(), m, method);

                    }
                }
            }
        }

        LOGGER.info("Analysis finished");


    }



    private static void analyse (ZipFile zip, ClassVisitor visitor) throws IOException {
        Enumeration<? extends ZipEntry> en = zip.entries();
        while (en.hasMoreElements()) {
            ZipEntry e = en.nextElement();
            String name = e.getName();
            if (name.endsWith(".class")) {
                InputStream in = zip.getInputStream(e);
                // debug("analysing " + zip.getName() + " # " + e.getName());
                analyse(in,visitor);
                in.close();
            }
        }
    }

    private static void analyse (File file, ClassVisitor visitor) throws IOException {

        String name = file.getName();
        if (file.isDirectory()) {
            for (File child:file.listFiles()) {
                analyse(child,visitor);
            }
        }

        else if (name.endsWith(".class")) {
            InputStream in = new FileInputStream(file);
            // debug("analysing " + file.getAbsolutePath());
            analyse(in,visitor);
            in.close();
        }

        else if (name.endsWith(".jar") || name.endsWith(".zip") || name.endsWith(".war") || name.endsWith(".ear")) {
            ZipFile zip = new ZipFile(file);
            analyse(zip,visitor);
        }

    }

    private static void analyse (InputStream in, ClassVisitor visitor) throws IOException {
        try {
            new ClassReader(in).accept(visitor, 0);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Error analysing: "+in.toString());
        }
    }

    private static boolean checkFlag (int flags, int opCode) {
        return (flags & opCode) == opCode;
    }

    private static final Pattern DESCR_PARAM_TYPES = Pattern.compile("(\\w|\\.|_|/|\\$)+\\;"); // removed L in pattern to match type variables

    private static Set<String> findParamTypesInSignature (String signature) {
        Set<String> matches = new HashSet<>();
        int idx = signature.indexOf("<");
        if (idx>-1) {
            signature = signature.substring(idx);
        }
        Matcher m = DESCR_PARAM_TYPES.matcher(signature);
        while (m.find()) {
            String s = m.group();
            s = s.substring(1,s.length()-1);
            matches.add(s);
        }

        return matches;
    }

    private static Set<Class> getContainerTypes(DirectedGraph<Class, SubtypeOf> subtypeGraph,Map<String,Class> classesByName) {
        String[] roots = new String[]{"java/util/Collection","java/util/Map"};
        Set<Class> containerTypes = new HashSet<>();
        for (String root:roots) {
            Class klass = classesByName.get(root);
            assert klass!=null;
            if (klass == null) {
                System.err.println(root);
                System.exit(-1);
            }
            recursivelyCollect(klass,subtypeGraph,containerTypes,true);
        }

        return containerTypes;
    }


    private static Set<Class> getSerializableTypes(DirectedGraph<Class, SubtypeOf> subtypeGraph,Map<String,Class> classesByName) {
        String root = "java/io/Serializable";
        Set<Class> serTypes = new HashSet<>();
        Class klass = classesByName.get(root);
        assert klass!=null;
        recursivelyCollect(klass,subtypeGraph,serTypes,true);
        return serTypes;
    }

    private static Set<Class> getSuperTypes(DirectedGraph<Class, SubtypeOf> subtypeGraph,Class klass) {
        Set<Class> superTypes = new HashSet<>();
        recursivelyCollect(klass, subtypeGraph, superTypes,false);
        return superTypes;
    }

    private static <V,E> void recursivelyCollect(V vertex, DirectedGraph<V, E> graph, Set<V> collector, boolean reverse) {
        if (collector.add(vertex)) {
            Collection<V> children = reverse ? graph.getPredecessors(vertex) : graph.getSuccessors(vertex);
            for (V next:children) {
                recursivelyCollect(next, graph, collector, reverse);
            }
        }
    }

    // central lookup, could create phantom classes here
    private static Class lookup(Map<String,Class> index, Set<String> namedOfClassesNotFound, String name) {
        if (name==null) return null;
        Class clazz = index.get(name);
        if (clazz==null) {
            namedOfClassesNotFound.add(name);
        }
        return clazz;
    }

    private static boolean isSupertypeOf(DirectedGraph<Class, SubtypeOf> graph, Class subType, Class superType) {
        if (subType.equals(superType)) return true;
        DijkstraShortestPath<Class,SubtypeOf> dijkstra = new DijkstraShortestPath(graph);
        List<SubtypeOf> path = dijkstra.getPath(subType,superType);
        return path != null && path.size()>0;
    }



    private Set<Composite> findJustComposites() {
        DirectedGraph<Method,MethodRelationship> invocationGraph = (DirectedGraph)new EdgePredicateFilter(e -> e instanceof Invokes).apply(chaGraph);
        DirectedGraph<Method,MethodRelationship> overridenByGraph = (DirectedGraph)new EdgePredicateFilter(e -> e instanceof OverriddenBy).apply(chaGraph);

        return associationGraph.getEdges()
                .parallelStream()
                .filter(assoc -> assoc.getKind()==Association.Kind.MANY)
                .filter(assoc -> isSupertypeOf(subtypeGraph,associationGraph.getSource(assoc),associationGraph.getDest(assoc)))
                .map(assoc -> {
                            Class container = associationGraph.getSource(assoc);
                            Class component = associationGraph.getDest(assoc);
                            Composite composite = new Composite(container,component);
                            return composite;
                        }
                )
                .collect(Collectors.toSet());

    }

    private Set<Composite> findComposites() {

        DirectedGraph<Method,MethodRelationship> invocationGraph = (DirectedGraph)new EdgePredicateFilter(e -> e instanceof Invokes).apply(chaGraph);
        DirectedGraph<Method,MethodRelationship> overridenByGraph = (DirectedGraph)new EdgePredicateFilter(e -> e instanceof OverriddenBy).apply(chaGraph);

        return associationGraph.getEdges()
                .parallelStream()
                .filter(assoc -> assoc.getKind()==Association.Kind.MANY)
                .filter(assoc -> isSupertypeOf(subtypeGraph,associationGraph.getSource(assoc),associationGraph.getDest(assoc)))
                .map(assoc -> {
                            Class container = associationGraph.getSource(assoc);
                            Class component = associationGraph.getDest(assoc);
                            Composite composite = new Composite(container,component);

                            // TODO: should we also look for inherited methods ?
                            for (Method method:container.getMethods()) {
                                for (Method called:invocationGraph.getSuccessors(method)) {
                                    if (overridenByGraph.getSuccessors(called).contains(method) && component.getAllMethods().contains(called)) {
                                        if (!method.getName().contains("<"))
                                            composite.addMethod(method);
                                    }
                                }
                            }
                            return composite;
                        }
                )
                .filter(comp -> comp.getRecursiveMethods().size()>0)
                .collect(Collectors.toSet());

    }




}
