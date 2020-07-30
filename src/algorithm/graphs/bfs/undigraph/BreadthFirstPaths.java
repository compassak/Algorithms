package algorithm.graphs.bfs.undigraph;

import algorithm.graphs.Digraph;
import algorithm.graphs.Graph;

import java.util.*;

public class BreadthFirstPaths {
    private boolean[] marked;   // 到达该顶点的最短路径是否已知
    private int[] edgeTo;       // 记录起点到各连通结点的最短路径
    private final int s;        // 起点

    public BreadthFirstPaths(Graph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g, s);
    }

    public BreadthFirstPaths(Digraph g, int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g, s);
    }

    /**
     * 广度优先搜索函数（使用队列控制搜索元素）
     * @param g 无向图
     * @param s 起点
     */
    private void bfs(Graph g, int s){
        Queue<Integer> queue = new ArrayDeque<>();
        marked [s] = true;
        queue.offer(s);
        while (!queue.isEmpty()){
            int v = queue.poll();
            for (int w : g.adj(v)){
                if (!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    /**
     * 广度优先搜索函数（使用队列控制搜索元素）
     * @param g 有向图
     * @param s 起点
     */
    private void bfs(Digraph g, int s){
        Queue<Integer> queue = new ArrayDeque<>();
        marked [s] = true;
        queue.offer(s);
        while (!queue.isEmpty()){
            int v = queue.poll();
            for (int w : g.adj(v)){
                if (!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    /**
     * 查看当前顶点 v 是否与起点连通
     * @param v 当前顶点
     * @return 连通 true，不连通
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
