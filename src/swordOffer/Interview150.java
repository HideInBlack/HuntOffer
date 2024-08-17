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



        /**
         * ----------------------------------自测------------------------------------
         */
    public static void main(String[] args) {
        Interview150 interview = new Interview150();
        char a = Character.toLowerCase('A');
        System.out.println(a);
        System.out.println(Character.isUpperCase('0'));
        System.out.println(Character.isLowerCase('0'));
    }
}
