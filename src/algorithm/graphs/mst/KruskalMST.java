package algorithm.graphs.mst;

import algorithm.graphs.UF;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph g){
        mst = new ArrayDeque<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            if ((o1.weight() - o2.weight()) > 0)
                return 1;
            else if ((o1.weight() - o2.weight()) == 0)
                return 0;
            else
                return -1;
        });
        pq.addAll(g.edges());
        UF uf = new UF(g.V());

        while (!pq.isEmpty() && mst.size() < g.V()-1){
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)){ continue;}
            uf.union(v, w);
            mst.add(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double sum = 0;
        for (Edge e : mst){
            sum += e.weight();
        }
        return sum;
    }
}
