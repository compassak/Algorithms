package algorithm.graphs.dfs.undigraph;

import algorithm.graphs.Digraph;
import algorithm.graphs.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstPaths {
    private boolean[] marked;   // 标记已访问过的顶点
    private int[] edgeTo;       // 记录起点到各连通顶点的路径
    private final int s;        // 起点

    public DepthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    public DepthFirstPaths(Digraph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    /**
     * 深度优先搜索函数(无向图)
     * @param g 无向图
     * @param v 当前顶点
     */
    private void dfs(Graph g, int v){
        marked[v] = true;
        for (int w : g.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    /**
     * 深度优先搜索函数(有向图)
     * @param g 有向图
     * @param v 当前顶点
     */
    private void dfs(Digraph g, int v){
        marked[v] = true;
        for (int w : g.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    /**
     * 查看顶点 v 是否与起点联通
     * @param v 顶点
     * @return 联通 true，不连通 false
     */
    public boolean hasPathTo(int v){
        return marked[v];
    }

    /**
     * 若顶点 v 与起点联通，获取路径上的结点
     * @param v 当前顶点
     * @return 表示顶点路径的list集合
     */
    public List<Integer> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.add(x);
        path.add(s);
        Collections.reverse(path);
        return path;
    }
}
