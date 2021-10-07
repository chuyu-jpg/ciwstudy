package acar.analysis.minicfg;

import java.util.Objects;

public class Edge {
    public Node start;
    public Node next;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge1 = (Edge) o;
        return Objects.equals(start, edge1.start) &&
                Objects.equals(next, edge1.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, next);
    }

    public Edge(Node f, Node n) {
        this.start = f;
        this.next = n;
    }
}
