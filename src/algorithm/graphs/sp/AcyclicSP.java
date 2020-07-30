package algorithm.graphs.sp;

import algorithm.graphs.dfs.digraph.DepthFirstOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph g, int s){
        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        for (int v = 0; v < g.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        DepthFirstOrder order = new DepthFirstOrder(g);
        // 按照顶点拓扑排序遍历图
        for (int v : order.reversePost()){
            relax(g, v);
        }
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
