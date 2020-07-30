package test;

import org.junit.Test;
import algorithm.sorting.heap.HeapSort;
import algorithm.sorting.merge.Merge;
import algorithm.sorting.quick.Quick;
import algorithm.sorting.quick.Quick3way;

import java.util.Arrays;

public class SortTest {

    private int[] a = {54, 79, 10, 3, 57, 74, 51, 32, 85, 1, 16, 62, 14, 35, 87, 39, 66, 75, 45, 18, 44, 6, 60, 93, 19, 30, 48, 61, 89, 20, 17, 83, 97, 98, 26, 12, 27, 41, 25, 9, 28, 31, 63, 13, 82, 80, 86, 11, 55, 73, 29, 2, 70, 5, 40, 76, 15, 94, 58, 42, 59, 90, 72, 88, 67, 22, 65, 24, 43, 92, 46, 78, 0, 38, 4, 77, 56, 95, 49, 21, 23, 8, 81, 84, 33, 37, 50, 7, 96, 47, 68, 99, 53, 36, 34, 91, 64, 52, 71, 69};

    /**
     * 并归排序测试1
     */
    @Test
    public void mergeSort1Test(){
        //测试
        long time1 = System.nanoTime();
        Merge.sort1(a);
        long time2 = System.nanoTime();
        System.out.println(Arrays.toString(a));
        System.out.println("sort1:"+(time2-time1));
    }

    /**
     * 并归排序测试2
     */
    @Test
    public void mergeSort2Test(){
        //测试
        long time1 = System.nanoTime();
        Merge.sort2(a);
        long time2 = System.nanoTime();
        System.out.println(Arrays.toString(a));
        System.out.println("sort2:"+(time2-time1));
    }

    /**
     * 经典快排
     */
   @Test
    public void quickSortTest() {
       //测试
        long time1 = System.nanoTime();
        Quick.sort(a);
        long time2 = System.nanoTime();
        System.out.println(Arrays.toString(a));
        System.out.println("qSort2:"+(time2-time1));
    }

    /**
     * 3向切分快排
     */
    @Test
    public void quick3waySortTest() {
        //测试
        long time1 = System.nanoTime();
        Quick3way.sort(a);
        long time2 = System.nanoTime();
        System.out.println(Arrays.toString(a));
        System.out.println("q3Sort2:"+(time2-time1));
    }

    /**
     * 计算 0 - n 的素数个数
     */
    @Test
    public void countPrimes(){
        int n = 1000;
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]){
                //System.out.println(i);
                count++;
            }
        System.out.println(count);
    }

    /**
     * 堆排序
     */
    @Test
    public void HeapSortTest(){
        //测试
        long time1 = System.nanoTime();
        HeapSort.sort(a);
        long time2 = System.nanoTime();
        System.out.println(Arrays.toString(a));
        System.out.println("hSort:"+(time2-time1));
    }


}
