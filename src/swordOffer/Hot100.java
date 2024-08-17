package swordOffer;

import java.util.*;

/**
 * LeetCode Hot100 题目练习 time：2024年4月10日15:46:10 ->
 */
public class Hot100 {

    //1： 1. 两数之和 time: 2024年4月10日15:48:21 -> 2024年4月10日16:00:15
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2]; //保存答案

        for (int i = 0; i < nums.length; i++){
            //进来先判断map中是否存在有 target-num[i],如果有直接返回结束
            if (map.containsKey(target - nums[i])){
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }

        return result;
    }

    //2： 49. 字母异位词分组 time：2024年4月10日19:08:38 -> 2024年4月10日19:25:07
    public List<List<String>> groupAnagrams(String[] strs) {
        //思路：使用map存储，其中key为排序后的单词，value为一个list
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++){
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            List<String> list = map.getOrDefault(sortedStr, new ArrayList<>());
            list.add(strs[i]); //存入到list
            map.put(sortedStr, list); //存入到map

        }

        return map.values().stream().toList();
    }

    //3： 128. 最长连续序列 time：2024年4月10日19:27:03 -> 2024年4月10日19:39:36
    public int longestConsecutive(int[] nums) {
        //先全都存储到set中，使用set的快速判断一个元素存不存在当前集合中
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }

        //再从set中遍历，对于每一个元素，如果其存在num-1，则表明其不是第一个，跳过，如果不存在，则开始遍历统计
        int maxLength = 0;
        for (int num : set){
            if (!set.contains(num - 1)){
                int count = 0;
                while (set.contains(num)){
                    count++;
                    num++;
                }
                //查找完毕 更新一波最大值
                maxLength = Math.max(maxLength, count);
            }
        }
        return maxLength;
    }

    //4： 283. 移动零 time：2024年4月10日19:44:15 -> 2024年4月10日19:50:34
    public void moveZeroes(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != 0){
                nums[left] = nums[i];
                left++;
            }
        }
        //left及其以后全部补0
        for (int i = left; i < nums.length; i++){
            nums[i] = 0;
        }
    }

    //5： 11. 盛最多水的容器 time：2024年4月10日19:50:50 -> 2024年4月10日20:00:56
    public int maxArea(int[] height) {
        //思路：谁小谁移动，移动过程中保存最大值（贪心的思想 谁小就移动谁）
        int maxValue = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right){
            //先直接更新一波最大值
            maxValue = Math.max(maxValue, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]){
                left++;
            }else {
                right--;
            }
        }

        return maxValue;
    }

    //6： 15. 三数之和 time：2024年4月11日15:36:35 -> 2024年4月11日16:07:13
    public List<List<Integer>> threeSum(int[] nums) {
        //整体思路：先排序 + 一层for循环 + 双指针
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums); //先对数组进行排序

        for (int i = 0; i < nums.length; i++){
            //去重
            if (i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            //双指针 把二三层for循环变成一层双指针
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                int curSum = nums[i] + nums[left] + nums[right];
                if (curSum > 0){ //1.大于0 表明要减小
                    right--;
                }else if (curSum < 0){ //2.小于0 表明要增大
                    left++;
                }else { //3.等于0 则加入到结果集中,然后同时增大减小
                    List<Integer> one = new ArrayList<>();
                    one.add(nums[i]);
                    one.add(nums[left]);
                    one.add(nums[right]);
                    result.add(one);

                    //再往中间聚拢还可能有结果呢！（left right的去重）
                    while (left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right -1]){
                        right--;
                    }
                    //上面这两步是走到最后相等的一个，然后再往下走一个就不相等了
                    left++;
                    right--;
                }

            }
        }
        return result;
    }

    //7： 42. 接雨水 time：
    public int trap(int[] height) {
        // TODO
        return 0;
    }

    //8: 3. 无重复字符的最长子串 time：2024年4月11日16:08:18 -> 2024年4月11日16:29:53
    public int lengthOfLongestSubstring(String s) {
        //核心思想：使用类似滑动窗口指针 + (动态维护set)set快速判断集合中是否有一个元素
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();

        int left = 0; //窗口的最左边指针
        int maxLength = 0;
        for (int i = 0; i < chars.length; i++){
            if (set.contains(chars[i])){ //1.如果包含就需要做更新指针和维护set操作
                //动态维护set，从left删除到当前重复的地方！
                while (set.contains(chars[i])){
                    set.remove(chars[left]);
                    left++;
                }
            }
            //2.包不包含 都需要加入到set中
            set.add(chars[i]);

            //3.每每遍历一次就更新一波最大值
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }

    //9：438. 找到字符串中所有字母异位词 time: 2024年4月11日16:31:47 -> 2024年4月11日17:00:09
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()){ // 特殊情况判断
            return new ArrayList<>();
        }
        //整体思路：使用数组存储元素个数 + 固定滑动窗口
        int[] arrayS = new int[26];
        int[] arrayP = new int[26]; //数组内存储的是元素出现的个数！

        for (int i = 0; i < p.length(); i++){
            arrayP[p.charAt(i) - 'a'] += 1;
            arrayS[s.charAt(i) - 'a'] += 1; //第一个窗口特殊处理
        }

        int right = p.length() - 1;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= s.length() - p.length(); i++){
            //i=0时，特殊处理
            if (i == 0){
                if (Arrays.equals(arrayS, arrayP)){
                    result.add(0);
                }
            }else {
                //窗口滑动，移除元素
                arrayS[s.charAt(i - 1) - 'a'] -= 1;
                //窗口滑动，加入元素
                arrayS[s.charAt(right) - 'a'] += 1;

                //窗口滑动完毕 开始判断
                if (Arrays.equals(arrayS, arrayP)){
                    result.add(i);
                }
            }
            right++;
        }
        return result;
    }

    //10：【前缀和做出，但复杂度较高】560. 和为 K 的子数组 time：2024年4月11日18:50:05 -> 2024年4月11日19:03:08
    public int subarraySum(int[] nums, int k) {
        //整体思路：前缀和（先求前缀和 + 双层for循环）
        int[] numSum = new int[nums.length + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            numSum[i + 1] = sum;
        }

        //遍历求个数
        int count = 0;
        for (int i = 0; i < numSum.length; i++){
            for (int j = i + 1; j < numSum.length; j++){
                if (numSum[j] - numSum[i] == k){
                    count++;
                }
            }
        }
        return count;
    }

    //10：题解做法 和为 K 的子数组 time：2024年4月11日19:14:04 -> 2024年4月11日19:22:35
    public int subarraySum2(int[] nums, int k) {
        //整体思路：使用hashmap保存下 前缀和:个数 的数据，然后一遍求前缀和，一边求pre-k的个数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //一定要注意初始化！前缀和为0的有一个！
        int preSum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            preSum += nums[i];
            if (map.containsKey(preSum - k)){
                //如果包含，就把数量加到count计数中去
                count += map.get(preSum - k);
            }
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);//如果有就在原数据上+1，如果没有就默认为0+1
        }
        return count;
    }

    //11：239. 滑动窗口最大值 time：2024年4月11日19:23:11（来电话啦！拿到腾讯Tencent offer啦） -> 2024年4月12日15:37:19
    public int[] maxSlidingWindow(int[] nums, int k) {
        //核心思路：单调队列 1.入队，保持单调性 2.出队，滑出去的是最大值才出队
        Deque<Integer> queue = new LinkedList<>();
        //第一个窗口的特殊处理
        for (int i = 0; i < k; i++){
            while (!queue.isEmpty() && nums[i] > queue.getLast()){
                queue.removeLast();
            }
            queue.add(nums[i]);
        }
        //开始遍历操作
        int right = k - 1;
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++){
            if (i == 0){
                //逻辑站位
            }else {
                //1.先判断窗口移出的是否需要出队
                if (queue.getFirst() == nums[i - 1]){
                    queue.removeFirst();
                }
                //2.再入队新加入到窗口中的元素
                while (!queue.isEmpty() && nums[right] > queue.getLast()){
                    queue.removeLast();
                }
                queue.add(nums[right]);
            }
            //求出当前窗口最大值(每次动态维护完 队首元素就是最大值)
            result[i] = queue.getFirst();

            //窗口滑动
            right++;
        }

        return result;
    }

    //12：76. 最小覆盖子串 time：2024年4月12日15:38:36 ->
    public String minWindow(String s, String t) {
        // TODO
        return "";
    }

    //13: 53. 最大子数组和 time：2024年4月12日15:41:51 -> 2024年4月12日15:49:57
    public int maxSubArray(int[] nums) {
        //整体思路：贪心的方法，只要当前和<0，就丢弃，直接从新开始计算
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++){
            //上来就相加，加完就求一波最大值
            sum += nums[i];
            maxValue = Math.max(maxValue, sum);

            if (sum < 0){
                sum = 0;
            }
        }
        return maxValue;
    }

    //13：另一种暴力做法(超时) 最大子数组和 time：2024年4月12日15:50:18 -> 2024年4月12日15:59:59
    public int maxSubArray2(int[] nums) {
        //整体思路：(暴力)使用前缀和的思想尝试一下
        int[] preSum = new int[nums.length + 1]; //使用前缀和这里一定要注意！要把第一个值留为0！要多一个！
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            preSum[i + 1] = sum;
        }
        //双层for循环暴力求解
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < preSum.length; i++){
            for (int j = i + 1; j < preSum.length; j++){
                maxValue = Math.max(maxValue, preSum[j] - preSum[i]);
            }
        }
        return maxValue;
    }

    //14：56. 合并区间 time：2024年4月12日16:00:58 ->
    public int[][] merge(int[][] intervals) {

        return null;
    }



    public static void main(String[] args) {
        Hot100 hot100 = new Hot100();
        int[] nums = {1, 1, 1};
        int result = hot100.subarraySum(nums, 2);
        System.out.println("result = " + result);
    }
}
