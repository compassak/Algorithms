package algorithm.graphs.dfs.undigraph;

import algorithm.graphs.Graph;

public class DepthFirstSearch {
    private boolean[] marked;   // 标记已访问过的顶点
    private int count;          // 与起点连通的结点数

    public DepthFirstSearch(Graph g, int s){
        marked = new boolean[g.V()];
        dfs(g, s);
    }

    /**
     * 深度优先搜索函数
     * @param g 图
     * @param v 当前顶点
     */
    private void dfs(Graph g, int v){
        marked[v] = true;
        count++;
        for (int w : g.adj(v))
            if (!marked[w]) dfs(g, w);
    }

    /**
     * 查看顶点 w 是否与起点联通
     * @param w 顶点
     * @return 联通 true，不连通 false
     */
    public boolean marked(int w){
        return marked[w];
    }

    /**
     * 获取与起点联通的结点数
     * @return 结点数
     */
    public int count(){
        return count;
    }
}
