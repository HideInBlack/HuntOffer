package oldExam.meituan;


import java.util.Scanner;

/**
 * 第二题 time：2024年3月8日16:45:44 -> 2024年3月8日16:57:37
 */
public class Test5 {

    // 直接开始写
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int y = input.nextInt();
        int z = input.nextInt();
        int count = 1; //天数
        int curValue = 0;

        while (true){
            //1.首先每天都可以浇水
            curValue += x;
            //2.只有 % 3 = 1的时候才可以施肥
            if (count % 3 == 1){//1. 既可以浇水又可以施肥
                curValue += y;
            }

            //判断成长值
            if (curValue >= z) break;

            //更新天数
            count++;
        }
        System.out.println(count);

    }
}
