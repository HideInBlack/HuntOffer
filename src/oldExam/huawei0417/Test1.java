package oldExam.huawei0417;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * √ 华为 4.17 第一题 time：2024年4月23日21:11:36 -> 2024年4月23日21:47:03
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++){
            list.add(input.next());
        }
//        System.out.println(list);

        //开始核心消消乐操作
        for (int i = 0; i <= list.size() - 3; i++){
            if (list.get(i).equals(list.get(i + 1)) && list.get(i + 1).equals(list.get(i + 2))){
                list.remove(i);
                list.remove(i);
                list.remove(i);
                //删除完之后就回退3个，为什么3个！因为需要回退2个，最后还要i++导致+1呢
                i = Math.max(-1, i - 3);
            }
        }
        if (list.isEmpty()) {
            System.out.println(0);
        }else {
            for (int i = 0; i < list.size(); i++){
                System.out.print(list.get(i) + " ");
            }
        }
    }

}
