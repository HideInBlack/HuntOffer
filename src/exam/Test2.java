package exam;

import java.util.Scanner;

/**
 * 第二题 time：2024年3月9日10:09:29 ->
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int length =input.nextInt(); //1.数组长度
        int q = input.nextInt(); //2.询问次数

        long[] nums = new long[length];//数组赋值
        int countZero = 0;//统计0个数
        long sum = 0;
        for (int i = 0; i < nums.length; i++){
            nums[i] = input.nextInt();
            //统计所有值的和
            sum += nums[i];
            //统计数组中有多少个0
            if (nums[i] == 0){
                countZero++;
            }
        }

        //q次询问
        for (int i = 0; i < q; i++){
            long minValue = 0;
            long maxValue = 0;

            long left = input.nextInt();
            long right = input.nextInt();

            //开始求最大值和最小值
            minValue = sum + countZero * left;
            maxValue = sum + countZero * right;

            System.out.print(minValue + " ");
            System.out.print(maxValue);
            System.out.println();


        }


    }
}
