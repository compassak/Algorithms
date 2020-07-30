package algorithm.searching;

public class BinarySearch {
    //二分法查找元素的索引
    public static int Search(int[] a, int key){
        int lo=0, hi = a.length-1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            if      (key > a[mid]) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            //found
            else return mid;
        }
        //not found
        return -1;
    }
}
