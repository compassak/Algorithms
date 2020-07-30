package algorithm.graphs.sp;

public class DirectedEdge {
    private final int v;            // 边起点
    private final int w;            // 边终点
    private final double weight;    // 边权重

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
