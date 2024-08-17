package examination.mihayou;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 第一题 time：2024年3月17日10:33:16 ->
 */
public class Test1 {

    public static void main(String[] args) {

//        long[] nums = {0, -100, -1, 0};
//        System.out.println(isAllKilled(nums));


        Scanner input = new Scanner(System.in);
        int query = input.nextInt();

        //总共有以下query组 去做
        for (int i = 0; i < query; i++){
            int length = input.nextInt();
            //每个怪物的血量
            long[] life = new long[length];

            for (int j = 0; j < length; j++){
                life[j] = input.nextLong();
            }
            //E伤害 and R伤害
            long E = input.nextLong();
            long R = input.nextLong();

            //下面开始真正逻辑计算
            //表示每个怪物是否已被追加过R（不可重复追加）
            int[] useR = new int[length];
            long[] lifeHalf = new long[length];//存储原血量的一半
            for (int k = 0; k < length; k++){
                lifeHalf[k] = life[k] / 2;
            }

            //开始计算ER
            long ECount = 0;
            long RCount = 0;
            long canUseR = 0;

            out:
            while (true){
                //上来直接一个E
                int isZero = 0;
                ECount++;
                for (int ei = 0; ei < life.length; ei++){
                    if (life[ei] <= 0){//小于0 无需扣减
                        isZero++;
                        continue;
                    }

                    life[ei] = life[ei] - E;

                    //看是否需要叠加R
                    if (life[ei] <= lifeHalf[ei] && useR[ei] == 0){
                        useR[ei] = 1;//表明加入了
                        canUseR++;
                    }
                    //判断是否<0;
                    if (life[ei] <= 0){
                        isZero++;
                    }
                }

                //判断一次
                if (isZero == life.length) {
                    System.out.println(ECount + " " + RCount);
                    break out;
                }

                isZero = 0;

                //判断是否可以使用R
                while (canUseR > 0){
                    //直接使用一次R
                    RCount++;
                    canUseR--;
                    int isLowerZero = 0;
                    for (int ri = 0; ri < life.length; ri++){

                        if (life[ri] <= 0){//小于0 无需扣减
                            isLowerZero++;
                            continue;
                        }

                        life[ri] = life[ri] - R;

                        //看是否需要叠加R
                        if (life[ri] <= lifeHalf[ri] && useR[ri] == 0){
                            useR[ri] = 1;//表明加入了
                            canUseR++;
                        }

                        //统计血量 判断是否<0;
                        if (life[ri] <= 0){
                            isLowerZero++;
                        }
                    }

                    //全部all killed
                    if (isLowerZero == life.length) {
                        System.out.println(ECount + " " + RCount);
                        break out;
                    }
                }
            }

//            System.out.println(Arrays.toString(life));
//            System.out.println("E = " + E);
//            System.out.println("R = " + R);

        }

    }


}
