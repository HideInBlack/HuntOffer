package swordOffer;

import codeFlowIdea.ListNode;

import java.math.BigDecimal;
import java.util.*;

/**
 * 力扣 面试150 题目练习 time：2024年8月17日14:45:33 ->
 */
public class Interview150 {

    // （1）88. 合并两个有序数组 time：2024年8月17日14:49:25 -> 2024年8月17日15:09:20
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int right1 = m - 1;
        int right2 = n - 1;
        int rightNew = nums1.length - 1;
        while (right1 >= 0 && right2 >= 0){
            if (nums1[right1] > nums2[right2]){
                nums1[rightNew] = nums1[right1];
                right1--;
            }else {
                nums1[rightNew] = nums2[right2];
                right2--;
            }
            rightNew--;
        }
        if (right1 < 0){
            while (right2 >= 0){
                nums1[rightNew] = nums2[right2];
                right2--;
                rightNew--;
            }
        }
    }

    // （2）27. 移除元素 time：2024年8月17日15:10:38 -> 2024年8月17日15:36:09
    public int removeElement(int[] nums, int val) {
        // 从后往前遍历，把所有的val挪到后面去
        int right = nums.length - 1;
        for (int i = right; i >= 0; i--){
            if (nums[i] == val){
                nums[i] = nums[right];
                right--;
            }
        }
        return right + 1;
    }

    // （3）26. 删除有序数组中的重复项 time：2024年8月17日15:58:07 -> 2024年8月17日16:05:12
    public int removeDuplicates(int[] nums) {
        int index = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if (!set.contains(nums[i])){
                set.add(nums[i]);
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    // （4）80. 删除有序数组中的重复项 II time：2024年8月17日16:09:01 -> 2024年8月17日16:15:09
    public int removeDuplicates2(int[] nums) {
        int index = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) <= 2){
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    //（5）169. 多数元素 time：2024年8月17日16:16:00 -> 2024年8月17日16:24:38
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[(nums.length - 1) / 2];
    }

    //（6）189. 轮转数组 time：2024年8月17日16:29:27 -> 2024年8月17日16:49:07
    public void rotate(Integer[] nums, int k) {
        if (k > nums.length){
            k = k % nums.length;
        }

        // 1.先翻转nums.length - k到 nums.length - 1
        overturn(nums, nums.length - k, nums.length - 1);
        // 2.再反转0 到 num.length - k -1
        overturn(nums, 0, nums.length - k -1);
        // 3.再翻转整体
        overturn(nums, 0, nums.length - 1);
    }
    // 翻转指定区间内的数组（左闭右闭）
    private <T> void overturn(T[] nums, int left, int right){
        while (left < right){
            T temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    // （7）238. 除自身以外数组的乘积 time：2024年8月17日16:57:06 -> 2024年8月17日17:10:32
    // 重要总结：一定要记住前缀和、后缀和、前缀积、后缀积！这个思路！
    public int[] productExceptSelf(int[] nums) {
        // 思路：保存所有的前缀积和后缀积
        int[] pre = new int[nums.length];
        int[] suf = new int[nums.length];
        // 前缀积
        int preMultiple = 1;
        for (int i = 0; i < nums.length; i++){
            preMultiple = preMultiple * nums[i];
            pre[i] = preMultiple;
        }

        // 后缀积
        int sufMultiple = 1;
        for (int i = nums.length - 1; i >= 0; i--){
            sufMultiple = sufMultiple * nums[i];
            suf[i] = sufMultiple;
        }

        int[] answer = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            if (i == 0){
                answer[i] = suf[i + 1];
            }else if (i == nums.length - 1){
                answer[i] = pre[i - 1];
            }else {
                answer[i] = pre[i - 1] * suf[i + 1];
            }
        }
        return answer;
    }

    //（8）13. 罗马数字转整数 time：2024年8月17日17:11:44 -> 2024年8月17日17:33:12
    // 思路总结：正常应该怎么做呢？
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        int sum = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == 'I' && i != s.length() - 1){
                if (s.charAt(i + 1) == 'V'){
                    sum += 4;
                    i++;
                } else if (s.charAt(i + 1) == 'X') {
                    sum += 9;
                    i++;
                } else {
                    sum += map.get(c);
                }
            } else if (c == 'X'  && i != s.length() - 1){
                if (s.charAt(i + 1) == 'L'){
                    sum += 40;
                    i++;
                } else if (s.charAt(i + 1) == 'C') {
                    sum += 90;
                    i++;
                } else {
                    sum += map.get(c);
                }
            } else if (c == 'C'  && i != s.length() - 1) {
                if (s.charAt(i + 1) == 'D'){
                    sum += 400;
                    i++;
                } else if (s.charAt(i + 1) == 'M') {
                    sum += 900;
                    i++;
                } else {
                    sum += map.get(c);
                }
            } else {
                sum += map.get(c);
            }
        }
        return sum;
    }

    //（9）14. 最长公共前缀 time：2024年8月17日20:20:22 -> 2024年8月17日20:34:15
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        char cur;
        out:
        for (int index = 0; index < strs[0].length(); index++){
            for (int i = 0; i < strs.length; i++){
                cur = strs[0].charAt(index);
                if (index > strs[i].length() - 1 || cur != strs[i].charAt(index)){
                    break out;
                }
                if (i == strs.length - 1){
                    result.append(cur);
                }
            }
        }
        return result.toString();
    }

    //（10）58. 最后一个单词的长度 time：2024年8月17日20:34:53 -> 2024年8月17日20:37:07
    public int lengthOfLastWord(String s) {
        String[] strings = s.split(" ");
        return strings[strings.length - 1].length();
    }

    //（11）151. 反转字符串中的单词 time：2024年8月17日20:37:10 -> 2024年8月17日20:57:06
    public String reverseWords(String s) {
        String newS = s.strip();
        String[] strings = newS.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = strings.length - 1; i >= 0; i--){
            result.append(strings[i]);
            if (i != 0){
                result.append(" ");
            }

        }
        return result.toString();
    }

    //（12）6. Z 字形变换 time：2024年8月17日20:57:19 -> 2024年8月17日21:32:22
    // 思路：不要跟着题目的陷阱走！如果真的走Z太复杂了，直接以行为角度，存储每一行的即可！
    public String convert(String s, int numRows) {
        // 1.有多少行，就建立多少个行
        Map<Integer, StringBuilder> map = new HashMap<>();
        for (int i = 0; i < numRows; i++){
            StringBuilder stringBuilder = new StringBuilder();
            map.put(i, stringBuilder);
        }

        // 2.开始模拟走z字型的路线往行里塞字符
        int indexS = 0;
        out:
        while (true){
            for (int i = 0; i < numRows; i++){
                if (indexS >= s.length()){
                    break out;
                }
                map.get(i).append(s.charAt(indexS));
                indexS++;
            }
            for (int i = numRows - 2; i >= 1; i--){
                if (indexS >= s.length()){
                    break out;
                }
                map.get(i).append(s.charAt(indexS));
                indexS++;
            }
        }

        // 3.组装最后结果
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++){
            result.append(map.get(i));
        }
        return result.toString();
    }

    //（13）125. 验证回文串 time：2024年8月17日21:48:50 -> 2024年8月17日21:56:47
    public boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i)) || Character.isLowerCase(s.charAt(i))){
                stringBuilder.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        String string = stringBuilder.toString();
        String reverse = stringBuilder.reverse().toString();
        return Objects.equals(string, reverse);
    }

    // 改变一下思路：先刷面熟的！常考常手撕的算法
    //（14）167. 两数之和 II - 输入有序数组 time：2024年8月18日10:04:10 -> 2024年8月18日10:14:01
    // 思路总结：从双指针的思路去走会非常的简单！！！
    public int[] twoSum(int[] numbers, int target) {
        //使用双指针来做
        int left = 0;
        int right = numbers.length - 1;
        int[] result = new int[2];
        while (left < right){
            if (numbers[left] + numbers[right] < target){
                left++;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            }
        }
        return result;
    }

    //（15）15. 三数之和 time：2024年8月18日10:16:13 -> 2024年8月18日10:46:04
    public List<List<Integer>> threeSum(int[] nums) {
        // 1.先进行一手排序
        Arrays.sort(nums);

        // 2.再进行相关过滤
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            // 去重
            if (i != 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // 3.最后两层循环得使用双指针进行改造
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                if (nums[left] + nums[right] + nums[i] < 0){
                    left++;
                } else if (nums[left] + nums[right] + nums[i] > 0) {
                    right--;
                } else {
                    List<Integer> curLine = new ArrayList<>();
                    curLine.add(nums[i]);
                    curLine.add(nums[left]);
                    curLine.add(nums[right]);
                    result.add(curLine);

                    // 重点重点！先移动一次再进行去重判断
                    left++;
                    right--;
                    // 去重：把left和right移动到不重复的位置
                    while (left < right && nums[left] == nums[left - 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]){
                        right--;
                    }
                }
            }
        }
        return result;
    }

    //（16）209. 长度最小的子数组 time：2024年8月18日10:47:21 -> 2024年8月18日11:17:35
    // 思路总结：就是滑动窗口的思路！具体编码实现的细节需要注意！建议again！
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口的思路 left++; right++;
        int left = 0;
        int right = 0;
        int sum = nums[0];
        int minLength = Integer.MAX_VALUE;
        while (true){
            if (sum < target){
                right++;
                if (right > nums.length - 1) break; // 在取值前单独判断即可！right
                sum += nums[right];
            } else {
                // 记录每一个符合条件的长度 取最小的子数组长度
                minLength = Math.min(minLength, right - left + 1);
                if (left > nums.length - 1) break; // 在取值前单独判断即可！left
                sum -= nums[left];
                left++;
            }
        }
        if (minLength == Integer.MAX_VALUE){
            return 0;
        }
        return minLength;
    }

    //（17）3. 无重复字符的最长子串 time：2024年8月18日12:27:18 -> 2024年8月18日12:59:07
    public int lengthOfLongestSubstring(String s) {
        // 特殊情况
        if (s.isEmpty()) return 0;
        if (s.length() == 1) return 1;

        //滑动窗口：滑动窗口的本质就是left和right的移动
        int left = 0;
        int right = 1;
        int maxlength = 1;

        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        while (right < s.length()){
            if (set.contains(s.charAt(right))){
                while (s.charAt(left) != s.charAt(right)){
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;
            } else {
                // 不包含就算一次最大长度
                maxlength = Math.max(maxlength, right - left + 1);
                set.add(s.charAt(right));
            }
            right++;
        }
        return maxlength;
    }

    //（18）不用while 使用for再试一下滑动窗口 time：2024年8月18日14:41:36 ->
    public int minSubArrayLen2(int target, int[] nums) {
        // 一定要固定右端right使用for，左边left使用while这样足够简单
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++){
            sum += nums[right];
            if (sum >= target){
                while (sum >= target){
                    minLength = Math.min(minLength, right - left + 1);
                    sum -= nums[left];
                    left++;
                }
            }
        }

        if (minLength == Integer.MAX_VALUE){
            return 0;
        }
        return minLength;
    }

    //（19）36. 有效的数独 time：2024年8月18日14:57:51 -> 2024年8月18日15:19:15
    // 思路总结：使用数学的思路模拟出来了，但是总感觉正确的做法应该不是这样的
    public boolean isValidSudoku(char[][] board) {
        Set<Character> setRow = new HashSet<>();
        Set<Character> setCow = new HashSet<>();
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        Set<Character> set3 = new HashSet<>();
        for (int i = 0; i < board.length; i++){
            if (i % 3 == 0){
                set1.clear();
                set2.clear();
                set3.clear();
            }
            for (int j = 0; j < board.length; j++){
                // 对行进行有效判断
                if (setRow.contains(board[i][j])){
                    return false;
                }
                if (board[i][j] != '.') {
                    setRow.add(board[i][j]);
                }

                // 对列进行有效判断
                if (setCow.contains(board[j][i])){
                    return false;
                }
                if (board[j][i] != '.') {
                    setCow.add(board[j][i]);
                }

                // 对9方格进行有效判断
                if (j / 3 == 0){
                    if (set1.contains(board[i][j])){
                        return false;
                    }
                    if (board[i][j] != '.') {
                        set1.add(board[i][j]);
                    }
                }
                if (j / 3 == 1){
                    if (set2.contains(board[i][j])){
                        return false;
                    }
                    if (board[i][j] != '.') {
                        set2.add(board[i][j]);
                    }
                }
                if (j / 3 == 2){
                    if (set3.contains(board[i][j])){
                        return false;
                    }
                    if (board[i][j] != '.') {
                        set3.add(board[i][j]);
                    }
                }
            }
            setCow.clear();
            setRow.clear();
        }
        return true;
    }

    //（20）73. 矩阵置零 time：2024年8月18日15:22:23 -> 2024年8月18日15:30:30
    public void setZeroes(int[][] matrix) {
        // 1.先遍历一遍所有应该置为0的所有行、所有列。并存储起来
        int[] row = new int[matrix.length];
        int[] cow = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    row[i] = 1;
                    cow[j] = 1;
                }
            }
        }

        // 2.执行置0操作
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (row[i] == 1 || cow[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    //（21）205. 同构字符串 time：2024年8月19日10:22:51 -> 2024年8月19日10:36:43
    // 思路：因为是互相映射的关系，所以要采用两个map互相映射才可以
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            if (map1.get(s.charAt(i)) == null && map2.get(t.charAt(i)) == null){
                map1.put(s.charAt(i), t.charAt(i));
                map2.put(t.charAt(i), s.charAt(i));
            } else {
                // 如果不为null 并且还不相等 则直接返回false
                if (!Objects.equals(map1.get(s.charAt(i)), t.charAt(i)) || !Objects.equals(s.charAt(i), map2.get(t.charAt(i))) ){
                    return false;
                }
            }
        }
        return true;
    }

    //（22）290. 单词规律 time：2024年8月19日10:37:45 -> 2024年8月19日10:50:06
    public boolean wordPattern(String pattern, String s) {
        // 继续使用双map 进行双向连接
        char[] patternArray = pattern.toCharArray();
        String[] strings = s.split(" ");
        Map<Character, String> c2s = new HashMap<>();
        Map<String, Character> s2c = new HashMap<>();

        if (patternArray.length != strings.length) return false;
        for (int i = 0; i < patternArray.length; i++){
            // 1.当双向映射关系都为空的时候才加入到map里，否则就返回false
            if (c2s.get(patternArray[i]) == null && s2c.get(strings[i]) == null){
                c2s.put(patternArray[i], strings[i]);
                s2c.put(strings[i], patternArray[i]);
            } else {
                // 2.不为null，则映射关系就必须一致，否则返回false
                if (!Objects.equals(c2s.get(patternArray[i]), strings[i]) || !Objects.equals(s2c.get(strings[i]), patternArray[i])){
                    return false;
                }
            }
        }
        return true;
    }

    //（23）49. 字母异位词分组 time：2024年8月19日13:39:31 -> 2024年8月19日13:59:33
    public List<List<String>> groupAnagrams(String[] strs) {
        // 以排序后的相同的字符串为key
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 1.先分割成char进行排序，排序之后再new成新的字符串
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            // 2.放进put里
            List<String> mapOrDefault = map.getOrDefault(key, new ArrayList<>());
            mapOrDefault.add(str);
            map.put(key, mapOrDefault);
        }
        List<List<String>> result = new ArrayList<>();
        map.forEach((key,value) -> result.add(value));
        return result;
    }

    //（24）128. 最长连续序列 time：2024年8月19日14:00:41 -> 2024年8月19日14:18:12
    public int longestConsecutive(int[] nums) {
        // 1.先把整个数组放到set里
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // 2.开始遍历，对于100:只需要判断是否存在100-1，如果存在则代表其不是头直接结束；如果不存在则开始while遍历+1到另一头
        int maxLength = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curLength = 0;
                int temp = num;
                while (set.contains(temp)) {
                    curLength++;
                    temp++;
                }
                // 计算一遍最长连续序列长度
                maxLength = Math.max(maxLength, curLength);
            }
        }
        return maxLength;
    }

    //（25）219. 存在重复元素 II time：2024年8月19日14:19:00 -> 2024年8月19日14:32:08
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 使用map保存起来值与下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            Integer index1 = map.get(nums[i]);
            if (index1 != null){
                if (Math.abs(i - index1) <= k){
                    return true;
                }
            }
            // 无论与否都需要更新下标
            map.put(nums[i], i);
        }
        return false;
    }

    //（26）228. 汇总区间 time：2024年8月21日10:04:06 -> 2024年8月21日10:27:48
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int left = 0;
        int right = 0;
        if (nums.length == 1){
            result.add(String.valueOf(nums[0]));
            return result;
        }

        for (int i = 0; i < nums.length; i++){
            // 第一个特殊处理
            if (i == 0){
                left = nums[0];
                right = nums[0];
                continue;
            }
            // 正常逻辑
            if (nums[i] != right + 1){
                // 如果不等，就需要保存起来了
                if (left == right){
                    result.add(String.valueOf(left));
                } else {
                    result.add(left + "->" + right);
                }
                left = nums[i];
                right = nums[i];
            } else {
                right = nums[i];
            }
            // 最后一个特殊处理
            if (i == nums.length - 1){
                if (left == right){
                    result.add(String.valueOf(left));
                } else {
                    result.add(left + "->" + right);
                }
            }
        }
        return result;
    }

    //（27）56. 合并区间 time：2024年8月21日10:29:34 -> 2024年8月21日11:01:42
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        // 1.先对intervals进行排序，按照num[0]进行排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        // 2.再进行遍历合并
        result.add(new int[]{intervals[0][0], intervals[0][1]});
        for (int i = 1; i < intervals.length; i++){
            int[] last = result.get(result.size() - 1);
            if (intervals[i][0] > last[1]){
                // 区间不相接连
                result.add(new int[]{intervals[i][0], intervals[i][1]});
            } else {
                // 区间相连
                last[1] = Math.max(intervals[i][1],  last[1]);
            }
        }
//        int[][] realRet = new int[result.size()][2];
//        for (int i = 0; i < realRet.length; i++){
//            realRet[i] = result.get(i);
//        }
//        return realRet;
        return result.toArray(new int[result.size()][]);
    }

    //（28）57. 插入区间 time：2024年8月21日13:50:55 -> 2024年8月21日14:06:18
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 此题思路：先插入到一个二维数组中，再进行排序与区间合并即可
        // 1.先把二维数组转化为list，并插入最新的一个区间
        List<int[]> list = new ArrayList<>(Arrays.stream(intervals).toList());
        list.add(newInterval);

        // 2.按照startIndex进行排序
        list.sort(Comparator.comparingInt(o -> o[0]));

        // 3.最后合并区间
        List<int[]> result = new ArrayList<>();
        result.add(new int[]{list.get(0)[0], list.get(0)[1]});
        list.forEach(array -> {
            int[] last = result.get(result.size() - 1);
            if (array[0] > last[1]){
                // 如果当前区间left比记录的最后一个的right还要大，则新增区间
                result.add(new int[]{array[0], array[1]});
            } else {
                // 如果当前区间left比记录的最后一个right要小，则需要区间合并，最终的right要取两个right的最大值
                last[1] = Math.max(last[1], array[1]);
            }
        });
        return result.toArray(new int[result.size()][]);
    }

    //（29）452. 用最少数量的箭引爆气球 time：2024年8月21日14:07:33 -> 2024年8月21日14:38:40
    public int findMinArrowShots(int[][] points) {
        // 1.先进行一手按照left排序
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

        // 2.再进行计算弓箭数量
        int arrow = 1;
        int minCommon = points[0][1];
        // 就得从第二个开始
        for (int i = 1; i < points.length; i++){
            if (points[i][0] > minCommon){
                // 如果下一个区间left大于交集
                arrow++;
                minCommon = points[i][1];
            } else {
                // 如果下一个区间小于等于交集
                minCommon = Math.min(minCommon, points[i][1]);
            }
        }
        return arrow;
    }

    //（30）71. 简化路径 time：2024年8月21日14:42:50 -> 2024年8月21日15:11:27
    // 细节还挺多注意点，缝缝补补
    public String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String s : split) {
            if (Objects.equals(s, "") || Objects.equals(s, ".")) {
                continue;
            }
            if (Objects.equals(s, "..") && !stack.isEmpty()) {
                stack.pop();
            } else {
                if (Objects.equals(s, "..")) continue;
                stack.push(s);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()){
            result.insert(0, stack.pop());
            result.insert(0, "/");
        }
        if (result.isEmpty()){
            result.insert(0, "/");
        }
        return result.toString();
    }

    //（31）155. 最小栈 time：2024年8月22日09:48:19 -> 2024年8月22日10:08:41
    // 见MinStack类，已完成

    //（32）141. 环形链表 time：2024年8月22日10:32:10 -> 2024年8月22日10:51:44
    // 链表题目尽量全部做完：喜欢考
    // 思路：两种方法都可以，一种是快慢指针相遇就代表有环，还有一个就是把节点直接放在set进行判断是否包含，这个可以找到环的起始位置！
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null){
            // 1.包含则返回
            if (set.contains(node)){
                return true;
            }

            // 2.未包含，继续往下走
            set.add(node);
            node = node.next;
        }
        return false;
    }
    // （33）其实最经典的是快慢指针相遇
    public boolean hasCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    //（34）142. 环形链表 II time：2024年8月22日10:58:26 -> 2024年8月22日11:00:22
    // 还别说！还真别说！这set还真行！但是时间复杂度有一点高！
    // 还是需要使用快慢指针：首先快慢指针相遇在某节点，此时一个结点在head处，一个在相遇处，继续都一步一步的走，再次相遇时就是起始节点！
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null){
            // 1.包含则返回
            if (set.contains(node)){
                return node;
            }

            // 2.未包含，继续往下走
            set.add(node);
            node = node.next;
        }
        return null;
    }

    //（35）2. 两数相加 time：2024年8月22日11:44:02 -> 2024年8月22日12:12:33
    // 难点：完全可以使用字符串自己模拟整数相加，但是不如BigDecimal来得快
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        ListNode head1 = l1;
        ListNode head2 = l2;
        while (head1 != null){
            a.insert(0, head1.val);
            head1 = head1.next;
        }
        while (head2 != null){
            b.insert(0, head2.val);
            head2 = head2.next;
        }

        // 难点：使用字符串计算超大加法问题

        BigDecimal numa = new BigDecimal(a.toString());
        BigDecimal numb = new BigDecimal(b.toString());
        BigDecimal result = numa.add(numb);
        String string = result.toString();

        ListNode fakeHead = new ListNode();
        ListNode last = fakeHead;
        for (int i = string.length() - 1; i >= 0; i--){
            last.next = new ListNode(Integer.parseInt(String.valueOf(string.charAt(i))));
            last = last.next;
        }
        return fakeHead.next;
    }

    //（36）21. 合并两个有序链表 time：2024年8月22日12:25:14 -> 2024年8月22日12:34:05
    // 思路：有序链表的合并有两种解题思路：1.把b链表合并到a里 2.新建一个fakeHead假头结点，再把a、b合并到第三个链表里
    // 我倾向于第2个解决方案，更加通俗易懂，也正是这样实现的，其中注意的细节有：1.首先创建fakeHead假头结点；2.处理剩余的节点
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode curA = a;
        ListNode curB = b;
        ListNode fakeHead = new ListNode();
        ListNode curR = fakeHead;

        while (curA != null && curB != null){
            if (curA.val < curB.val){
                curR.next = curA;
                curA = curA.next; // a下移一个
            } else {
                curR.next = curB;
                curB = curB.next; // b下移一个
            }
            curR = curR.next; // r下移一个
        }

        // 处理剩余的
        if (curA != null){
            curR.next = curA;
        }
        if (curB != null){
            curR.next = curB;
        }
        return fakeHead.next;
    }

    //（37）138. 随机链表的复制 time：2024年8月22日12:36:44 -> 2024年8月22日13:04:31
    public Node copyRandomList(Node head) {
        Node fakeHead = new Node(-1);
        Node newLast = fakeHead;
        Node cur = head;
        // 1.先建立标准的链表，建立的时候存一个map；map里保存的是key=旧Node，value=新Node；这样就可以根据旧的Node找到新的Node
        Map<Node, Node> map = new HashMap<>();
        while (cur != null){
            Node newOne = new Node(cur.val); // 1.新建
            newOne.random = cur.random; // 先复制给他，后续可以改变

            // 这一步是关键！建立旧节点与新节点的关系
            map.put(cur, newOne);

            newLast.next = newOne; // 2.接入
            newLast = newLast.next; // 3.新的下移
            cur = cur.next; // 4.旧的下移
        }
        // 2.再进行赋值random指针
        newLast = fakeHead; //回头重新遍历新list
        while (newLast != null){
            newLast.random = map.get(newLast.random);
            newLast = newLast.next;
        }
        return fakeHead.next;
    }

    //（38）92. 反转链表 II time：2024年8月23日11:12:04 -> 2024年8月23日11:25:43
    public ListNode reverseBetween(ListNode head, int left, int right) {
        //使用栈的思路 试试：首先一定需要假头结点
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode cur = fakeHead; // 遍历指针
        ListNode pre = null; // left左边一个的记录指针
        ListNode suf = null; // right右边一个的记录指针

        int index = 0;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null){
            if (index == left - 1){
                pre = cur;
            }
            if (index == right + 1){
                suf = cur;
            }
            if (index >= left && index <= right){
                // 直接把结点入栈
                stack.add(cur);
            }
            index++;
            cur = cur.next;
        }

        //再出栈节点 组装链表
        while (!stack.isEmpty()){
            pre.next = stack.pop();
            pre = pre.next;
        }
        pre.next = suf; //接上后缀

        return fakeHead.next;
    }

    //（39）25. K 个一组翻转链表 time：2024年8月23日11:39:10 -> 2024年8月23日11:59:53
    public ListNode reverseKGroup(ListNode head, int k) {
        // 使用栈的思路来吧，栈满k则出栈接入
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;

        ListNode cur = head;
        ListNode pre = fakeHead;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null){
            if (stack.size() == k){
                // 1.如果栈满了，则出栈接入list
                while (!stack.isEmpty()){
                    pre.next = stack.pop();
                    pre = pre.next;
                }
                pre.next = cur;
            } else {
                // 2.栈不满，则直接入栈
                stack.add(cur);
                cur = cur.next;
            }
        }
        // 最后再判断一下 如果栈是满的还是需要再改过来的
        if (stack.size() == k) {
            while (!stack.isEmpty()) {
                pre.next = stack.pop();
                pre = pre.next;
            }
            pre.next = null;
        }
        return fakeHead.next;
    }

    //（40）19. 删除链表的倒数第 N 个结点 time：2024年8月23日13:54:26 -> 2024年8月23日14:08:29
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 倒数第n个，正向第size-n+1个，但要先找siz-n个即可
        int length = 0;
        ListNode cur = head;
        while (cur != null){
            length++;
            cur = cur.next;
        }

        // 1.删除第一个特殊处理
        if (length - n + 1 == 1){
            return head.next;
        }
        // 2.非第一个则找length-n个节点即可
        int index = 1;
        cur = head;
        while (index < length - n){
            index++;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return head;
    }
    //（40）方法二，一次遍历就删除倒数第N个节点 time：2024年8月23日14:10:48 -> 2024年8月23日14:19:25
    // 思路无敌！
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 使用快慢指针来做，让fast指针先多走n + 1个节点
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode fast = fakeHead;
        ListNode slow = fakeHead;

        // 1.快指针先走n+1步
        int index = n;
        while (index >= 0){
            fast = fast.next;
            index--;
        }

        // 2.快慢指针再同时走
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return fakeHead.next;
    }

    //（41）82. 删除排序链表中的重复元素 II time：2024年8月23日14:20:04 -> 2024年8月23日14:37:30
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        // 1.先遍历一遍记录下所有该删除的节点值
        ListNode cur = head;
        Set<Integer> deleteSet = new HashSet<>();
        while (cur.next != null){
            if (cur.val == cur.next.val) {
                deleteSet.add(cur.val);
            }
            cur = cur.next;
        }

        // 2.针对deleteSet进行删除
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode pre = fakeHead;
        while (pre.next != null){
            if (deleteSet.contains(pre.next.val)){
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return fakeHead.next;
    }

    //（42）83. 删除排序链表中的重复元素 time：2024年8月23日14:52:24 -> 2024年8月23日15:00:01
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) return null;
        ListNode pre = head;
        while (pre.next != null){
            if (pre.val == pre.next.val){
                // 1.如果相等则删除下一个节点：但注意不要下移pre指针，因为还需要继续判断
                pre.next = pre.next.next;
            } else {
                // 2.不等则下移pre指针
                pre = pre.next;
            }
        }
        return head;
    }

    //（43）61. 旋转链表 time：2024年8月24日10:16:51 -> 2024年8月24日10:36:04
    // 思路：此题就是考的查到倒数第n个链表节点
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;

        // 1.先遍历一遍找到length总长度
        int length = 0;
        ListNode cur = head;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        int realK = k % length;
        if (realK == 0) return head;

        // 2.遍历找到倒数第realK + 1个节点，以及最后一个结点
        ListNode fast = head;
        ListNode slow = head;
        while (realK > 0){
            realK--;
            fast = fast.next;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        // 3.重新拼接链表
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    //（44）86. 分隔链表 time：2024年8月24日10:50:10 -> 2024年8月24日11:09:00
    public ListNode partition(ListNode head, int x) {
        // 思路：无论是否在x的左边或者右边，只要其小于x，就统一放到栈里并且删除掉，最后再从fakeHead头插
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;

        // 1.先进行遍历并且保存队列与删除小于x的节点
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = fakeHead;
        while (cur.next != null){
            if (cur.next.val < x){
                stack.push(cur.next); //放入栈
                cur.next = cur.next.next; //删除
            } else {
                cur = cur.next;
            }
        }

        // 2.出栈，然后头插入到链表里
        while (!stack.isEmpty()){
            ListNode node = stack.pop();
            node.next = fakeHead.next;
            fakeHead.next = node;
        }
        return fakeHead.next;
    }

    //（45）104. 二叉树的最大深度 time：2024年8月24日11:12:49 -> 2024年8月24日11:38:57
    // 思路：记住递归是递进去、还要归回来的！往里进的时候（入参）是当前深度，最后返回的时候是返回最大的深度！
    public int maxDepth(TreeNode root) {
        // 此题使用递归来做试试
        return midTree(root, 0);
    }
    public int midTree(TreeNode root, int curDepth){
        // 此题使用递归来做试试
        if (root != null){
            curDepth++;
            int left = midTree(root.left, curDepth);
            int right = midTree(root.right, curDepth);
            return Math.max(left, right);
        } else {
            return curDepth;
        }
    }

    //（46）100. 相同的树 time：2024年8月24日15:05:40 -> 2024年8月24日15:11:33
    // 这得要后序遍历,因为后序要使用到左子树的结果和右子树的结果
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null){
            if (p.val != q.val){
                return false;
            }
            boolean left= isSameTree(p.left, q.left);
            boolean right = isSameTree(p.right, q.right);
            return left && right;
        } else {
            return p == null && q == null;
        }
    }

    //（47）226. 翻转二叉树 time：2024年8月24日15:12:09 -> 2024年8月24日15:26:14
    // 这个得使用后序遍历
    public TreeNode invertTree(TreeNode root) {
        if (root != null){
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            // 赋值就是根结点操作
            root.left = right;
            root.right = left;
            return root;
        } else {
            return null;
        }
    }

    //（48）101. 对称二叉树 time：2024年8月24日15:27:09 -> 2024年8月24日15:36:22
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricIn(root.left, root.right);
    }
    // 还是后序遍历！
    public boolean isSymmetricIn(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null){
            if (root1.val != root2.val){
                return false;
            }
            boolean left = isSymmetricIn(root1.left, root2.right); // 左子树
            boolean right = isSymmetricIn(root1.right, root2.left); // 右子树
            return left && right; // 中间

        } else return root1 == null && root2 == null;
    }

    //（49）199. 二叉树的右视图 time：2024年8月24日15:38:07 -> 2024年8月24日15:48:31
    public List<Integer> rightSideView(TreeNode root) {
        //此题思路：层次遍历，然后保存每一层的最后一个结点
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()){
            int length = deque.size(); // 先记住队列的长度，这是一次性要出队的所有节点
            while (length > 0){
                // 1.出队
                TreeNode cur = deque.removeFirst();
                // 2.入队刚刚出队节点的左节点和右节点
                if (cur.left != null) deque.add(cur.left);
                if (cur.right != null) deque.add(cur.right);

                // 3.此题特殊要求，当每一层最后一个结点的时候，需要保存到结果里
                if (length == 1){
                    result.add(cur.val);
                }
                length--;
            }
        }
        return result;
    }

    //（50）637. 二叉树的层平均值 time：2024年8月24日15:49:05 -> 2024年8月24日15:56:30
    public List<Double> averageOfLevels(TreeNode root) {
        // 二叉树的层次遍历
        List<Double> result = new ArrayList<>();

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            int tempSize = size;
            long sum = 0;
            while (size > 0){
                TreeNode node = deque.removeFirst();
                sum += node.val;
                if (node.left != null) deque.add(node.left);
                if (node.right != null) deque.add(node.right);
                size--;
            }
            result.add((double) sum / tempSize);
        }
        return result;
    }

    //（51）102. 二叉树的层序遍历 time：2024年8月24日15:56:49 ->2024年8月24日16:09:12
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int length = deque.size(); // 先记住队列的长度，这是一次性要出队的所有节点
            List<Integer> curLine = new ArrayList<>();
            while (length > 0) {
                // 1.出队
                TreeNode cur = deque.removeFirst();
                curLine.add(cur.val);
                // 2.入队刚刚出队节点的左节点和右节点
                if (cur.left != null) deque.add(cur.left);
                if (cur.right != null) deque.add(cur.right);
                length--;
            }
            result.add(curLine);
        }
        return result;
    }

    //（52）103. 二叉树的锯齿形层序遍历 time：2024年8月24日16:59:48 -> 2024年8月24日17:20:47
    // 思路：不要想那么多，用什么栈啥的，都不需要，就正儿八经的队列！然后再存当前行数据的时候，用头插即可！
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 层次遍历的改编版
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        int key = 0;
        while (!deque.isEmpty()){
            int size = deque.size();
            List<Integer> curLine = new ArrayList<>();
            while (size > 0){
                TreeNode node = deque.removeFirst();
                if (key % 2 == 0){
                    // 余数为0 则从左到右
                    curLine.add(node.val);
                } else {
                    // 余数为1 则从右到左
                    curLine.add(0, node.val);
                }
                if (node.left != null) deque.add(node.left);
                if (node.right != null) deque.add(node.right);
                size--;
            }
            result.add(curLine);
            key++;
        }
        return result;
    }

    //（53）105. 从前序与中序遍历序列构造二叉树 time：2024年8月25日10:11:02 -> 2024年8月25日10:39:34
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 1.先把inorder中元素与小标对应起来（注意：只有在无重复元素的情况下才可以使用）
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            inMap.put(inorder[i], i);
        }

        // 2.递归调用构造二叉树方法:都是左闭右闭
        return buildTreeIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    }

    public TreeNode buildTreeIn(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight, Map<Integer, Integer> inMap) {
        if (preLeft > preRight && inLeft > inRight){
            return null;
        } else {
            // 这里得使用后序遍历
            Integer index = inMap.get(preorder[preLeft]);
            int leftLength = index - inLeft;
            int rightLength = inRight - index;
            TreeNode leftNode = buildTreeIn(preorder, preLeft + 1, preLeft + leftLength, inorder, inLeft, index - 1, inMap); // 构建左子树
            TreeNode rightNode = buildTreeIn(preorder, preLeft + leftLength + 1, preRight, inorder, index + 1, inRight, inMap); // 构建右子树
            TreeNode root = new TreeNode(preorder[preLeft]);
            root.left = leftNode;
            root.right = rightNode;
            return root;
        }
    }

    //（54）117. 填充每个节点的下一个右侧节点指针 II time：2024年8月25日10:47:41 -> 2024年8月25日10:51:11
    // 层次遍历 这里就不做了

    //（55）114. 二叉树展开为链表 time：2024年8月25日10:52:10 -> 2024年8月25日11:09:29
    // 思路：万事不要着急！画图模拟一下！对于二叉树就用最简单的三个节点来模拟即可！
    public void flatten(TreeNode root) {
        // 递归去做
        flattenIn(root);
    }
    public TreeNode flattenIn(TreeNode root) {
        if (root != null){
            TreeNode leftNode = flattenIn(root.left);// 展开左子树
            TreeNode rightNode = flattenIn(root.right);// 展开右子树
            // 1.先把右孩子根结点接到左孩子的最后一个结点上
            TreeNode cur = leftNode;
            if (cur == null){
                leftNode = rightNode;
            }else {
                while (cur.right != null){
                    cur = cur.right;
                }
                cur.right = rightNode;
            }

            // 2.左孩子置空
            root.left = null;

            //3.右孩子接上左孩子头结点
            root.right = leftNode;

            return root;
        } else {
            return null;
        }
    }

    //（56）129. 求根节点到叶节点数字之和 time：2024年8月25日11:11:35 -> 2024年8月25日11:26:13
    // 思路：此题得使用回溯来做吧：随时记录着当前路径，知道遇到叶子节点的时候才保存下来
    Integer allSum = 0;
    public int sumNumbers(TreeNode root) {
        StringBuilder blank = new StringBuilder();
        backTracking(root, blank);
        return allSum;
    }
    private void backTracking(TreeNode root, StringBuilder list){
        if (root != null){
            list.append(root.val);
            backTracking(root.left, list);
            list.deleteCharAt(list.length() - 1);
            backTracking(root.right, list);
            list.deleteCharAt(list.length() - 1);
            if (root.left == null && root.right == null){
                // 只有叶子节点的时候才计算总和
                int num = Integer.parseInt(list.toString());
                allSum += num;
            }
        } else {
            list.append("-"); // 加个字符，方便回溯
        }
    }

    //（57）129 方法二
    Integer sum = 0;
    public int sumNumbers1(TreeNode root) {
        backTracking(root, 0);
        return sum;
    }
    private void backTracking(TreeNode root, int curSum){
        if (root != null){
            curSum = curSum * 10 + root.val;
            backTracking(root.left, curSum);
            backTracking(root.right, curSum);
            if (root.left == null && root.right == null){
                // 只有叶子节点的时候才计算总和
                sum += curSum;
            }
        }
    }

    //（58）173. 二叉搜索树迭代器 time：2024年8月25日15:10:26 -> 2024年8月25日15:25:56
    // 见下面BSTIterator类：我巧妙使用的list存储来解决

    //（59）222. 完全二叉树的节点个数 time：2024年8月25日15:27:38 -> 2024年8月25日15:31:28
    Integer countNodes = 0;
    public int countNodes(TreeNode root) {
        countNodesIn(root);
        return countNodes;
    }
    // 无所谓哪个顺序遍历 反正是统计总个数
    public void countNodesIn(TreeNode root) {
        if (root != null){
            countNodes++; // 根节点
            countNodesIn(root.left); // 左子树
            countNodesIn(root.right); // 右子树
        }
    }

    //（60）236. 二叉树的最近公共祖先 time：2024年8月25日15:31:47 -> 2024年8月25日15:51:35
    // 思路：后序遍历：找到q或者p就直接返回！null也返回
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null){
            // 1.如果找到了p、q就直接返回
            if (root == p || root == q){
                return root;
            }

            // 2.后序遍历寻找
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left != null && right != null) return root;
            if (left != null) return left;
            return right;
        } else {
            return null;
        }
    }

    //（61）530. 二叉搜索树的最大绝对差 time：2024年8月26日10:04:01 -> 2024年8月26日10:12:31
    public int getMaxDifference(TreeNode root) {
        // 二叉搜索树：我们知道中序遍历是有序递增的，但这里不需要遍历全局，我们只需要找到最左边的节点、最右边的节点即可。
        TreeNode left = root;
        TreeNode right = root;
        while (left.left != null){
            left = left.left;
        }
        while (right.right != null){
            right = right.right;
        }

        // 计算最大绝对差值
        return right.val - left.val;
    }

    //（62）530. 二叉搜索树的最小绝对差 time：2024年8月26日10:13:19 -> 2024年8月26日10:27:48
    Integer minDiff = Integer.MAX_VALUE;
    Integer preVal = -100000;
    public int getMinimumDifference(TreeNode root) {
        // 此题思路：正常中序遍历，遍历一遍即可
        countMinDiff(root);
        return minDiff;
    }
    // 中序遍历
    public void countMinDiff(TreeNode root){
        if (root != null){
            countMinDiff(root.left); // 左孩子遍历
            minDiff = Math.min(minDiff, Math.abs(preVal - root.val));
            preVal = root.val; // 中间节点遍历
            countMinDiff(root.right); // 右孩子遍历
        }
    }

    //（63）230. 二叉搜索树中第 K 小的元素 time：2024年8月26日10:29:10 -> 2024年8月26日10:38:32
    Integer kthSmallest;
    Integer count = 0;
    public int kthSmallest(TreeNode root, int k) {
        // 中序遍历即可，到第k小保存下来
        kthSmallestIn(root, k);
        return kthSmallest;
    }
    public void kthSmallestIn(TreeNode root, int k) {
        if (root != null){
            kthSmallestIn(root.left, k); // 遍历左孩子

            count++; // 遍历中间节点
            if (count == k) kthSmallest = root.val;

            kthSmallestIn(root.right, k); // 遍历右孩子
        }
    }

    //（64）98. 验证二叉搜索树 time：2024年8月26日10:42:01 -> 2024年8月26日10:59:14
    Long pre = Long.MIN_VALUE;
    Boolean isValidBST = true;
    public boolean isValidBST(TreeNode root) {
        // 中序遍历是否是递增的
        isValidBSTIn(root);
        return isValidBST;
    }
    public void isValidBSTIn(TreeNode root) {
        // 中序遍历是否是递增的
        if (root != null){
            isValidBSTIn(root.left); // 左孩子
            if (pre >= root.val){ // 中节点
                isValidBST = false;
            }
            pre = (long) root.val;
            isValidBSTIn(root.right); // 右孩子
        }
    }

    //（65）9. 回文数 time：2024年8月26日11:04:04 -> 2024年8月26日11:11:23
    public boolean isPalindrome(int x) {
        // 1.所有负数都不是回文数
        if (x < 0){
            return false;
        }

        // 2.正数的判断
        StringBuilder num = new StringBuilder(String.valueOf(x));
        StringBuilder reverse = num.reverse();
        StringBuilder numNew = new StringBuilder(String.valueOf(x));
        int result = numNew.compareTo(reverse);

        return result == 0;
    }

    //（66）66. 加一 time：2024年8月26日14:30:24 -> 2024年8月26日14:47:48
    public int[] plusOne(int[] digits) {
        // 1.999等需要特殊处理（增加组数长度）
        boolean key = true;
        for (int digit : digits) {
            if (digit != 9) {
                key = false;
                break;
            }
        }
        if (key){
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }

        // 2.其他都只需要原数组上进行变换即可
        int remain = 1;
        for (int i = digits.length - 1; i >= 0; i--){
            int curR = (digits[i] + remain) / 10;
            int curV = (digits[i] + remain) % 10;
            remain = curR;
            digits[i] = curV;
        }
        return digits;
    }

    //（67）172. 阶乘后的零 time：2024年8月26日15:10:42 -> 2024年8月26日15:40:25
    // 思路：此题学到了！找尾尾随0的数量，其实就是找乘数中10的数量，而10只等于2*5，所以就是找2和5成对的数量，而其中2远远要比5的个数多，所以此题就变成在乘数中有多少5了？
    public int trailingZeroes(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++){
            int cur = i;
            while (cur > 0){
                // 1.如果上来就没有因子5，直接结束
                if (cur % 5 != 0){
                    break;
                }

                // 2.有因子5
                count++;
                cur = cur / 5;
            }
        }
        return count;
    }

    //（68）69. x 的平方根  time：2024年8月26日14:49:44 -> 2024年8月26日15:10:42
    public int mySqrt(int x) {
        if(x == 1) return 1;
        int pre = 1;
        for (int i = 1; i <= x / 2; i++){
            long cur = (long) i * i;
            if (cur < x){
                pre = i;
            } else if (cur == x){
                return i;
            } else if (cur > x){
                return pre;
            }
        }
        return x / 2;
    }

    //（69）215. 数组中的第K个最大元素 time： ->
    public int findKthLargest(int[] nums, int k) {

    }










        /**
         * ----------------------------------自测------------------------------------
         */
    public static void main(String[] args) {
        Interview150 interview = new Interview150();
//        Map<Character, Character> map = new HashMap<>();
//        map.put('e', 'a');
//        System.out.println(map.get('e'));
//        System.out.println(map.get('d'));

//        String path = "/.../a/..//b/c/../d/./";
//        String[] strings = path.split("/");
//        System.out.println(Arrays.toString(strings));
    }
}

/**
 * 173. 二叉搜索树迭代器
 */
class BSTIterator {
    Integer curIndex;
    List<Integer> values;

    public BSTIterator(TreeNode root) {
        // 开始初始化
        this.values = new ArrayList<>();
        BST(root);
        this.curIndex = 0;
    }
    private void BST(TreeNode root){
        if (root != null){
            BST(root.left); // 左子树
            this.values.add(root.val);
            BST(root.right); // 右子树
        }
    }

    public int next() {
        return values.get(curIndex++);
    }

    public boolean hasNext() {
        return curIndex < values.size();
    }
}

/**
 *（31）155. 最小栈 time：2024年8月22日09:48:19 -> 2024年8月22日10:08:41
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 */
class MinStack {

    Integer min;
    Stack<Integer> stack;

    public MinStack() {
        this.stack = new Stack<>();
        this.min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        stack.push(val);
        this.min = Math.min(min, val);
    }

    public void pop() {
        stack.pop();
        if (stack.isEmpty()){
            this.min = Integer.MAX_VALUE;
        } else {
            this.min = stack.peek();
        }
        stack.forEach(num -> {
            this.min = Math.min(num, min);
        });
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return this.min;
    }
}

/**
 * 可以用一个栈，这个栈同时保存的是每个数字 x 进栈的时候的值 与 插入该值后的栈内最小值。
 * 即每次新元素 x 入栈的时候保存一个元组：（当前值 x，栈内最小值）。
 */
class MinStack2 {

    // 把截止到每一个栈顶的当前最小值都记录在数据本身上
    Stack<int[]> stack;

    public MinStack2() {
        this.stack = new Stack<>();
        // 先插入一个假的栈底: 这样栈就永远不会为空，方便后续
        stack.push(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE});
    }

    public void push(int val) {
        // 每一个栈状态的最小值，都在这个地方计算下来
        int min = Math.min(stack.peek()[1], val);
        stack.push(new int[]{val, min});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}