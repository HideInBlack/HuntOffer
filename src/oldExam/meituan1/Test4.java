package oldExam.meituan1;

import java.util.Scanner;

/**
 * 第四题 time：2024年3月8日20:04:17 ->
 * 注意：一定要查看范围！ 是使用int 还是long！ 注意一定运行main 不要点下面的！
 */
public class Test4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();

        int[] nums = new int[length];// 存储所有排列
        for (int i = 0; i < length; i++){
            nums[i] = scanner.nextInt();
        }

        int x = scanner.nextInt();
        int y = scanner.nextInt();

        //开始遍历判断(往后判断！)
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] == x){
                if (nums[i + 1] == y){
                    System.out.println("Yes");
                    return;
                }
            }
            if (nums[i] == y){
                if (nums[i + 1] == x){
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");

    }
}
