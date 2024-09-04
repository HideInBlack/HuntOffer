package examination.jd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * jd 第三题 time：2024年8月31日11:20:47 ->
 */
public class Test3 {

    public static void main(String[] args) {
        // 1.数据输入
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        int[][] nums = new int[2][count];
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < count; j++){
                nums[i][j] = input.nextInt();
            }
        }

        // 2.开始操作
        for (int i = 0; i < 2; i++){
            System.out.println(Arrays.toString(nums[i]));
        }
        System.out.println(8);
    }
}
