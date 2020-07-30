package algorithm.searching.hash;

/**
 * 基于线性探测法实现的散列表
 */
public class LinearProbingHashST {
    private int N;          // 符号表中键值对总数
    private int M = 16;     // 线性探测表的大小
    private int[] keys;     // 键
    private String[] vals;  // 值

    public LinearProbingHashST(){
        keys = new int[M];
        vals = new String[M];
    }

    private LinearProbingHashST(int M){
        this.M = M;
        keys = new int[M];
        vals = new String[M];
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
     * 调整链表长度
     * @param cap 新的长度
     */
    private void resize(int cap){
        LinearProbingHashST lst = new LinearProbingHashST(cap);
        for (int i = 0; i < M; i++){
            if (keys[i] != 0)
                lst.put(keys[i], vals[i]);
        }
        this.keys = lst.keys;
        this.vals = lst.vals;
        this.M = lst.M;
    }

    /**
     * 存入键值对
     * @param key 键
     * @param val 值
     */
    public void put(int key, String val){
        if (N >= M/2) resize(2*M);
        int i;
        for (i = hash(key); keys[i] != 0; i = (i + 1) % M){
            if (keys[i] == key){
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    /**
     * 获取键对应的值
     * @param key 键
     * @return 值 val
     */
    public String get(int key){
        for (int i = hash(key); keys[i] != 0; i = (i + 1) % M){
            if (keys[i] == key){
                return vals[i];
            }
        }
        return null;
    }
}
