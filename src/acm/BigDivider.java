package acm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class BigDivider {
    /**
     *（75）2737:大整数除法 http://bailian.openjudge.cn/practice/2737/
     * time：2024年8月30日15:28:09 -> 2024年8月30日15:59:53
     * 最简单的实现方法
     */
    public static void main(String[] args) {
        // 1.控制台输入
        Scanner input = new Scanner(System.in);
        String a = input.next();
        String b = input.next();

        // 2.使用BigDecimal
        BigDecimal bigA = new BigDecimal(a);
        BigDecimal bigB = new BigDecimal(b);
//        BigDecimal divide = bigA.divide(bigB, 0, RoundingMode.DOWN); // 仅保留整数部分（向下取整）
        BigDecimal divide = bigA.divide(bigB, 2, RoundingMode.HALF_UP); // 保留两位小数并且四舍五入

        // 3.检查结果是否是整数
        if (divide.stripTrailingZeros().scale() <= 0) {
            System.out.println(divide.toBigInteger());  // 如果是整数，返回整数部分
        } else {
            System.out.println(divide); // 四舍五入保留两位小数
        }
    }
}
