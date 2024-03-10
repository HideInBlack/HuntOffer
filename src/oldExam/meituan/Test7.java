package oldExam.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第七题 time：2024年3月8日18:09:47 ->
 */
public class Test7 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inputStr = input.next();

        String[] strings = inputStr.split("\\.");

        //不合法情况1 不是四个
        if (strings.length != 4) {
            System.out.println("invalid");
            return;
        }

        //不合法情况2 不是数字，或者数字范围
        for (int i = 0; i < 4; i++){
            String cur = strings[i];
            if (cur.length() > 0 && cur.charAt(0) == '0'){
                //无效情况 -不是数字
                System.out.println("invalid");
                return;
            }else {
                //无效情况 -超过范围
                if (Integer.parseInt(cur) < 0 || Integer.parseInt(cur) > 255) {
                    System.out.println("invalid");
                    return;
                }
            }
        }


        //到达这里保证是有效的

        if (Integer.parseInt(strings[0]) >= 1 && Integer.parseInt(strings[0]) < 126){
            System.out.println("A_address");
            return;
        }
        if (Integer.parseInt(strings[1]) >= 128 && Integer.parseInt(strings[1]) < 192){
            System.out.println("B_address");
            return;
        }
        if (Integer.parseInt(strings[1]) > 192 && Integer.parseInt(strings[1]) < 224){
            System.out.println("B_address");
            return;
        }
        System.out.println("other");


    }
}
