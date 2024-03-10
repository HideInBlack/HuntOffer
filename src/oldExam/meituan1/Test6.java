package oldExam.meituan1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第6 题 time：2024年3月8日20:51:32
 */
public class Test6 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        long[] nums = new long[num];

        long sum = 0;
        for (int i = 0; i < num; i++){
            nums[i] = input.nextInt();
            sum += nums[i];
        }

        int x = input.nextInt();
        int y = input.nextInt();

        if (x > y){
            int temp = x;
            x = y;
            y = temp;
        }

//        System.out.println(Arrays.toString(nums));

        //寻找当前的最短路径
        long newSum = 0;
        for (int i = x - 1; i <= y - 2; i++){
            newSum += nums[i];
        }
        long result = Math.min(newSum, sum - newSum);
        System.out.println(result);
    }
}
