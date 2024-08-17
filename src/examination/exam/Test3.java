package examination.exam;

import java.util.Scanner;

/**
 * 第三题 time：2024年3月9日10:24:52 ->
 */
public class Test3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[][] array = new int[size][size];

        //接受二维数组
        int bigCount1 = 0;
        int bigCount0 = 0;

        for (int i = 0; i < array.length; i++){
            String string = input.next();
            char[] chars = string.toCharArray();

            for (int j = 0; j < array.length; j++){
                array[i][j] = Integer.parseInt(chars[j]+"");
                if (array[i][j] == 0){
                    bigCount0++;

                }else {
                    bigCount1++;
                }
            }
        }
//        System.out.println("bigCount0 = " + bigCount0);
//        System.out.println("bigCount1 = " + bigCount1);

        int[] result = new int[size];
        result[0] = 0;
        if (bigCount0 == bigCount1){
            result[size - 1] = 1;
        }else {
            result[size - 1] = 0;
        }

        //开始处理所有的子数组
        for (int k = 2; k < array.length; k++){
//            System.out.println(k);

            int count = 0;
            for (int i = 0; i <= array.length - k; i++) {
                for (int j = 0; j <= array.length - k; j++) {
                    if (sonArray(array, k, i, j)){
                        count++;
                    }

//                    System.out.println(sonArray(array, k, i, j));
//                    System.out.println("-------------");
                }
            }
            result[k - 1] = count;

        }

        //打印结果
        for (int i = 0; i < result.length; i++){
            System.out.println(result[i]);
        }



    }

    //开始遍历n * n 的二维 i * i 的子数组 指定一个左上角顶点 遍历
    public static boolean sonArray(int[][] array, int lines, int x, int y){
        int count1 = 0;
        int count0 = 0;

        for (int i = x; i < x + lines; i++){
            for (int j = y; j < y + lines; j++){
//                System.out.print(array[i][j] + " ");
                if (array[i][j] == 0){
                    count0++;
                    if (count0 > lines * lines / 2){
                        return false;
                    }

                }else {
                    count1++;
                    if (count1 > lines * lines / 2){
                        return false;
                    }
                }
            }
//            System.out.println();
        }
        return count0 == count1;
    }
}
