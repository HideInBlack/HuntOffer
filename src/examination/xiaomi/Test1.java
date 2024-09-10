package examination.xiaomi;

public class Test1 {

    public static int minOperations(String numsString, int x) {
        // 从字符序列中提取每个数字
        int totalSum = 0;
        for (char c : numsString.toCharArray()) {
            if (Character.isDigit(c)) {
                totalSum += Character.getNumericValue(c);
            }
        }
        // 计算余数
        int r = totalSum % x;
        if (r == 0) return 0;

        int reduceOps = r;
        int increaseOps = x - r;

        return Math.min(reduceOps, increaseOps);
    }

    public static void main(String[] args) {
        String numsString = "36912";  // 代表数字 3, 6, 9, 1, 2
        int x = 5;
        System.out.println(minOperations(numsString, x));  // 输出最少操作次数
    }
}



