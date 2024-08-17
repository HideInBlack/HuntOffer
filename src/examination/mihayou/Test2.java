package examination.mihayou;

import java.util.*;

/**
 * 第二题 time：2024年3月17日11:37:00 ->
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        Set<Long> setA = new HashSet<>();
        Set<Long> setB = new HashSet<>();
        for (int i = 0; i < n; i++){
            setA.add(input.nextLong());
        }
        for (int i = 0; i < n; i++){
            setB.add(input.nextLong());
        }

        //存储完毕 开始计算
        Set<List<Long>> xy = new HashSet<>();
        for (Long valueA : setA){
            for (Long valueB : setB){

                List<Long> list1 = new ArrayList<>();
                list1.add(valueA);
                list1.add(valueB);
                List<Long> list2 = new ArrayList<>();
                list2.add(valueB);
                list2.add(valueA);

                xy.add(list1);
                xy.add(list2);

            }
        }
//        System.out.println(xy);

        //查出所有的情况
        long count = 0;

        for (List<Long> xAndy1 : xy){
            for (List<Long> xAndy2 : xy){
                System.out.print(xAndy1);
                System.out.print(xAndy2);
                System.out.println();
//                if (xAndy2.get(0) <= xAndy1.get(0) && xAndy2.get(1) <= xAndy1.get(1)){
//                    count++;
//                }
            }

        }
        System.out.println(count);

        //
//        List<Long> list1 = new ArrayList<>();
//        list1.add(1L);
//        list1.add(2L);
//
//        List<Long> list2 = new ArrayList<>();
//        list2.add(1L);
//        list2.add(3L);
//
//        Set<List<Long>> result = new HashSet<>();
//        result.add(list1);
//        result.add(list2);
//
//        System.out.println(result);

    }
}
