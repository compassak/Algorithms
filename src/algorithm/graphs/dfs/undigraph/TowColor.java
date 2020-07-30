package algorithm.graphs.dfs.undigraph;

import algorithm.graphs.Graph;

public class TowColor {
    private boolean[] marked;              // 标记已访问过的顶点
    private boolean[] color;               // 顶点颜色
    private boolean isTowColorable = true; // 当前图是否可以构成二分图

    public TowColor(Graph g){
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++)
            if (!marked[s])
                dfs(g, s);
    }

    /**
     * 深度优先搜索函数（如果当前顶点的一条边指向被一个访问过的顶点，且二者颜色一致，则无法构成二分图）
     * @param g 图
     * @param v 当前顶点
     */
    private void dfs(Graph g, int v){
        marked[v] = true;
        for (int w : g.adj(v)){
            if (!marked[w]){
                color[w] = !color[v];
                dfs(g, w);
            }else if (color[w] == color[v])
                isTowColorable = false;
        }
    }

    public boolean isTowColorable() {
        return isTowColorable;
    }
}
