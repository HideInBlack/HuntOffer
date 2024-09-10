package examination.xiechengqiu;

import java.util.Scanner;

/**
 * 携程笔试 第一题 time：2024年9月5日19:00:00 -> 2024年9月5日19:17:53
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();

        int index = 0;
        for (int i = 0; i < n; i++){
            if (i + 1 >= k){
                System.out.print(n - index);
                System.out.print(" ");
                index++;
                continue;
            }
            System.out.print(i + 1);
            System.out.print(" ");
        }
    }
}
