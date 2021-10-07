package acar.analysis.minicfg;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import org.objectweb.asm.Opcodes;

import java.util.*;

public class CFGraph {

    DirectedGraph<Node, Edge> graph;

    public CFGraph(List<Node> instructions) {
        graph = new DirectedSparseGraph();
        Node prev = null;
        Map<String, Node> labels = new HashMap<>();
        for(Node i: instructions) {
            if (i instanceof LabelNode) {
                labels.put(((LabelNode)i).label, i);
            }
            graph.addVertex(i);
            if (prev != null) {
                if (prev instanceof JumpNode && ((JumpNode) prev).opcode == Opcodes.GOTO) {
                  ;
                } else 
                graph.addEdge(new Edge(prev, i), prev, i);

            }
            prev = i;
        }
        for(Node i: instructions) {
            if (i instanceof JumpNode) {
                graph.addEdge(new Edge(i, labels.get(((JumpNode)i).location)), i, labels.get(((JumpNode)i).location));

            }
        }
    }
    public boolean hasPath(Node s, Node t) {
        CallNode cn = (CallNode) s;
        List<Node> visited = new ArrayList<>();
        List<Node> e = new ArrayList<>();
        for(Node v: graph.getSuccessors(s)) {
            e.add(v);
        }

        while(e.size() != 0) {

            Node n = e.remove(0);
            visited.add(n);
            for(Node v: graph.getSuccessors(n)) {
                if (v.equals(t))
                    return true;
                if (!visited.contains(v) && !e.contains(v))
                 e.add(v);

            }
        }
        return false;
    }

    public String toGraphViz() {
        String result = " digraph G {";
        Collection<Edge> edges = graph.getEdges();
        for(Edge e: edges) {
            result = result + "\"" + e.start + "\" -> \"" + e.next + "\";\n";
        }
        return result + "}";
    }
}
