package algorithm.sorting.quick;

public class Quick {
    public static void sort(int[] a){
        sort(a, 0, a.length - 1);
    }

    private static void sort(int[] a, int lo, int hi){
        if(hi <= lo)return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    //切分操作
    private static int partition(int[] a, int lo, int hi){
        //左右扫描指针
        int i = lo, j = hi+1;
        //切分元素
        int V = a[lo];
        while (true){
            //扫描左右元素，出现左元素比V大或右元素比V小，则交换两元素位置。
            while (a[++i] < V) if (i == hi) break;
            while (a[--j] > V) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    //交换元素位置
    private static void exch(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
