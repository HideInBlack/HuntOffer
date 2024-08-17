package codeFlowIdea;

import dataStructure.Singleton;

import java.util.*;

/**
 * 实习前的算法复习 2024年3月1日11:10:02
 * author：董政宇
 */
public class Review {

    // √ 209. 长度最小的子数组 （双指针） time：2024年3月1日11:10:43 -> 2024年3月1日11:36:02
    public int minSubArrayLen(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int currentValue = 0;
        for (int i = 0; i < nums.length; i++){
            currentValue +=  nums[i];

            //只有满足条件的时候 才左移和计算最小长度
            if (currentValue >= target){
                while (currentValue >= target){
                    //移动left
                    currentValue -= nums[left];
                    left++;
                }
                minLength = Math.min(minLength, i - left + 2);
            }

        }

        if (minLength == Integer.MAX_VALUE) return 0;
        return minLength;
    }

    // × 82. 【理解错误，这是去重！】删除排序链表中的重复元素 II （链表） time：2024年3月1日14:48:13 -> 2024年3月1日15:03:26
    public ListNode deleteDuplicates2(ListNode head) {
        //使用双指针
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null){//cur == null 的时候 就是走到了最后一个的next
            if (pre.val != cur.val){ // 1.不重复 继续往下走
                pre = cur;
                cur = cur.next;
            }else { // 2.重复 直接删除
                cur = cur.next;
                pre.next = cur;
            }
        }
        return head;
    }

    // √ 82. 删除排序链表中的重复元素 II （链表） time： 2024年3月1日15:05:01 -> 2024年3月1日15:36:28
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }
        //使用哨兵结点
        ListNode fake = new ListNode();
        fake.next = head;
        ListNode pre = fake;
        ListNode q = fake.next;
        while (q != null && q.next != null){
            if (q.val != q.next.val){ //不重复
                pre = q;
                q = q.next;
            }else {//重复，一直找到不重复的
                while (q.next != null && q.val == q.next.val){
                    q = q.next;
                }
                //找到后面第一个不重复的了 删除
                q = q.next;

                pre.next = q;//删除

            }
        }
        return fake.next;
    }

    // √ 92. 反转链表 II time: 2024年3月1日19:24:38 -> 2024年3月1日19:54:24
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode leftNode;
        ListNode cur;

        //先找到pre的位置，然后后续才可以一直进行头插法
        for (int i = 1; i < left; i++){
            pre = pre.next;
        }
        leftNode = pre.next;
        //一直往后头插right-left次
        for (int i = 0; i < right -left; i++){
            //开始头插操作
            cur = leftNode.next;
            leftNode.next = cur.next;//先把cur删除
            cur.next = pre.next; //头插 接入
            pre.next = cur;
        }
        return fakeHead.next;

    }

    // √ 206. 反转链表 time：2024年3月1日19:59:49 -> 2024年3月1日20:08:38
    //一定要画图理解！什么方便！
    public ListNode reverseList(ListNode head){
        if (head == null) return null;
        ListNode fake = new ListNode();
        fake.next = head;
        ListNode left = fake.next;
        ListNode cur;
        while (left.next != null){
            cur = left.next;
            left.next = cur.next;
            cur.next = fake.next;
            fake.next = cur;
        }
        return fake.next;
    }

    // √ 49. 字母异位词分组 time: 2024年3月2日10:51:19 -> 2024年3月2日11:16:43
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++){

            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);

            //有就取出来，没有就创建新的！
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(strs[i]);
            map.put(key, list);
        }

        List<List<String>> result = new ArrayList<>(map.values());
        return result;
    }

    // 1.两数之和 重要：key：放数值value ， value：放对应的下标
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i])){
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    // 18. 四数之和 time：2024年3月3日10:22:41 -> 2024.03.03 10:46
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //先排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        //由于是四数之和 所以两层for循环 + 双指针
        for (int i = 0; i < nums.length; i++){
            //对第一层遍历去重
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }

            for (int j = i + 1; j < nums.length; j++){
                //对第二层遍历去重
                if (j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }

                //开始双指针(j右边模块里的 一个在最左边 一个在最右边)
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right){
                    //1.先计算总和
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    //2.在比较大小
                    if (sum < target){
                        left++;
                    }else if (sum > target){
                        right--;
                    }else {// 3.相等的时候 就去重
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        while (left < right && nums[left] == nums[left + 1]){
                            left++;// 对left降重
                        }
                        while (left < right && nums[right] == nums[right - 1]){
                            right--;// 对right去重
                        }
                        left++;
                        right--;
                    }

                }

            }
        }
        return result;

    }

    // 239. 滑动窗口最大值 time：2024年3月4日10:48:40 -> 2024年3月4日11:19:57
    //此题是滑动窗口 + 单调队列 ：因为使用到单调队列 所以需要动态维护单调队列（如何维护呢？保证其单调就可以了！入的时候，出的时候！）
    public int[] maxSlidingWindow(int[] nums, int k) {
        //滑动窗口，肯定在滑动的时候就会出一个 入一个
        Deque<Integer> deque = new LinkedList<>();
        //先初始化单调队列
        for (int i = 0; i < k; i++){
            //逐个入队即可
            if (deque.isEmpty()){//1.为空
                deque.add(nums[i]);
            }else if (nums[i] < deque.getLast()){ //2.不为空,已经单调
                deque.add(nums[i]);
            }else {// 3.不为空，也不单调，需要保持单调
                while (!deque.isEmpty() && nums[i] > deque.getLast()){ //当不为空，并且还比入队头大的时候，就出队
                    deque.pollLast();
                }
                //满足条件了 开始入队
                deque.add(nums[i]);
            }
        }
        //初始化完成，下面进行遍历操作
        int[] result = new int[nums.length - k + 1];
        result[0] = deque.getFirst();
        for (int i = k; i < nums.length; i++){
            //1.先出队
            if (deque.getFirst() == nums[i - k]) deque.pollFirst();

            //2.再入队
            if (deque.isEmpty()){//1.为空
                deque.add(nums[i]);
            }else if (nums[i] < deque.getLast()){ //2.不为空,已经单调
                deque.add(nums[i]);
            }else {// 3.不为空，也不单调，需要保持单调
                while (!deque.isEmpty() && nums[i] > deque.getLast()){ //当不为空，并且还比入队头大的时候，就出队
                    deque.pollLast();
                }
                //满足条件了 开始入队
                deque.add(nums[i]);
            }

            //3.入队完成后，取出最大值
            result[i - k + 1] = deque.getFirst();
        }
        return result;
    }

    // √ 692. 前K个高频单词 time：2024年3月4日13:23:30 -> 2024年3月4日13:39:16
    public List<String> topKFrequent(String[] words, int k) {
        //先使用map统计个数
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++){
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        //使用优先权队列
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Objects.equals(map.get(o2), map.get(o1))){
                    return o1.compareTo(o2); //如果相等就按照字典循序 排序
                }else {
                    return map.get(o2) - map.get(o1);// 如果不等 那就降序
                }

            }
        });
        //全部入队
        for ( Map.Entry<String, Integer> entry : map.entrySet()){
            queue.add(entry.getKey());
        }
        //取出前k个
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= k; i++){
            result.add(queue.poll());
        }
        return result;
    }

    // 144. 二叉树的前序遍历 TLR 递归练习 time：2024年3月6日10:45:38 -> 2024年3月6日10:52:56
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        backTracking(list, root);
        return list;
    }
    public void backTracking(List<Integer> list, TreeNode root){
        if (root != null){
            list.add(root.val);
            backTracking(list, root.left);
            backTracking(list, root.right);
        }
    }

    // 102. 二叉树的层序遍历 time：2024年3月6日10:54:00 -> 2024年3月6日11:04:11
    public List<List<Integer>> levelOrder(TreeNode root) {


        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root); //先入队根节点
        while (!deque.isEmpty()){
            //需要记下来刚刚上一层 有多少个 然后全部出队（也就是这一层的个数）
            int length = deque.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < length; i++){
                TreeNode cur = deque.poll();
                list.add(cur.val);
                if (cur.left != null) deque.add(cur.left);
                if (cur.right != null) deque.add(cur.right);
            }

            result.add(list);
        }
        return result;

    }

    // 236. 二叉树的最近公共祖先（一定要使用回溯，所以一定是使用后序遍历LRT） time：2024年3月6日13:02:28 -> 2024年3月6日13:17:20
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 首先判断是不是p q null 如果是就直接返回来，不需要再往下判断
        if (root == p || root == q || root == null) return root;

        //使用后序遍历 天然的回溯 才能走到最下面然后返回，其实所以整个思路就是要先走到最下面然后进行回溯往上返回
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null){
            //1.左右孩子都不为null，那此时root就是公共祖先
            return root;
        }else if (left == null && right != null){
            //2.谁不为null，就返回谁，因为right此时就是返回上来的公共祖先
            return right;
        } else if (left != null && right == null) {
            //3.谁不为null，就返回谁
            return left;
        }else {
            //4.都为null
            return null;
        }

    }

    /**
     * 40. 组合总和 II time：2024年3月12日14:15:35 -> 2024年3月12日14:41:56
     */
    private List<List<Integer>> cbResult = new ArrayList<>();
    private List<Integer> cbPath = new ArrayList<>();
    private int cbSum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //建议先进行一手排序
        Arrays.sort(candidates);

        cbBackTracking(candidates, 0, target);
        return cbResult;
    }

    private void cbBackTracking(int[] candidates, int startIndex, int target){

        if (cbSum >= target){ // 1.递归终止条件
            if (cbSum == target){
                cbResult.add(new ArrayList<>(cbPath));
            }
            return;
        }

        //从 startIndex开始
        for (int i = startIndex; i < candidates.length; i++){
            if (i != startIndex && candidates[i] == candidates[i - 1]){
                continue; // 去掉重复解
            }

            //加入结果
            cbSum += candidates[i];
            cbPath.add(candidates[i]);

            //递归
            cbBackTracking(candidates,  i + 1, target);

            //回溯
            cbSum -= candidates[i];
            cbPath.remove(cbPath.size() - 1);

        }
    }

    // 491. 非递减子序列 time：2024年3月12日16:02:51 -> 2024年3月12日16:43:08
    private List<List<Integer>> fsResult = new ArrayList<>();
    private List<Integer> fsPath = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        fsBackTracking(nums, 0);
        return fsResult;
    }
    private void fsBackTracking(int[] nums, int startIndex){
        if (startIndex > nums.length){
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++){
            if (set.contains(nums[i])){ // 非有序数组的去重
                continue;
            }

            //判断
            if (fsPath.isEmpty()){ //1.为空的情况
                fsPath.add(nums[i]);
                set.add(nums[i]);
            }else { //2.不为空的情况
                if (fsPath.get(fsPath.size() - 1) <= nums[i]){ //满足递增
                    //如果>= 成立则加入路径
                    fsPath.add(nums[i]);
                    set.add(nums[i]);
                    fsResult.add(new ArrayList<>(fsPath));
                }else { //不满足递增
                    continue;
                }
            }

            //进入递归
            fsBackTracking(nums, i + 1);
            fsPath.remove(fsPath.size() - 1);//回溯

        }
    }

    // 47. 全排列 II time：2024年3月12日17:43:16 -> 2024年3月12日17:55:03
    private List<List<Integer>> puResult = new ArrayList<>();
    private List<Integer> puPath = new ArrayList<>();
    private Set<Integer> set = new HashSet<>();// 存储路径的下标的

    public List<List<Integer>> permuteUnique(int[] nums) {
        puBackTracking(nums);
        return puResult;
    }
    private void puBackTracking(int[] nums){
        if (puPath.size() == nums.length){//1.回溯终止条件
            puResult.add(new ArrayList<>(puPath));
            return;
        }

        //使用set进行path上去重，也就是在同一层去重
        Set<Integer> setFor = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            //全排列去重，已访问的节点，下一层不能再访问了
            //也就是path里内部去重
            if (set.contains(i)){
                continue;
            }
            if (setFor.contains(nums[i])){
                continue;
            }

            puPath.add(nums[i]);//操作
            set.add(i);
            setFor.add(nums[i]); //同层之间的去重 set是不需要回溯的！！！

            puBackTracking(nums);//递归

            puPath.remove(puPath.size() - 1);//回溯
            set.remove(i);
        }
    }

    // 376. 摆动序列 time：2024年3月13日10:27:13 -> 2024年3月13日10:37:42
    // 此题一定要先去重存储到list中，再单独实现好实现！
    public int wiggleMaxLength(int[] nums) {
        // 先使用list进行去重
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            if (i != 0 && nums[i] == nums[i - 1]){
                // 对nums进行去重，去重之后 整体各自单调
                continue;
            }
            list.add(nums[i]);
        }
        int count = 0;
        // 再次遍历整个链表 寻找谷顶、谷底、两端 则就是最长子序列的长度
        for (int i = 0; i < list.size(); i++){
            //两端点 不用判断直接加入长度 并跳过
            if(i == 0 || i == list.size() -1){ //两端
                count++;
                continue;
            }
            if ((list.get(i) > list.get(i - 1) && list.get(i) > list.get(i + 1)) || //谷顶
                    (list.get(i) < list.get(i - 1) && list.get(i) < list.get(i + 1))){ //谷底
                count++;
            }
        }
        return count;
    }

    // 【195 / 210 个通过的测试用例】最大子数组和 time：2024年3月13日10:50:04 -> 2024年3月13日11:13:39
    // 此题使用前缀和 感觉也无敌的呀！【不行！有错误！】
    // 可以做确实是可以做！但是有缺陷！
    public int maxSubArray(int[] nums) {
        int[] numsSum = new int[nums.length];
        int maxSum = Integer.MIN_VALUE;
        int minPre = Integer.MAX_VALUE;

        //求前缀和里的最大值和最小值
        int preSum = 0;
        for (int i = 0; i < nums.length; i++){
            preSum += nums[i];
            numsSum[i] = preSum;
            minPre = Math.min(minPre, preSum);
            //讨论
            if (minPre < 0){
                maxSum = Math.max(maxSum, preSum - minPre);
            }else {
                maxSum = Math.max(maxSum, preSum);
            }
        }

        //最后 前缀和里的最大值-最小值 则一定是连续子数组的最大和！巧妙！无敌！
        return maxSum;

    }

    //贪心：time：2024年3月13日13:45:05 -> 2024年3月13日14:01:09
    //贪心思想：每次取到最大子数组和<0的时候立马放弃，重新计算
    public int maxSubArray2(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < nums.length; i++){
            curSum += nums[i];
            //加完之后 先直接进行一次判断
            maxSum = Math.max(maxSum, curSum);
            //贪心思想：如果curSum<0,就直接丢弃从0开始
            if (curSum <= 0) curSum = 0;
        }
        return maxSum;
    }

    //贪心：55. 跳跃游戏 time：2024年3月13日14:01:22 -> 2024年3月13日14:31:37
    public boolean canJump(int[] nums) {
        //贪心的思想：不要掉入陷阱，不要跟着跳着遍历
        //而是从0开始，一个个的往前遍历，每往前走一个就比较一次，剩余步数和当前值步数，取最大的
        int curNums = nums[0];
        for (int i = 0; i < nums.length; i++){
            if (i == 0){
                curNums = nums[i];//第一步特殊情况 不需要跳
            }else {
                curNums--;
                if (curNums < 0) return false;// 要立刻进行判断！ 是不是能跳过来！<0是不可以的！但是=0是可以！
                curNums = Math.max(curNums, nums[i]);

            }

        }
        return true;
    }

    //贪心：45. 跳跃游戏 II time：2024年3月13日14:31:53 -> 2024年3月13日15:03:19
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        //使用当前最大跳跃范围 和 下一步最大跳跃范围
        int curMax = nums[0]; //当前最大跳跃范围
        int nextMax = Integer.MIN_VALUE; //下一步最大跳跃范围
        int count = 0; //统计最小步数
        for (int i = 0; i < nums.length; i++){
            nextMax = Math.max(nextMax, nums[i] + i);

            //如果走到最后一个直接进行步数加一返回即可！
            if (i == nums.length - 1){
                count++;
                return count;
            }

            //当走到最大范围的时候，就必须要跳了，不得不跳！
            if (i == curMax){
                //如果当前i超过当前最大跳跃范围 则必须要+1步,并且更新当前最大范围
                count++;
                curMax = nextMax;
            }
        }
        return count;
    }

    //复习 dp 509. 斐波那契数 time：2024年3月13日15:03:34 -> 2024年3月13日15:09:48
    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        //1.dp数组的定义：dp[i]就是F(i)的斐波那契数
        int[] dp = new int[n + 1];
        //2.初始化
        dp[0] = 0;
        dp[1] = 1;

        //3.状态转移方程 dp[i]=dp[i-1]+dp[i-2];
        for (int i = 2; i < dp.length; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // 70. 爬楼梯 time：2024年3月13日15:12:19 -> 2024年3月13日15:20:51
    public int climbStairs(int n) {
        //1.dp数组的定义：dp[i]表示爬到i层，一共有dp[i]种方法
        int[] dp = new int[n + 1];
        //2.初始化
        dp[0] = 1;
        dp[1] = 1;
        //3.状态转移方程 dp[i] = dp[i - 1] + dp[i - 2]
        for (int i = 2; i < dp.length; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    //dp 746. 使用最小花费爬楼梯 time：2024年3月13日15:28:07 ->
    public int minCostClimbingStairs(int[] cost) {
        //1.dp数组的定义：dp[i]为到达i层的最低花费;
        int[] dp = new int[cost.length + 1];

        //2.dp数组初始化
        dp[0] = 0;
        dp[1] = 0;

        //3.状态转移方程：dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        //4.确定遍历顺序
        for (int i = 2; i < dp.length; i++){
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    //dp 416. 分割等和子集 time：2024年3月13日21:47:40 -> 2024年3月13日22:11:42
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum % 2 == 1) return false;
        int bagSize = sum / 2;

        //1.dp数组的定义：dp[i]为容量为i的集合，选择整个数组中的任意元素，集合中可以装的下的(不超过背包总和的)总和的最大值【如果最大值就=bagSize 表示可以分成两个相等的】
        int[] dp = new int[bagSize + 1];
        //2.dp状态转移方程 dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]); ①这是放的下的时候，②放不下的时候 dp[j]=dp[j] 因为我们倒序遍历就不会变

        //3.dp的初始化
        dp[0] = 0;//容量为0的时候 一个都放不下！

        //4.遍历顺序 先遍历物品，这里就是数值，再遍历背包容量(容量要倒序遍历)
        for (int i = 0; i < nums.length; i++){
            for (int j = bagSize; j >= nums[i]; j--){
                //只有放的下的时候才考虑放与不放
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                //而放不下的时候，直接不用管，倒序遍历、自动复制
            }
        }
        return dp[bagSize] == bagSize;
    }





    public static void main(String[] args) {
        Review review1 = new Review();
        System.out.println("review1 = " + review1);
        Review review2 = new Review();
        System.out.println("review2 = " + review2);
        System.out.println(review1 == review2);
//        int[] nums = {1,3,-1,-3,5,3,6,7};
//        Review review = new Review();
//        int[] result  = review.maxSlidingWindow(nums, 3);
//        System.out.println(Arrays.toString(result));

//        int[] nums = {10,1,2,7,6,1,5};
//
//        String a = "A";
//        String b = "B";
//
//        System.out.println(a.compareTo(b));

        Singleton singleton1 = Singleton.getSingleton();
        System.out.println("singleton1 = " + singleton1);
        Singleton singleton2 = Singleton.getSingleton();
        System.out.println("singleton2 = " + singleton2);
        System.out.println(singleton1 == singleton2);

    }

}
