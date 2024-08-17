package oldExam.huawei0417;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * √ 第二题 time：2024年4月23日21:47:31 -> 2024年4月23日22:29:47
 */
public class Test2 {
    public static void main(String[] args) {
        //核心思想：也是并查集 但是只能使用map来保存并查集了
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();

        //开始接受输入
        String[][] problem = new String[n][];
        input.nextLine();
        for (int i = 0; i < n; i++){
            String[] lines = input.nextLine().split(" ");
            problem[i] = lines;
        }

        //因为ai bi只能是字符串，所以只能只用map来保存并查集
        Map<String, String> father = new HashMap<>();
        //1.并查集初始化-建立father树
        for (int i = 0; i < n; i++){
            father.put(problem[i][0], problem[i][1]);
        }

        //2.开始遍历结算
        Map<String, int[]> count = new HashMap<>();
        for (int i = 0; i < n; i++){
            String root = findRoot(father, problem[i][0]);
            int[] values = count.getOrDefault(root, new int[2]);
            values[Integer.parseInt(problem[i][2])] += Integer.parseInt(problem[i][3]);
            count.put(root, values);
        }
        //3.计算最终的di
        int sum = 0;
        for (Map.Entry<String, int[]> entry : count.entrySet()){
//            System.out.print(entry.getKey() + " ");
//            System.out.println(Arrays.toString(entry.getValue()));
            int temp = 5 * entry.getValue()[0] + 2 * entry.getValue()[1];
            if (temp > m){
                sum++;
            }
        }
        System.out.println(sum);
    }

    //递归找根节点
    public static String findRoot(Map<String, String> father, String child){
        if (father.get(child).equals("*")){
            return child;
        }else {
            return findRoot(father, father.get(child));
        }
    }

}
