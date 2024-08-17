package examination.xieCheng;

import java.util.Arrays;
import java.util.Scanner;

public class Test2 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            long[] nums = new long[n];

            // 读取数组元素并计算总和
            long sum = 0;
            for (int i = 0; i < n; i++) {
                long temp = scanner.nextLong();
                nums[i] = temp;
                sum += temp;
            }

            // 计算每个元素与其他元素的绝对差值之和
            long totalSum = 0;
            for (int i = 0; i < n; i++) {
                totalSum += Math.abs(n * nums[i] - sum);
            }

            // 打印结果
            System.out.println(totalSum);
        }

}
