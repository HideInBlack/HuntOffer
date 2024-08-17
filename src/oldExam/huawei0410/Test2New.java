package oldExam.huawei0410;

import java.util.*;

/**
 * √ 并查集做法 time：2024年4月23日14:22:59 -> 2024年4月23日15:17:43
 */
public class Test2New {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        //使用数组保存所有的其根节点是什么
        int[] father = new int[n];
        Map<Integer, Integer> map = new HashMap<>();

        //并查集初始化为 father[i]=i
        for (int i = 0; i < father.length; i++){
            father[i] = i;
        }

        //开始核心处理
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                int temp = input.nextInt();

                if (j <= i && temp > 0){
                    join(father, i, j, temp, map);
                }
            }

        }

//        System.out.println(Arrays.toString(father));
//        System.out.println(map);
        List<Integer> list = new ArrayList<>(map.values().stream().toList());
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        //开始打印
        for (int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
    }

    //并查集操作-递归遍历寻找根节点
    private static int findRoot(int[] father, int num){
        if (father[num] == num){ //1.找到了
            return num;
        }else {
            return father[num] = findRoot(father, father[num]); //2.继续递归找
        }
    }

    //并查集操作-把i，j加入到并查集中(过程就是找到i的根，找到j的根；如果root一样不进行操作，如果不一样则把j的根改成i的根 合成一个)
    private static void join(int[] father, int i, int j, int value, Map<Integer, Integer> map){
        int iRoot = findRoot(father, i);
        int jRoot = findRoot(father, j);
        if (iRoot == jRoot) {
            //做相似度总和计算的操作
            map.put(iRoot, map.getOrDefault(iRoot, 0) + value);
            return;
        }
        father[jRoot] = iRoot;
        //做相似度总和计算的操作
        map.put(iRoot, map.getOrDefault(iRoot, 0) + map.getOrDefault(jRoot, 0) + value);
        //合并之后jRoot要删掉
        map.remove(jRoot);
    }

}
