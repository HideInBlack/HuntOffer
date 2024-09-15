package acm;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 给一个有很多数字的字符串，和一个int整型的被除数。计算除法，要求有小数的最终四舍五入保留2位小数，可以被整除的不显示小数。
 * 大数除法 time：2024年9月1日10:22:46 -> 2024年9月1日14:20:42
 * 任然不完美！需要借用到大数相加！已经要多补0得到最终结果
 */
public class BigDividerOwn {

    public static void main(String[] args) {
        // 1.输入
        String str = "12345678912345678912345678911";
        int divider = 695;

        // 2.开始操作
        int numLength = (divider + "").length();
        int strLength = str.length();

        BigDecimal result = new BigDecimal(0); // 结果商
        long remain = 0; // 余数：默认为0
        int index = 0; // 下标：默认为0
        while (true){
            // 1.组成当前被除数
            int nextSteps = numLength + 1; // 下一步要走多少位
            StringBuilder cur = new StringBuilder();

            if (remain != 0) {
                nextSteps -= (remain + "").length();
                cur.append(remain);
            }

            if (index + nextSteps > strLength) break;  // 如果越界即可跳出终止
            while (nextSteps > 0){
                cur.append(str.charAt(index++));
                nextSteps--;
            }

            // 2.开始计算当前商和余数
            long curNum = Long.parseLong(cur.toString());
//            BigDecimal value = new BigDecimal((curNum / divider + "0".repeat(strLength - index)));
            BigDecimal value = new BigDecimal((curNum / divider + "0"));
            result = result.add(value);
            remain = curNum % divider;
        }

        // 3.输出结果
        System.out.println(result);
    }
}
