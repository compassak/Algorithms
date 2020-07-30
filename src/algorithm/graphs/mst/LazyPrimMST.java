package algorithm.graphs.mst;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrimMST
{
    private boolean[] marked;       // 最小生成树的顶点
    private Queue<Edge> mst;        // 最小生成树的边
    private PriorityQueue<Edge> pq; // 横切边（包括失效的边）
    public LazyPrimMST(EdgeWeightedGraph G)
    {
        pq = new PriorityQueue<>((o1, o2) -> {
            if ((o1.weight() - o2.weight()) > 0)
                return 1;
            else if ((o1.weight() - o2.weight()) == 0)
                return 0;
            else
                return -1;
        });
        marked = new boolean[G.V()];
        mst = new ArrayDeque<>();
        // 假设G是连通的
        visit(G, 0);
        while (!pq.isEmpty()) {
            // 从pq中得到权重最小的边
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            // 跳过失效的边
            if (marked[v] && marked[w]) continue;
            // 将边添加到树中
            mst.add(e);
            // 将顶点（v或w）添加到树中
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // 标记顶点v并将所有连接v和未被标记顶点的边加入pq
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.add(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight(){
        double sum = 0;
        for (Edge e : mst){
            sum += e.weight();
        }
        return sum;
    }
}
