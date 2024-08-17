package examination.meituan0316;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第一题 time：2024年3月16日19:02:10 ->
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        int n = input.nextInt();
        long[] cooks = new long[n];
        long sum = 0;
        for (int i = 0; i < cooks.length; i++){
            cooks[i] = input.nextLong();
            sum += cooks[i];
        }
        long x = input.nextLong();
        long y = input.nextLong();

//        System.out.println(Arrays.toString(cooks));
        System.out.println(sum - x - y);

    }

}
