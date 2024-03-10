package oldExam.meituan;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * 美团秋招第三场 模拟 第一题 time：2024年3月8日13:37:44 -> 2024年3月8日14:33:31 （思路错了！）
 */
public class Test1 {
    public static void main(String[] args) {
        //解法：双指针 滑动窗口
//        int[] nums = {1 ,3, 2, 4 ,1};
//        System.out.println(getMaxLength(nums, 2));
        Scanner input = new Scanner(System.in);
        int numsLength = input.nextInt();
        long key = input.nextLong();

        long[] nums = new long[numsLength];
        for (int i = 0; i < numsLength; i++){
            nums[i] = input.nextLong();
        }
//        System.out.println(key);
        System.out.println(getMaxLength(nums, key));

    }

    //给定数组，求其平均数为k 的连续最长子数组
    public static int getMaxLength(long[] nums, long key){
        int maxLength = Integer.MIN_VALUE;
        long sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++){
            //先计算left-right的平均值 的总和！
            sum += nums[right];
            //1.相等 比较
            if (sum == key * (right - left + 1)){
                maxLength = Math.max(maxLength, right - left + 1);
            }else if (sum < key * (right - left + 1)){
                //2. 如果平均数 > key
                continue;
            }else if (sum > key * (right - left + 1)){
                //如果比它大 就一直走，直到比他小/或者等于！
                while (sum > key * (right - left + 1) && left < right){
                    sum -= nums[left];
                    left++;
                }
                if (sum == key * (right - left + 1)){
                    maxLength = Math.max(maxLength, right - left + 1);
                }
            }
        }
        if (maxLength == Integer.MIN_VALUE) return -1;
        return maxLength;
    }

}
