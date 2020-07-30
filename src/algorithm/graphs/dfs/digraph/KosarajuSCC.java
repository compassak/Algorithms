package algorithm.graphs.dfs.digraph;

import algorithm.graphs.Digraph;

public class KosarajuSCC {
    private boolean[] marked;   // 标记已访问过的顶点
    private int[] id;           // 顶点对应索引，值为连通分量的id
    private int count;          // 连通分量数

    public KosarajuSCC(Digraph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        // 使用图的后逆序遍历顶点，以顶点作为起点搜索图
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        for (int s : order.reversePost()) {
            System.out.println(s);
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(Digraph g, int v){
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v))
            if (!marked[w]) dfs(g, w);
    }

    public boolean stronglyConnected(int v, int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}
