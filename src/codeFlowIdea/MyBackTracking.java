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
     * √（7）39. 组合总和 time：2023年11月14日19:53:10 -> 2023年11月14日20:44:31
     * 我的思路：
     */
    List<List<Integer>> combinationSumResult = new ArrayList<>();
    List<Integer> combinationSumList = new ArrayList<>();
    int sum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSumBackTrack(candidates, target, 0);
        return combinationSumResult;
    }
    private void combinationSumBackTrack(int[] candidates, int target, int startIndex){
        if (sum > target){//终止条件为：递归了最大可以次数
            return;
        }

        for (int i = startIndex; i < candidates.length; i++){
            sum += candidates[i];//处理结果
            combinationSumList.add(candidates[i]);
            if (sum == target) combinationSumResult.add(new ArrayList<>(combinationSumList));//处理结果

            if (sum >= target){//剪枝优化
                sum -= candidates[i];//回溯
                combinationSumList.remove(combinationSumList.size() - 1);
                return;
            }
            combinationSumBackTrack(candidates, target, i);//递归
            sum -= candidates[i];//回溯
            combinationSumList.remove(combinationSumList.size() - 1);
        }
    }

    /**
     * × 【172 / 176 个通过的测试用例】（8） 40. 组合总和 II time：2023年11月14日22:12:15 -> 2023年11月14日22:33:38
     * 重要笔记：所以我们要去重的是同一树层上的“使用过”，同一树枝上的都是一个组合里的元素，不用去重！
     * √ 看了题解，添加了去重的剪枝优化之后！做对了
     */
    List<List<Integer>> combinationSum2Result = new ArrayList<>();
    List<Integer> combinationSum2Path = new ArrayList<>();
    int cb2Sum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum2BackTracking(candidates, target, 0);
        return combinationSum2Result;
    }
    private void combinationSum2BackTracking(int[] candidates, int target, int startIndex){
        if (cb2Sum > target){//回溯的终止条件
            return;
        }
        //所以我们要去重的是同一树层上的“使用过”，同一树枝上的都是一个组合里的元素，不用去重！！！！
        for (int i = startIndex; i < candidates.length; i++){
            //去重的剪枝优化
            if (i != startIndex && candidates[i] == candidates[i - 1]){
                continue;
            }
            cb2Sum += candidates[i]; // 处理结果
            combinationSum2Path.add(candidates[i]);
            if (cb2Sum == target) combinationSum2Result.add(new ArrayList<>(combinationSum2Path));

            if (cb2Sum >= target){//剪枝优化
                cb2Sum -= candidates[i]; // 回溯
                combinationSum2Path.remove(combinationSum2Path.size() - 1);
                return;
            }

            combinationSum2BackTracking(candidates, target, i + 1);//递归：因为不重复，所以要i+1开始
            cb2Sum -= candidates[i]; // 回溯
            combinationSum2Path.remove(combinationSum2Path.size() - 1);
        }
    }

    /**
     * （9） 131.分割回文串 time：
     */
    public List<List<String>> partition(String s) {
        return null;
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(1); list1.add(2); list1.add(3);
        list2.add(1); list2.add(3); list2.add(2);
        list2.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1- o2;
            }
        });
        set.add(list1);
        set.add(list2);
        System.out.println(set.stream().toList());
    }
}
