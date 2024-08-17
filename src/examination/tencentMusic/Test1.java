package examination.tencentMusic;

import java.util.Scanner;

/**
 * 第二题
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int temp = 1;
        int sum = 1;
        for (int i = 1; i < n; i++){
            temp = temp * 2;
            sum += temp;
        }

        //开始建树操作
        int[] result = new int[sum];

        int key = 1;
        int sumTemp = 1;
        for (int i = 0; i < n; i++){
            if (i != 0){
                key = key * 2;
                sumTemp += key;
                temp = temp / 2;
                for (int j = sumTemp - key; j < sumTemp; j++){
                    result[j] = temp;
                }
            }else {
                result[0] = temp;
            }
        }

        //输出结果
        System.out.print("{");
        for (int i = 0; i < result.length; i++){
            if (i != result.length - 1){
                System.out.print(result[i]+ ",");
            }else {
                System.out.print(result[i]);
            }

        }
        System.out.print("}");
//        System.out.println(Arrays.toString(result));
    }
}
