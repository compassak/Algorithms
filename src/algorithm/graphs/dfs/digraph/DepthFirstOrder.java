package algorithm.graphs.dfs.digraph;

import algorithm.graphs.Digraph;
import algorithm.graphs.sp.DirectedEdge;
import algorithm.graphs.sp.EdgeWeightedDigraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstOrder {
    private boolean[] marked;       // 标记已访问过的顶点
    private ArrayList<Integer> pre; // 前序遍历顶点排列
    private ArrayList<Integer> post;// 后序遍历顶点排列

    /**
     * @param g 有向图
     */
    public DepthFirstOrder(Digraph g){
        pre = new ArrayList<>();
        post = new ArrayList<>();
        marked = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++)
            if (!marked[s]) dfs(g, s);
    }

    /**
     * @param g 加权有向图
     */
    public DepthFirstOrder(EdgeWeightedDigraph g){
        pre = new ArrayList<>();
        post = new ArrayList<>();
        marked = new boolean[g.V()];
        for (int s = 0; s < g.V(); s++)
            if (!marked[s]) dfs(g, s);
    }

    /**
     * 深度优先搜索函数(有向图)
     * @param g 图
     * @param v 当前顶点
     */
    private void dfs(Digraph g, int v){
        pre.add(v);
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) dfs(g, w);
        }
        post.add(v);
    }

    /**
     * 深度优先搜索函数(有向图)
     * @param g 图
     * @param v 当前顶点
     */
    private void dfs(EdgeWeightedDigraph g, int v){
        pre.add(v);
        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            if (!marked[e.to()]) dfs(g, e.to());
        }
        post.add(v);
    }

    public List<Integer> pre(){
        return pre;
    }

    public List<Integer> post() {
        return post;
    }

    public List<Integer> reversePost(){
        Collections.reverse(post);
        return post;
    }
}
