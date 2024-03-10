package oldExam.meituan1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 第一题 time：2024年3月8日19:04:17 ->
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int nums = input.nextInt();
        double[][] money = new double[nums][2];

        //全部原价总额
        double allMoney = 0;
        //全部折扣总额
        double allDiscount = 0;

        for (int i = 0; i < nums; i++){
            money[i][0] = input.nextDouble();
            money[i][1] = input.nextDouble();

            allMoney += money[i][0];
            allDiscount += money[i][1];

            //不合法情况
            if (money[i][1] > money[i][0]){
                System.out.println("error");
                return;
            }
            if (money[i][1] <= 0 || money[i][0] <= 0){
                System.out.println("error");
                return;
            }
        }
        double man = input.nextDouble();
        double remove = input.nextDouble();
        if (man <= 0 || remove <= 0){
            System.out.println("error");
            return;
        }

        //不合法情况
        if (remove > man){
            System.out.println("error");
            return;
        }

        //1.计算折扣方案的结果:allDiscount就是
        //2.计算满减后价格
        double manjian = 0;
        if (allMoney >= man){
            manjian = allMoney - remove;
        }else {
            manjian = allMoney;
        }

        //两者比较
        double result = Math.min(manjian, allDiscount);
        String four = String.format("%.2f",result);
        System.out.println(four);

    }

}
