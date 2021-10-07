package acar.utils;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;


public class DoopCallGraph {

    class Edge {
        public Edge(String from, String to) {
            this.from = from;
            this.to = to;
        }
        public String from;
        public String to;
    }

    public DirectedGraph<String,Edge> g;

    public DoopCallGraph() {
        HashMap<Edge,String> edges = new HashMap<Edge,String>();
        g = new DirectedSparseGraph<>();
        int edgeCounter = 0;
        String m1 = "", m2 = "";
        try (BufferedReader in = new BufferedReader(new FileReader(new File("callgraph.csv")))) {
            String line = null;
            while ((line = in.readLine()) != null) {
                line = line.trim();
                int index = line.indexOf(")>, ");
                m1 = line.substring(1, index + 1).trim();
                m2 = line.substring(index+5, line.length()-1);
                g.addEdge(new Edge(m1, m2), m1, m2);
            }
        } catch (IOException exception) {

        }


    }

    public static void main(String[] args) throws Exception {

        DoopCallGraph cg = new DoopCallGraph();

        //String start = "JEEDriver: void main(java.lang.String[])";
        assert args.length == 2;

        String start = args[0];
        String end = args[1];

        List<Edge> path = new DijkstraShortestPath<String,Edge>(cg.g).getPath(start, end);
        for(Edge i: path) {
            System.out.println(i.from +" > "+i.to);
        }


    }
}
