package codeFlowIdea;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    //方法二 (2.1) 94. 二叉树的中序遍历 time：2023年11月1日22:10:41 -> 2023年11月1日22:23:38
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null){
            return result;
        }
        //这个时候需要借助指针来进行遍历操作入栈 而出栈只是用来访问元素
        TreeNode current = root;
        while (current != null || !stack.isEmpty()){
            if (current != null){
                stack.push(current);
                current = current.left;
            }else if (!stack.isEmpty()){
                current = stack.pop();
                result.add(current.val);
                //出栈完要往右走了！因为是中序 是左中右
                current = current.right;
            }
        }
        return result;
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
    //方法二 非递归前序遍历 time：2023年11月1日21:54:58 -> 2023年11月1日22:06:53
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //根节点为空时要先做判断！
        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            //先进行出栈,出栈之时便是访问之后
            TreeNode temp = stack.pop();//temp缓存出栈元素
            list.add(temp.val);
            //先右再左的压入到栈中 这样出栈时才是先左才右
            if (temp.right != null){//如果出栈元素的右孩子不为空 则进栈
                stack.push(temp.right);
            }
            if (temp.left != null){
                stack.push(temp.left);
            }
        }
        return list;

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
    //方法二 (2.3) 145. 二叉树的后序遍历 非递归方法 time：2023年11月1日22:23:58 -> 2023年11月1日22:47:03
    //重点笔记：1.后序遍历和前序遍历的迭代思路一样！直接进行入栈出栈，然后再入栈其左右孩子即可。只不过后序需要用前序来做 然后翻转（其中的先左还是先右还有一点不一样！） 2.而中序遍历的迭代方法需要用到遍历节点！较难
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        //先进行先序遍历 TLR
        while (!stack.isEmpty()){
            //出栈之时 访问之时
            TreeNode cur = stack.pop();
            list.add(cur.val);
            if (cur.left != null){//先左后右！
                stack.push(cur.left);
            }
            if (cur.right != null){
                stack.push(cur.right);
            }
        }
        List<Integer> list2 = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--){
            list2.add(list.get(i));
        }
        return list2;
    }




    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyBinaryTree myBinaryTree = new MyBinaryTree();

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
