package algorithm.graphs.dfs.digraph;

import algorithm.graphs.Digraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

    public class DirectedCycle {
    private boolean[] marked;   // 标记已访问过的顶点
    private int[] edgeTo;       // 记录起点到各连通顶点的路径
    private List<Integer> cycle;// 存放发现的有向环的所有顶点
    private boolean[] onStack;  // 递归调用的栈上的所有顶点

    public DirectedCycle(Digraph g){
        onStack = new boolean[g.V()];
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        for (int v = 0; v < g.V(); v++)
            if (!marked[v]) dfs(g, v);
    }

    private void dfs(Digraph g, int v){
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new ArrayList<>();
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.add(i);
                }
                cycle.add(w);
                cycle.add(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public List<Integer> cycle() {
        Collections.reverse(cycle);
        return cycle;
    }
}

