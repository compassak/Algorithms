package algorithm.graphs.sp;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BellmanFordSP {
    private double[] distTo;                // 从起点到某个顶点的路径长度
    private DirectedEdge[] edgeTo;          // 从起点到某个顶点的最后一条边
    private boolean[] onQ;                  // 该顶点是否存在于队列中
    private Queue<Integer> queue;           // 正在被放松的顶点
    private int cost;                       // relax()的调用次数
    private List<DirectedEdge> cycle;       // edgeTo[]中的是否有负权重环

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new ArrayDeque<>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        queue.add(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle())
        {
            int v = queue.poll();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w])
                {
                    queue.add(w);
                    onQ[w] = true;
                }
            }
            if (cost++ % G.V() == 0)
                findNegativeCycle();
        }
    }

    private void findNegativeCycle(){
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(edgeTo.length);
        for (DirectedEdge e : edgeTo)
            if (e != null)
                spt.addEdge(e);
        EdgeWeightedCycleFinder cf = new EdgeWeightedCycleFinder(spt);
        cycle = cf.cycle();
    }

    public boolean hasNegativeCycle(){
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }
}
