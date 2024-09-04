package acm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 给一个有很多数字的字符串，和一个int整型的被除数。计算除法，要求有小数的最终四舍五入保留2位小数，可以被整除的不显示小数。
 * time：2024年9月2日09:40:19 ->
 */
public class BigDividerNew {

    public static void main(String[] args) {
        // 1.开始输入
        Scanner input = new Scanner(System.in);
        String bigNum = input.next();
        String num = input.next();

        // 2.核心操作
        BigDecimal bigNumDecimal = new BigDecimal(bigNum);
        BigDecimal numDecimal = new BigDecimal(num);
        BigDecimal divide = bigNumDecimal.divide(numDecimal, 2, RoundingMode.HALF_UP);

        // 3.开始判断是否需要保留小数
        // divide.stripTrailingZeros().scale()得到的是所有的0都抹去之后，所在的位置，所以<=0就代表是整数（小数点为坐标原点）
        if (divide.stripTrailingZeros().scale() <= 0){
            System.out.println(divide.toBigInteger());
        } else {
            System.out.println(divide);
        }
    }

}
