package algorithm.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class SymbolGraph {
    private HashMap<String, Integer> map;   // 符号名 -> 索引
    private String[] keys;                  // 索引 -> 符号名
    private Graph graph;                    // 图

    public SymbolGraph(BufferedReader reader, String sp) throws IOException {
        map = new HashMap<>();
        String temp;
        // 初始化符号表，生成正向索引
        while ((temp = reader.readLine()) != null) {
            String[] vertex = temp.split(sp);
            for (String s : vertex) {
                if (!map.containsKey(s))
                    map.put(s, map.size());
            }
        }

        // 初始化反向索引
        keys = new String[map.size()];
        for (String name : map.keySet()) {
            keys[map.get(name)] = name;
        }

        graph = new Graph(map.size());
        reader.reset();
        // 添加边
        while ((temp = reader.readLine()) != null) {
            String[] vertex = temp.split(sp);
            int v = map.get(vertex[0]);
            for (int i = 1; i < vertex.length; i++)
                graph.addEdge(v, map.get(vertex[i]));
        }
    }

    public boolean contains(String s){
        return map.containsKey(s);
    }

    public int index(String s){
        return map.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph getGraph(){
        return graph;
    }
}
