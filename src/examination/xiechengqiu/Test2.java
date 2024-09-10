package examination.xiechengqiu;

import java.util.Scanner;

/**
 * 第二题 time：2024年9月5日19:18:13 -> 2024年9月5日20:00:16
 */
public class Test2 {
    public static void main(String[] args) {
        // 1.开始输入
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String string = input.next();

        // 2.开始遍历所有的子串
        int sum = 0;
        int count = 0;
        for (int i = 0; i < string.length(); i++){
            for (int j = i; j < string.length(); j++){
                // 长度为奇数的子串才开始统计权值
                if ((j - i) % 2 == 0){
                    // 剪枝优化
                    if (j != i && j != i + 1 && string.charAt(j) == string.charAt(j - 1) && string.charAt(j) == string.charAt(j - 2) ){
                        if (count % 2 == 1) {
                            sum++;
                        }
                        continue;
                    }
                    count = getCount(string, i, j);
                    if (count % 2 == 1) {
                        sum++;
                    }
                }

            }
        }

        System.out.println(sum);
    }
    // 先写一个统计字符串需要操作多少次的函数
    public static int getCount(String target, int left, int right){
        int count = 1;
        for (int i = left; i < right; i++){
            if (target.charAt(i) != target.charAt(i + 1)){
                count++;
            }
        }
        if (target.charAt(right) == '1'){
            count--;
        }
        return count;
    }
}
