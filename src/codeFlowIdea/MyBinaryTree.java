package codeFlowIdea;


import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月1日21:21:36 ->
 * author：董政宇
 * 第七部分 二叉树部分：MyBinaryTree
 */
public class MyBinaryTree {

    /**
     * √ (2.1) 94. 二叉树的中序遍历 time：2023年11月1日21:23:03 -> 2023年11月1日21:33:38
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
    // √ 方法二 (2.1) 94. 二叉树的中序遍历 time：2023年11月1日22:10:41 -> 2023年11月1日22:23:38
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
     * √（2.2）144. 二叉树的前序遍历 time：2023年11月1日21:34:59 -> 2023年11月1日21:38:44
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
    //√ 方法二 非递归前序遍历 time：2023年11月1日21:54:58 -> 2023年11月1日22:06:53
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
     * √ (2.3) 145. 二叉树的后序遍历 time：2023年11月1日21:38:38 -> 2023年11月1日21:41:47
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
    // √ 方法二 (2.3) 145. 二叉树的后序遍历 非递归方法 time：2023年11月1日22:23:58 -> 2023年11月1日22:47:03
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
     * （4）二叉树的统一迭代法 前序、中序、后序遍历 time：2023年11月2日10:35:53 -> 2023年11月2日11:02:01
     * 思路：1.还是入栈-出栈-入栈的思路；2.但是出栈的时候不记录数据，只有当前出栈为空时 采记录下一个结点
     */
    // √ 前序 TLR
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (cur != null){
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
                stack.push(cur);
                stack.push(null);
            }else {
                list.add(stack.pop().val);
            }
        }
        return list;
    }
    // √ 中序
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            //先出栈
            TreeNode cur = stack.pop();
            if (cur != null){ //1.如果当前结点不为空 直接先不记录，出栈后以右、中、左继续入栈
                //入栈、入栈、入栈、入栈 全是入栈
                if (cur.right != null) stack.push(cur.right);
                stack.push(cur);
                stack.push(null);
                if (cur.left != null) stack.push(cur.left);
            }else { //2.如果当前结点为空了 就需要出栈记录空指针的下一个几点
                //出栈 取得值
                result.add(stack.pop().val);
            }
        }
        return result;
    }
    // √ 后序 LRT
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (cur != null){
                //入栈、入栈、入栈、入栈 全是入栈
                stack.push(cur);
                stack.push(null);
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
            }else {
                //出栈 取得值
                list.add(stack.pop().val);
            }
        }
        return list;
    }

    /**
     * √（5.1）102. 二叉树的层序遍历 time：2023年11月2日11:08:37 -> 2023年11月2日11:31:41
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //以层为单位遍历每一层的节点为一组
            List<Integer> list = new ArrayList<>();

            //先记住当前的队列中长度 这个指的是当前层的个数有几个！
            int length = queue.size();
            for (int i = 0; i < length; i++){
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            //每次一层为单位添加
            result.add(list);
        }
        return result;
    }
    /**
     * √（5.2）107. 二叉树的层序遍历 II time：2023年11月2日14:04:39 -> 2023年11月2日14:13:46
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++){
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            //直接只有这里不一样 直接进行头插就可以了（用list可以每一次都把其插入到第一个）
            result.add(0, list);
        }
        return result;
    }
    /**
     * （5.3）199. 二叉树的右视图 time：2023年11月2日14:16:42 -> 2023年11月2日14:23:27
     *  我的思路：使用层次遍历 每次在这一层的时候 只留下最后一个值（或者说只保存最后一个值即可）
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return list;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            //首先取出当前队列中的长度 因为要把他们都排出
            int len = queue.size();
            TreeNode cur = queue.peek();
            for (int i = 0; i < len; i++){
                cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            //此时的cur就是每一层的最后一个结点 将其保存即可
            list.add(cur.val);
        }
        return list;
    }
    /**
     * （5.4）637. 二叉树的层平均值 time：2023年11月2日14:26:19 -> 2023年11月2日14:34:50
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            long sum = 0;
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            list.add( ((double)sum / len));
        }
        return list;
    }
    /**
     * （5.5）429. N 叉树的层序遍历 time：2023年11月2日15:31:51 -> 2023年11月2日15:38:25
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            //一样获得当前队列的长度
            int len = queue.size();
            for (int i = 0; i < len; i++){
                Node node = queue.poll();
                list.add(node.val);
                //因为当前结点的孩子是List<Node> 类型 所以遍历整个list即可
                for (int j = 0; j < node.children.size(); j++){
                    //挨个遍历children list进队列
                    queue.add(node.children.get(j));
                }
            }
            result.add(list);
        }
        return result;
    }
    /**
     * （5.6）515. 在每个树行中找最大值 time：2023年11月2日15:39:09 -> 2023年11月2日15:47:03
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //先去当前这一层的第一个为最大值
            Long max = Long.valueOf(queue.peek().val);
            int len = queue.size();
            //遍历当前这一层
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            list.add(Math.toIntExact(max));
        }
        return list;
    }
    /**
     * （5.7）116. 填充每个节点的下一个右侧节点指针 time：2023年11月2日15:58:06 -> 2023年11月2日16:13:32
     * 此题目在dataStructure 文件夹下的MyConnect类中
     */
//    public dataStructure.Node connect(dataStructure.Node root) {
//        Queue<dataStructure.Node> queue = new LinkedList<>();
//        if (root == null) return null;
//        queue.add(root);
//        while (!queue.isEmpty()){
//            int len = queue.size();
//            for (int i = 0; i < len; i++){
//                dataStructure.Node cur = queue.poll();
//                if (i == len - 1){//每行的最后一个一定要做特殊处理！
//                    cur.next = null;
//                }else {
//                    cur.next = queue.peek();
//                }
//                if (cur.left != null) queue.add(cur.left);
//                if (cur.right != null) queue.add(cur.right);
//            }
//        }
//        return root;
//    }
    /**
     * （5.8）117. 填充每个节点的下一个右侧节点指针 II time：2023年11月2日16:24:46 -> 2023年11月2日16:28:05
     */
//    public dataStructure.Node connect2(dataStructure.Node root) {
//        Queue<dataStructure.Node> queue = new LinkedList<>();
//        if (root == null) return null;
//        queue.add(root);
//        while (!queue.isEmpty()){
//            int len = queue.size();
//            for (int i = 0; i < len; i++){
//                dataStructure.Node cur = queue.poll();
//                if (i == len - 1){//每行的最后一个一定要做特殊处理！
//                    cur.next = null;
//                }else {
//                    cur.next = queue.peek();
//                }
//                if (cur.left != null) queue.add(cur.left);
//                if (cur.right != null) queue.add(cur.right);
//            }
//        }
//        return root;
//    }
    /**
     *（5.9）104. 二叉树的最大深度 time：2023年11月2日16:28:52 -> 2023年11月2日16:34:41
     * 我的思路：可以使用层次遍历 直接取出其深度
     */
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()){
            int len = queue.size();
            count++;
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return count;
    }
    /**
     * （5.10）111. 二叉树的最小深度 time：2023年11月2日16:36:18 -> 2023年11月2日16:45:30
     * 我的思路：直接进行层次遍历 当层次遍历的时候最先找到一个节点是叶子节点时 直接进行返回层数即可
     */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        //一定要先把头结点入队！
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()){
            //count表示当前层数
            count++;
            //计算当前队的总长度 也就是当前层次的总结点数量
            int len = queue.size();
            //下面针对当前层的所有节点出队 并且出队后入队其孩子节点
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null){
                    return count;
                }
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return -1;
    }
    /**
     * （6）226. 翻转二叉树 time：
     */
    public TreeNode invertTree(TreeNode root) {
        return root;
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

// n 叉树的定义
class Node{

    public int val;

    public List<Node> children;

    public Node(){}

    public Node(int val){
        this.val = val;
    }

    public Node(int val, List<Node> children){
        this.val = val;
        this.children = children;
    }

}