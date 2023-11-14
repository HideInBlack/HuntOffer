package codeFlowIdea;


import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月14日15:55:49 ->
 * author：董政宇
 * 第八部分 回溯部分：MyBackTracking
 */
public class MyBackTracking {

    /**
     *  × 【回溯 无思路】（2）77. 组合 time：2023年11月14日15:57:15 -> ×
     */
    List<List<Integer>> combineResult = new ArrayList<>();
    List<Integer> combinePath = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        combineBackTracking(n, k, 1);
        return combineResult;
    }
    public void combineBackTracking(int n, int k, int startIndex){
        if (combinePath.size() == k){
            //注意这里添加到结果里的时候还是要new一下的！
            combineResult.add(new ArrayList<>(combinePath));
            return;
        }
        for (int i = startIndex; i <= n - (k - combinePath.size()) + 1; i++){//还需要选择的元素：k-path.size 至多要从n-（k-path.size）开始，剩下的个数不够，达不到标准
            combinePath.add(i);//处理结果
            combineBackTracking(n, k, i + 1);
            combinePath.remove(combinePath.size() - 1);
        }
    }

    /**
     * （4）216. 组合总和 III time：2023年11月14日18:32:32 -> 2023年11月14日18:46:55
     *  回溯模版 + 剪枝优化
     */
    List<List<Integer>> combinationSum3Result = new ArrayList<>();
    List<Integer> combinationSum3List = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3BackTracking(k, n, 0, 1);
        return combinationSum3Result;
    }
    private void combinationSum3BackTracking(int k, int n, int curSum, int startIndex){
        if (combinationSum3List.size() == k){//终止条件：k个数，到k个必须停止
            if (curSum == n) combinationSum3Result.add(new ArrayList<>(combinationSum3List));
            return;
        }
        //剪枝优化：还需要k-size个，至多从9-(k - size) + 1开始
        for (int i = startIndex; i <= 9 - (k - combinationSum3List.size()) + 1; i++){//for 循环嵌套递归
            curSum += i;
            combinationSum3List.add(i); //1.开始处理
            combinationSum3BackTracking(k, n, curSum, i + 1);//2.开始递归
            curSum -= i; //回溯，一层层的回溯，所以这里其实就是减去i
            combinationSum3List.remove(combinationSum3List.size() - 1);//3.出来递归之后，进行结果回溯
        }
    }

    /**
     * （5）17. 电话号码的字母组合 time：2023年11月14日19:02:09 -> 2023年11月14日19:24:51
     */
    List<String> letterResult = new ArrayList<>();
    StringBuilder letterString = new StringBuilder();
    Map<Character, Character[]> map = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if (Objects.equals(digits, ""))return letterResult;
        map.put('2', new Character[]{'a', 'b', 'c'});
        map.put('3', new Character[]{'d', 'e', 'f'});
        map.put('4', new Character[]{'g', 'h', 'i'});
        map.put('5', new Character[]{'j', 'k', 'l'});
        map.put('6', new Character[]{'m', 'n', 'o'});
        map.put('7', new Character[]{'p', 'q', 'r', 's'});
        map.put('8', new Character[]{'t', 'u', 'v'});
        map.put('9', new Character[]{'w', 'x', 'y', 'z'});
        char[] digit = digits.toCharArray();
        letterBackTracking(digit, 0);
        return letterResult;
    }
    private void letterBackTracking(char[] digit, int index){
        if (letterString.length() == digit.length){//1.终止条件
            letterResult.add(letterString.toString());
            return;
        }
        Character[] abc = map.get(digit[index]);
        for (int i = 0; i < abc.length; i++){
            letterString.append(abc[i]);// 1.先进行处理
            letterBackTracking(digit, index + 1);// 2.递归
            letterString.deleteCharAt(letterString.length() - 1); // 3.回溯
        }
    }

    /**
     *
     */



    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

    }
}
