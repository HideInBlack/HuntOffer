package acm;

import java.util.Arrays;

/**
 * 快速排序练习 time：2024年3月24日15:08:24 -> 2024年3月24日15:20:55
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {5, 1, 3, 2, 6, 4, 0, 9, 3};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public static void quickSort(int[] nums, int i, int j){
        if (i < j){
            int left = i;
            int right = j;
            int keyValue = nums[left];

            while (left < right){
                //先从右边开始遍历,找第一个比其小的
                while (left < right && nums[right] > keyValue){
                    right--;
                }
                nums[left] = nums[right];
                nums[right] = keyValue;

                if (left < right){//一定要注意！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                    left++;
                }

                //从左边开始遍历，找到第一个比其大的
                while (left < right && nums[left] < keyValue){
                    left++;
                }
                nums[right] = nums[left];
                nums[left] = keyValue;

                if (left < right){//一定要注意！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                    right--;
                }
            }

            //递归再排左边和右边
            quickSort(nums, i, left - 1);
            quickSort(nums, left + 1, j);

        }

    }


}
