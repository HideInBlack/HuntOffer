package codeFlowIdea;


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
     * （8） 343. 整数拆分 time：
     */
    public int integerBreak(int n) {
        return 0;
    }



    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

    }
}
