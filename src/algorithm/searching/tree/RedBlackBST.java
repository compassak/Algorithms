package algorithm.searching.tree;

public class RedBlackBST {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root; //根结点

    /**
     * 结点静态内部类
     */
    private static class Node {
        private int key;          //结点键
        private String value;     //结点值
        private Node left, right; //指向左右子树的连接
        private int N;            //以该结点为根的子树的结点数
        private boolean color;    //由其父结点指向它的链接的颜色

        Node(int key, String value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    /**
     * 判断一个结点的颜色是否为红色
     * @param node 结点
     */
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    /**
     * 获取当前树的结点数
     */
    public int size() {
        return size(root);
    }
    private int size(Node node){
        if (node == null) return 0;
        else return node.N;
    }

    /**
     * 红色右链接旋转为红色左链接
     * @param oldR 旋转前子树的根节点
     * @return 新子树的根节点
     */
    private Node rotateLeft(Node oldR){
        Node newR = oldR.right;
        oldR.right = newR.left;
        newR.left = oldR;
        newR.color = oldR.color;
        newR.N = oldR.N;
        oldR.color = RED;
        oldR.N = 1 + size(oldR.left) + size(oldR.right);
        return newR;
    }

    /**
     * 红色左链接旋转为红色右链接
     * @param oldR 旋转前子数的根节点
     * @return 新子树的根节点
     */
    private Node rotateRight(Node oldR){
        Node newR = oldR.left;
        oldR.left = newR.right;
        newR.right = oldR;
        newR.color = oldR.color;
        newR.N = oldR.N;
        oldR.color = RED;
        oldR.N = 1 + size(oldR.left) + size(oldR.right);
        return newR;
    }

    /**
     * 颜色转换，子结点变黑，父节点变红
     * @param node 当前结点
     */
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


    /**
     * 根据键值获取节点的值，节点不存在返回NUll
     */
    public String get(int key) {
        Node node = root;
        while (node != null){
            if      (key > node.key) node = node.right;
            else if (key < node.key) node = node.left;
            else return node.value;
        }
        return null;
    }

    /**
     * 查找key，命中更新value，否则新建一个结点
     * @param key 新插入结点的键
     * @param value 新插入结点的值
     */
    public void put(int key, String value){
        root = put(root, key, value);
        root.color = BLACK;
    }
    private Node put(Node node, int key, String value){
        //标准插入,和父节点用红链接相连
        if(node == null)
            return new Node(key, value, 1, RED);
        if (key > node.key) node.right = put(node.right, key, value);
        else if (key < node.key) node.left = put(node.left, key, value);
        else node.value = value;
        //调整红黑树
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
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

    /**
     * 查看最右侧路径上的节点值
     */
    public void printRight(){
        Node node = root;
        while (node != null){
            System.out.println(node.value);
            node = node.right;
        }
    }
}
