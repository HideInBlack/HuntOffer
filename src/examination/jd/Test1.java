package examination.jd;

import java.util.Objects;
import java.util.Scanner;

/**
 * jd 第一题 time：2024年8月31日10:30:18 -> 2024年8月31日10:44:58
 */
public class Test1 {

    public static void main(String[] args) {
        // 1.输入
        Scanner input = new Scanner(System.in);
        String line = input.next();
        char[] chars = line.toCharArray();

        // 2.开始操作
        for (int i = chars.length - 1; i >= 0; i--){
            if (chars[i] != 'z'){
                chars[i] = (char) (chars[i] + 1);
                break;
            }
            if (chars[i] == 'z' && i == 0){
                System.out.println(-1);
                return;
            }
        }

        // 3.打印结果
        String result = new String(chars);
        System.out.println(result);
    }
}
