package codeFlowIdea;

import java.util.Arrays;
import java.util.Objects;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年10月25日21:09:36 ->
 * author：董政宇
 * 第四部分 字符串部分：MyString
 */
public class MyString {

    /**
     * √（1）344. 反转字符串 time：2023年10月25日21:13:33 -> 2023年10月25日21:18:13
     * 我的思路：典型的双指针 不让用新建数组
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char temp;
        while (left < right){
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * √（2）541. 反转字符串 II time：2023年10月25日21:22:34 -> 2023年10月25日21:44:09
     * 我的思路：转化成数组来翻转 翻转之后再new成字符串 直接根据总结的公式来：每次都是0k -> k-1、2k -> 3k-1、4k -> 5k-1再反转！
     * 注意：其中最需要注意的地方是Math.min(right, chars.length - 1)！不让右边界越界！以及停止条件是左边界停止！
     */
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int left = 0; int right = k - 1;
        while (left < chars.length){
            //直接进行翻转 根据公式来
            reverseString2(chars, left, Math.min(right, chars.length - 1));//这里要注意 如果right>char.length-1 直接去数组的长度不越界！
            left += 2*k;
            right += 2*k;
        }
        s = new String(chars);
        return s;
    }
    //再写一个按照下标翻转字符串数组的方法
    public void reverseString2(char[] s, int left, int right) {
        char temp;
        while (left < right){
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * √ (3) LCR 122. 路径加密 time：2023年10月26日09:27:29 -> 2023年10月26日09:33:39
     * 我的思路：先把path转化成array 然后遍历一遍操作即可
     */
    public String pathEncryption(String path) {
        char pathArray[] = path.toCharArray();
        for(int i = 0; i < pathArray.length; i++){
            if (pathArray[i] == '.'){
                pathArray[i] = ' ';
            }
        }
        return new String(pathArray);
    }
    // √（3.1）剑指Offer 05.替换空格 time：2023年10月26日09:53:29 -> 2023年10月26日10:06:31
    //示例 1： 输入：s = "We are happy."  输出："We%20are%20happy."
    public String pathEncryption2(String path) {
        char pathArray[] = path.toCharArray();
        String result = "";
        for(int i = 0; i < pathArray.length; i++){
            if (pathArray[i] == ' '){
                result += "%20";
            }else {
                result += pathArray[i];
            }
        }
        return result;
    }

    /**
     * √（4）151. 反转字符串中的单词 time：2023年10月26日10:09:00 -> 2023年10月26日10:27:20
     * 重要注意点：1.注意空格也会被分开 但是只会被分成“” 或者“ ”。 2.字符串的截取substring(i, j) 是左闭右开
     */
    public String reverseWords(String s) {
        String[] array = s.split(" ");
        String result = "";
        for (int i = array.length - 1; i >= 0; i--){
            if (!Objects.equals(array[i], " ") && !Objects.equals(array[i], "")){
                result += array[i] + " ";
            }
        }
        return result.trim();
    }
    // √ 题解方法二 （4）151. 反转字符串中的单词 time：2023年10月26日14:37:35 -> 2023年10月26日14:55:18
    public String reverseWords2(String s) {
        //先去除前后缀的空白符
        s = s.replaceAll("\\s+", "");
        String stringArray[] = s.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = stringArray.length - 1; i >= 0; i--){
            result.append(stringArray[i]);
            if (i != 0){
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * √（5）LCR 182. 动态口令 time：2023年10月26日15:02:01 -> 2023年10月26日15:06:46
     * 我的思路：先使用StringBuilder来操作 练手
     */
    public String dynamicPassword(String password, int target) {
        StringBuilder stringBuilder = new StringBuilder(password);
        //注意是左闭右开
        String former = stringBuilder.substring(0, target);
        stringBuilder.delete(0, target);
        stringBuilder.append(former);
        return stringBuilder.toString();
    }
    // 方法二 （5）LCR 182. 动态口令
    //题解思路：局部翻转 + 局部翻转 + 全部翻转 【左旋等思路】
    public String dynamicPassword2(String password, int target) {
        char passwordArray[] = password.toCharArray();
        reverseString2(passwordArray, 0, target - 1);
        reverseString2(passwordArray, target, passwordArray.length - 1);
        reverseString2(passwordArray, 0, passwordArray.length - 1);
        return new String(passwordArray);
    }

    /**
     * （6）28. 找出字符串中第一个匹配项的下标 time：2023年10月26日15:23:41 -> 2023年10月26日15:25:26
     * StringBuilder可以使用但是过于简单
     */
    public int strStr(String haystack, String needle) {
        //我先用StringBuilder来试试
        StringBuilder stringBuilder = new StringBuilder(haystack);
        return stringBuilder.indexOf(needle);
    }
    // √ 方法二 （6）28. 找出字符串中第一个匹配项的下标  time：2023年10月26日15:31:05 -> 2023年10月26日15:58:46
    public int strStr2(String haystack, String needle) {
        char arrayA[] = haystack.toCharArray();
        char arrayB[] = needle.toCharArray();
        if (haystack.length() < needle.length()){
            return -1;
        }
        int temp;
        //两个for循环搞定
        for (int i = 0; i < arrayA.length; i++){
            //如果第一个数字匹配才继续往前走
            if (arrayA[i] == arrayB[0]){
                temp = i;
                for (int j = 0; j < arrayB.length; j++){
                    //这里的i要加哦！
                    if (temp >= arrayA.length || arrayA[temp] != arrayB[j]){
                        break;
                    }
                    if (j == arrayB.length - 1 && arrayA[temp] == arrayB[j]){//如果匹配到最后一个还相等 那就直接返回结果了
                        return i;
                    }
                    temp++;
                }
            }
        }
        return -1;
    }

    /**
     * ×【不会做】（7）459. 重复的子字符串 time：2023年10月26日16:03:43 -> 2023年10月26日16:16:33
     */
    public boolean repeatedSubstringPattern0(String s) {
        return false;
    }
    // √ 题解方法一【暴力遍历】 （7）459. 重复的子字符串 time：2023年10月26日16:39:51 -> 2023年10月26日16:52:26
    //首先主要思路：可重复n次，则n一定是s.length的因数！遍历每一个因数，则所有的j一定与j-n相等才可以！
    public boolean repeatedSubstringPattern(String s) {
        boolean result = true;
        //n意为：n一组的开始重复。因为其最少重复两次 所以其因数最大就是长度的一半
        for (int n = 1; n <= s.length() / 2; n++){//从遍历所有的因数开始！！！
            if (s.length() % n == 0){//找到最小重复组数n
                //这里从n个开始往下遍历 一直与第j前的n个比较下去到最后！
                result = true;
                for (int j = n; j < s.length(); j++){
                    if (s.charAt(j) != s.charAt(j-n)){
                        result = false;
                        break;
                    }
                }
                if (result){
                    return true;
                }
            }
        }
        return false;
    }
    // √ 题解方法二【移动匹配】 （7）459. 重复的子字符串 time：2023年10月26日17:07:37 -> 2023年10月26日17:07:45
    //思路：s + s, 掐头去尾后 如果还存在s 则此字符串可以由重复的子字符串构成
    public boolean repeatedSubstringPattern2(String s) {
        //拼接s + s
        StringBuilder stringBuilder = new StringBuilder(s + s);
        //掐头去尾 左闭右开
        int result = stringBuilder.substring(1, stringBuilder.length()-1).indexOf(s);
        return result != -1;
    }
    /**
     * √ 空格去重尝试（去重的思想！） time：2023年10月26日18:52:16 -> 2023年10月26日19:13:17
     * 题目意思：输入：__hello___world__  输出：hello_world
     */
        public String deleteSpace(String s){
            s = s.replaceAll("\\s+", "");
            StringBuilder stringBuilder = new StringBuilder(s);
            for (int i = 0; i < stringBuilder.length(); i++){
                //此为空格 且前一个也是空格才进行删掉【不删除第一个空格，只删除第一个之后的空格】
                if (stringBuilder.charAt(i) == ' ' && stringBuilder.charAt(i-1) == ' '){
                    stringBuilder.deleteCharAt(i);
                    //这里特别注意！因为你把它删掉了所以要i--
                    i--;
                }
            }
            return stringBuilder.toString();
        }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyString myString = new MyString();

        //测试 空格去重尝试
        System.out.println(myString.deleteSpace("  h  e   l   l   o    "));
        System.out.println(myString.deleteSpace("  hello   world   "));
        System.out.println(myString.deleteSpace("i     hello      world     !"));

        //测试StringBuilder
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("hello world");
//        System.out.println(stringBuilder);
//        System.out.println(stringBuilder.indexOf("l"));//查询多个字符的位置
//        System.out.println(stringBuilder.indexOf("l", 4));//从n位置开始查询首个“字符串”的位置
//        //下面是增删改查 （涉及双边界都是左闭右开）
//        int index = stringBuilder.indexOf(" ");
//        System.out.println(stringBuilder.replace(index, index + 1, ".."));//替换多个字符串（setCharAt：替换单个）
//        System.out.println(stringBuilder.delete(index, index + 2));//删除多个（deleteCharAt：删除单个）
//        System.out.println(stringBuilder.insert(index, "!-!"));//插入多个字符串
//        stringBuilder.setCharAt(index, '%');//替换单个字符
//        System.out.println(stringBuilder);
//        System.out.println(stringBuilder.charAt(index));//查询单个字符
//        System.out.println(stringBuilder.substring(0, 5));//获取任意位置子“字符串”
//        System.out.println(stringBuilder.reverse());//字符翻转

        //测试 （4）151. 反转字符串中的单词
//        String string = "  hello   world";
//        System.out.println(myString.reverseWords(string));

        //测试 （3.1）剑指Offer 05.替换空格
//        System.out.println(myString.pathEncryption2("We are happy."));

        //测试Java库函数的翻转字符串
//        String test = "abcdefghijk";
//        String result = new StringBuilder(test).reverse().toString();
//        System.out.println(result);

    }
}
