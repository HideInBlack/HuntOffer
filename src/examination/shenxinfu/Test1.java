package examination.shenxinfu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第一题 time：2024年9月13日19:45:02 ->
 */
public class Test1 {
    static Integer count = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        char[] chars = string.toCharArray();
        Arrays.sort(chars);

        // 去重操作
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < chars.length; i++){
            if (i != 0 && chars[i] == chars[i - 1]){
                continue;
            }
            newString.append(chars[i]);
        }
        backTracking(newString.toString(), 0);
        System.out.println(count);
    }

    // 开始回溯
    private static void backTracking(String chars, int startIndex){
        for (int i = startIndex; i < chars.length(); i++){
            count++;
            backTracking(chars, i + 1);
        }
    }
}
