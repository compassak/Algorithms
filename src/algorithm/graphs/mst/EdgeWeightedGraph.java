package algorithm.graphs.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
    private final int V;                 // 顶点总数
    private int E;                       // 边的总数
    private List<ArrayList<Edge>> adj;   // 邻接表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList<>();
        for (int v = 0; v < V; v++)
            adj.add(new ArrayList<>());
    }

    public EdgeWeightedGraph(BufferedReader reader) throws IOException {
        this(Integer.parseInt(reader.readLine()));     // 读取 V
        int E = Integer.parseInt(reader.readLine());   // 读取 E
        for (int i = 0; i < E; i ++){
            String str = reader.readLine();
            String[] edge= str.split(" ");
            int v = Integer.parseInt(edge[0]);
            int w = Integer.parseInt(edge[1]);
            double weight = Double.parseDouble(edge[2]);
            addEdge(new Edge(v, w, weight));          // 添加边
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj.get(v).add(e);
        adj.get(w).add(e);
        E++;
    }

    public List<Edge> adj(int v) {
        return adj.get(v);
    }

    public List<Edge> edges() {
        List<Edge> es = new ArrayList<>();
        for (int v = 0; v < V; v++)
            for (Edge e : adj.get(v))
                if (e.other(v) > v)
                    es.add(e);
        return es;
    }
}