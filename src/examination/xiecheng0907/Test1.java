package examination.xiecheng0907;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int group = input.nextInt();

        while (group > 0){
            group--;
            int n = input.nextInt();
            int k = input.nextInt();
            int sum = input.nextInt();

            int target = sum / k;
            int remain = sum % k;
            int count = 0;
            int countBig = 0;
            for (int i = 1; i <= n; i++){
                if (i > target){
                    count += (i - target);
                    countBig++;
                }
            }
            count -= Math.min(remain, countBig);
            System.out.println(count);
        }
    }
}
