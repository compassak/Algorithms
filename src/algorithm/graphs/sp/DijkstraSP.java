package algorithm.graphs.sp;

import java.util.*;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PriorityQueue<Node> pq;

    private static class Node{
        int v;          // 顶点
        Double weight;  // 连接顶点 v 和起点的最佳边的权重
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

    public DijkstraSP(EdgeWeightedDigraph g, int s){
        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        pq = new PriorityQueue<>(Comparator.comparing(o -> o.weight));

        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0.0;
        pq.add(new Node(s, 0.0));
        while (!pq.isEmpty())
            relax(g, pq.poll().v);
    }

    /**
     * 顶点放松函数，寻找顶点到起点的最短（权重）路径
     * @param g 加权有向图
     * @param v 当前顶点
     */
    private void relax(EdgeWeightedDigraph g, int v){
        for (DirectedEdge e : g.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
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

    /**
     * 起点到 v 最小权重路径的权重
     * @param v 当前顶点
     * @return 权重
     */
    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * 获取起点到 v 最小权重路径边的集合
     * @param v 当前顶点
     * @return 最小权重路径边的集合
     */
    public List<DirectedEdge> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        List<DirectedEdge> path = new ArrayList<>();
        for (DirectedEdge x = edgeTo[v]; x != null ; x = edgeTo[x.from()]) {
            path.add(x);
        }
        Collections.reverse(path);
        return path;
    }
}
