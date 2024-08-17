package oldExam.huawei0410;

import java.util.*;

/**
 * 第一题 云服务计费 time：2024年4月22日21:28:22 -> 2024年4月22日22:08:12
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        String[][] logs = new String[n][];
        for (int i = 0; i < logs.length; i++){
            String next = input.next();
            String[] strings = next.split(",");
            logs[i] = strings;
        }

        int m = input.nextInt();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++){
            String next = input.next();
            String[] strings = next.split(",");
            map.put(strings[0], Integer.valueOf(strings[1]));
        }

        //开始操作
        TreeMap<String, Integer> result = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i = 0; i < logs.length; i++){
            int temp = Integer.parseInt(logs[i][3]);
            if (temp < 1 || temp > 100){
                temp = 0;
            }
            Integer curSum = result.getOrDefault(logs[i][1], 0);
            Integer count = map.getOrDefault(logs[i][2], 0);
            curSum += count * temp;
            result.put(logs[i][1], curSum);
        }


        //打印一下
        for (String key : result.keySet()){
            System.out.println(key + "," + result.get(key));
        }
    }

}
