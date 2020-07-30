package algorithm.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Digraph{
    private final int V;    // 顶点数
    private int E;          // 边数
    private Node[] adj;     // 邻接表

    /**
     * 邻接表结点静态内部类
     */
    private static class Node{
        int vertex;         // 顶点
        Node next;          // 下一个元素的引用

        Node(){}
        Node(int vertex, Node next){
            this.vertex = vertex;
            this.next = next;
        }
    }

    public Digraph(int V){
        this.V = V;
        this.E = 0;
        this.adj = new Node[V];     // 创建邻接表
    }

    public Digraph(BufferedReader reader) throws IOException {
        this(Integer.parseInt(reader.readLine()));     // 读取 V
        int E = Integer.parseInt(reader.readLine());   // 读取 E
        for (int i = 0; i < E; i ++){
            String str = reader.readLine();
            String[] edge= str.split(" ");
            int v = Integer.parseInt(edge[0]);
            int w = Integer.parseInt(edge[1]);
            addEdge(v, w);          // 添加边
        }
    }

    /**
     * 添加一条边 v -> w
     * @param v 顶点1
     * @param w 顶点2
     */
    public void addEdge(int v, int w){
        Node first = adj[v];
        adj[v] = new Node(w, first);    // 将顶点w添加到顶点v的链表中
        E++;
    }

    /**
     * 获取与顶点 v 邻接的所有顶点
     * @param v 顶点
     * @return 邻接顶点set集合
     */
    public Set<Integer> adj(int v){
        Node node = adj[v];
        Set<Integer> set = new HashSet<>();
        while (node != null){
            set.add(node.vertex);
            node = node.next;
        }
        return set;
    }

    /**
     * 获取当前图的反向图
     * @return 反向图
     */
    public Digraph reverse(){
        Digraph dr = new Digraph(V);
        for (int v = 0; v < V; v++){
            for (int w : adj(v))
                dr.addEdge(w, v);
        }
        return dr;
    }

    public int V(){ return V;}
    public int E(){ return E;}
}
