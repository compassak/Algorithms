package algorithm.graphs.dfs.undigraph;

import algorithm.graphs.Graph;

/**
 * 判断图中是否有环，假设不存在自环，平行环
 */
public class Cycle {
    private boolean[] marked;   // 标记已访问过的顶点
    private boolean hasCycle;   // 是否含有环

    public Cycle(Graph g){
        marked = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++)
            if (!marked[s])
                dfs(g, s, s);
    }

    /**
     * 深度优先搜索函数（在有顶点的边指向已标记的顶点，且已标记顶点不是当前顶点的父顶点时，判定图出现环）
     * @param g 图
     * @param v 当前顶点
     * @param u 上一个访问的顶点（v的父顶点）
     */
    private void dfs(Graph g, int v, int u){
        marked[v] = true;
        for (int w : g.adj(v))
            if (!marked[w])
                dfs(g, w, v);
            else if (w != u)
                hasCycle = true;
    }

    public boolean HasCycle(){
        return hasCycle;
    }
}
