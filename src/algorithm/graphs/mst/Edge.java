package algorithm.graphs.mst;

public class Edge {
    private final int v;            // 顶点之一
    private final int w;            // 另一个顶点
    private final double weight;    // 边的权重

    public Edge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge))
            return false;
        Edge e = (Edge)obj;
        return this.v == e.v && this.weight == e.weight && this.w == e.w;
    }
}
