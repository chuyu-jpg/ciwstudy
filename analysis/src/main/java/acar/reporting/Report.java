package acar.reporting;

import acar.analysis.Analysis;
import acar.reporting.elements.*;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Report {

    public static Logger LOGGER = Logger.getLogger(Report.class);
    static final String[] excludedPackages = new String[]{"org.omg", "org.jcp", "org.w3c", "org.ietf", "org.xml", "java", "com.oracle", "com.sun", "sun", "jdk", "javax"};
    static final String[] excludedPackagesComp = new String[]{"org.omg", "org.jcp",  "org.ietf", "org.xml", "java", "com.oracle", "com.sun", "sun", "jdk", "javax"};
    static final String compositeFilename = "Composite";
    static final String simpleRecFilename = "SimpleRecursiveCall";
    static final String simpleParamTriggerFilename = "SimpleParamTrigger";
    static final String simpleThisTriggerFilename = "SimpleThisTrigger";
    static final String simpleParamFlowFilename = "SimpleParamWithFlow";
    static final String simpleThisFlowFilename = "SimpleThisWithFlow";




    static String dir;
    public static void main(String[] args) {
        BasicConfigurator.configure();

        if (args.length != 2) {
            System.err.println("Usage: acar.reporting.Report <command> <directory>");
        }
        String project;
        dir = args[1];
        switch (args[0]) {
            case "Results":
                LOGGER.info("Printing results");
                getProjects().stream().forEach(p -> {
                    Collection<Method> comp = getSimpleRecursionParam(p);
                    comp.addAll(getSimpleRecursionThis(p));
                    Collection<Method> trav = getSimpleParamFlow(p);
                    trav.addAll(getSimpleThisFlow(p));
                    Collection<Method> trig = getSimpleParamTrigger(p);
                    trig.addAll(getSimpleThisTrigger(p));
                    System.out.println(String.format("%s\t%s\t%s\t%s\t%s\t%s",
                            p,
                            getTime(p),
                            getComposites(p).size(),
                            comp.size(),
                            trav.size(),
                            trig.size()
                            ));
                        }
                );
                break;
            case "Composites":
                LOGGER.info("Printing composites");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getComposites(p).size());
                        }
                );
                break;
            case "SimpleRecursionThis":
                LOGGER.info("Printing simple recursion (this)");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getSimpleRecursionThis(p).size());
                        }
                );

                break;
            case "SimpleRecursionThisAll":
                LOGGER.info("Printing simple recursion (this) All");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getSimpleRecursionThisAll(p).size());
                        }
                );

                break;
            case "SimpleRecursionParam":
                LOGGER.info("Printing simple recursion (param)");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getSimpleRecursionParam(p).size());
                        }
                );

                break;

            case "SimpleRecursionParamAll":
                LOGGER.info("Printing simple recursion (param) All");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getSimpleRecursionParamAll(p).size());
                        }
                );

                break;

            case "SimpleThisTrigger":
                LOGGER.info("Printing simple recursion trigger (this)");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getSimpleThisTrigger(p).size());
                        }
                );

                break;
            case "SimpleParamTrigger":
                LOGGER.info("Printing simple recursion trigger (param)");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getSimpleParamTrigger(p).size());
                        }
                );
                break;
            case "Time":
                LOGGER.info("Printing times");
                getProjects().stream().forEach(p -> {
                            System.out.println(p + "\t" +
                                    getTime(p));
                        }
                );
                break;
            case "PrintMethodsProject":
                LOGGER.info("Printing param methods for project");
                project = args[2];
              //  getSimpleParamTrigger(project).stream().forEach(p -> {
                getSimpleParamTrigger(project).stream().forEach(p -> {
                            System.out.println(p.getDoopName());
                        }
                );
                break;
            case "PrintThisMethodsProject":
                LOGGER.info("Printing this methods for project");
                project = args[2];
                getSimpleThisTrigger(project).stream().forEach(p -> {
                            System.out.println(p.getOwner().getName() + "\t" + p.getDoopName());
                        }
                );
                break;
            case "PrintAllRecursion":
                LOGGER.info("Printing all recursive methods for project");
                project = args[2];
                Set<Method> methods = getSimpleRecursionThisAll(project).stream().collect(Collectors.toSet());
                methods.addAll(getSimpleRecursionParamAll(project).stream().collect(Collectors.toSet()));
                for(Method m: methods) {
                    System.out.println(m.getDoopName());
                }

                break;
            default:
                LOGGER.debug("Unknown option: " + args[0]);
        }
    }

    public static int getTime(String p) {
        String fileName = dir+"/" + p + "/log.txt";
        try {
            List<String> allLines = Files.readAllLines(Paths.get(fileName));
            return Integer.parseInt(allLines.get(1).split("\\s+")[0]);


        } catch (IOException e) {
            LOGGER.error("Error reading: " + fileName);
        }
        return -1;
    }



    public static Collection<Method> getSimpleThisFlow(String project) {
        return getData(project, simpleThisFlowFilename).stream()
                .map(SimpleFlowThis::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleFlowThis::getMethod)
                .collect(Collectors.toSet());
    }

    public static Collection<Method> getSimpleParamFlow(String project) {
        return getData(project, simpleParamFlowFilename).stream()
                .map(SimpleFlowParam::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleFlowParam::getMethod)
                .collect(Collectors.toSet());
    }


    public static Collection<Method> getSimpleThisTrigger(String project) {
        return getData(project, simpleThisTriggerFilename).stream()
                .map(SimpleThisTrigger::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getFromMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleThisTrigger::getFromMethod)
                .collect(Collectors.toSet());
    }



    public static Collection<Method> getSimpleParamTrigger(String project) {
        return getData(project, simpleParamTriggerFilename).stream()
                .map(SimpleParamTrigger::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getFromMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleParamTrigger::getFromMethod)
                .collect(Collectors.toSet());
    }


    public static Collection<Method> getSimpleRecursionThis(String project) {
        return getData(project, simpleRecFilename + "This").stream()
                .map(SimpleRecursionThis::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleRecursionThis::getMethod)
                .collect(Collectors.toSet());
    }

    public static Collection<Method> getSimpleRecursionThisAll(String project) {
        return getData(project, simpleRecFilename + "ThisAll").stream()
                .map(SimpleRecursionThisAll::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleRecursionThisAll::getMethod)
                .collect(Collectors.toSet());
    }

    public static Collection<Method> getSimpleRecursionParam(String project) {
        return getData(project, simpleRecFilename + "Parameter").stream()
                .map(SimpleRecursionParam::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleRecursionParam::getMethod)
                .collect(Collectors.toSet());
    }

    public static Collection<Method> getSimpleRecursionParamAll(String project) {
        return getData(project, simpleRecFilename + "ParameterAll").stream()
                .map(SimpleRecursionParamAll::parse)
                .filter(c -> Arrays.stream(excludedPackages)
                        .noneMatch(e -> c.getMethod().getOwner().getName().startsWith(e))
                )
                .map(SimpleRecursionParamAll::getMethod)
                .collect(Collectors.toSet());
    }

    public static List<Composite> getComposites(String project) {
        return getData(project, compositeFilename).stream()
                .map(Composite::parse)
                .filter(c -> Arrays.stream(excludedPackagesComp)
                        .noneMatch(e -> c.getContainer().getName().startsWith(e))
                )
                .collect(Collectors.toList());

    }

    public static List<String> getData(String project, String file) {
        String fileName = dir+"/" + project + "/" + file + ".csv";

        try {
            List<String> allLines = Files.readAllLines(Paths.get(fileName));
            return allLines;

        } catch (IOException e) {
            LOGGER.error("Error reading file:  " + fileName);
            e.printStackTrace();
        }
        return new ArrayList<String>();

    }

    public static List<String> getProjects() {
        File[] directories = new File(dir).listFiles(File::isDirectory);
        List<String> result = Arrays.stream(directories).map(f -> f.getName()).collect(Collectors.toList());
        Collections.sort(result);
        return result;
    }
}
