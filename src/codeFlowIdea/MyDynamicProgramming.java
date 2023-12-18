package codeFlowIdea;


import java.util.Arrays;
import java.util.Scanner;

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
            for (int j = bagSize; j > 0; j--){
                if (j >= weight[i]){ // 放的下的时候
                    dp[j] = Math.max(dp[j], dp[j -weight[i]] + value[i]);
                }
                //放不下的时候 默认不变就可以了！(默认不变就相当于复制的上一层)
            }
        }
        System.out.println(dp[bagSize]);
    }




    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

        //测试 0-1 背包问题（不可切割）
//        int[] weight = {1,3,4};
//        int[] value = {15,20,30};
//        int bagSize = 4;
//        testWeightBagProblem(weight,value,bagSize);

        //测试【卡码网】 0-1 背包问题
        // Scanner 输入
        Scanner input = new Scanner(System.in);
        int length = input.nextInt();
        int bagSize = input.nextInt();
        int[] weight = new int[length];
        int[] value = new int[length];
        for (int i = 0; i < length; i++){
            weight[i] = input.nextInt();
        }
        for (int i = 0; i < length; i++){
            value[i] = input.nextInt();
        }
        takeResearch2(weight, value, bagSize);
//        System.out.println("length = " + length);
//        System.out.println("bagSize = " + bagSize);
//        System.out.println(Arrays.toString(weight));
//        System.out.println(Arrays.toString(value));
    }
}
