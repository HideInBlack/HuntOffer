package examination.xiaomi0919;

import java.util.Scanner;

/**
 * 第一题 time：2024年9月19日16:59:06 ->
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        for (int i = 0; i < n; i++){
            int size = input.nextInt();
            int count = input.nextInt();
            int c = input.nextInt();

            int[] nums = new int[count + c];

            for (int j = 0; j < count; j++){
                nums[j] = input.nextInt();
            }
            for (int k = count; k < count + c; k++){
                nums[k] = 1;
            }

            //System.out.println(Arrays.toString(nums));
            if (isSuccess(nums, size)){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
    }

    // dp解决背包问题
    public static boolean isSuccess(int[] nums, int n){
        boolean[] dp = new boolean[n + 1];
        // 默认初始化
        dp[0] = true;

        for (int num : nums){
            for (int j = n; j >= num; j--){
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[n];
    }
}
