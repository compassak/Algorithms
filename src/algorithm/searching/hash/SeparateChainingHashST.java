package algorithm.searching.hash;

/**
 * 基于拉链法实现的散列表
 */
public class SeparateChainingHashST {
    private int N;      //键值对总数
    private int M;      //散列表大小(尽量使用素数)
    private Node[] st;

    /**
     * 用于存放键值对和下一个元素
     */
    private static class Node{
        int key;        //键
        String val;        //值
        Node next;      //下一个元素

        Node(){};
        Node(int key, String val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SeparateChainingHashST(){
        this(97);
    }

    public SeparateChainingHashST(int M){
        this.M = M;
        st = new Node[M];
        for (int i = 0; i < M; i++)
            st[i] = new Node();
    }

    public int size(){
        return N;
    }

    /**
     * 散列函数
     * @param key 散列key
     * @return 散列值
     */
    private int hash(int key){
        return (Integer.hashCode(key) & 0x7fffffff) % M;
    }

    /**
     * 获取键值
     * @param key 键
     * @return 值val
     */
    public String get(int key){
        Node first = st[hash(key)];
        for (Node x = first; x != null; x = x.next){
            //存在key，返回val
            if (key == x.key)
                return x.val;
        }
        return null;
    }

    /**
     * 插入键值对
     * @param key 键
     * @param val 值
     */
    public void put(int key, String val){
        Node first = st[hash(key)];
        for (Node x = first; x != null; x = x.next){
            //存在key，替换val
            if (key == x.key) {
                x.val = val;
                return;
            }
        }
        //不存在key，插入链表头
        st[hash(key)] = new Node(key, val, first);
        N++;
    }
}
