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
     * （3） 70. 爬楼梯 time：
     */
    public int climbStairs(int n) {
        return 0;
    }



    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

    }
}
