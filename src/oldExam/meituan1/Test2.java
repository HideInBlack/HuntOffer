package oldExam.meituan1;

import java.util.Scanner;

/**
 * 第二题 time：2024年3月8日19:39:57 ->
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        String strS = input.next();
        String strT = input.next();

        int count = 0;
        for (int i = 0; i < num; i++){
            if (strS.charAt(i) == strT.charAt(i)){
                count++;
            }
        }

        System.out.println(count + 2);
    }
}
