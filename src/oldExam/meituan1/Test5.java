package oldExam.meituan1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第五题 time：2024年3月8日20:25:12 ->
 */
public class Test5 {
    public static void main(String[] args) {
        //本质就是生成一个我找到规律的数组
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        int left = 0;
        int right = num - 1;
        int max = num;
        int min = 1;

        //开始推导生成数组
        int[] result = new int[num];

        for (int i = 0; i < result.length; i++){
            if (i % 2 == 0){
                result[i] = min++;
            }else {
                result[i] = max--;
            }
        }
//        while (true){
//            result[right--] = max--;
//            if (left > right) break;
//            result[left++] = max--;
//            if (left > right) break;
//            result[right--] = min++;
//            if (left > right) break;
//            result[left++] = min++;
//            if (left > right) break;
//        }
//        System.out.println(Arrays.toString(result));
        //打印出来

        for (int i = 0; i < result.length; i++){
            System.out.print(result[i] + " ");
        }
    }

}
