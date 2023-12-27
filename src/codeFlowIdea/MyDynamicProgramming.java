package codeFlowIdea;


import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月24日11:58:41
 * author：董政宇
 * 第十部分 动态规划部分：MyDynamicProgramming
 */
public class MyDynamicProgramming {

    /**
     * √（2） 509. 斐波那契数 time：2023年11月24日14:31:20 -> 2023年11月24日14:37:05
     * 我的思路：直接使用递归完成
     * 下面要尝试使用 dynamic programming
     */
    public int fib(int n) {
        if (n > 1){
            return fib(n - 1) + fib(n- 2);
        }else if (n == 1){
            return 1;
        }else {
            return 0;
        }
    }
    /**
     * √ 方法二【dynamic programming】（2） 509. 斐波那契数
     * 动态规划五部曲
     * 1.确定dp数组的含义：是第i个斐波那契数
     * 2.确定状态转移公式：Fn = Fn-1 + Fn-2 (当前等于前两个之和)
     * 3.确定dp数组如何初始化：dp[0]=0; dp[1]=1;
     * 4.确定遍历顺序：这里是从前往后
     * 5.举例推到dp数组，验证dp公式正确性 0,1,1,2,3,5,8,13,21,34,55...
     */
    public int fib2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        //1.确定dp数组
        int[] dp = new int[n + 1];
        //2.初始化
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    // √ 方法三【无需保存整个数组 只需保存两个】（2）斐波那契
    public int fib3(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        //1.确定dp数组，并直接进行初始化
        int a = 0; int b = 1;
        //2.状态转移推理结果
        int temp = 0;
        for (int i = 2; i <= n; i++){
            //1.先临时保存dp[0],防止被覆盖
            temp = b;
            //2.数组内容往后移动
            b = a + b;
            a = temp;
        }
        return b;
    }

    /**
     * √ 回归算法 dp 尝试 time：2023年12月14日16:21:04 -> 2023年12月14日16:38:00
     * 1.确定dp数组的含义
     * 2.确定dp数组中的状态转移方程
     * 3.确定dp数组如何初始化
     * 4.确定dp数组的遍历顺序
     * 5.举例验证dp数组的正确性
     */
    public int fibSuccess(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        //dp数组的含义：就是每一个斐波那契数
        int[] dp = new int[n + 1];
        //dp数组的初始化
        dp[0] = 0; dp[1] = 1;
        //状态转移方程 来推到dp数组
        for (int i = 2; i < dp.length; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * ×（3） 70. 爬楼梯 time：2023年12月14日16:56:02 -> 2023年12月14日17:10:51
     * 我的思路：感觉是可以尝试一下回溯来做的？
     * 结果：【21 / 45 个通过的测试用例】回溯会超时 因为回溯的量级在0-20内，超出这个量级一般都会超时！就需要想办法使用贪心或者dp了！
     */
    int sumLength = 0;
    int NumResolution = 0;
    public int climbStairs(int n) {
        backTracking(n);
        return NumResolution;
    }
    private void backTracking(int n){
        if (sumLength == n){ // 终止条件
            NumResolution++;
            return;
        }
        if (sumLength > n) return;
        //下面其实就是相当于for循环里面的：
        sumLength += 1;
        backTracking(n); // 进入递归
        sumLength -= 1; // 回溯

        sumLength += 2;
        backTracking(n); // 进入递归
        sumLength -= 2; // 回溯

    }
    // √（自己的）方法二 （3）70. 爬楼梯 time：2023年12月14日17:13:17 -> 2023年12月14日17:26:57
    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1; dp[2] = 2;
        for(int i = 3; i < dp.length; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     *  √（4） 746. 使用最小花费爬楼梯 time：2023年12月14日19:46:42 -> 2023年12月14日20:08:08
     * 1.确定dp数组定义是什么
     * 2.确定状态转移方程
     * 3.dp数组初始化
     * 4.遍历顺序
     * 5.举例子验证
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 2) return Math.min(cost[0], cost[1]); // 特殊情况

        //dp数组定义为：到达此处的最小花费
        int[] dp = new int[cost.length];
        dp[0] = cost[0]; dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++){
            if (i == cost.length - 1){ // 此题的dp数组的最后一个位置 要特殊判断处理
                dp[i] = Math.min(dp[i - 1], dp[i - 2] + cost[i]);
            }else {
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            }
        }
        return dp[cost.length - 1];
    }

    /**
     * √（6） 62. 不同路径 time：2023年12月14日20:43:59 -> 2023年12月14日21:02:55
     * 1.确定dp数组的定义
     * 2.确定状态转移方程：dp[][] = 左 + 右（其中如果在边界外就+0即可） //dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
     * 3.初始化
     * 4.比遍历顺序：二维矩阵从头往后遍历
     * 5.举例验证
     */
    public int uniquePaths(int m, int n) {
        int[][] dp =new int[m][n];
        dp[0][0] = 1; // dp数组的初始化操作
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0 && j == 0) continue;
                if (i - 1 < 0){
                    dp[i][j] = dp[i][j - 1];
                }else if (j - 1 < 0){
                    dp[i][j] = dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * √（7） 63. 不同路径 II time： 2023年12月14日21:09:50 -> 2023年12月14日21:26:05
     * 1.确定dp数组的含义：到达当前路径有多少不同的路径
     * 2.确定dp数组的状态转移方程：dp[i][j] = dp[i - 1][j] + dp[i][j - 1];【若任何一方有障碍物则设置为0即可】
     * 3.dp数组的初始化：dp[0][0] = 1
     * 4.遍历顺序：一行一行的遍历
     * 5.举例验证打印dp数组：。。。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //读懂题目！特殊处理！
        if (obstacleGrid[0][0] == 1) return 0;

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        dp[0][0] = 1; // dp数组的初始化
        for (int i = 0; i < obstacleGrid.length; i++){
            for (int j = 0; j < obstacleGrid[0].length; j++){
                if (i == 0 && j == 0) {
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i - 1 < 0){
                    dp[i][j] = dp[i][j - 1];
                } else if (j - 1 < 0){
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    /**
     *  ×【12 / 50 个通过的测试用例】（8） 343. 整数拆分 time：2023年12月17日15:57:13 -> 2023年12月17日16:28:52
     */
    public int integerBreak(int n) {
        int key = (int) Math.sqrt(n);
        int num =  n / key;
        int last = n % key;
        if (last == 0){
            if (key == 1) return n - 1;
            return (int) Math.pow(key, num);
        }else if (last == 1){
            return (int) Math.pow(key, num - 1) * (key + 1);
        }else {
            return (int) Math.pow(key, num) * last;
        }
    }
    // √ （8） 题解方法：动态规划 time：2023年12月17日19:31:26 -> 2023年12月17日19:50:03
    // 1.dp[i]定义为：分解数字i，可以得到最大乘积dp[i] 2.状态转移方程：dp[i] = max(dp[i], max((i-j) * j, dp[i-j] * j)) // j是每一个拆分点 从1开始！
    //细节1：j到i/2就停止了，因为再大也不满足相乘获得最大乘积了【小技巧】
    public int integerBreak2(int n) {
        int[] dp = new int[n +1];
        dp[0] = 0; dp[1] = 1; dp[2] = 1;
        for (int i = 3; i < n + 1; i++){
            for (int j = 1; j <= i / 2; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));// 要么分2个，要么分2个以上：一生2,2生万物！
            }
        }
        return dp[n];
    }

    /**
     * ×（9） 96. 不同的二叉搜索树 time：2023年12月17日20:01:38 -> 2023年12月17日20:24:07
     * 想不出来状态转移方程！
     * 总结：其实就是不停的拆分拆分，和上一题很像，就是把一大问题，分解成两个子问题！ 整数拆分是每次至少拆分成2个，然后需要多做一步判断；而二叉树的拆分是左右从0-i-1拆分【左0右i-1、左i-1右0】
     */
    // 题解方法：递归
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n + 1; i++){
            for (int j = 0; j < i; j++){
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    /**
     * √ 0-1 背包问题（不可切割） time：2023年12月17日22:20:36 -> 2023年12月17日22:45:13
     * 1.dp数组的定义：二维数组 i行是第i个物品， j列是背包的容量为j，dp[i][j]是当前位置的最大价值
     * 2.情况① 背包放不下 不放：dp[i][j] = dp[i - 1][j] 等于上一个物品此时的最大价值 情况② 放得下： dp[i][j]=Math.max(dp[i - 1][j], dp[i -1][j - weight[i]] + value[i]) 在放与不放之间选择最大值
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){
        //1.定义dp数组
        int[][] dp = new int[weight.length][bagSize + 1];
        //2.dp数组的初始化(最左边默认就位0，无需初始化)
//        for (int i = 0; i < weight.length; i++){
//            //最左边的一列初始化为0
//            dp[i][0] = 0;
//        }
        for (int j = 0; j <= bagSize; j++){
            //最上面的第一行初始化
            if (j >= weight[0]){//如果容量大于等于第一个物品则 加上价值
                dp[0][j] = value[0];
            }
        }
        //3.根据状态转移公式 开始推理
        for (int i = 1; i < weight.length; i++){ // 因为第一行、第一列已被初始化，所以从第二行第二列开始推导
            for (int j = 1; j <= bagSize; j++){ // 横着遍历
                if (weight[i] > j){ // 若当前物品比容量大 则不放:dp[i][j] = dp[i - 1][j]
                    dp[i][j] = dp[i - 1][j];
                }else { // <= 若可以放，则腾出来位置放进去；最后取放与不放的最大价值 dp[i][j]=Math.max(dp[i - 1][j], dp[i -1][j - weight[i]] + value[i]) 在放与不放之间选择最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        // 4.打印dp数组
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    /**
     * √（11） 0-1 背包问题 【卡码网】 46. 携带研究材料（第六期模拟笔试） time：2023年12月18日11:20:47 -> 2023年12月18日11:50:00
     * 1.首先确定dp数组的定义：dp[i][j] i是第i个物品 j是背包的容量为j！ dp[i][j]为当前j放到i（当然这里决定着放与不放）的最大价值
     * 2.状态转移方程： ①放不下： dp[i][j]=dp[i-1][j] ②放得下(取放和不放之间的最大值)：dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i])
     * 3.dp数组的初始化操作，首先最上面一行要初始化，j大于物品0重量的，初始化为其重量
     */
    public static void takeResearch(int[] weight, int[] value, int bagSize){
        //定义dp数组
        int[][] dp = new int[weight.length][bagSize + 1];
        //dp数组的初始化
        for (int i = 0; i <= bagSize; i++){
            if (i >= weight[0]) dp[0][i] = value[0];// 这里一定要记得有等于号！
        }
        //dp数组的推导
        for (int i = 1; i < weight.length; i++){ // 需要记住0-1背包 行列都从1开始遍历
            for (int j = 1; j <= bagSize; j++){
                if (weight[i] > j){ // 1.放不下
                    dp[i][j] = dp[i - 1][j];
                }else { // 2.放得下
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }
        System.out.println(dp[weight.length - 1][bagSize]);
    }
    // √ 方法二 【一维数组】0-1背包问题 time：2023年12月18日12:16:23 -> 2023年12月18日12:28:46
    // 采用一维数组 也就是滑动数组 因为当前物品的状态总是可以取决于上一个物品的状态 所以可以使用一个数组就可以了！
    // 1.dp数组定义为：j容量背包的容纳的最大价值
    // 2.需要注意的是j的遍历顺序要从后往前！
    // 3.此时就需要从i = 0； j = 0；开始遍历！
    public static void takeResearch2(int[] weight, int[] value, int bagSize){
        //1.定义dp数组
        int[] dp = new int[bagSize + 1];
        //2.默认初始化为0
        //3.开始推导dp数组
        for (int i = 0; i < weight.length; i++){
            for (int j = bagSize; j >= 0; j--){
                if (j >= weight[i]){ // 放的下的时候
                    dp[j] = Math.max(dp[j], dp[j -weight[i]] + value[i]);
                }
                //放不下的时候 默认不变就可以了！(默认不变就相当于复制的上一层)
            }
        }
        System.out.println(dp[bagSize]);
    }

    /**
     * √（13）416. 分割等和子集 time：2023年12月18日12:42:18 -> 2023年12月18日13:17:18
     * 我的思路：首先两个子集相等肯定就是可以平分的！然后设置包的阈值为其sum/2，求其最大价值 如果等于sum/2 那就可以！
     * 独立做对！！！ 牛！
     * 重要笔记：此题的难点在于想到使用动态规划、想到使用0-1背包！
     */
    public boolean canPartition(int[] nums) {
        //1.先求和
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        //2.设置阈值为 sum / 2；
        if (sum % 2 != 0) return false; // 不可以平分的 直接失败
        int bagSize = sum / 2;

        //3.下面使用0-1背包计算其最大价值是否可以达到sum/2！
        //dp数组的定义：dp[j]就是容量j的最大价值【这里的容量和价值是一样的！！！】
        int[] dp = new int[bagSize + 1];
        //默认已初始化为0
        //dp数组的推导
        for (int i = 0; i < nums.length; i++){
            for (int j = bagSize; j > 0; j--){
                if (j >= nums[i]){ // 放得下！
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                    //在这里进行判断！
                    if (dp[j] == bagSize) return true;
                }
                //放不下 一维数组默认不变！
            }
        }
        return false;

    }

    /**
     * ×【超出时间限制 46 / 163 个通过的测试用例】（13.1）拓展 698. 划分为k个相等的子集 time：2023年12月19日14:29:54 -> 2023年12月19日15:19:44
     * 我的思路：由于len(nums) <= 16 所以此题目 可以使用暴力求解法 也就是回溯！因为回溯的极限范围就是【0-20】
     * 回溯法: 单纯的优化不够 导致超时！ 而代码没有问题！
     */
    int curPath = 0;
    Set<Integer> set = new HashSet<>(); // 使用哈希set保证访问过的位置不再访问
    boolean resultK = false;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        System.out.println(target);
        backTrackingK(nums, target, 0, k);
        return resultK;
    }
    private void backTrackingK(int[] nums, int target, int curValue, int k){
        if (set.size() == nums.length){ // 终止条件
            if (curPath == k) resultK = true;
            return;
        }
        if (resultK) return; // 找到一个就可以 避免多余的计算

        for (int i = 0; i < nums.length; i++){
            if (!set.contains(i)){ // 不包含的位置 直接开始操作
                set.add(i);
                int temp = curValue;
                temp += nums[i]; // 计算当前值

                if (temp == target) {
                    curPath++;
                    backTrackingK(nums, target, 0, k); // 进入递归
                    curPath--; // 回溯
                    set.remove(i); // 回溯
                }else if (temp < target){
                    backTrackingK(nums, target, temp, k); // 进入递归
                    set.remove(i); // 回溯
                }else  { // temp > target
                    set.remove(i); // 回溯
                    return;
                }
            }
        }
    }

    /**
     * ×（14）1049. 最后一块石头的重量 II time：2023年12月19日15:56:23 -> 2023年12月19日16:20:49
     * √ 题解方法：这一题不要微观细节上去分析，太绕了，要从宏观上出发：直接分成两堆（最相近的两堆！因为他们互相撞最后肯定剩下的就是两个大堆之间的差距）
     * √ 所以可以使用0-1背包来解决！ 0-1背包无敌！
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++){
            sum += stones[i];
        }
        int target = sum / 2;
        // dp数组的定义：dp[j] 就是容量为j的目标下的可分配最大堆重量
        int[] dp = new int[target + 1];
        // dp数组无需初始化 默认初始化为0
        // 开始推导dp数组
        for (int i = 0; i < stones.length; i++){
            for (int j = target; j >= 0; j--){
                if (j >= stones[i]){ //只有放得下的时候 才进行操作，否则直接是保存为原来的
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }
        }
        return sum - 2 * dp[target];
    }

    /**
     * ×【无思路】（16） 494. 目标和 time：2023年12月19日16:43:25 -> 2023年12月19日16:56:20
     * 题解方法：此题和上一题一样的思路！居然没想到 也是从宏观上去分析问题！+-的意思其实就是分成两个堆（或者叫做两个组合），然后left-right= target!
     * 然后在判断完所有的元素之后，最后dp数组有满足条件的数组就是最后的结果
     * time：2023年12月19日17:09:34 -> 2023年12月19日17:30:58
     * 重要笔记：组合问题的状态转移方程！！！ dp[j] = dp[j] + dp[j - nums[i]];
     * dp[j] 表示：填满j（包括j）这么大容积的包，有dp[j]种方法
     * dp[j]，j 为5， 已经有一个1（nums[i]） 的话，有 dp[4]种新方法 凑成 容量为5的背包。【dp[4]是新方法 所以要加上原来的方法总和】
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if ((sum + target) % 2 != 0) return 0;
        if (Math.abs(target) > sum) return 0;
        int size = (sum + target) / 2;

        //dp数组的定义 填满容量j的背包 一共有多少种方法 有dp[j]中方法
        int[] dp = new int[size + 1];
        //dp数组初始化 此时需要把dp[0]=1 因为填满0背包，有1中方法
        dp[0] = 1;
        //开始推导dp数组
        for (int i = 0; i < nums.length; i++){
            for (int j = size; j >= 0; j--){
                if (j >= nums[i]){ //1.如果放得下 那就在原来方法数量的基础上加上新增加的方法数量
                    dp[j] = dp[j] + dp[j - nums[i]]; //dp[j - nums[i]]是新方法数量 再加上原来的！
                }
                //2.如果放不下 那就总方法数量不变呀！因为没受影响！
            }
        }
        return dp[size];
    }

    /**
     * √ （17） 474. 一和零 time：2023年12月19日20:09:43 -> 2023年12月19日21:08:07
     * 我的思路：动态规划 0-1背包 涉及最大值问题可以使用0-1背包：因为要求子集中最多m个0、n个1 这就是背包的容量
     * dp[i][j]定义为：所遍历的当前集合中满足i个0、j个1的子集个数
     * 状态转移方程： dp[i][j] = Math.max(dp[i][j], dp[i - num0][j - num1] + 1);
     * 初始化：dp[0][0] = 1
     * 本质：其实就是组合问题的2个维度的0-1背包
     */
    public int findMaxForm(String[] strs, int m, int n) {
        //定义dp数组
        int[][] dp = new int[m + 1][n + 1];
        //初始化 默认为0就可以
        //推导dp数组
        for (int x = 0; x < strs.length; x++){
            int num0 = count(strs[x], '0');
            int num1 = count(strs[x], '1');

            for (int i = m; i >= 0; i--){
                for (int j = n; j >= 0; j--){
                    if (i >= num0 && j >= num1){ //满足条件的 加入计算
                        dp[i][j] = Math.max(dp[i][j], dp[i - num0][j - num1] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }
    private int count(String string, char target){
        char[] characters = string.toCharArray();
        int count = 0;
        for (int i = 0; i < characters.length; i++){
            if (characters[i] == target) count++;
        }
        return count;
    }

    /**
     * √（18）完全背包问题 【卡码网】 52. 携带研究材料（第七期模拟笔试） time：2023年12月19日21:36:26 -> 2023年12月19日21:55:49
     * 完全背包问题与0-1背包只有一个地方不同：j的遍历循序 完全背包的遍历为从小到大！
     */
    public static void testCompletePack(int[] weight, int[] value, int bagSize){
        //1.dp数组的定义: dp[j]为容量j的背包所携带的最大价值
        int[] dp = new int[bagSize + 1];
        //2.dp数组的初始化：默认为0 无需初始化
        //3.dp数组的推导
        for (int i = 0; i < weight.length; i++){
            for (int j = 0; j <= bagSize; j++){
                if (j >= weight[i]){ // 1.放得下额度时候
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
                //2.放不下的时候默认不变
            }
        }
        System.out.println(dp[bagSize]);
    }

    /**
     * √（19）518. 零钱兑换 II time：2023年12月19日21:58:39 -> 2023年12月19日22:15:33
     * 我的思路：这应该是完全背包的组合问题！ 问有多少个组合数？而不是能否组合成功
     * dp[j] 定义为 凑成j面值的硬币，共有j种方法
     * 若dp[j], amount=5. 若已经有1，则需要凑齐剩下的4 有dp[4]种方法
     * 因此状态转移方程为：dp[j] = dp[j] + dp[j - coins[i]];
     */
    public int change(int amount, int[] coins) {
        // dp[j] 定义为 凑成j面值的硬币，共有j种方法
        int[] dp = new int[amount + 1];
        //初始化
        dp[0] = 1; //凑成0总共有1种
        //开始推理dp数组
        for (int i = 0; i < coins.length; i++){
            for (int j = 0; j <= amount; j++){
                if (j >= coins[i]){ // 1.放得下：
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
                // 2.放不下 那就总数量不变
            }
        }
        return dp[amount];
    }

    /**
     * √（21） 377. 组合总和 Ⅳ time：2023年12月19日22:26:22 -> 2023年12月19日22:34:08
     * 名字叫着是组合 但实际上解决的确是排列的问题！因为【请注意，顺序不同的序列被视作不同的组合。】
     * 重要笔记：解决排列问题 （由组合->排列）只需要调换一下物品、容量的遍历顺序即可（先容量j->物品i）
     */
    public int combinationSum4(int[] nums, int target) {
        //dp定义：dp[j]为 和为j的所有组合总个数
        int[] dp = new int[target + 1];
        //初始化
        dp[0] = 1;
        //开始推导dp数组
        for (int j = 0; j <= target; j++){
            for (int i = 0; i < nums.length; i++){
                    if (j >= nums[i]){ //1.放得下
                        dp[j] = dp[j] + dp[j - nums[i]];
                    }
                    //2.放不下
            }
        }
        return dp[target];
    }

    /***
     * √（22）【卡码网】 57. 爬楼梯（第八期模拟笔试） time：2023年12月19日22:42:18 -> 2023年12月19日22:59:57
     * 我的思路：首先这是一个组合问题还是排列问题：排列！
     *  物品：1-m  容量是：n 其次是可以重复选择，所以此题目是完全背包问题
     */
    public static void climbStairs(int n, int m){
        //dp数组的定义：爬到j共有dp[j]种方法
        int[] dp = new int[n + 1];
        //求排列问题，所以初始化要=1
        dp[0] = 1;
        //开始推导
        for (int j = 0; j <= n; j++){ // 因为是排列问题， 所以要先遍历j 容量
            for (int i = 1; i <= m; i++){ // 因为是完全背包问题，所以这里要从前往后遍历
                if (j >= i){
                    dp[j] = dp[j] + dp[j - i];
                }
            }
        }
        System.out.println(dp[n]);
    }

    /**
     * √ 试一下使用完全背包来解决经典的爬楼梯 time：2023年12月20日11:02:15 -> 2023年12月20日11:13:43
     * 挑战使用完全背包来解决爬楼梯问题
     */
    public int climbStairs9(int n) {
        //dp数组的定义：dp[j]为爬到j层，所需要的dp[j]种方法
        int[] dp = new int[n + 1];
        //dp初始化
        dp[0] = 1;
        //开始推导dp数组
        for (int j = 0; j <= n; j++){ //因为是完全背包问题！所以这里需要改成0->n！（0-1背包到完全背包 只需要改这里！）
            for (int i = 1; i <= 2; i++){ // 又由于是求排列问题，所以要先遍历背包容量j 再遍历物品i
                if (j >= i){
                    dp[j] = dp[j] + dp[j - i];
                }
            }
        }
        return dp[n];
    }

    /**
     * √（23） 322. 零钱兑换 time：2023年12月20日11:48:38 -> 2023年12月20日12:33:30
     * 我的思路：首先是完全背包问题、其次是组合问题！而不是排列问题！最后，其是求最大价值问题，而不是求有多少种方法
     */
    public int coinChange(int[] coins, int amount) {
        //1.dp数组的定义：dp[j]为凑成总金额j所需的最少硬币数
        int[] dp = new int[amount + 1];
        //2.dp数组的初始化 因为这里比较的是取最小值 所以这里要初始化其他的为max
        for (int j = 1; j <= amount; j++){
            dp[j] = Integer.MAX_VALUE;
        }
        //3.dp数组的推导
        for (int i = 0; i < coins.length; i++){
            for (int j = 0; j <= amount; j++){ //完全背包问题！所以j需要从0->amount
                if (j >= coins[i] && dp[j - coins[i]] != Integer.MAX_VALUE){ //dp[j - coins[i]]为初始值就跳过
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }
    //首先试试不用动态规划来做 感觉可以！只需要使用贪心每次选最大的就可以了！time：2023年12月20日11:53:32 -> 2023年12月20日12:06:13
    //【解答错误 51 / 189 个通过的测试用例】因为这样不一定可以凑成真正的总金额！所以还是要使用动态规划
    public int coinChange2(int[] coins, int amount) {
        Arrays.sort(coins);
        int nums = 0;
        int curAmount = amount;
        for (int i = coins.length - 1; i >= 0; i--){
            if (coins[i] <= curAmount){ // 小的才表明可以使用
                nums += curAmount / coins[i];
                curAmount = curAmount % coins[i];

            }
            if (curAmount == 0) return nums;
        }
        return -1;
    }

    /**
     * （24） 279. 完全平方数 time：2023年12月20日13:57:36 -> 2023年12月20日14:17:33
     * 我的思路：最小数量 其实就是求可以放满背包的最少数量 和上一题的零钱对换很像
     */
    public int numSquares(int n) {
        int length = (int)Math.sqrt(n) + 1;
        //dp数组的定义：dp[j]凑齐j所需要的最小平方数数量
        int[] dp = new int[n + 1];
        //dp数组的初始化
        dp[0] = 0;
        for (int j = 1; j < n + 1; j++){ //其他>0的都初始化为最大值即可 （因为求得是最小数量）
            dp[j] = Integer.MAX_VALUE;
        }
        //开始推导dp数组
        for (int i = 0; i <= length; i++){
            for (int j = 0; j <= n; j++) { //因为可以重复使用，所以是完全背包 所以 0->n
                if (j >= i * i && dp[j - i*i] != Integer.MAX_VALUE){ //放得下
                    dp[j] = Math.min(dp[j], dp[j - i*i] + 1);//dp[j]是不放的数量、 dp[j - i*i] + 1是放下的数量 取其最小值
                }

            }
        }
        return dp[n];
    }
    //也就可以是下面这种！
//    public int numSquares(int n) {
//        int length = (int)Math.sqrt(n) + 1;
//        //dp数组的定义：dp[j]凑齐j所需要的最小平方数数量
//        int[] dp = new int[n + 1];
//        //dp数组的初始化
//        dp[0] = 0;
//        for (int j = 1; j < n + 1; j++){ //其他>0的都初始化为最大值即可 （因为求得是最小数量）
//            dp[j] = 10001; //这里有变化！
//        }
//        //开始推导dp数组
//        for (int i = 0; i <= length; i++){
//            for (int j = 0; j <= n; j++) { //因为可以重复使用，所以是完全背包 所以 0->n
//                if (j >= i * i){ //放得下 这里也改动啦！【不需要做多余的判断】
//                    dp[j] = Math.min(dp[j], dp[j - i*i] + 1);//dp[j]是不放的数量、 dp[j - i*i] + 1是放下的数量 取其最小值
//                }
//
//            }
//        }
//        return dp[n];
//    }

    /**
     * ×【无思路】（26）139. 单词拆分 time：2023年12月20日14:28:57 -> 2023年12月20日14:45:21
     * 我的思路：1.重复使用所以是完全背包问题，j是0->n
     * √ 题解方法：dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。***
     * 此方法比较绕！建议看下一个！正宗的j代表背包容量，i代表物品的背包解题思路！
     * 关键：如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。
     */
    public boolean wordBreak(String s, List<String> wordDict) {//这里的j确实是背包容量，但这里的i却不是物品的意思了！

        //dp数组
        boolean[] dp = new boolean[s.length() + 1];
        //初始化
        dp[0] = true;//1:true   0:false
        //推导dp数组
        for (int j = 1; j <= s.length(); j++){ //完全背包【先0 -> length】
            for (int i = 0; i < j ; i++){ //排列问题【先j -> i】
                if (j > i && !dp[j]){
                    String target = s.substring(i, j); //腾出来位置 取出多于的字符串【左闭右开】
                    if (dp[i] && wordDict.contains(target)){
                        dp[j] = true;
                    }
                }
            }
        }
        return dp[s.length()];
    }
    // √ 正宗的j代表背包容量，i代表物品的背包解题思路！
    public boolean wordBreak2(String s, List<String> wordDict) {
        //dp数组
        boolean[] dp = new boolean[s.length() + 1];
        //初始化
        dp[0] = true;//1:true   0:false
        //推导dp数组
        for (int j = 1; j <= s.length(); j++){ //完全背包【先0 -> length】
            for (int i = 0; i < wordDict.size() ; i++){ //排列问题【先j -> i】
                String word = wordDict.get(i);
                if (j >= word.length()){ //1.放得下的！
                    if (dp[j - word.length()] && word.equals(s.substring(j - word.length(), j))){
                        dp[j] = true;
                        break; //这里加break的意思代表着，字典根本不需要遍历完 因为只要为true就可以？？？
                    }
                    //2.放不下 不变
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * ×【无思路】（29） 198. 打家劫舍 time：2023年12月20日19:11:06 -> -- 2023年12月20日19:45:39
     * 我的思路：首先不是背包问题，因为没有bagSize要求
     * √ 题解方法：影响dp[i]的只有dp[i-1]和dp[i-2]，1.如果偷i，那就是要考虑dp[i-2]; 2.如果不偷i，那就考虑dp[i-1]，并且直接相等dp[i-1]
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        //1.dp数组的定义：dp[i]表明偷到i时的最高金额
        int[] dp = new int[nums.length];
        //2.dp数组的初始化操作：关键到只需要初始化前两个即可
        dp[0] = nums[0];//偷到0家，那最高金额一定是nums[0]
        dp[1] = Math.max(nums[0], nums[1]); //偷到1家，由于其两个挨着，所以一定是取他们两个里面的最大的！
        //3.开始推导dp数组
        for (int i = 2; i < nums.length; i++){
            //在偷与不偷之间选择最大的！
            dp[i] = Math.max(dp[i -1], dp[i - 2] + nums[i]); //1.不偷：dp[i -1] 2.偷：dp[i - 2] + nums[i]
        }
        return dp[nums.length - 1];
    }

    /**
     * ×【无思路】（30） 213. 打家劫舍 II time：2023年12月20日20:26:41 -> 2023年12月20日20:40:38
     *  我的思路：在上面的打家劫舍上稍微改动
     *  √ 题解方法：考虑只含首元素、考虑只含尾元素 然后取其最大值
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        //操作只含有首元素的
        int onlyStart = robHome(nums, 0, nums.length - 2);
        int onlyEnd = robHome(nums, 1, nums.length - 1);
        return Math.max(onlyStart, onlyEnd);

    }
    private int robHome(int[] nums, int startIndex, int endIndex){
        if (endIndex - startIndex == 0) return nums[startIndex];
        if (endIndex - startIndex == 1) return Math.max(nums[startIndex], nums[startIndex + 1]);

        //定义dp数组
        int[] dp = new int[nums.length]; //注意dp数组还是和nums一样长！只需要最后取值的时候 取对值就可以了
        //初始化dp数组
        dp[startIndex] = nums[startIndex];
        dp[startIndex + 1] = Math.max(nums[startIndex], nums[startIndex + 1]);
        //推导dp数组
        for (int i = startIndex + 2; i <= endIndex; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[endIndex];
    }

    /**
     * ×【无思路】（31） 337. 打家劫舍 III time：2023年12月21日11:02:52 -> 2023年12月21日11:18:27
     * √ 题解方法：树形dp：此题一定要使用后序遍历（LRT）因为要根据左右孩子返回值做进一步判断 其他的就是正常的dp推导了
     * dp[j]定义：仅仅是一个长度为2的数组，首先dp[0]是不偷当前结点的最高金额、dp[1]是偷当前结点的最高金额
     * 永远考虑的都只有当前结点的 偷/不偷 两种情况！time：2023年12月21日11:49:36 -> 2023年12月21日12:09:48
     */
    public int rob(TreeNode root) {
        int[] resultDp = treeDp(root);
        return Math.max(resultDp[0], resultDp[1]);
    }
    //一定要是后序遍历 LRT
    private int[] treeDp(TreeNode root){ //函数返回值为当前结点的偷和不偷的dp数组！
        if (root != null){
            //截止到j 首先dp[0]是不偷当前结点的最高金额、dp[1]是偷当前结点的最高金额
            int[] left = treeDp(root.left); //左
            int[] right = treeDp(root.right); //右

            int[] dp = new int[2]; //中
            //先是不偷当前节点！那就考虑偷左孩子、右孩子的！但注意是考虑偷而不是一定偷！
            dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            //再是偷当前结点！那左右孩子就一定不能偷！
            dp[1] = root.val + left[0] + right[0];
            return dp;

        }else {
            return new int[]{0, 0}; //空节点偷不偷都是0
        }
    }

    /**
     * ×【无思路】（32）121. 买卖股票的最佳时机 time：2023年12月21日12:17:56 -> 2023年12月21日12:46:05
     * √ 题解方法：我觉得动态规划的方法过去牵强了 就使用贪心的方法就可以！
     */
    // √ 题解方法:动态规划 只讨论当前这一天到底持不持有此股票；所得的最多现金
    public int maxProfit(int[] prices) {
        //我觉得不适合动态规划的方法 ！
        //dp[i][0] 表示第i天持有股票所得最多现金（一开始现金是0，买了之后为负的）；dp[i][1] 表示第i天不持有股票所得最多现金；
        int[][] dp = new int[prices.length][2];
        //初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        //开始推导dp数组
        for (int i = 1; i < prices.length; i++){
            //当天i持有此股票的最多现金: 本来就持有、本来没有(当天才买)
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);// 这里一定是-prices[i]，因为只能买一次 所以买的时候是没有前的！为0！
            //当天i不持有此股票的最多现金：本来就没有、今天才卖！
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }
    // √ dp无思路 试一试贪心 思路：在遍历整个一圈价格时候，始终保存左边的最小值和当前的最大差距，遍历完一遍之后找到最优值
    //重要笔记：此方法更巧妙：在遍历的同时更新左边的最小值以及更新最大差距！！ 贪心思想
    public int maxProfit2(int[] prices) {
        int leftMin = Integer.MAX_VALUE;
        int curMaxDiff = 0;
        for (int i = 0; i < prices.length; i++){
            leftMin = Math.min(leftMin, prices[i]); //始终在遍历的同时更新左边的最小值
            curMaxDiff = Math.max(curMaxDiff, prices[i] - leftMin); //始终在遍历的同时更新最大差距
        }
        return curMaxDiff;
    }

    /**
     * √ （34） 122. 买卖股票的最佳时机 II time：-> 2023年12月21日14:15:29
     * 动态规划：与上一题的股票一样！但是仍然有更简单的方法就是使用贪心算法！
     */
    public int maxProfit3(int[] prices) {
        //我觉得不适合动态规划的方法 ！
        //dp[i][0] 表示第i天持有股票所得最多现金（一开始现金是0，买了之后为负的）；dp[i][1] 表示第i天不持有股票所得最多现金；
        int[][] dp = new int[prices.length][2];
        //初始化
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        //开始推导dp数组
        for (int i = 1; i < prices.length; i++){
            //当天i持有此股票的最多现金: 本来就持有、本来没有(当天才买)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);// 这里就是 dp[i - 1][1] - prices[i]：因为买的时候有之前的利润！这也是能一直获利的原因！
            //当天i不持有此股票的最多现金：本来就没有、今天才卖！
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

    /**
     * ×【错误！这样不能同时满足maxDiff1 + maxDiff2是最大的！】（35） 123. 买卖股票的最佳时机 III time：2023年12月21日14:17:36 -> 2023年12月21日14:47:09
     * 我的思路：尝试尝试贪心算法 先找第一个！找到第一个之后初始化第二个 然后找第二个！如果期间第一个变化了 立刻初始化第二个！
     */
    //错的思路！
    public int maxProfit4(int[] prices) {
        int leftMin1 = Integer.MAX_VALUE;
        int maxDiff1 = 0;
        int leftMin2 = Integer.MAX_VALUE;
        int maxDiff2 = 0;
        for (int i = 0; i < prices.length; i++){
            //找到新的左边最小值1
            leftMin1 = Math.min(prices[i], leftMin1);
            //找到新的左边最小值2
            leftMin2 = Math.min(prices[i], leftMin2);
            //这是找2的 最大间距值
            maxDiff2 = Math.max(prices[i] - leftMin2, maxDiff2);
            //这是找1的
            if (prices[i] - leftMin1 > maxDiff1){ //找到新的最大间距值
                maxDiff1 = prices[i] - leftMin1;
                //找到新1，重置2
                leftMin2 = Integer.MAX_VALUE;
                maxDiff2 = 0;
            }
        }
        System.out.println(maxDiff1);
        System.out.println(maxDiff2);
        return maxDiff1 + maxDiff2;
    }
    //√ 题解方法：动态规划 四种核心状态 time：2023年12月21日14:47:09 -> 2023年12月21日15:08:05
    public int maxProfit5(int[] prices) {
        //dp[i][0] 表示第i天第一次持有股票所得最多现金（一开始现金是0，买了之后为负的）；dp[i][1] 表示第i天第一次不持有股票所得最多现金；
        //dp[i][2] 表示第i天第二次持有股票所得最多现金（一开始现金是0，买了之后为负的）；dp[i][3] 表示第i天第二次不持有股票所得最多现金；
        int[][] dp = new int[prices.length][4];
        //初始化
        dp[0][0] = -prices[0];//买了
        dp[0][2] = -prices[0];//买了 卖了 又买了

        //开始推导dp数组
        for (int i = 1; i < prices.length; i++){
            //第一次
            //当天i持有此股票的最多现金: 本来就持有、本来没有(当天才买)
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);// 这里就是 dp[i - 1][1] - prices[i]：因为买的时候有之前的利润！这也是能一直获利的原因！
            //当天i不持有此股票的最多现金：本来就没有、今天才卖！
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);

            //第二次
            //当天i持有此股票的最多现金: 本来就持有、本来没有(当天才买)
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);// 这里就是 dp[i - 1][1] - prices[i]：因为买的时候有之前的利润！这也是能一直获利的原因！
            //当天i不持有此股票的最多现金：本来就没有、今天才卖！
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);

            //可以看到特别重要的一点是：每一个状态总是和上面的一个状态紧密相关！！！
        }
        return dp[prices.length - 1][3];
    }

    /**
     * √ again一遍! 买卖股票一题足以！ 买卖股票的最佳时机 II  time：2023年12月26日15:21:34 -> 2023年12月26日21:53:34
     * 1.dp数组的定义：dp[i][0]、dp[i][1] 其中dp[i][0]代表不持有股票的最大剩余现金 dp[i][1]代表持有股票的最大剩余现金
     * 2.dp数组的推导：dp[i][0/1] 都是有前一天的dp[i-1][0/1]推导而来， 都是由前一天的最大值
     *  dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]) // 1.i-1也不持有 2.i-1持有（那就是当天才卖）
     *  dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]) // 1.i-1不持有（当天才买） 2.i-1持有
     * 3.初始化：dp[0][0]=0; dp[0][1]=-prices[0];
     */
    public int maxProfitAgain(int[] prices) {
        //dp定义
        int[][] dp = new int[prices.length][2];
        //初始化
        dp[0][0] = 0; dp[0][1] = -prices[0];
        //开始推导
        for (int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); // 1.i-1也不持有 2.i-1持有（那就是当天才卖）
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]); // 1.i-1不持有（当天才买） 2.i-1持有
        }
        return dp[prices.length - 1][0];
    }

    /**
     * √ again一遍! 买卖股票的最佳时机 III time：2023年12月26日21:53:57 -> 2023年12月26日22:10:13
     * 1.dp数组的定义：dp[i][0]、dp[i][1] 其中dp[i][0]代表第一次持有股票的最大剩余现金 dp[i][1]代表第一次不持有股票的最大剩余现金
     *               dp[i][2]、dp[i][3] 其中dp[i][2]代表第二次持有股票的最大剩余现金 dp[i][3]代表第二次不持有股票的最大剩余现金
     * 其中的交易顺序其实就是 0 -> 1 -> 2 -> 3 【最后是不持有 就是卖掉之后！】
     * 2.dp数组的推导：dp[i][0/1/2/3] 都是有前一天的dp[i-1][0/1/2/3]推导而来， 都是由前一天的最大值
     *   i天第一次持有 dp[i][0] = Math.max(dp[i - 1][0], 0 - prices[i]) // 1.i-1天第一次也持有 2.i-1天第一次不持有（当天买）
     * i天第一次不持有 dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]) // 1.i-1也不持有 2.i-1天持有（当天卖）
     *   i天第二次持有 dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]) // 1.i-1第二次也持有 2.i-1第二次不持有（当天买）
     * i天第二次不持有 dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]) // 1.i-1第二次也不持有， 2.i-1第二次持有（当天卖）
     *
     *3.初始化： dp[0][0] = -prices[0]; dp[0][2] = -prices[0];
     */
    public int maxProfitAgain0(int[] prices) {
        //dp定义
        int[][] dp = new int[prices.length][4];
        //初始化
        dp[0][0] = -prices[0]; dp[0][2] = -prices[0];
        //开始推导
        for (int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], 0 - prices[i]); // 1.i-1天第一次也持有 2.i-1天第一次不持有（当天买）
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]); // 1.i-1也不持有 2.i-1天持有（当天卖）
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]); // 1.i-1第二次也持有 2.i-1第二次不持有（当天买）
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]); // 1.i-1第二次也不持有， 2.i-1第二次持有（当天卖）
        }
        return dp[prices.length - 1][3];
    }

    /**
     * √（36） 188. 买卖股票的最佳时机 IV time：2023年12月26日22:11:43 -> 2023年12月26日22:26:14
     * 我的思路：上一题是2次，此题是k次！只需要对照着2次的题目找规律写出来就可以！所以要先写只可以交易2次的！
     * 15分钟直接搞定！ 完全一样的思路！所以一定要搞会买卖股票的第3题！
     */
    public int maxProfit1226(int k, int[] prices) {
        //dp定义
        int[][] dp = new int[prices.length][2*k];
        //初始化
        for (int i = 0; i < 2 * k; i = i + 2){
            dp[0][i] = -prices[0]; //dp[0][0] = -prices[0]; dp[0][2] = -prices[0];
        }
        //开始推导
        for (int i = 1; i < prices.length; i++){
            for (int j = 0; j < 2 * k; j = j + 2){
                if (j == 0){
                    dp[i][j] = Math.max(dp[i - 1][j], 0 - prices[i]); // 1.i-1天第一次也持有 2.i-1天第一次不持有（当天买）
                    dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]); // 1.i-1也不持有 2.i-1天持有（当天卖）
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]); // 1.i-1第二次也持有 2.i-1第二次不持有（当天买）
                    dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] + prices[i]); // 1.i-1第二次也不持有， 2.i-1第二次持有（当天卖）
                }

            }
        }
        return dp[prices.length - 1][2*k - 1];
    }

    /**
     * √ 题解方法：（37）309. 买卖股票的最佳时机含冷冻期 time：2023年12月27日10:45:55 -> 2023年12月27日11:17:44
     * 重要笔记：与2相比，其实就是多了一个冷冻期，需要把不持有股票的状态再细分一下【一定要关注状态转移啊！看看是怎么进行状态转移的！】
     * 定义：
         * 状态0：股票持有状态（无需分类，因为随时都可以卖出股票的） 【持有】
         * 状态1: 2天前就已卖出（随时可以买入）  【不持有】
         * 状态2：前一天才卖出，今天是冷冻期    【不持有】
         * 状态3：今天才卖出，下一天则是冷冻期  【不持有】
     * 状态转移：
     * 状态0：dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i], dp[i - 1][2] - prices[i]); // 1.本来就是持有状态， 2.今天刚买入 3.紧挨着冷冻期后买入
     * 状态1：dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]); // 1.本来就可以买 2.刚过了冷冻期，才可以买
     * 状态2：dp[i][2] = dp[i - 1][3]; //前一天才卖出
     * 状态3：dp[i][3] = dp[i - 1][0] + prices[i]; //今天才卖出
     */
    public int maxProfitCold(int[] prices) {
        //dp定义
        int[][] dp = new int[prices.length][4];
        //dp初始化
        dp[0][0] = -prices[0]; dp[0][1] = 0; dp[0][2] = 0; dp[0][3] = 0;
        //开始推导
        for (int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1] - prices[i], dp[i - 1][2] - prices[i])); // 1.本来就是持有状态， 2.今天刚买入 3.紧挨着冷冻期后买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2]); // 1.本来就可以买 2.刚过了冷冻期，才可以买
            dp[i][2] = dp[i - 1][3]; //冷冻期和前一天不变
            dp[i][3] = dp[i - 1][0] + prices[i]; //今天才卖出
        }
        return Math.max(dp[prices.length - 1][1], Math.max(dp[prices.length - 1][2], dp[prices.length - 1][3]));
    }

    /**
     * （39） 714. 买卖股票的最佳时机含手续费 time： 2023年12月27日11:44:41 -> 2023年12月27日11:51:44
     */
    public int maxProfitFee(int[] prices, int fee) {
        //dp[i][0] 表示第i天持有股票所得最多现金；dp[i][1] 表示第i天不持有股票所得最多现金；
        int[][] dp = new int[prices.length][2];
        //初始化
        dp[0][0] = -prices[0];
        //开始推导dp数组
        for (int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);//1.本来就持有   2.今天刚买
            //这里是唯一改动的地方！！！ - fee 在每次卖出股票的时候，减去手续费就可以了啊！很简单！
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);//1.本来就不持有 2.今天刚卖
        }
        return dp[prices.length - 1][1];
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyDynamicProgramming myDynamicProgramming = new MyDynamicProgramming();

        int[] prices = {3,3,5,0,0,3,1,4};
        myDynamicProgramming.maxProfit4(prices);

//        System.out.println("0123456".substring(2, 8));
//        System.out.println("123456".contains("1235"));
//        System.out.println("123456123".replaceFirst("123", ""));

//        System.out.println(Math.pow(5, 3));
//        System.out.println((int)Math.sqrt(9));
//        int[] coins = {1};
//        System.out.println(myDynamicProgramming.coinChange2(coins, 0));

        //测试【卡码网】爬楼梯
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        int m = input.nextInt();
//        climbStairs(n, m);

//        System.out.println(myDynamicProgramming.count("0001", '1'));

        //测试【卡码网】完全背包问题
//        Scanner input = new Scanner(System.in);
//        int length = input.nextInt();
//        int bagSize = input.nextInt();
//        int[] weight = new int[length];
//        int[] value = new int[length];
//        for (int i = 0; i < length; i++){
//            weight[i] = input.nextInt();
//            value[i] = input.nextInt();
//        }
//        System.out.println(Arrays.toString(weight));
//        System.out.println(Arrays.toString(value));
//        testCompletePack(weight, value, bagSize);


        //测试（16）
//        int[] nums = {1,1,1,1,1};
//        myDynamicProgramming.findTargetSumWays(nums, 3);

        //测试 （13.1）
//        int[] nums = {18,20,39,73,96,99,101,111,114,190,207,295,471,649,700,1037};
//        System.out.println(myDynamicProgramming.canPartitionKSubsets(nums, 4));

//        int[] nums = {1,5,11,5};
//        System.out.println(myDynamicProgramming.canPartition(nums));

        //测试 0-1 背包问题（不可切割）
//        int[] weight = {1,3,4};
//        int[] value = {15,20,30};
//        int bagSize = 4;
//        testWeightBagProblem(weight,value,bagSize);

        //测试【卡码网】 0-1 背包问题
        // Scanner 输入
//        Scanner input = new Scanner(System.in);
//        int length = input.nextInt();
//        int bagSize = input.nextInt();
//        int[] weight = new int[length];
//        int[] value = new int[length];
//        for (int i = 0; i < length; i++){
//            weight[i] = input.nextInt();
//        }
//        for (int i = 0; i < length; i++){
//            value[i] = input.nextInt();
//        }
//        takeResearch2(weight, value, bagSize);
//        System.out.println("length = " + length);
//        System.out.println("bagSize = " + bagSize);
//        System.out.println(Arrays.toString(weight));
//        System.out.println(Arrays.toString(value));

    }
}
