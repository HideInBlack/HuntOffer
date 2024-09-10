package examination.meituanqiu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 第二题 time：2024年9月7日10:33:24 -> 2024年9月7日10:51:39
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // 代表几组数据

        while (n > 0){
            n--;
            // 每一组进行操作
            int k = input.nextInt();

            // 1.遍历收集
            Map<Integer, Integer> sonNum = new HashMap<>();
            Map<Integer, Integer> aggregation = new HashMap<>();
            for (int i = 0; i < k - 1; i++){
                int x = input.nextInt();
                int y = input.nextInt();
                sonNum.put(x, sonNum.getOrDefault(x, 0) + 1);
                if (!sonNum.containsKey(y)){
                    sonNum.put(y, 0);
                }
            }

            // 2.开始聚合操作
            sonNum.forEach((key, value) -> {
                aggregation.put(value, aggregation.getOrDefault(value, 0) + 1);
            });

            AtomicInteger sum = new AtomicInteger();
            aggregation.forEach((key, value) -> {
                int cur = (value * (value - 1)) / 2;
                sum.addAndGet(cur);
            });
            System.out.println(sum);
        }
    }
}
