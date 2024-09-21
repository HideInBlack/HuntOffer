package examination.vivo;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 第一题 time：2024年9月13日15:34:16 -> 2024年9月13日16:07:22
 */
public class Test1 {

    public static void main(String[] args) {
        int[] num = {80, 100, 70, 90, 60, 85, 75, 95, 110};
        getWave(num, 4);
    }

    // 维护两个单调队列解决 一个递增队列一个递减队列
    public static void getWave(int[] memoryUsage, int k){
        Deque<Integer> up = new LinkedList<>();
        Deque<Integer> down = new LinkedList<>();

        int[] result = new int[memoryUsage.length - k + 1];

        for (int i = 0; i < memoryUsage.length; i++){
            // 1.模拟滑动窗口出队
            if (i >= k){
                if (up.getFirst() == memoryUsage[i - k]) up.removeFirst();
                if (down.getFirst() == memoryUsage[i - k]) down.removeFirst();
            }

            // 2.维护单调增队列
            if (up.isEmpty() || memoryUsage[i] <= up.getLast()){
                up.addLast(memoryUsage[i]);
            } else {
                while (!up.isEmpty() && memoryUsage[i] > up.getLast()){
                    up.removeLast();
                }
                up.addLast(memoryUsage[i]);
            }

            // 3.维护单调减队列
            if (down.isEmpty() || memoryUsage[i] >= down.getLast()){
                down.addLast(memoryUsage[i]);
            } else {
                while (!down.isEmpty() && memoryUsage[i] < down.getLast()){
                    down.removeLast();
                }
                down.addLast(memoryUsage[i]);
            }

            // 4.计算结果
            if (i >= k - 1){
                result[i + 1 - k] = up.getFirst() - down.getFirst();
            }
        }
        System.out.println(Arrays.toString(result));
    }
}
