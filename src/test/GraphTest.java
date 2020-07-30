package test;

import algorithm.graphs.*;
import algorithm.graphs.bfs.undigraph.BreadthFirstPaths;
import algorithm.graphs.dfs.digraph.DepthFirstOrder;
import algorithm.graphs.dfs.digraph.DirectedCycle;
import algorithm.graphs.dfs.digraph.DirectedDFS;
import algorithm.graphs.dfs.digraph.KosarajuSCC;
import algorithm.graphs.dfs.undigraph.*;
import algorithm.graphs.mst.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class GraphTest {
    private Graph graph;
    private Digraph digraph;

    @Before
    public void init1()throws IOException {
        File file = new File("src/resources/tinyG.txt");
        InputStream in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        graph = new Graph(reader);
    }

    @Before
    public void init2()throws IOException {
        File file = new File("src/resources/tinyDG.txt");
        InputStream in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        digraph = new Digraph(reader);
    }

    /**
     * 深度优先搜索测试(无向图)
     */
    @Test
    public void DFSTest() {
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        System.out.println(dfs.count());
    }

    /**
     * 深度优先搜索寻找路径测试(无向图)
     */
    @Test
    public void DFPTest(){
        int s = 0, v = 6;
        //dfp test
        DepthFirstPaths dfp1 = new DepthFirstPaths(graph, s);
        List<Integer> path1= dfp1.pathTo(v);
        System.out.print("dfp "+s+"->"+ v + " : " + s);
        for (int w = 1; w < path1.size(); w++){
            System.out.print("-" + path1.get(w));
        }
    }

    /**
     * 广度优先搜索寻找路径测试(无向图)
     */
    @Test
    public void BFPTest(){
        int s = 0, v = 6;
        //bfp test
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph, s);
        List<Integer> path2 = bfp.pathTo(v);
        System.out.print("bfp "+s+"->"+ v + " : " + s);
        for (int w = 1; w < path2.size(); w++){
            System.out.print("-" + path2.get(w));
        }
    }

    /**
     * 连通分量测试(无向图)
     */
    @Test
    public void CCTest(){
        CC cc = new CC(graph);
        System.out.println(cc.count());
        System.out.println(cc.connected(1,2));
    }

    /**
     * 环测试(无向图)
     */
    @Test
    public void cycleTest(){
        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.HasCycle());
    }

    /**
     * 二分图测试
     */
    @Test
    public void toColorTest(){
        TowColor towColor = new TowColor(graph);
        System.out.println(towColor.isTowColorable());
    }


    /**
     * 符号图测试(无向图)
     */
    @Test
    public void symbolGraphTest() throws IOException {
        File file = new File("src/resources/route.txt");
        InputStream in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        reader.mark((int)(file.length()+1));
        SymbolGraph graph = new SymbolGraph(reader, " ");
        System.out.println(graph.index("PHX"));
        System.out.println(graph.name(6) + "\n----------------------------------------------");

        String s = "JFK", v = "LAS";

        //dfp test
        DepthFirstPaths dfp = new DepthFirstPaths(graph.getGraph(), graph.index(s));
        List<Integer> path1= dfp.pathTo(graph.index(v));
        System.out.print("dfp "+ s +"->"+ s + " : " + s);
        for (int w = 1; w < path1.size(); w++){
            System.out.print("-" + graph.name(path1.get(w)));
        }

        //bfp test
        BreadthFirstPaths bfp = new BreadthFirstPaths(graph.getGraph(),  graph.index(s));
        List<Integer> path2 = bfp.pathTo(graph.index(v));
        System.out.print("\nbfp "+ s +"->"+ s + " : " + s);
        for (int w = 1; w < path2.size(); w++){
            System.out.print("-" + graph.name(path2.get(w)));
        }
    }


    /**
     * 深度优先搜索测试（有向图）
     */
    @Test
    public void DirectedDFSTest(){
        DirectedDFS directedDFS = new DirectedDFS(digraph,12);
        System.out.println(directedDFS.count());
    }

    /**
     * 深度优先搜索寻找路径测试(有向图)
     */
    @Test
    public void DirectedDFPTest(){
        int s = 12, v = 5;
        //dfp test
        DepthFirstPaths dfp1 = new DepthFirstPaths(digraph, s);
        List<Integer> path1= dfp1.pathTo(v);
        System.out.print("dfp "+s+"->"+ v + " : " + s);
        for (int w = 1; w < path1.size(); w++){
            System.out.print("->" + path1.get(w));
        }
    }

    /**
     * 广度优先搜索寻找路径测试(有向图)
     */
    @Test
    public void DirectedBFPTest(){
        int s = 12, v = 5;
        //bfp test
        BreadthFirstPaths bfp = new BreadthFirstPaths(digraph, s);
        List<Integer> path2 = bfp.pathTo(v);
        System.out.print("bfp "+s+"->"+ v + " : " + s);
        for (int w = 1; w < path2.size(); w++){
            System.out.print("->" + path2.get(w));
        }
    }

    /**
     * 深度优先搜索，寻找有向环
     */
    @Test
    public void  DirectedCycle(){
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        List<Integer> cycle= directedCycle.cycle();
        System.out.println("hasCycle : "+directedCycle.hasCycle());
        System.out.print("dfp cycle : " + cycle.get(0));
        for (int w = 1; w < cycle.size(); w++){
            System.out.print("->" + cycle.get(w));
        }
    }

    /**
     * 拓扑排序测试
     */
    @Test
    public void TopologicalTest() throws IOException {
        File file = new File("src/resources/course.txt");
        InputStream in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        reader.mark((int)(file.length()+1));
        SymbolDigraph digraph = new SymbolDigraph(reader,"/");

        DirectedCycle cycleFinder = new DirectedCycle(digraph.getGraph());
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(digraph.getGraph());
            List<Integer> topological = dfs.reversePost();
            for (int v : topological){
                System.out.println(v + "--" + digraph.name(v));
            }
        }
    }

    /**
     * Kosaraju 算法寻找强连通分量测试
     */
    @Test
    public void kosarajuTest(){
        KosarajuSCC scc = new KosarajuSCC(digraph);
        System.out.println(scc.count());
    }

    @Test
    public void PrimTest() throws IOException {
        File file = new File("src/resources/tinyEWG.txt");
        InputStream in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        EdgeWeightedGraph graph = new EdgeWeightedGraph(reader);

        PrimMST pmst = new PrimMST(graph);
        LazyPrimMST lpmst = new LazyPrimMST(graph);
        KruskalMST kmst = new KruskalMST(graph);
        System.out.println(kmst.weight() +" == "+ pmst.weight() +" == "+ lpmst.weight());

        for (Edge e : pmst.edges()){
            System.out.println(e);
        }
        System.out.println("====================");
        for (Edge e : lpmst.edges()){
            System.out.println(e);
        }
        System.out.println("====================");
        for (Edge e : kmst.edges()){
            System.out.println(e);
        }
    }

    @Test
    public void test(){

    }
}