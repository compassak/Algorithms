package algorithm.graphs.mst;

import java.util.*;

public class PrimMST {
    private Edge[] edgeTo;           // 距离树最近的边
    private double[] distTo;         // distTo[w]=edgeTo[w].weight()
    private boolean[] marked;        // 如果v在树中则为true
    private PriorityQueue<Node> pq;  // 有效的横切边

    private static class Node{
        int v;          // 顶点
        Double weight;  // 连接顶点 v 和树的最佳边的权重
        Node(int v, Double weight){
            this.v = v; this.weight = weight;
        }

        @Override
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (!(obj instanceof Node))
                return false;
            Node node = (Node)obj;
            return this.v == node.v && this.weight.equals(node.weight);
        }
    }

    public PrimMST(EdgeWeightedGraph g){
        edgeTo = new Edge[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new PriorityQueue<>(Comparator.comparing(o -> o.weight));

        distTo[0] = 0.0;
        // 用顶点0和权重0初始化pq
        pq.add(new Node(0, 0.0));
        while (!pq.isEmpty())
            // 将最近的顶点添加到树中
            visit(g, pq.poll().v);
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // 将顶点v添加到树中，更新数据
        marked[v] = true;
        for (Edge e : G.adj(v))
        {
            int w = e.other(v);
            // v-w失效
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                // 连接w和树的最佳边Edge变为e
                edgeTo[w] = e;
                distTo[w] = e.weight();
                Node node =  new Node(w, distTo[w]);
                for (Object obj : pq.toArray()) {
                    if (((Node) obj).v == node.v) {
                        pq.remove(obj);
                        break;
                    }
                }
                pq.add(node);
            }
        }
    }

    public List<Edge> edges() {
        return new ArrayList<>(Arrays.asList(edgeTo).subList(1, edgeTo.length));
    }

    public double weight(){
        List<Edge> mst = edges();
        double sum = 1;
        for (Edge e : mst){
            sum += e.weight();
        }
        return sum-1;
    }
}
