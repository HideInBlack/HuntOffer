package oldExam.meituan;

import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 第二题 time：2024年3月8日14:35:55 -> 2024年3月8日15:06:34
 */
public class Test2 {

    public static void main(String[] args) {
        //直接开始
        Scanner input = new Scanner(System.in);
        int boxNum = input.nextInt();
        int operate = input.nextInt();
        Set<Integer> set = new HashSet<>();

        int minValue = -1;
        int temp2 = 0;

        for (int i = 0; i < operate; i++){
            int x = input.nextInt();
            int y = input.nextInt();

            //在这里拦截不需要做的操作
            if (minValue != -1) continue;//已经找到了，不需要继续了

            if (x == 1){ //1.放入y的情况
                set.add(y);
            }else { //2.放入除了y之外的情况

                if (temp2 == y){ //剪枝优化
                    continue;
                }

                for (int j = 1; j <= boxNum; j++){
                    if (j != y){
                        set.add(j);
                        //做一次判断，如果找到就赋值！
                        if (set.size() == boxNum){
                            System.out.println(i + 1);
                            return;
                        }
                    }
                }
                temp2 = y;
            }

            //做一次判断，如果找到就赋值！
            if (set.size() == boxNum){
                minValue = i + 1; //赋值注意i+1！
            }

        }

        System.out.println(minValue);

    }

}
