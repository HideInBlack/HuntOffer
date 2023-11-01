package codeFlowIdea;


import java.util.ArrayList;
import java.util.List;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月1日21:21:36 ->
 * author：董政宇
 * 第七部分 二叉树部分：MyBinaryTree
 */
public class MyBinaryTree {

    /**
     * (2.1) 94. 二叉树的中序遍历 time：2023年11月1日21:23:03 -> 2023年11月1日21:33:38
     * 我的思路：递归的三个条件：1.确定传入的参数 2.确定停止条件 3.确定单层递归的逻辑
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderSee(root, result);
        return result;
    }
    //递归的中序遍历
    private void inorderSee(TreeNode root, List<Integer> list){
        //当前节点不为空时 进行遍历
        if (root != null){
            //先遍历左孩子
            inorderSee(root.left, list);
            //再进行遍历此节点
            list.add(root.val);
            //最后遍历右孩子
            inorderSee(root.right, list);
        }
    }

    /**
     * （2.2）144. 二叉树的前序遍历 time：2023年11月1日21:34:59 -> 2023年11月1日21:38:44
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderSee(root, list);
        return list;
    }
    private void preorderSee(TreeNode root, List<Integer> list){
        //当前节点不为空时 进行遍历
        if (root != null){
            //先访问当前树节点
            list.add(root.val);
            //再遍历左孩子
            preorderSee(root.left, list);
            //最后遍历右孩子
            preorderSee(root.right, list);
        }
    }

    /**
     * (2.3) 145. 二叉树的后序遍历 time：2023年11月1日21:38:38 -> 2023年11月1日21:41:47
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderSee(root, list);
        return list;
    }
    private void postorderSee(TreeNode root, List<Integer> list){
        if (root != null){
            postorderSee(root.left, list);
            postorderSee(root.right, list);
            list.add(root.val);
        }
    }
    //方法二 (2.3) 145. 二叉树的后序遍历 非递归方法 time：2023年11月1日21:44:29 ->
    public List<Integer> postorderTraversal2(TreeNode root) {
        return null;
    }




    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

    }
}

//二叉树类的定义
class TreeNode{

    int val;

    TreeNode left;

    TreeNode right;

    public TreeNode(){}

    public TreeNode(int value){
        this.val = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right){
        this.val = value;
        this.left = left;
        this.right = right;
    }

}
