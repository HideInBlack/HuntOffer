package examination.xieCheng;

import java.util.*;

/**
 * 第三题 time：2024年3月13日21:13:30 -> 2024年3月13日21:37:53
 */
public class Test1 {

    public static void main(String[] args) {
        //[1(1),2(2),-3(1),-1(1),-1(1),1(2),1(3),-1(2)]
        Scanner input = new Scanner(System.in);
        String string = input.next();
        string = string.substring(1, string.length() - 1);
        String[] array = string.split(",");
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++){

            int leftIndex = array[i].indexOf('(');
            int rightIndex = array[i].indexOf(')');
            String value = array[i].substring(0, leftIndex);
            String count = array[i].substring(leftIndex + 1, rightIndex);

            int[] cur = new int[2];
            cur[0] = Integer.parseInt(value);
            cur[1] = Integer.parseInt(count);
//            System.out.println(Arrays.toString(cur));

            //第一个不做压缩
            if (i != 0){
                if (cur[0] == list.get(list.size() - 1)[0]){
                    list.get(list.size() - 1)[1] += cur[1];
                    continue;//相同的就不加入到list中去了
                }
            }

            list.add(cur);
        }

        System.out.print("[");
        for (int i = 0; i < list.size(); i++){

            System.out.print(list.get(i)[0]);
            System.out.print("(");
            System.out.print(list.get(i)[1]);
            System.out.print(")");
            if (i != list.size() - 1){
                System.out.print(",");
            }
        }
        System.out.print("]");
//        System.out.println(Arr.toString(strings));


    }
}
