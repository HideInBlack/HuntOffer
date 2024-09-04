package examination.jd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * jd 第二题 time：2024年8月31日10:51:50 ->
 */
public class Test2 {

    public static void main(String[] args) {
        // 1.输入
        Map<Long, Integer> map = new HashMap<>();
        Scanner input = new Scanner(System.in);
        int count = input.nextInt();
        long[] nums = new long[count];
        for (int i = 0; i < count; i++){
            nums[i] = input.nextInt();
            map.put(nums[i], i);
        }

        // 2.开始处理
        // 先进行排序
        Arrays.sort(nums);
        int result = 0;
        for (int i = 0; i < nums.length; i++){
            int index = map.get(nums[i]);
            int diff = Math.abs(index - i);
            if (diff % 2 != 0){
                result++;
            }
        }
        System.out.println(result / 2);
    }
}
