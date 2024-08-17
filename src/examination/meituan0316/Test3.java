package examination.meituan0316;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第三题 time：2024年3月16日19:36:45 ->
 */
public class Test3 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int q = input.nextInt();

        long[] nums = new long[n];
        for (int i = 0; i < nums.length; i++){
            nums[i] = input.nextLong();
        }

//        int[] Q = new int[q];
//        for (int i = 0; i < q; i++){
//            Q[input.nextInt() - 1] -= 1;
//        }


        long sum = 0;
        for (int i = 0; i < q; i++){
            int cur = input.nextInt();

            for (int j = 0; j < nums.length; j++){

                if (j == cur - 1){
                    if (i == q - 1){
                        sum += nums[j];
                    }
                    continue;
                }
                nums[j] = nums[j] * 2;
                if (i == q - 1){
                    sum += nums[j];
                }
            }
//            System.out.println(Arrays.toString(nums));

        }
        System.out.println(sum % (1000000000 + 7));

    }
}
