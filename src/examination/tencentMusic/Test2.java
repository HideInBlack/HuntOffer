package examination.tencentMusic;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 第三题 time：2024年4月18日20:17:07 -> 2024年4月18日20:36:21
 */
public class Test2 {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; //降序
            }
        });

        Scanner input = new Scanner(System.in);
        String text = input.next();
        int k = input.nextInt();
        int count = 0;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == '1'){
                count++;
            }else {
                //先入队
                if (count == 0) continue;
                queue.add(count);
                count = 0;
            }
            //最后一个1 特殊处理
            if (i == text.length() - 1 && text.charAt(i) == '1'){
                queue.add(count);
            }
        }
        System.out.println(queue);

        //开始计算最小值操作
        while (k > 0 && !queue.isEmpty()){
            //只有k大的时候 才出队
            if (k > queue.peek()){
                int maxValue = queue.poll();
                k = k - maxValue;
            }else {
                int maxValue = queue.poll();
                maxValue = maxValue - k;
                k = 0;
                queue.add(maxValue);
            }

        }
        if (queue.isEmpty()){
            System.out.println(0);
        }else {
            System.out.println(queue.peek());
        }

    }
}
