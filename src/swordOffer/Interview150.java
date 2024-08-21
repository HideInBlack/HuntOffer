package swordOffer;

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

    //（31）155. 最小栈 time： ->














        /**
         * ----------------------------------自测------------------------------------
         */
    public static void main(String[] args) {
        Interview150 interview = new Interview150();
//        Map<Character, Character> map = new HashMap<>();
//        map.put('e', 'a');
//        System.out.println(map.get('e'));
//        System.out.println(map.get('d'));
        String path = "/.../a/..//b/c/../d/./";
        String[] strings = path.split("/");
        System.out.println(Arrays.toString(strings));


    }
}
