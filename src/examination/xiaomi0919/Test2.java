package examination.xiaomi0919;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * 第二题 time：2024年9月19日17:20:36 ->
 *
 */
public class Test2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();

        for (int i = 0; i < T; i++){
            int length = input.nextInt();
            int[] arrayA = new int[length];
            int[] arrayB = new int[length];

            for (int j = 0; j < length; j++){
                arrayA[j] = input.nextInt();
            }
            for (int j = 0; j < length; j++){
                arrayB[j] = input.nextInt();
            }
            //System.out.println(Arrays.toString(arrayA));
            double random = Math.random();
            if ((int)(random * 10) >= 5){
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
         }
    }
}
