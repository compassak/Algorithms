package test;

import org.junit.Test;
import algorithm.searching.BinarySearch;
import algorithm.searching.hash.LinearProbingHashST;
import algorithm.searching.hash.SeparateChainingHashST;
import algorithm.searching.tree.BinarySearchTree;
import algorithm.searching.tree.RedBlackBST;
import algorithm.sorting.quick.Quick3way;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchTest {
    private int[] a = {54, 79, 10, 3, 57, 74, 51, 32, 85, 1, 16, 62, 14, 35, 87, 39, 66, 75, 45, 18, 44, 6, 60, 93, 19, 30, 48, 61, 89, 20, 17, 83, 97, 98, 26, 12, 27, 41, 25, 9, 28, 31, 63, 13, 82, 80, 86, 11, 55, 73, 29, 2, 70, 5, 40, 76, 15, 94, 58, 42, 59, 90, 72, 88, 67, 22, 65, 24, 43, 92, 46, 78, 0, 38, 4, 77, 56, 95, 49, 21, 23, 8, 81, 84, 33, 37, 50, 7, 96, 47, 68, 99, 53, 36, 34, 91, 64, 52, 71, 69};

    /**
     * 二分查找测试
     */
    @Test
    public void binarySearchTest(){
        Quick3way.sort(a);
        //测试
        long time1 = System.nanoTime();
        int res = BinarySearch.Search(a, 10);
        long time2 = System.nanoTime();
        System.out.println("binarySearch res : " + res +"\ntime : "+ (time2-time1));
    }

    /**
     * 二叉查找树测试
     */
    @Test
    public void binarySearchTreeTest(){
        //构建一个随机无序的键值对的map
        Map<Integer, String> map = new LinkedHashMap<>();
        for (int val : a) {
            map.put(val, "str" + val);
        }
        //构建树
        BinarySearchTree tree = new BinarySearchTree();
        for (int i : map.keySet()){
            tree.put(i, map.get(i));
        }
        //测试
        long time1 = System.nanoTime();
        String res=tree.get(1);
        long time2 = System.nanoTime();
        long time3 = System.nanoTime();
        tree.put(100,"str"+100);
        long time4 = System.nanoTime();
        System.out.println("binarySearch res : " + res + "\ngetTime : " + (time2-time1) + "\nputTime : " + (time4-time3));
        System.out.println("size : " + tree.size());
        System.out.println("rank : " + tree.rank(30));
        //中序遍历
        tree.inorderPrint();
    }

    @Test
    public void RedBlackTreeTest(){
        //构建一个有序的键值对的map
        Map<Integer, String> map = new HashMap<>();
        for (int val : a) {
            map.put(val, "str" + val);
        }
        //构建红黑树
        RedBlackBST tree = new RedBlackBST();
        for (int i : map.keySet()){
            tree.put(i, map.get(i));
        }
        //测试

        long time1 = System.nanoTime();
        String res = tree.get(53);
        long time2 = System.nanoTime();
        System.out.println("get : " + res + "\n"+"time : "+(time2-time1));
        System.out.println("size : " + tree.size());

        tree.printRight();
        tree.inorderPrint();
    }

    @Test
    public void separateChainingHashST(){
        //构建一个有序的键值对的map
        Map<Integer, String> map = new HashMap<>();
        for (int val : a) {
            map.put(val, "str" + val);
        }
        //构建基于拉链法的散列表
        SeparateChainingHashST table = new SeparateChainingHashST(97);
        for (int i : map.keySet()){
            table.put(i, map.get(i));
        }
        //查找元素测试
        for (int key : map.keySet()){
            System.out.println(key +" : "+ table.get(key));
        }

        long time1 = System.nanoTime();
        String res=table.get(21);
        long time2 = System.nanoTime();
        System.out.println("res : " + res + "\n" + (time2 - time1));
    }

    @Test
    public void LinearProbingHashST(){
        //构建一个有序的键值对的map
        Map<Integer, String> map = new HashMap<>();
        for (int val : a) {
            map.put(val, "str" + val);
        }
        //构建基于线性探测的散列表
        LinearProbingHashST table = new LinearProbingHashST();
        for (int i : map.keySet()){
            table.put(i, map.get(i));
        }
        //查找元素测试
        for (int key : map.keySet()){
            System.out.println(key +" : "+ table.get(key));
        }

        long time1 = System.nanoTime();
        String res=table.get(21);
        long time2 = System.nanoTime();
        System.out.println("res : " + res + "\n" + (time2 - time1) + "\nsize : " + table.size());
    }
}
