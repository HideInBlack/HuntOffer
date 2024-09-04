package acm;

/**
 * 给一个有很多数字的字符串，和一个n（n<10）整型的被除数。计算除法，要求有小数的最终四舍五入保留2位小数，可以被整除的不显示小数。
 * time：2024年9月3日10:31:06 -> 2024年9月3日11:09:15
 */
public class realBigDivider {
    public static void main(String[] args) {
        // 1.输入数据
        String string = "3000"; // 一定要补充3位0
        int n = 2;

        // 2.开始核心操作
        int remain = 0;
        int index = 0;
        StringBuilder result = new StringBuilder();
        while (index < string.length()){
            String cur = String.valueOf(remain) + string.charAt(index);
            int curReal = Integer.parseInt(cur);

            int curResult = curReal / n;
            remain = curReal % n;
            result.append(curResult);
            index++;
        }

        // 3.返回结果的特殊处理
        if (result.charAt(0) == '0'){
            result.deleteCharAt(0);
        }
        result.insert(result.length() - 3, '.');
        System.out.println(result);
        if (Integer.parseInt(String.valueOf(result.charAt(result.length() - 1))) >= 5){
            int change = Integer.parseInt(String.valueOf(result.charAt(result.length() - 2))) + 1;
            result.replace(result.length() - 2, result.length(), String.valueOf(change));
        } else {
            result.deleteCharAt(result.length() - 1);
        }
        System.out.println(result);
    }
}
