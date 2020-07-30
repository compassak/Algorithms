package algorithm.graphs.dfs.undigraph;

import algorithm.graphs.Graph;

public class CC {
    private boolean[] marked;   // 标记已访问过的顶点
    private int[] id;           // 顶点对应索引，值为连通分量的id
    private int count;          // 连通分量数

    public CC(Graph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0; s < g.V(); s++){
            if (!marked[s]){
                dfs(g, s);
                count++;
            }
        }
    }

    /**
     * 深度优先搜索函数
     * @param g 图
     * @param v 当前顶点
     */
    private void dfs(Graph g, int v){
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v))
            if (!marked[w]) dfs(g, w);
    }

    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}
