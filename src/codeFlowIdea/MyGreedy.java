package codeFlowIdea;

import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月17日21:33:13 ->
 * author：董政宇
 * 第九部分 贪心部分：MyGreedy
 */
public class MyGreedy {

    /**
     * （2） 455. 分发饼干 time：2023年11月17日21:36:11 -> 2023年11月17日21:56:15
     */
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0) return 0;
        //先直接对g，s进行排序【两边都从最小的开始】
        Arrays.sort(g);
        Arrays.sort(s);
        //下面使用双指针来做
        int curCake = 0; int count = 0;
        for (int i = 0; i < g.length; i++){//以孩子为根本进行遍历！因为目的是满足尽可能多的孩子
            if (curCake >= s.length) return count;
            //找到能够满足当前需求最小孩子的 最小cake
            while (s[curCake] < g[i]){
                curCake++;
                if (curCake >= s.length) return count;
            }
            //找到s了！: s[curCake] >= g[i]
            count++;
            curCake++; // cake消耗
        }
        return count;
    }

    /**
     * ×【整个逻辑是对的，但是回溯的复杂度太高！】（3）摆动序列 time：2023年11月18日09:55:32 -> 2023年11月18日10:49:13
     *  我的思路：看到是数组中的子序列！记住子序列和子串是不一样的！前者是可以跳着取值得！
     *  【看到子序列！立马想到回溯！】错！ 这个要看问题的维度的！100、1000维度的问题，一定不要去尝试回溯！直接使用贪心或者动态规划！
     *  【就当练习一下回溯的思想了！后面做题！一定超过20分钟看不出错误就去看题解！不需要死缠烂打浪费时间！】
     */
    List<Integer> wigglePath = new ArrayList<>();
    PriorityQueue<Integer> pathLength = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    public int wiggleMaxLength0(int[] nums) {
        wiggleBackTracking(nums, 0, 0);
        return pathLength.peek();
    }
    private void wiggleBackTracking(int[] nums, int startIndex, int preDiff){
        if (startIndex >= nums.length){ // 终止条件
            pathLength.add(wigglePath.size()); // 记录当前长度退出
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            //1.首先处理path为空的情况
            if (wigglePath.size() == 0){
                wigglePath.add(nums[i]); // 1.处理结果
                wiggleBackTracking(nums, i + 1, 0); // 2.进入递归
                wigglePath.remove(wigglePath.size() - 1);
            }else if (wigglePath.size() == 1){
                int curDiff = nums[i] - wigglePath.get(0); //反正就一个 直接取出第一个
                if (curDiff == 0) {
                    pathLength.add(wigglePath.size());
                    return; // 只有两个数时，不可以相等
                }
                wigglePath.add(nums[i]); // 1.处理结果
                wiggleBackTracking(nums, i + 1, curDiff); // 2.进入递归
                wigglePath.remove(wigglePath.size() - 1); // 3.回溯
            }else { // 此时是至少两个数的
                int curDiff = nums[i] - wigglePath.get(wigglePath.size() - 1);
                if (curDiff == 0) {
                    pathLength.add(wigglePath.size()); // 记录当前长度退出
                    return;
                }
                if (preDiff > 0 && curDiff > 0) {
                    pathLength.add(wigglePath.size()); // 记录当前长度退出
                    return;
                }
                if (preDiff < 0 && curDiff < 0) {
                    pathLength.add(wigglePath.size()); // 记录当前长度退出
                    return;
                }
                //此时走到这里的，都是相反符号的
                wigglePath.add(nums[i]); // 1.处理结果
                wiggleBackTracking(nums, i + 1, curDiff); // 2.进入递归
                wigglePath.remove(wigglePath.size() - 1); // 3.回溯

            }
        }
    }
    //题解方法：贪心做法！time：2023年11月20日15:59:40 -> 2023年11月20日16:22:45
    //重要笔记：贪心的思想：那就是每一个坡度上只保留两个峰值，所有的峰值加起来
    // √ 我的思路：先对数组进行去重，连续的相同的都进行去掉，保存到list中，然后找真正的峰值，也就是比两边都大的，或者比两边都小的，最后再+2
    //此题的关键在于能否把题目抽象成波浪线、峰值！
    public int wiggleMaxLength(int[] nums) {
        //总之找真正的峰值
        List<Integer> list = new ArrayList<>();
        //对连续相等元素的去重操作
        for (int i = 0; i < nums.length; i++){
            //对连续相同元素的后面的进行去重
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
        }
        //特殊值处理
        if (list.size() == 1) return 1;
        //统计峰值
        int peakValue = 0;
        for (int i = 0; i < list.size(); i++){
            //(list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i)) < 0 这个表明i 要么是最高峰值，要么是最低峰值
            if (i != 0 && i != list.size() - 1 && (list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i)) < 0){
                peakValue++;
            }
        }
        peakValue = peakValue + 2;//只统计了中间的峰值，但其中最后还有两边的端点。
        return peakValue;
    }

    /**
     * ×【无思路！】（4） 53. 最大子数组和 time：2023年11月20日16:29:27 -> 2023年11月20日16:53:53
     */
    // √ 题解方法 time：2023年11月20日17:47:35 -> 2023年11月20日18:02:21
    //主要笔记：算法确实很简单，但是这一题确实难！很难想
    public int maxSubArray(int[] nums) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i =0; i < nums.length; i++){
            curSum += nums[i];
            maxSum = Math.max(curSum, maxSum);
            if (curSum <= 0) curSum = 0; //如果curSum比0还小，那就重新计算count！重新计算其位置
        }
        return maxSum;
    }






    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyGreedy myGreedy = new MyGreedy();
        //测试 摆动序列

    }
}
