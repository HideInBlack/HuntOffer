package codeFlowIdea;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
     * 试一下使用完全背包来解决经典的爬楼梯
     */

    /**
     * （23） 322. 零钱兑换 time：
     */
    public int coinChange(int[] coins, int amount) {
        return 0;
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

        MyDynamicProgramming myDynamicProgramming = new MyDynamicProgramming();

        //测试【卡码网】爬楼梯
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        climbStairs(n, m);

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
