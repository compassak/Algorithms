package algorithm.graphs.sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EdgeWeightedCycleFinder {

    private boolean[] marked;           // 标记已访问过的顶点
    private DirectedEdge[] edgeTo;               // 记录起点到各连通顶点的路径
    private List<DirectedEdge> cycle;   // 存放发现的有向环的所有边
    private boolean[] onStack;          // 递归调用的栈上的所有顶点

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph g){
        onStack = new boolean[g.V()];
        marked = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        for (int v = 0; v < g.V(); v++)
            if (!marked[v]) dfs(g, v);
    }

    private void dfs(EdgeWeightedDigraph g, int v){
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new ArrayList<>();
                DirectedEdge ce;
                for (ce = e; ce.to() != w; ce = edgeTo[ce.from()]) {
                    cycle.add(ce);
                }
                cycle.add(ce);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public List<DirectedEdge> cycle() {
        Collections.reverse(cycle);
        return cycle;
    }
}
