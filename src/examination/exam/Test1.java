package examination.exam;

import java.util.Scanner;

/**
 * 第一题 time：2024年3月9日10:03:21 ->
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();//字符长度
        int k = input.nextInt();//操作次数
        String strings = input.next();

        int count = 0;
        for (int i = 0; i < strings.length(); i++){
            if (strings.charAt(i) != 'M' && strings.charAt(i) != 'T'){
                if (k > 0){
                    count++;
                    k--;
                }
            }else {
                count++;
            }
        }
        System.out.println(count);
    }
}
