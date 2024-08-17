package acm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * NC95 数组中的最长连续子序列 time：2024年3月22日11:45:34 -> 2024年3月22日11:56:08
 */
public class NC95 {


    public static void main(String[] args) {
        int[] testNums = {100,4,200,1,3,2, 2, 4, 5};
        int count = getCount2(testNums);
        System.out.println("count = " + count);
    }

    //2.算法复杂度为O(n)的做法
    public static int getCount2(int[] nums){
        //首先都放到set中 可以快速判断一个数到底存不存在这个集合里 //从而还可以达到去重的效果
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        int maxCount = 1;
        //然后 只从起点开始算起，什么是起点呢 就是没有前一个数的数
        for (int value : set){
            //不包含value-1 表明此点是起点
            if (!set.contains(value - 1)){
                int curCount = 1;
                int curValue = value + 1;

                while (set.contains(curValue)){
                    curCount++;
                    curValue++;
                }
                //找寻完 开始比较最大值
                maxCount = Math.max(maxCount, curCount);
            }
        }
        return maxCount;
    }

    //1.算法复杂度为O(n logn)的做法
    public static int getCount(int[] nums){
        Arrays.sort(nums);
        int curCount = 0;
        int maxCount = 1;
        for (int i = 0; i < nums.length; i++){
            if (i == 0){
                curCount++;
                continue;
            }
            if (nums[i] - nums[i - 1] == 1){
                curCount++;
                maxCount = Math.max(maxCount, curCount);
            }else {
                if (nums[i] == nums[i - 1]){
                    //如果相等什么都计算
                }else {
                    curCount = 1;
                }
            }
        }
        return maxCount;
    }
}
