package oldExam.huawei0410;

import java.util.*;

/**
 * 第二题 time：2024年4月22日22:09:10 -> 2024年4月22日22:50:14(错误)
 * 并查集做法 time：2024年4月23日14:22:21 ->
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        //直接开始操作
        int[] type = new int[n]; //使用int数组保存此时的图片到底属于哪一组
        Map<Integer, Integer> map = new HashMap<>(); //map保存其中的key为type， value为相似度综合
        int curType = 1;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                //每个都需要读取，但并不是每一个都需要处理
                int curValue = input.nextInt();

                if (j <= i && curValue > 0){ //只有j <= i才进行处理

                    if (type[j] == 0){
                        type[i] = curType;
                        type[j] = curType;
                        map.put(curType, curValue);
                        curType++;
                    }else {
                        type[i] = type[j];
                        map.put(type[j], map.get(type[j]) + curValue);
                    }

                }

            }
        }
        for (int i = 0; i < type.length; i++){
            if (type[i] == 0){
                map.put(curType, 0);
                curType++;
            }
        }

        List<Integer> list = new ArrayList<>(map.values().stream().toList());
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
//        System.out.println(Arrays.toString(type));
//        System.out.println(list);
        //开始打印
        for (int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
    }


}
