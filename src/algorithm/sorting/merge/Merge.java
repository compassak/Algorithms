package algorithm.sorting.merge;

public class Merge {
    //并归所需的辅助数组
    private static int[] aux;

    /**
     * sort1 自顶向下的并归排序
     * @param a 待排序数组
     */
    public static void sort1(int[] a) {
        //分配空间
        aux = new int[a.length];
        sort1(a, 0, a.length-1);
    }

    private static void sort1(int[] a, int lo, int hi) {
        //数组长度为1
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //排序左半边
        sort1(a, lo, mid);
        //排序右半边
        sort1(a, mid + 1, hi);
        //并归结果
        merge(a, lo, mid, hi);
    }

    /**
     * sort2 自底向上的并归排序
     * @param a 待排序数组
     */
    public static void sort2(int[] a){
        int n = a.length;
        aux = new int[n];
        for (int sz = 1; sz < n; sz = sz+sz){
            for (int lo = 0; lo < n-sz; lo += sz+sz ){
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, n-1));
            }
        }
    }

    //并归操作
    private static void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        //复制数组(a[lo...hi] -> aux[lo...hi])
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        //并归元素到 a[lo...hi] 中
        for (int k = lo; k <= hi; k++) {
            //左半边元素用尽
            if (i > mid)
                a[k] = aux[j++];
            //右半边元素用尽
            else if (j > hi)
                a[k] = aux[i++];
            //右半边当前元素大于左半边当前元素
            else if (aux[j] > aux[i])
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
    }
}
