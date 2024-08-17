package acm;

import java.util.Scanner;

public class HJ33ip {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String ip = in.next();
        long intAddress = in.nextLong();

        String[] strings = ip.split("\\.");
        StringBuilder allTwo = new StringBuilder();
        for (int i = 0; i < strings.length; i++){
            allTwo.append(tenToTwo(Integer.parseInt(strings[i])));
        }
        long tenResult = twoToTen(allTwo.toString());
        System.out.println(tenResult);

        //整数 -> ip地址
        String two4 = tenToTwo2(intAddress);
        StringBuilder result = new StringBuilder();
        result.append(twoToTen(two4.substring(0, 8))).append(".");
        result.append(twoToTen(two4.substring(8, 16))).append(".");
        result.append(twoToTen(two4.substring(16, 24))).append(".");
        result.append(twoToTen(two4.substring(24, 32)));

        System.out.println(result);
    }

    // HJ33 整数与IP地址间的转换 time：2024年3月22日10:31:07 -> 2024年3月22日11:42:58
    public static String tenToTwo(int num){

        //0需要特殊处理
        if (num == 0) {
            return "00000000";
        }

        int remain = 0;
        StringBuilder result = new StringBuilder();
        while (num != 1){
            remain = num % 2;
            num = num / 2;
            result.append(remain);
        }
        result.append("1");

        //判断是否需要补充到8位
        if (result.length() < 8){
//            result.append("0".repeat(8 - result.length()));
            for (int i = result.length() + 1; i <= 8; i++){
                result.append("0");
            }
        }

        result = result.reverse();
        return result.toString();
    }

    public static String tenToTwo2(long num){

        //0需要特殊处理
        if (num == 0) {
            return "00000000000000000000000000000000";
        }

        long remain = 0;
        StringBuilder result = new StringBuilder();
        while (num != 1){
            remain = num % 2;
            num = num / 2;
            result.append(remain);
        }
        result.append("1");

        //判断是否需要补充到8位
        if (result.length() < 32){
//            result.append("0".repeat(32 - result.length()));
            for (int i = result.length() + 1; i <= 32; i++){
                result.append("0");
            }
        }

        result = result.reverse();
        return result.toString();
    }

    public static long twoToTen(String twoNum){
        long result = 0;
        long curIndex = 1;
        for (int i = twoNum.length() - 1; i >= 0; i--){
            if (twoNum.charAt(i) == '1'){
                result += curIndex;
            }
            //无论0-1都要先要做这个计算
            curIndex = curIndex * 2;
        }
        return result;
    }

}
