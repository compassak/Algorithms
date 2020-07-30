package algorithm.graphs.sp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {
    private final int V;                         // 顶点总数
    private int E;                               // 边的总数
    private List<ArrayList<DirectedEdge>> adj;   // 邻接表

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj =  new ArrayList<>();
        for (int v = 0; v < V; v++)
            adj.add(new ArrayList<>());
    }

    public EdgeWeightedDigraph(BufferedReader reader) throws IOException {
        this(Integer.parseInt(reader.readLine()));     // 读取 V
        int E = Integer.parseInt(reader.readLine());   // 读取 E
        for (int i = 0; i < E; i ++){
            String str = reader.readLine();
            String[] edge= str.split(" ");
            int v = Integer.parseInt(edge[0]);
            int w = Integer.parseInt(edge[1]);
            double weight = Double.parseDouble(edge[2]);
            addEdge(new DirectedEdge(v, w, weight));          // 添加边
        }
    }

    /**
     * 在有向边起点对应的邻接表添加一条边
     * @param e 有向边
     */
    public void addEdge(DirectedEdge e)
    {
        adj.get(e.from()).add(e);
        E++;
    }

    /**
     * 查询所有从顶点出的边。
     * @param v 顶点
     * @return 所有从顶点出的边
     */
    public List<DirectedEdge> adj(int v){
        return adj.get(v);
    }

    public List<DirectedEdge> edges(){
        List<DirectedEdge> edges = new ArrayList<>();
        for (List<DirectedEdge> es : adj){
            edges.addAll(es);
        }
        return edges;
    }

    public int V() { return V; }
    public int E() { return E; }
}
