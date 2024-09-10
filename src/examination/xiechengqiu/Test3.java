package examination.xiechengqiu;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 第三题 time：2024年9月5日20:00:34 -> 2024年9月5日20:38:29
 */
public class Test3 {
    static long sum = 0;
    static Set<Long> set = new HashSet<>();
    public static void main(String[] args) {
        // 此题必然是回溯的思想
        // 1.开始输入
        Scanner input = new Scanner(System.in);
        long n = input.nextLong();
        long m = input.nextLong();
        long k = input.nextLong();

        // 2.开始操作
        backTracking(n, m, k, new StringBuilder());
        System.out.println(sum);
    }
    public static void backTracking(long n, long m, long k, StringBuilder cur){
        for (long i = 0; i <= n; i++){
            if (cur.length() == 0 && i == 0){
                continue;
            }
            if (set.contains(i)){
                continue;
            }
            cur.append(i); // 拼接
            set.add(i);
            if (cur.length() == m){
                long curInt = Long.parseLong(cur.toString());
                if (curInt > k){
                    sum++;
                    cur.deleteCharAt(cur.length() - 1); // 回溯
                    set.remove(i);
                    continue;
                }

                cur.deleteCharAt(cur.length() - 1); // 回溯
                set.remove(i);
                continue;
            }
            backTracking(n, m, k, cur); // 递归
            cur.deleteCharAt(cur.length() - 1); // 回溯
            set.remove(i);
        }
    }
}
