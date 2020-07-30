package algorithm.searching.tree;

public class BinarySearchTree {
    private Node root;      //根节点

    /**
     * 结点静态内部类
     */
    private static class Node {
        private int key;         //节点键
        private String value;    //节点值
        private Node left, right; //指向左右子树的连接
        private int N;           //以该节点为根的子树的节点数

        Node(int key, String value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    //根据键值获取节点的值，节点不存在返回NUll
    public String get(int key) {
        Node node = root;
        while (node != null){
            if      (key > node.key) node = node.right;
            else if (key < node.key) node = node.left;
            else return node.value;
        }
        return null;
    }

    //向二叉查找树插入一个节点，若节点存在则更新value
    public void put(int key, String value) {
        root = put(root, key, value);
    }
    private Node put(Node node, int key, String value) {
        if (node == null) return new Node(key, value, 1);
        if      (key > node.key) node.right = put(node.right, key, value);
        else if (key < node.key) node.left = put(node.left, key, value);
        else node.value = value;
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * rank 从1开始排序, 查找元素的排名
     */
    public int rank(int key){
        return rank(root, key);
    }
    private int rank(Node node, int key){
        if (node == null) return 0;
        if      (key > node.key) return 1 + size(node.left) + rank(node.right, key);
        else if (key < node.key) return rank(node.left, key);
        else return size(node.left) + 1;
    }

    /**
     * 中序遍历打印所有节点
     */
    public void inorderPrint(){
        inorderPrint(root);
    }
    private void inorderPrint(Node node){
        if (node == null) return;
        inorderPrint(node.left);
        System.out.println(node.key);
        inorderPrint(node.right);
    }

    //查看最右侧路径上的节点值
    public void printRight(){
        Node node = root;
        while (node != null){
            System.out.println(node.value);
            node = node.right;
        }
    }

    // 获取当前树的节点数
    public int size() {
       return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        else return node.N;
    }

}
