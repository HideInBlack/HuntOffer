package codeFlowIdea;

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
     * (3) LCR 122. 路径加密
     */
    public String pathEncryption(String path) {

        return "";
    }





    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

        //测试Java库函数的翻转字符串
//        String test = "abcdefghijk";
//        String result = new StringBuilder(test).reverse().toString();
//        System.out.println(result);

    }
}
