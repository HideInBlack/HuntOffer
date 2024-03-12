package swordOffer;

import java.util.*;

/**
 * Leetcode Hot100 题目练习 time：2024年3月10日10:46:50 ->
 */
public class Hot100 {

    //1.两数之和 time：2024年3月10日10:47:23 -> 2024年3月10日10:59:49
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];// 用来保存答案

        for (int i = 0; i < nums.length; i++){

            //先进行判断有没有
            if (map.containsKey(target - nums[i])){
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                //找到了直接进行一个返回
                return result;
            }

            //如果没有，再把自己添加进去
            map.put(nums[i], i);

        }
        return result;

    }

    // 2.字母异位词分组 time：2024年3月10日10:59:53 -> 2024年3月10日11:14:01
    public List<List<String>> groupAnagrams(String[] strs) {
        //对每一个字符串排序，存到hash map里
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++){
            //先进行一手排序
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);//排序之后再转成字符串

            //有就取出来list，没有就新建一个list
            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
            list.add(strs[i]);
            map.put(sorted, list);


        }
        return map.values().stream().toList();

    }

    // 3. 最长连续序列 time：2024年3月10日11:14:41 -> 2024年3月10日11:30:20
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return 1;
        }
        Arrays.sort(nums);
        int maxLength = Integer.MIN_VALUE;
        int curLength = 1;
        for (int i = 0; i < nums.length; i++){
            if (i == 0) continue;
            if (nums[i] == nums[i - 1] + 1){
                curLength++;
            }else if (nums[i] == nums[i - 1]){
                //这里是去重，如果有相同元素
            }else {
                curLength = 1;
            }

            maxLength = Math.max(maxLength, curLength);
        }
        return maxLength;
    }

    /**
     1.定义一个HashSet来存储数组nums中的元素，这样可以快速检查一个数字是否存在于数组中。
     2.遍历nums数组，将每个数字添加到HashSet中。
     3.初始化longestStreak变量为0，用于存储最长连续序列的长度。
     4.遍历HashSet中的每个数字num，对于每个数字：
         1.如果num - 1不在HashSet中，说明num是一个连续序列的起点。【最重要的一句话！！】
         2.在一个连续序列中，从起点开始逐个增加数字，直到找到不在HashSet中的数字为止，记录连续序列的长度。
         3.更新longestStreak为当前最长连续序列的长度。
     5.返回longestStreak作为最终结果，即数组中最长连续序列的长度。
     总体来说，这段代码利用HashSet的快速查找特性，以线性时间复杂度遍历数组并找出最长连续序列的长度。
     */
    //3.1 最长连续序列 如何使用hash set解决？
    //注意：这里利用的就是 hashset 快速判断/或者找寻 一个数到底在不在一个集合里的能力！O（1）
    public int longestConsecutive1(int[] nums) {
        //先把nums中所有值加入到hashset中
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);//加入到set中可以进行快速判断！
        }

        //进行找寻判断处理
        int longestLength = 0;
        for (int num : set){ //这里坑！直接使用去重之后的set！即可！
            //当num的 num-1存在集合里的时候直接跳过，因为这就表明num不是连续序列的起点！ 无需判断
            if (!set.contains(num - 1)){
                //只有不存在num-1，这才表明它才是起点！
                int curValue = num;
                int curLength = 1;

                while (true){
                    curValue++;
                    if (set.contains(curValue)){
                        curLength++;
                    }else {
                        break;
                    }
                }

                longestLength = Math.max(longestLength, curLength);

            }
        }

        return longestLength;

    }

    // 4.移动零 time：2024年3月10日14:53:19 -> 2024年3月10日14:56:08
    public void moveZeroes(int[] nums) {
        //使用两个指针
        int zeroPosition = 0;

        for (int i = 0; i < nums.length; i++){
            //只有两种结果 要么=0，要么!=0
            if (nums[i] != 0){
                nums[zeroPosition] = nums[i];
                zeroPosition++;
            }
        }
        for (int i = zeroPosition; i < nums.length; i++){
            nums[i] = 0;
        }

    }

    // （无思路）5.盛最多水的容器 time：2024年3月10日14:56:56 -> 2024年3月10日15:21:04
    // √ 题解思路：①设置双指针在两端， 每次移动短板的指针往里走，因为短板决定着容积高度；②移动短板有可能容积会变大，但是移动长板，面积一定减小；
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int maxValue = Integer.MIN_VALUE;
        while (left < right){
            int curValue = Math.min(height[left], height[right]) * (right - left);
            maxValue = Math.max(maxValue, curValue);
            //谁小就移动谁！
            if (height[left] <= height[right]){
                left++;
            }else if (height[left] > height[right]){
                right--;
            }
        }
        return maxValue;
    }

    // √ 3. 无重复字符的最长子串 time：2024年3月10日15:25:43 -> 2024年3月10日16:10:10
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){
            return 0;
        }
        int left = 0;
        int maxLength = Integer.MIN_VALUE;

        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++){
            //查有没有重复，去重等，一等要使用set！
            set.add(chars[right]);
            //更新一手最大值
            maxLength = Math.max(maxLength, set.size());

            if ((right - left + 1) != set.size()){
                //如果大小不等，说明有重复，需要滑动left到第一个不等于当前值chars[right]的地方
                //1.移动left
                while (chars[left] != chars[right]){
                    //移动一次就要删除一次其在set里的位置
                    set.remove(chars[left]);
                    left++;
                }
                left++;
            }
        }
        return maxLength;

    }

    // 2024美团春招 真题练习-完美矩阵-二维前缀和 time：2024年3月12日10:16:05 -> 2024年3月12日11:10:40
    public void getPerfect(int[][] nums){
        //获取当前矩阵中所有 i*i子矩阵的解
        //首先求出来所有的二维前缀和【这里要加一行、加一列、才可以更好的进行计算（只需要一个公式，不需要讨论三个）】
        int[][] numsSum = new int[nums.length + 1][nums.length + 1];
        for (int i = 0; i < nums.length; i++){
            int sum = 0;
            for (int j = 0; j < nums[0].length; j++){
                sum += nums[i][j];
                numsSum[i + 1][j + 1] = sum + numsSum[i][j + 1];
            }
        }

        int[] result = new int[nums.length];
        //根据二维前缀和开始计算所有子矩阵
        for (int line = 1; line <= nums.length; line++){
            if (line == 1) continue;//剪枝优化
            //基于line*line 统计
            int count = 0;
            //其实就是使用双重for循环找每个子矩阵的左上角坐标即可！其他的右下角的自动去生成
            for (int i = 0; i < nums.length - line + 1; i++){
                for (int j = 0; j < nums[0].length - line + 1; j++){
                    //得到子矩阵是否完美的结果
                    //注意这里的所有坐标 都要 + 1！因为numSum变大了！
                    boolean prefect = isPerfect(numsSum, i + 1, j + 1, i + line, j + line);
                    if (prefect) count++;
                }
            }
            //保存结果
            result[line - 1] = count;
        }
        System.out.println(Arrays.toString(result));
        // 子矩阵总和 = 大的 - 左边 -上边 + 左上边
    }
    private boolean isPerfect(int[][] numsSum, int row1, int col1, int row2, int col2){
        //不应该用÷2；应该用*2
        int perfect = (row2 - row1 + 1) * (col2 - col1 + 1);
        //只有子矩阵的和等于预期面积的一半 则就是完美矩阵
        return (numsSum[row2][col2] - numsSum[row2][col1 - 1] - numsSum[row1 - 1][col2] + numsSum[row1 - 1][col1 - 1]) * 2 == perfect;
    }

    // 438. 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s.length() < p.length()){
            return list;
        }

        int[] arrayP = new int[26];//26个英文字母！
        int[] arrayS = new int[26];

        for (int i = 0; i < p.length(); i++){
            arrayP[p.charAt(i) - 'a']++;//默认为0，统计个数
            arrayS[s.charAt(i) - 'a']++;
        }
        //开始判断
        for (int i = 0; i <= s.length() - p.length(); i++){
            if (Arrays.equals(arrayP, arrayS)){
                list.add(i);
            }
            if (i != s.length() - p.length()){
                //窗口移出
                arrayS[s.charAt(i) - 'a']--;
                //移进窗口
                arrayS[s.charAt(i + p.length()) - 'a']++;
            }

        }
        return list;
    }

    // ×（因为有负数）560. 和为 K 的子数组 time：2024年3月12日19:08:24 -> 2024年3月12日19:21:05
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        //使用双指针

        int left = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            if (sum == k){
                count++;
                //left不需要动
            }else if (sum > k){
                while (sum > k){
                    //先减去此值，再移动指针
                    sum -= nums[left];
                    left++;
                }
                if (sum == k){
                    count++;
                }
                //if sum<k,就什么都不做，因为下一步就是i++；
            }

        }
        return count;
    }
    //暴力双层for循环，因为是子数组 就是left-right的区间，可以使用双层for循环搞定
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            int sum = 0;
            for (int j = i; j < nums.length; j++){
                sum += nums[j];
                if (sum == k){
                    count++;
                }
            }
        }
        return count;
    }
    //使用官方题解 前缀和做法 time：2024年3月12日19:48:30 -> 2024年3月12日19:59:06
    public int subarraySum3(int[] nums, int k) {
        int pre = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //一定要初始化 默认和为前缀和为0的有1个
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++){
            pre += nums[i];
            if (map.containsKey(pre - k)){//因为都是前缀和 所以这里是pre-k
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }






    public static void main(String[] args) {
        Hot100 hot100 = new Hot100();
        int[][] nums = {{1,0,1,0},{0,1,0,1},{1,1,0,0},{0,0,1,1}};
        hot100.getPerfect(nums);
    }



}
// 附加题 304. 二维区域和检索 - 矩阵不可变 time ：2024年3月11日10:06:57 -> 2024年3月11日10:13:46
// 二维矩阵 使用一维前缀和：time：2024年3月11日10:33:01 ->
/*
class NumMatrix {

    private int[][] matrixSum;

    public NumMatrix(int[][] matrix) {
        this.matrixSum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            int sum = 0;
            for (int j = 0; j < matrix[0].length; j++){
                //计算每一行的前缀和
                sum += matrix[i][j];
                matrixSum[i][j] = sum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        //求所有前缀和 :注意这里只需要按照行来遍历就可以了！！！因为我们的前缀和就是按照行进行收集的！
        int sum = 0;
        for (int i = row1; i <= row2; i++){
            if (col1 == 0){
                sum += matrixSum[i][col2];
            }else {
                sum += matrixSum[i][col2] - matrixSum[i][col1 - 1];
            }
        }
        return sum;
    }
}*/
// 二维矩阵 使用二维前缀和： time：2024年3月11日16:20:38 ->
// 之前都是使用一维前缀和 就是和数组一样 每一行记录一组前缀和
class NumMatrix {

    // 二维前缀和：即从(0,0)开始至 (i,j)的总和
    private int[][] matrixSum;

    public NumMatrix(int[][] matrix) {
         matrixSum = new int[matrix.length][matrix[0].length];
         for(int i = 0; i < matrix.length; i++){
             int sum = 0;
             //还是要以行为开始
             for (int j = 0; j < matrix[0].length; j++){
                 sum += matrix[i][j];
                 if (i == 0){ //i = 0 特殊处理 因为不需要加上面的
                     matrixSum[i][j] = sum;
                 }else { // 除了第一行都要加前面的！
                     // 这里每一个代表的都是 从(0,0)开始至 (i,j)的总和
                     matrixSum[i][j] = matrixSum[i - 1][j] + sum;
                 }
             }
         }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0){
            //1.左顶点 行列都为0 直接返回
            return matrixSum[row2][col2];
        }else if (row1 == 0 && col1 != 0){
            //2.左顶点 行为0，只减左边就可以
            return matrixSum[row2][col2] - matrixSum[row2][col1 - 1];
        }else if (col1 == 0 && row1 != 0){
            //3.左顶点 列为0，只减上边就可以
            return matrixSum[row2][col2] - matrixSum[row1 - 1][col2];
        }else {
            //4.左顶点 行列都不为0，减左边 减右边 再加上左上角
            return matrixSum[row2][col2] - matrixSum[row1 - 1][col2] - matrixSum[row2][col1 - 1] + matrixSum[row1 - 1][col1 - 1];
        }

    }

}

// 附加题 303. 区域和检索 - 数组不可变 time：2024年3月11日10:16:12 -> 2024年3月11日10:18:33
// 前缀和思路：time：2024年3月11日10:25:19 ->
class NumArray {

    //其中numsSum[i]表示，0-i之间所有元素的和！
    private final int[] numsSum;

    public NumArray(int[] nums) {
        numsSum = new int[nums.length];

        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            numsSum[i] = sum;
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0){
            return numsSum[right];
        }else {
            return numsSum[right] - numsSum[left - 1];
        }
    }
}
