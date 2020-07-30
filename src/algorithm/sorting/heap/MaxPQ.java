package algorithm.sorting.heap;

/**
 * 基于堆实现的优先队列
 */
public class MaxPQ {
    private int[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq = new int[maxN+1];
    }

    //返回优先队列长度
    public int size(){
        return N;
    }

    //交换元素位置
    private  void exch(int i, int j){
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    //元素上浮
    private void swim(int k){
        while (k>1 && (pq[k/2] < pq[k])){
            exch(k/2, k);
            k = k/2;
        }
    }

    //元素下沉
    private void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            if (j < N && pq[j]<pq[j+1]) j++;
            //此时 j 为位置为 k 节点的子节点中值较大节点的索引
            if (pq[k] > pq[j]) break;
            exch(k, j);
            k = j;
        }
    }

    //插入元素
    public void insert(int v){
        pq[++N] = v;
        swim(N);
    }

    //删除最大元素
    public int delMax(){
        int max = pq[1];
        //有序化堆
        exch(1, N--);
        pq[N+1] = 0;
        sink(1);
        return max;
    }
}
