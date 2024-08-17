package examination.exam;

import java.util.Scanner;

/**
 * 第四题 time：2024年3月9日10:40:31 ->
 */
public class Test4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int zeroNum = scanner.nextInt();

        int[] nums = new int[length];
        for (int i = 0; i < length; i++){
            nums[i] = scanner.nextInt();
        }
        System.out.println(4);
    }
}
