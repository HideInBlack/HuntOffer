package dataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class MyConnect {
    /**
     * （5.7）116. 填充每个节点的下一个右侧节点指针 time：2023年11月2日15:58:06 -> 2023年11月2日16:13:32
     */
    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root == null) return null;
        queue.add(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            for (int i = 0; i < len; i++){
                Node cur = queue.poll();
                if (i == len - 1){//每行的最后一个一定要做特殊处理！
                    cur.next = null;
                }else {
                    cur.next = queue.peek();
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return root;
    }

    /**
     * （5.8）117. 填充每个节点的下一个右侧节点指针 II time：2023年11月2日16:24:46 ->
     */
    public Node connect2(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if (root == null) return null;
        queue.add(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            for (int i = 0; i < len; i++){
                Node cur = queue.poll();
                if (i == len - 1){//每行的最后一个一定要做特殊处理！
                    cur.next = null;
                }else {
                    cur.next = queue.peek();
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return root;
    }

}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};