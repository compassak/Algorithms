package algorithm.sorting.heap;

public class HeapSort {

    public static void sort(int[] a){
        int N = a.length;
        //堆有序化
        for (int k = N/2; k >= 1; k--){
            sink(a, k, N);
        }
        //下沉排序排序，销毁堆
        while (N > 1){
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    //元素下沉
    private static void sink(int[] a, int k, int N){
        while (2*k <= N){
            int j = 2*k;
            //比较时索引减一，将索引位置由1 ~ n 映射到 0 ~ n-1
            if (j < N && a[j-1] < a[j]) j++;
            //此时 j 为位置为 k 节点的子节点中值较大节点的索引
            if (a[k-1] > a[j-1]) break;
            exch(a ,k, j);
            k = j;
        }
    }

    //交换元素位置
    private static void exch(int[] a, int i, int j){
        //换位置索引减一，将索引位置由1 ~ n 映射到 0 ~ n-1
        i -= 1; j -= 1;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
