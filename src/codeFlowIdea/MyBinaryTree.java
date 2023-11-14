package codeFlowIdea;


import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月1日21:21:36 -> 2023年11月14日15:43:52
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
     * √（5.3）199. 二叉树的右视图 time：2023年11月2日14:16:42 -> 2023年11月2日14:23:27
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
     * √（5.4）637. 二叉树的层平均值 time：2023年11月2日14:26:19 -> 2023年11月2日14:34:50
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
     * √（5.5）429. N 叉树的层序遍历 time：2023年11月2日15:31:51 -> 2023年11月2日15:38:25
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
     * √（5.6）515. 在每个树行中找最大值 time：2023年11月2日15:39:09 -> 2023年11月2日15:47:03
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
     * √（5.7）116. 填充每个节点的下一个右侧节点指针 time：2023年11月2日15:58:06 -> 2023年11月2日16:13:32
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
     *  √（5.8）117. 填充每个节点的下一个右侧节点指针 II time：2023年11月2日16:24:46 -> 2023年11月2日16:28:05
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
     * √（5.9）104. 二叉树的最大深度 time：2023年11月2日16:28:52 -> 2023年11月2日16:34:41
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
     * √（5.10）111. 二叉树的最小深度 time：2023年11月2日16:36:18 -> 2023年11月2日16:45:30
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
     * √（6）226. 翻转二叉树 time：2023年11月3日09:19:17 -> 2023年11月3日09:31:33
     *  我的思路：递归实现：如果当前树不是叶子节点 就反转其左右孩子 叶子节点时不用处理即可
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        invert(root);
        return root;
    }
    //使用先序遍历 遍历每一个节点 翻转其
    private void invert(TreeNode treeNode){
        //如果当前结点是空节点 直接返回
        if (treeNode != null && (treeNode.left != null || treeNode.right != null)){//如果不是空节点 才有左右孩子 再判断其是否是叶子节点（叶子结点不需要翻转其左右孩子 直接返回）
            TreeNode tempLeft = treeNode.left;
            TreeNode tempRight = treeNode.right;
            //翻转当前结点的左右孩子
            treeNode.left = tempRight;
            treeNode.right = tempLeft;
            //再继续往下遍历
            invert(treeNode.left);
            invert(treeNode.right);
        }
    }
    // √ 翻转二叉树 方法二  来吧！先序遍历 试一下统一迭代法 time：2023年11月3日09:41:46 -> 2023年11月3日09:53:05
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            //先出栈一个
            TreeNode cur = stack.pop();
            if (cur != null){//如果出栈元素 不为空 则继续入栈 不做遍历等操作
                if (cur.right != null) stack.push(cur.right);
                if (cur.left != null) stack.push(cur.left);
                stack.push(cur);
                stack.push(null);//空指针作为标记
            }else {//如果出栈是空指针 则开始操作空指针的下一个结点！
                cur = stack.pop();
                if (cur.left != null || cur.right != null){//如果其不是叶子节点 就需要翻转
                    TreeNode left = cur.left;
                    TreeNode right = cur.right;
                    cur.left = right;
                    cur.right = left;
                }
            }
        }
        return root;
    }
    // √ 翻转二叉树 方法三 层序遍历 time：2023年11月3日09:54:22 -> 2023年11月3日10:00:10
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //来嘛  继续以行为单位吧
            int len = queue.size();
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                //下面再做 翻转操作
                if (cur.left != null || cur.right != null){
                    TreeNode left = cur.left;
                    TreeNode right = cur.right;
                    cur.left = right;
                    cur.right = left;
                }
            }
        }
        return root;
    }

    /**
     * √ 589. N 叉树的前序遍历 time：2023年11月3日10:05:59 -> 2023年11月3日10:13:21
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorderD(root, list);
        return list;
    }
    // N 叉树的先序遍历 TLR
    private void preorderD(Node root, List<Integer> list){//1.递归三要素第一 传入参数、返回类型的确定
        if (root != null){//2.递归三要素第二 递归的终止条件
            //3.递归三要素第三 递归的单层逻辑
            list.add(root.val);
            for (int i = 0; i < root.children.size(); i++){
                preorderD(root.children.get(i), list);
            }
        }
    }

    /**
     * 590. N 叉树的后序遍历 time：2023年11月3日10:14:03 -> 2023年11月3日10:19:41
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorderD(root, list);
        return list;
    }
    //递归的后续遍历 LRT
    private void postorderD(Node root, List<Integer> list){
        if (root != null){//2.递归三要素第二 递归的终止条件
            //3.递归三要素第三 递归的单层逻辑
            for (int i = 0; i < root.children.size(); i++){
                postorderD(root.children.get(i), list);
            }
            list.add(root.val);
        }
    }

    /**
     * （8）101. 对称二叉树 time：2023年11月3日15:33:26 -> 2023年11月3日16:12:12
     * 我的思路：判断二叉树是不是对称的 1.先翻转二叉树 2.翻转之后如果还是一样的结构一样的数值那就是对称的
     */
    public boolean isSymmetric(TreeNode root) {
        TreeNode newOne = new TreeNode(root.val);
        //先复制出一个一模一样的树出来
        copyTree(root, newOne);
        //然后再反转这个复制的树
        invertTree(newOne);
        //再递归遍历一遍看一下 这两个树的结构内容 是否都一样

        return twoTree(root, newOne);
    }
    //递归遍历两棵树
    private boolean twoTree(TreeNode root, TreeNode newOne){
        if (root != null && newOne != null){
            if (root.val != newOne.val) return false;
            //其实这里体现的就是后序遍历的思想！先进左 再进右边 最后返回
            return twoTree(root.left, newOne.left) && twoTree(root.right, newOne.right);
        }else if (root != null && newOne == null){
            return false;
        }else if (root == null && newOne != null){
            return false;
        }
        return true;
    }
    //复制一个完全一样的二叉树出来
    private void copyTree(TreeNode root, TreeNode newOne){
        if (root != null){
            if (root.left != null){
                TreeNode newOne1 = new TreeNode(root.left.val);
                newOne.left = newOne1;
                copyTree(root.left, newOne.left);
            }
            if (root.right != null){
                TreeNode newOne2 = new TreeNode(root.right.val);
                newOne.right = newOne2;
                copyTree(root.right, newOne.right);
            }

        }
    }
    // √ 方法二 题解方法 101. 对称二叉树 time：2023年11月3日16:21:37 -> 2023年11月3日16:29:49
    public boolean isSymmetric2(TreeNode root) {
        return twoSymmetricTree(root.left, root.right);
    }
    //递归遍历判断两个是否是对称树
    private boolean twoSymmetricTree(TreeNode left, TreeNode right){
        if (left != null && right != null){
            if (left.val != right.val) return false;
            //其实这里体现的就是后序遍历的思想！先进左 再进右边 最后返回
            //这里一定是这样：1.左子树往左走时，右子树往右走  2.左子树往右走时，右子树往左走
            return twoSymmetricTree(left.left, right.right) && twoSymmetricTree(left.right, right.left);
        }else if (left != null && right == null){//条件判断不要去判断当前的节点的左子树或者右子树，而是要在外面判断，只需要判断当前结点
            return false;
        }else if (left == null && right != null){
            return false;
        }
        return true;
    }

    /**
     * （8.1）100. 相同的树 time：2023年11月3日16:54:30 -> 2023年11月3日17:02:39
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null){
            //这里有一个好处就是 如果不同直接进行返回 就不需要继续往下遍历了 节省时间
            if (p.val != q.val) return false;
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }else if (p != null && q == null){
            return false;
        }else if (p == null && q != null){
            return false;
        }else {
            //都为空时 就需要返回true
            return true;
        }
    }

    /***
     * （8.2）572. 另一棵树的子树 time：2023年11月3日17:03:03 -> 2023年11月3日17:14:55
     * 我的思路：遍历每个节点 从当前节点和另一棵树进行判断是不是相同的树
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        //先就是一个常规的递归遍历
        if (root != null){
            //再进行优化一下 （下面注释中的是正确的答案）
            if (isSameTree(root, subRoot)) return true;
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

//            boolean cur = isSameTree(root, subRoot);
//            boolean left = isSubtree(root.left, subRoot);
//            boolean right = isSubtree(root.right, subRoot);
//            //只要找到一个就可以！
//            return cur || left || right;
        }else {
            return false;
        }
    }

    /**
     * √（9）方法二 递归求 104. 二叉树的最大深度
     */
    public int maxDepth2(TreeNode root) {
        int maxDep = 0;
        return maxDepthD(root, 0);
    }
    //递归先序遍历求最大深度
    private int maxDepthD(TreeNode root, int maxDep){
        if (root != null){
            maxDep += 1;
            return Math.max(maxDepthD(root.left, maxDep), maxDepthD(root.right, maxDep));
        }
        //当前结点为空时 直接返回原参数高度
        return maxDep;
    }

    /**
     * √（9.1）559. N 叉树的最大深度 time：2023年11月3日19:10:47 -> 2023年11月3日19:22:18
     * 递归方法 + 大顶堆 搞定 下面试一下层次遍历
     */
    public int maxDepthN(Node root) {
        int maxDepth = 0;
        return maxN(root, 0);
    }
    private int maxN(Node root, int maxDepth){
        if (root != null){
            maxDepth += 1;
            //正好使用一下大顶堆 练习一下大顶堆取最大值
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;//降序 所以是大顶堆
                }
            });
            for (int i = 0; i < root.children.size(); i++){
                priorityQueue.add(maxN(root.children.get(i), maxDepth));
            }
            if (priorityQueue.isEmpty()) return maxDepth;
            else return priorityQueue.poll();
        }else {
            return maxDepth;
        }
    }
    // √ 方法二 层序遍历n叉树 来求最大深度 2023年11月3日19:22:18 -> 2023年11月3日19:27:01
    public int maxDepth9(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()){
            height++;
            int len = queue.size();
            for (int i = 0; i < len; i++){
                Node cur = queue.poll();
                //把所有的孩子入对
                for (int j = 0; j < cur.children.size(); j++){
                    queue.add(cur.children.get(j));
                }
            }
        }
        return height;
    }

    /**
     * √（10）111. 二叉树的最小深度 time：2023年11月3日19:29:22 -> 2023年11月3日19:59:16
     *  求最小深度的递归需要注意一些！
     */
    public int minDepth8(TreeNode root) {
        if (root == null) return 0;
        int minDepth = 0;
        return min8(root, minDepth);
    }
    //递归求二叉树的最小深度
    private int min8(TreeNode root, int curDepth){
        //这个递归要特殊一些
        curDepth += 1;
        if (root.left == null && root.right == null) return curDepth;
        if (root.left != null && root.right == null){//此时只有左孩子不为空
            return min8(root.left, curDepth);
        }else if (root.right != null && root.left == null){
            return min8(root.right, curDepth);
        } else if (root.left != null && root.right != null) {
            return Math.min(min8(root.left, curDepth), min8(root.right, curDepth));
        }
        return -1;
    }

    /**
     * （11）222. 完全二叉树的节点个数 time：2023年11月3日20:04:28 -> 2023年11月3日20:09:04
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()){
            int len = queue.size();
            count += len;
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return count;
    }

    /**
     * （12）110. 平衡二叉树 time：2023年11月3日20:13:20 -> 2023年11月3日20:30:13
     * 我的思路：针对每一个节点 遍历求其左右孩子的最大深度 再求其绝对值判断
     */
    public boolean isBalanced(TreeNode root) {
        if (root != null){
            boolean cur = false;
            //获得当前结点的结果
            if (Math.abs(getMaxDepth(root.left, 0) - getMaxDepth(root.right, 0)) <= 1){
                cur = true;
            }else {//这里其实可以直接return false 进行优化！
                return false;
            }
            boolean left = isBalanced(root.left);
            boolean right = isBalanced(root.right);
            return left && right;
        }
        return true;
    }
    //递归遍历求最大深度
    private int getMaxDepth(TreeNode root, int curDepth){
        if (root != null){
            curDepth += 1;
            return Math.max(getMaxDepth(root.left, curDepth), getMaxDepth(root.right, curDepth));
        }
        return curDepth;
    }

    /**
     * √（13）257. 二叉树的所有路径 time：2023年11月3日21:05:11 -> 2023年11月3日21:17:55
     */
    public List<String> binaryTreePaths(TreeNode root) {
        //我先都用list存起来不然不方便
        List<String> result = new ArrayList<>();
        binaryTree(root, new StringBuilder(), result);
        return result;
    }
    //递归遍历
//    public void binaryTree(TreeNode root, String curPath, List<String> result) {
//        if (root != null){
//            curPath = curPath + "->" + root.val;
//            if (root.left == null & root.right == null) result.add(curPath.substring(2));//如果是叶子节点才添加到结果中
//            binaryTree(root.left, curPath, result);
//            binaryTree(root.right, curPath, result);
//        }
//    }
    //我试一下 StringBuilder
    public void binaryTree(TreeNode root, StringBuilder curPath, List<String> result) {
        if (root != null){
            curPath.append("->" + root.val);
            if (root.left == null & root.right == null) result.add(curPath.substring(2));//如果是叶子节点才添加到结果中
            binaryTree(root.left, new StringBuilder(curPath), result);
            binaryTree(root.right, new StringBuilder(curPath), result);
        }
    }

    /**
     *（15）404. 左叶子之和 time：2023年11月4日10:17:13 -> 2023年11月4日10:30:28
     * 递归思想 熟练掌握！牛逼！
     * 我的方法很巧妙：从当前结点往左右孩子走的时候 传递一个key表明它是左孩子还是右孩子就可以啦！
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeft(root, 0, 0);
    }
    //递归遍历所有的左叶子节点 然后求和【在这里自定义一个key的意思是说：如果是其父节点的左孩子 那就直接为1，不是则为0 来传递信息】
    public int sumOfLeft(TreeNode root, int key, int sum) {
        if (root != null){
            if (root.left == null && root.right == null && key == 1) return sum += root.val;
            return sumOfLeft(root.left, 1, sum) + sumOfLeft(root.right, 0, sum);
        }
        return sum;
    }

    /**
     * （16）513. 找树左下角的值 time：2023年11月4日10:38:36 -> 2023年11月4日10:44:13
     * 我的思路：这一题岂不是简单的要死？直接进行层序遍历去最后一层的第一个值就可以了
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = -1;
        while (!queue.isEmpty()){
            int len = queue.size();
            //取出最后一层的第一个值
            result = queue.peek().val;
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return result;
    }

    /**
     * （17）112. 路径总和 time：2023年11月4日10:48:42 -> 2023年11月4日11:08:52
     *  我的思路：那么这一题就切切实实的使用到回溯了【其实并没有使用回溯！不用回溯也可以啊！就直接只用先序遍历往下走就可以了】
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return pathSum(root, targetSum, 0);
    }
    //直接使用先序遍历 进行往下走就可以啊！
    private boolean pathSum(TreeNode root, int targetSum, int curSum){
        if (root != null){
            curSum += root.val;
            if (curSum == targetSum && root.right == null && root.left == null) return true;
            boolean left = pathSum(root.left, targetSum, curSum);
            boolean right = pathSum(root.right, targetSum, curSum);
            //思考这里为什么用|| 因为找到一个就可以了！
            return left || right;
        }
        //这种就是要特别注意 叶子结点应该返回什么？
        return false;
    }

    /**
     * √（17.1）113. 路径总和 II time：2023年11月4日13:49:10 -> 2023年11月4日14:22:57
     * 重点笔记：在递归里面要使用list时，一定要注意！是想让其共享(共享就直接list)，还是不共享(不共享就new ArrayList<>(curList))！
     */
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        pathSumAll(root, targetSum, curList, result);
        return result;
    }
    //对于list这种 在递归中要想不共享 就要进行回溯方法！并且二维list添加时记得new 因为一维改动 二维也会改动
    private void pathSumAll(TreeNode root, int targetSum, List<Integer> curList, List<List<Integer>> result){
        if (root != null){
            //把当前值加入到路径
            curList.add(root.val);
            int sum = 0;
            int len = curList.size();
            //求和当前路径
            for (int i = 0; i < len; i++){
                sum += curList.get(i);
            }
            if (sum == targetSum && root.left == null && root.right == null) result.add(new ArrayList<>(curList)); //中
            pathSumAll(root.left, targetSum, curList, result); //左
            //对于list 就需要进行回溯了
            if (curList.size() != len) curList.remove(curList.size() - 1); //回溯
            pathSumAll(root.right, targetSum, curList, result); //右
            //这里也是需要回溯！
            if (curList.size() != len) curList.remove(curList.size() - 1); //回溯
        }
    }
    // √ 递归方法二 如果我不使用回溯呢 直接new list是否可行？ time：2023年11月4日14:35:08 -> 2023年11月4日14:42:52
    private void pathSumAll2(TreeNode root, int targetSum, int curSum, List<Integer> curList, List<List<Integer>> result){
        if (root != null){
            curSum += root.val;
            curList.add(root.val);
            //如果和相等 并且是叶子节点时 直接加入到2维list
            if (curSum == targetSum && root.left == null && root.right == null) result.add(curList); //中
            pathSumAll2(root.left, targetSum, curSum, new ArrayList<>(curList), result); //左
            pathSumAll2(root.right, targetSum, curSum, new ArrayList<>(curList), result); //右
        }
    }

    /**
     * ×（18）106. 从中序与后序遍历序列构造二叉树 time：2023年11月4日15:01:04 -> 2023年11月4日15:13:26
     */
    public TreeNode buildTree0(int[] inorder, int[] postorder) {
        return null;
    }
    // √ 题解方法 106. 从中序与后序遍历序列构造二叉树 time：2023年11月4日15:35:32 -> 2023年11月4日16:05:34
    //整体思路：1.首先是后续遍历的最后一个值是根节点 2.要根据后续遍历的最后一个值去中序遍历中找到然后进行分组，分成的左右组个数是一定是相同的，利用这个特性可以顺利把后续分组（中序的好分组，找到值就是分组成功了） 3.最后往两边进行递归创建即可
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //首先把中序遍历集中到map中 方便查询
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            //记住这里要把位置存为value！
            map.put(inorder[i], i);
        }
        return buildTreeMidAfter(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    //中序和后序创建树的递归方法
    private TreeNode buildTreeMidAfter(int[] inorder, int leftIn, int rightIn, int[] postorder, int leftPost, int rightPost, Map<Integer, Integer> map){
        if (leftIn > rightIn || leftPost > rightPost){
            return null;
        }
        //先创建新节点
        TreeNode newOne = new TreeNode(postorder[rightPost]);
        //1.首先找到中序遍历根节点的位置
        int rootPosition = map.get(postorder[rightPost]);
        //2.获得中序遍历中左分组长度
        int len = rootPosition - leftIn;
        //3.往下继续递归
        newOne.left = buildTreeMidAfter(inorder, leftIn, rootPosition - 1, postorder, leftPost, leftPost + len - 1, map);
        newOne.right = buildTreeMidAfter(inorder, rootPosition + 1, rightIn, postorder, leftPost + len, rightPost - 1, map);
        return newOne;
    }

    /**
     * √（18.1）105. 从前序与中序遍历序列构造二叉树 time：2023年11月4日16:14:00 -> 2023年11月4日16:33:19
     */
    public TreeNode buildTreeB(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return buildTreeMidBefore(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1, map);
    }
    private TreeNode buildTreeMidBefore(int[] inorder, int leftIn, int rightIn, int[] preorder, int leftPre, int rightPre, Map<Integer, Integer> map){
        if (leftIn <= rightIn && leftPre <= rightPre){
            TreeNode newOne = new TreeNode(preorder[leftPre]);
            //1.先在中序遍历中找到根节点位置
            int position = map.get(preorder[leftPre]);
            //2.计算其在中序遍历中的左分组的长度
            int len = position - leftIn;
            //往下进行递归
            newOne.left = buildTreeMidBefore(inorder, leftIn, position - 1, preorder, leftPre + 1, leftPre + len, map);//左分组
            newOne.right = buildTreeMidBefore(inorder, position + 1, rightIn, preorder, leftPre + len + 1, rightPre, map);//右分组

            return newOne;
        }else {//否则就是叶子节点返回为null
            return null;
        }
    }

    /**
     * √（19）654. 最大二叉树 time：2023年11月4日16:48:57 -> 2023年11月4日17:04:21
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        return recursionMaximumBinaryTree(nums, 0, nums.length - 1, map);
    }
    //recursion 最大二叉树的递归函数
    private TreeNode recursionMaximumBinaryTree(int[] nums, int left, int right, Map<Integer, Integer> map){
        if (left <= right){
            //1.先利用堆排序取出最大值
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;//降序取出最大值
                }
            });
            for (int i = left; i <= right; i++){
                priorityQueue.add(nums[i]);
            }
            //2.再创建新节点
            TreeNode newOne = new TreeNode(priorityQueue.peek());
            //3.找到最大值的位置 方便进行分组
            int position = map.get(priorityQueue.peek());
            //4.进行递归
            newOne.left = recursionMaximumBinaryTree(nums, left, position - 1, map);
            newOne.right = recursionMaximumBinaryTree(nums, position + 1, right, map);
            return newOne;
        }else {
            return null;
        }
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树 time：2023年11月12日15:16:41 -> 2023年11月12日15:36:49
     */
    public TreeNode buildTreeAgain(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        //先把中序遍历的值放到map中，方便查询
        for (int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);//需要根据值来找位置
        }
        return midAndBack(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    public TreeNode midAndBack(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight, Map<Integer, Integer> map){
        if (inLeft <= inRight && postLeft <= postRight){
            int position = map.get(postorder[postRight]);
            int length = position - inLeft;
            //先创建当前结点
            TreeNode treeNode = new TreeNode(postorder[postRight]);
            treeNode.left = midAndBack(inorder, inLeft, position - 1, postorder, postLeft, postLeft + length - 1, map);
            treeNode.right = midAndBack(inorder, position + 1, inRight, postorder, postLeft + length, postRight - 1, map);
            return treeNode;
        }else {
            return null;
        }
    }

    /**
     * （21）617. 合并二叉树 time：2023年11月12日15:46:19 -> 2023年11月12日15:57:37
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return merge(root1, root2);
    }
    public TreeNode merge(TreeNode root1, TreeNode root2){
        if (root1 != null && root2 != null){
            TreeNode treeNode = new TreeNode(root1.val + root2.val);
            treeNode.left = merge(root1.left, root2.left);
            treeNode.right = merge(root1.right, root2.right);
            return treeNode;
        } else if (root1 != null) {
            TreeNode treeNode = new TreeNode(root1.val);
            treeNode.left = merge(root1.left, null);
            treeNode.right = merge(root1.right, null);
            return treeNode;
        } else if (root2 != null) {
            TreeNode treeNode = new TreeNode(root2.val);
            treeNode.left = merge(null, root2.left);
            treeNode.right = merge(null, root2.right);
            return treeNode;
        } else {
            return null;
        }
    }

    /**
     * （22）700. 二叉搜索树中的搜索 time：2023年11月12日20:10:27 -> 2023年11月12日20:15:59
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root != null){
            if (root.val == val) return root;
            if (root.val < val) return searchBST(root.right, val);
            return searchBST(root.left, val);
        }else {
            return null;
        }
    }

    /**
     * ×【75 / 83 个通过的测试用例】（23）98. 验证二叉搜索树 time：2023年11月12日20:17:32 -> 2023年11月12日20:34:00
     * 我的思路：遍历一遍看看是不是每一个节点都满足条件（从其父节点那里接受信息）
     */
    public boolean isValidBST(TreeNode root) {
        return validBST(root.left, root.val, 0) && validBST(root.right, root.val, 1);
    }
    //其中parentValue是父节点的值，key=0 代表其是其父节点的左孩子，如果key=1代表其是其父节点的右孩子
    private boolean validBST(TreeNode root, int parentValue, int key){
        if (root != null){
            //中 （判断当前节点）
            if (root.val == parentValue) return false;
            if (root.val < parentValue && key == 1) return false;
            if (root.val > parentValue && key == 0) return false;
            //左 + 右
            return validBST(root.left, root.val, 0) && validBST(root.right, root.val, 1);
        }else {
            //空节点直接就是true的！
            return true;
        }
    }
    //方法二 我的思路：保证是是否为二叉搜索树 要保证中序遍历是有序的？1.先获取中序遍历 2.在进行验证 time: 2023年11月12日20:34:00 -> 2023年11月12日20:43:28
    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        midSee(root, list);
        for (int i = 0; i < list.size() - 1; i++){
            //永远是当前的值 和 下一个值比较
            if (list.get(i) >= list.get(i + 1)) return false;
        }
        return true;
    }
    //中序遍历二叉树 LTR
    private void midSee(TreeNode root, List<Integer> list){
        if (root != null){
            midSee(root.left, list);
            list.add(root.val);
            midSee(root.right, list);
        }
    }

    /**
     * （24）530. 二叉搜索树的最小绝对差 time：2023年11月12日20:48:19 -> 2023年11月12日20:56:29
     *  我的思路：直接进行中序遍历 然后求两两之间的差值（绝对值） 然后保留最小的即可。
     */
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        midSee(root, list);
        //先取第一组差值为最小差值
        int minDiff = Math.abs(list.get(0) - list.get(1));
        for (int i = 0; i < list.size() - 1; i++){
            //永远是当前的值 和 下一个值比较
            minDiff = Math.min(minDiff, Math.abs(list.get(i) - list.get(i + 1)));
        }
        return minDiff;
    }

    /**
     * （25）501. 二叉搜索树中的众数 time：2023年11月12日20:58:56 -> 2023年11月12日21:18:46
     *  我的思路：先进行中序遍历（二叉搜索树离不开中序遍历的！） 排序+大顶堆！无敌
     */
    public int[] findMode(TreeNode root) {

        Map<Integer, Integer> map = new HashMap<>();
        midSee2(root, map);
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();//降序 大顶堆
            }
        });
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            priorityQueue.add(entry);
        }
        List<Integer> result = new ArrayList<>();
        int value = priorityQueue.peek().getValue();
        result.add(priorityQueue.poll().getKey());
        while (!priorityQueue.isEmpty() && priorityQueue.peek().getValue() == value){
            result.add(priorityQueue.poll().getKey());
        }
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++){
            resultArray[i] = result.get(i);
        }
        return resultArray;

    }
    private void midSee2(TreeNode root, Map<Integer, Integer> map){
        if (root != null){
            midSee2(root.left, map);
            map.put(root.val, map.getOrDefault(root.val, 0) + 1);
            midSee2(root.right, map);
        }
    }

    /**
     * √【思路不错 但是有点耗时】（26）236. 二叉树的最近公共祖先 time：2023年11月13日16:21:18 -> 2023年11月13日17:09:04
     *  我的思路：使用层次遍历 进行从根节点遍历到最先出现其中某一个节点的这一层，结束，此时的最终可以同时遍历到两个节点的根节点为最近祖先
     */
    boolean isP = false; boolean isQ = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //下面进行层序遍历
        TreeNode commonAncestor = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int len = queue.size();
            for (int i = 0; i < len; i++){
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                //每次判定前重置
                isP = false; isQ = false;
                judgeIsParent(cur, p.val, q.val);
                if (isP && isQ) commonAncestor = cur;

                //如果访问到其中任意一个值直接进行结束
                if (cur.val == p.val || cur.val == q.val) {
                    queue.clear();
                    break ;
                }
            }
        }
        return commonAncestor;
    }
    //使用递归遍历 遍历以此节点为根节点 是否可以同时遍历到两个节点
    public void judgeIsParent(TreeNode root, int pValue, int qValue){
        if (root != null){
            if (root.val == pValue) isP = true;
            if (root.val == qValue) isQ = true;
            if (isP && isQ) return;
            judgeIsParent(root.left, pValue, qValue);
            judgeIsParent(root.right, pValue, qValue);
        }
    }
    // √ 方法二 真正的考法！自底向上的遍历（正是后序遍历呀！LRT！回溯！）
    //重要笔记：1.后序遍历就是正宗的回溯！先访问其左右孩子，再访问本结点！2.就算找到节点也要一直往上回溯传递，通过节点一个个返回到最上面！
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return commonAncestor(root, p, q);
    }
    //先进行一个后序遍历
    public TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if (root != null){
            TreeNode left = commonAncestor(root.left, p, q);// 左
            TreeNode right = commonAncestor(root.right, p, q);// 右
            if (root.val == p.val) return p; // 中
            if (root.val == q.val) return q;
            if (left != null && right != null) return root;
            if (left == null && right != null) return right;
            if (left != null && right == null) return left;
            if (left == null && right == null) return null;
            return null;
        }else {
            return null;
        }
    }

    /**
     * √（27） 235. 二叉搜索树的最近公共祖先 time：2023年11月13日18:52:59 -> 2023年11月13日19:03:41
     */
    public TreeNode lowestCommonAncestor6(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null){
            TreeNode left = lowestCommonAncestor6(root.left, p, q);
            TreeNode right = lowestCommonAncestor6(root.right, p, q);
            if (root == p) return p;
            if (root == q) return q;
            if (left != null && right != null) return root;
            if (left != null) return left;
            if (right != null) return right;
            return null;//都为空的时候 就直接返回空
        }else {
            return null;
        }
    }
    // √ 方法二 思考二叉搜索树的特性！time：2023年11月13日19:13:22 -> 2023年11月13日19:42:42
    //重要笔记：1.二叉搜索树的特征是左子树一定都比其小，右子树上的一定都比其大 2.因此就有一个特性是从上往下遍历（先序遍历TLR），遇到的第一个在其范围内的节点就是其公共祖先！
    // 3.所以此题非常巧妙，利用性质！无需进行(后序遍历)回溯！ 先序遍历可以解决更快！
    TreeNode result7;
    public TreeNode lowestCommonAncestor7(TreeNode root, TreeNode p, TreeNode q) {
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        commonAncestor7(root, min, max);
        return result7;
    }
    //先序遍历 TLR
    public void commonAncestor7(TreeNode root, int min, int max){
        if (root != null){
            if (root.val >= min && root.val <= max){
                result7 = root;
                return;
            }
            if (max < root.val) commonAncestor7(root.left, min, max);
            if (min > root.val) commonAncestor7(root.right, min, max);
        }
    }

    /**
     * √（29）701. 二叉搜索树中的插入操作 time：2023年11月13日20:28:48 -> 2023年11月13日20:45:28
     *  我的思路：每次都把节点插入到叶子节点处？ 定义一个key来表示是其父节点的左孩子还是右孩子！【0：左 / 1：右】
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (root.val > val) insert(root.left, val, root, 0);
        if (root.val < val) insert(root.right, val, root, 1);
        return root;
    }
    //要利用到父节点（上一个节点）所以可以使用指针pre
    private void insert(TreeNode root, int val, TreeNode pre, int key){
        if (root != null){
            if (root.val > val) insert(root.left, val, root, 0);
            if (root.val < val) insert(root.right, val, root, 1);
        }else {
            //到达叶子节点时，就是要插入的时候
            TreeNode newNode = new TreeNode(val);
            if (key == 0) pre.left = newNode;
            if (key == 1) pre.right = newNode;
        }
    }
    // √ 方法二 改进递归
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        insert2(root, val);
        return root;
    }
    private void insert2(TreeNode root, int val){
        if (root != null){
            if (root.val > val && root.left == null) root.left = new TreeNode(val);
            if (root.val < val && root.right == null) root.right = new TreeNode(val);
            if (root.val > val && root.left != null) insert2(root.left, val);
            if (root.val < val && root.right != null) insert2(root.right, val);
        }
    }

    /**
     * ×【有思路，但无编码思路】（30）450. 删除二叉搜索树中的节点 time：2023年11月13日20:57:12 -> 2023年11月13日21:20:26
     * √ 方法二（题解方法） 继续做 time：2023年11月13日21:31:59 -> 2023年11月13日22:05:48
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        //使用一个哨兵结点！！！
        TreeNode newNode = new TreeNode(0);
        newNode.left = root;
        deleteNodeD(newNode.left, key, newNode, 0);
        return newNode.left;
    }
    private void deleteNodeD(TreeNode root, int key, TreeNode pre, int leftOrRight){//leftOrRight=0 时表示是其父节点的左孩子
        if (root != null){
            if (root.val == key){
                //进行删除操作
                if (root.left == null && root.right == null){//1.为叶子节点时：直接进行删除
                    if (leftOrRight == 0) pre.left = null;
                    if (leftOrRight == 1) pre.right = null;
                }else if (root.left != null && root.right == null){//2.左孩子不为空时：直接删除，左孩子补上
                    if (leftOrRight == 0) pre.left = root.left;
                    if (leftOrRight == 1) pre.right = root.left;
                } else if (root.right != null && root.left == null) {//3.右孩子不为空时：直接删除，右孩子补上
                    if (leftOrRight == 0) pre.left = root.right;
                    if (leftOrRight == 1) pre.right = root.right;
                }else { //4.都不为空时：直接删除，其右孩子当头结点，左孩子放到右孩子的最左边的节点的左叶子处。
                    TreeNode left = root.left;
                    TreeNode right = root.right;
                    TreeNode rightLeftest = getLeftest(right);
                    //把左子树插入到右子树的最左边
                    rightLeftest.left = left;
                    //把右子树接入到原树中
                    if (leftOrRight == 0) pre.left = right;
                    if (leftOrRight == 1) pre.right = right;
                }
            }else if (key < root.val){
                deleteNodeD(root.left, key, root, 0);//左边
            }else {
                deleteNodeD(root.right, key, root, 1);//右边
            }
        }
    }
    //找到当前节点的最左边的节点
    private TreeNode getLeftest(TreeNode root){
        while (root.left != null){
            root = root.left;
        }
        return root;
    }

    /**
     *  × 【不能完全示例通过】（31）669. 修剪二叉搜索树 time：2023年11月14日11:50:06 -> 2023年11月14日12:07:54
     *  我的思路：中序遍历 + 删除二叉搜索树中的节点
     */
    // √ 题解方法！ 无需借助父节点，直接进行返回！【这里使用的是每一次都把当前结点返回！来相当于删除操作】
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root != null){
            if (root.val < low) {
                TreeNode nextNode = root.right;//这一步骤就相当于删除了当前结点以及其左孩子，然后继续对其右孩子操作
                TreeNode success = trimBST(nextNode, low, high);
                return success;
            }else if (root.val > high) {
                TreeNode nextNode = root.left;//这一步骤就相当于删除了当前结点以及其右孩子，然后继续对其做孩子进行删除操作
                TreeNode success = trimBST(nextNode, low, high);
                return success;
            }else {// 否则在范围之内 需要保留的时候
                root.left = trimBST(root.left, low, high);
                root.right = trimBST(root.right, low, high);
                return root;
            }
        }else {
            return null;
        }
    }

    // √ again ! 删除二叉树中的指定结点  time：2023年11月14日10:56:54 -> 2023年11月14日11:49:37
    private TreeNode deleteNode(TreeNode root, TreeNode target){
        //哨兵结点
        TreeNode head = new TreeNode(-1);
        head.left = root;
        deleteNodeInBST(head.left, target, head, true);
        return head.left;
    }
    private void deleteNodeInBST(TreeNode root, TreeNode target, TreeNode pre, boolean isLeft) {
        if (root != null) {
            if (root.val == target.val) {
                //删除此节点操作
                if (root.left == null && root.right == null) {//1.为叶子节点时
                    if (isLeft) {
                        pre.left = null;
                    } else {
                        pre.right = null;
                    }
                } else if (root.left != null && root.right == null) {//2.左孩子不为空，左孩子补上
                    if (isLeft) {
                        pre.left = root.left;
                    } else {
                        pre.right = root.left;
                    }
                } else if (root.left == null && root.right != null) {//3.右孩子不为空.右孩子补上
                    if (isLeft) {
                        pre.left = root.right;
                    } else {
                        pre.right = root.right;
                    }
                } else if (root.left != null && root.right != null) {//4.左右孩子都不为空时，把左孩子移到右子树的最左边
                    TreeNode leftChild = root.left;
                    TreeNode rightChild = root.right;
                    TreeNode leftest = rightChild;
                    while (leftest.left != null) {
                        leftest = leftest.left;
                    }
                    leftest.left = leftChild;
                    if (isLeft) {
                        pre.left = rightChild;
                    } else {
                        pre.right = rightChild;
                    }
                }
            } else if (root.val < target.val) {
                deleteNodeInBST(root.right, target, root, false);
            } else if (root.val > target.val) {
                deleteNodeInBST(root.left, target, root, true);
            }
        }
    }

    /**
     *  √（32）108. 将有序数组转换为二叉搜索树 time：2023年11月14日14:12:43 -> 2023年11月14日14:37:21
     *  我的思路：不断进行取之间的值然后二分 递归
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        //先创建好根节点
        int position = (nums.length - 1) / 2;
        TreeNode root = new TreeNode(nums[position]);
        buildBST(nums, 0, position - 1, root, true);//左子树
        buildBST(nums, position + 1, nums.length - 1, root, false);//右子树
        return root;
    }
    private void buildBST(int[] nums, int leftIndex, int rightIndex, TreeNode pre, boolean isLeft){
        if (leftIndex <= rightIndex){
            int position = (leftIndex + rightIndex) / 2;
            TreeNode newCode = new TreeNode(nums[position]);
            //当前结点接上父节点
            if (isLeft){
                pre.left = newCode;
            }else {
                pre.right = newCode;
            }
            //构建左子树
            buildBST(nums, leftIndex, position - 1, newCode, true);
            //构建右子树
            buildBST(nums, position + 1, rightIndex, newCode, false);
        }
    }
    // √ 方法二 使用参数返回的形式构建 time：2023年11月14日14:43:57 -> 2023年11月14日14:49:22
    public TreeNode sortedArrayToBST2(int[] nums) {
        return buildBST2(nums, 0, nums.length - 1);
    }
    private TreeNode buildBST2(int[] nums, int leftIndex, int rightIndex){
        if (leftIndex <= rightIndex){
            int position = (leftIndex + rightIndex) / 2;
            TreeNode root = new TreeNode(nums[position]);
            root.left = buildBST2(nums, leftIndex, position - 1);
            root.right = buildBST2(nums, position + 1, rightIndex);
            return root;
        }else {
            return null;
        }
    }

    /**
     * √（33）538. 把二叉搜索树转换为累加树 time：2023年11月14日14:55:39 -> 2023年11月14日15:22:21
     *  我的思路: 两种方法1.首先中序遍历获得所有的值，然后把每一个节点值计算出来，然后再遍历一遍赋值
     *  √ 方法2.直接进行反着的中序遍历（RTL）,这样一边遍历，一边计算总和值，然后一边赋值即可
     */
    public TreeNode convertBST(TreeNode root) {
        oppositeMidSee(root, 0);
        return root;
    }
    //逆中序(RTL)遍历获取所有得值
    private int oppositeMidSee(TreeNode root, int sum){
        if (root != null){
            int rightSum = oppositeMidSee(root.right, sum);//右
            rightSum += root.val;//中
            root.val = rightSum;
            int leftSum = oppositeMidSee(root.left, rightSum);//左
            return leftSum;
        }else {
            return sum;
        }
    }

    /**
     * √ Again 450. 删除二叉搜索树中的节点 time：2023年11月14日13:21:49 -> 2023年11月14日13:38:49
     */
    public TreeNode deleteNodeA(TreeNode root, int key) {
        if (root != null){
            if (root.val == key){
                //删除操作
                if (root.left == null && root.right == null) return null;
                if (root.left != null && root.right == null) return root.left;
                if (root.left == null && root.right != null) return root.right;
                if (root.left != null && root.right != null){
                    TreeNode leftChild = root.left;
                    TreeNode rightChild = root.right;
                    TreeNode rightLeftest = rightChild;
                    while (rightLeftest.left != null){
                        rightLeftest = rightLeftest.left;
                    }
                    rightLeftest.left = leftChild;
                    return rightChild;
                }
            }
            if (root.val < key) root.right = deleteNodeA(root.right, key);//如果在右边 则左边的子树是不需要变得
            if (root.val > key) root.left = deleteNodeA(root.left, key);//如果在左边，则右边的子树也是不需要改变的
            return root;
        }else {
            return null;
        }
    }



    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyBinaryTree myBinaryTree = new MyBinaryTree();

        //测试 二维list就算已经添加了一维list 若一维改动 二维还是会跟着改动
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2); list.add(3); list.add(4);
        result.add(list);
        System.out.println(result); // [[1, 2, 3, 4]]
        //改动一维 二维会跟着改动（就算已经添加进去）
        list.remove(3);
        System.out.println(result); // [[1, 2, 3]]

        //测试 一下list删除操作
//        List<Integer> list = new ArrayList<>();
//        list.add(1); list.add(2); list.add(3); list.add(3); list.add(3); list.add(3); list.add(3); list.add(3);
//        System.out.println(list);
//        for (int i = list.size() - 1; i >= 2; i--){
//            list.remove(i);
//        }
//        System.out.println(list);
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