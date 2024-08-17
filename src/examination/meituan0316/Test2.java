package examination.meituan0316;

import java.util.Scanner;

/**
 * 第二题 time：2024年3月16日19:07:02 ->
 */
public class Test2 {
    public static void main(String[] args) {
//        char a = 'a';
//        char A = 'A';
//        System.out.println(Character.isLowerCase(a));
//        System.out.println(Character.isUpperCase(A));
//        System.out.println();
        Scanner input = new Scanner(System.in);
        String string = input.next();
        int isBig = 0;
        int isLow = 0;

        for (int i = 0; i < string.length(); i++){
            if (Character.isLowerCase(string.charAt(i))){
                isLow++;
            }else {
                isBig++;
            }
        }

        //判断是否本身已经合法
        if (Character.isUpperCase(string.charAt(0)) && isBig == 1){
            System.out.println(0);
            return;
        }

        if (isBig == isLow && Character.isUpperCase(string.charAt(0))){
            System.out.println(isLow - 1);
        }else {
            System.out.println(Math.min(isBig, isLow));
        }


//        System.out.println("isBig = " + isBig);
//        System.out.println("isLow = " + isLow);
    }
}
