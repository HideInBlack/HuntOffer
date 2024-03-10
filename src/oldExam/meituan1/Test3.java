package oldExam.meituan1;

import java.util.Scanner;

/**
 * 第三题 time：2024年3月8日19:53:50 ->
 */
public class Test3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();//总数量

        int[] weight = new int[num];//所有权重
        for (int i = 0; i < num; i++){
            weight[i] = scanner.nextInt();
        }

        int[][] relation = new int[num - 1][2];//存储所有的关系
        for (int i = 0; i < num - 1; i++){
            relation[i][0] = scanner.nextInt();
            relation[i][1] = scanner.nextInt();
        }

//        System.out.println(Arrays.toString(weight));
        System.out.println(weight.length - num/2);
    }
}
