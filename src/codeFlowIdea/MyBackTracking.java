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
     *  √（9） 131.分割回文串 time：2023年11月15日15:02:19 -> 2023年11月15日15:57:22
     * 我的思路：1.先把问题抽象成树结构！ 2.再构思回溯的模版
     */
    List<List<String>> partitionResult = new ArrayList<>();
    List<String> partitionPath = new ArrayList<>();
    public List<List<String>> partition(String s) {
        char[] string = s.toCharArray();
        partitionBackTracking(string, 0);
        return partitionResult;
    }
    private void partitionBackTracking(char[] string, int startIndex){
        if (startIndex == string.length){//回溯终止条件
            //能走到这里的必定都是回文，直接添加
            partitionResult.add(new ArrayList<>(partitionPath));
            return;
        }
        for (int i = startIndex; i < string.length; i++){
            //此时的i是 分别有组合个 startIndex->i 字符串加入到path中
            StringBuilder curAdd = new StringBuilder();
            for (int j = startIndex; j <= i; j++){
                curAdd.append(string[j]);
            }
            //剪枝优化：如果不是回文直接跳过！ 由于没有加入到结果中，所以不需要回溯
            if (!isPalindrome(curAdd.toString())) continue; // 这里是continue！要跳过！不能直接返回！直接返回for循环后面的就不可以运行了
            //如果是回文，则需要加入到结果中
            partitionPath.add(curAdd.toString()); // 1.处理结果
            partitionBackTracking(string, i + 1); // 2.递归
            partitionPath.remove(partitionPath.size() - 1); // 3.回溯
        }
    }
    private boolean isPalindrome(String string){
        int halfLength = string.length() / 2;
        for (int i = 0; i < halfLength; i++){
            if (string.charAt(i) != string.charAt(string.length() - 1 - i)) return false;
        }
        return true;
    }

    /**
     * √（10）93. 复原 IP 地址 time：2023年11月15日19:30:23 -> 2023年11月15日20:17:31
     * 我的思路：1.切割问题-> 组合问题 2.抽象化为树结构
     */
    List<String> trueResult = new ArrayList<>();
    List<List<String>> restoreIpAddressesResult = new ArrayList<>();
    List<String> restoreIpAddressesPath = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        restoreIpBackTracking(s, 0);
        for (List<String> list : restoreIpAddressesResult) {
            StringBuilder pathStr = new StringBuilder();
            pathStr.append(list.get(0)).append(".").append(list.get(1)).append(".").append(list.get(2)).append(".").append(list.get(3));
            trueResult.add(pathStr.toString());
        }
        return trueResult;
    }
    private void restoreIpBackTracking(String s, int startIndex){
        if (startIndex == s.length()){ // 终止条件
            if (restoreIpAddressesPath.size() == 4) restoreIpAddressesResult.add(new ArrayList<>(restoreIpAddressesPath));
            return;
        }
        for (int i = startIndex; i < s.length(); i++){
            //剪枝优化
            if (restoreIpAddressesPath.size() == 4) return;
            String curStr = s.substring(startIndex, i + 1);//左闭右开
            int curVal = Integer.parseInt(curStr);
            //剪枝优化
            if (curVal > 255){//无需往下递归，也不需要同层往下遍历
                break;
            }
            // 符合条件 加入path中
            restoreIpAddressesPath.add(curStr); // 1.处理结果
            restoreIpBackTracking(s, i + 1); // 2.递归
            restoreIpAddressesPath.remove(restoreIpAddressesPath.size() - 1); // 3.回溯
            //剪枝优化
            if (curStr.equals("0")){//如果第一个是0的话，其同一层的后面的都不需要往下走了
                break;
            }
        }
    }

    /**
     * √（11） 78. 子集 time：2023年11月15日20:34:41 -> 2023年11月15日20:50:09
     * 我的思路：直接进行回去，每次走一条路径path时，都将其存入到结果中。
     */
    List<List<Integer>> subsetsResult = new ArrayList<>();
    List<Integer> subsetsPath = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        subsetsBackTracking(nums, 0);
        subsetsResult.add(new ArrayList<>());//添加空子集
        return subsetsResult;
    }
    private void subsetsBackTracking(int[] nums, int startIndex){
        if (startIndex == nums.length){ // 终止条件：遍历完的时候 就是终止的时候
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            subsetsPath.add(nums[i]); // 1.处理结果
            subsetsResult.add(new ArrayList<>(subsetsPath)); // 因为是子集，所以每添加一个path路径 都将成为一个新的子集，所以都将加入到最终的结果里
            subsetsBackTracking(nums, i + 1); // 2.递归 （从当前结点的后面一个开始遍历！因为前面的都遍历过了！）
            subsetsPath.remove(subsetsPath.size() - 1); // 3.回溯
        }
    }

    /**
     *  √（13）90. 子集 II time：2023年11月15日21:24:37 -> 2023年11月15日21:39:14
     * 我的思路：原数据中会有重复，但解集 不能 包含重复的子集。表明: 需要对同一树层进行去重！
     */
    List<List<Integer>> subsetsWithDupResult = new ArrayList<>();
    List<Integer> subsetsWithDupPath = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //涉及到去重操作，所以要先对数组进行排序
        Arrays.sort(nums);
        subsetsWithDupBackTracking(nums, 0);
        //加入每一个都有的空集！！！
        subsetsWithDupResult.add(new ArrayList<>());
        return subsetsWithDupResult;
    }
    private void subsetsWithDupBackTracking(int[] nums, int startIndex){
        if (startIndex == nums.length) return;// 1.终止条件：遍历完就是
        for (int i = startIndex; i < nums.length; i++){
            //同一树层的去重操作
            if (i != startIndex && nums[i] == nums[i - 1]) continue; //当此数值与前一个值相等时，跳过到下一个（第一个值除外）
            subsetsWithDupPath.add(nums[i]); //2.处理结果
            subsetsWithDupResult.add(new ArrayList<>(subsetsWithDupPath));//因为求得是子集，所以是树上的每一个节点，所以每每新添加了一个值就需要添加到结果集中
            subsetsWithDupBackTracking(nums, i + 1);//3.进入递归
            subsetsWithDupPath.remove(subsetsWithDupPath.size() - 1);
        }
    }

    /**
     *  √（14） 491. 递增子序列 time：2023年11月16日09:58:32 -> 2023年11月16日10:26:33
     *  重要笔记：两种去重的思路： 1.直接存入set结果集进行总结果去重 2.使用set标记已经使用的节点，进行同一树层去重！
     */
    List<List<Integer>> findSubsequencesResult = new ArrayList<>();
    List<Integer> findSubsequencesPath = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        findSubsequencesBackTracking(nums, 0);
        return findSubsequencesResult;
    }
    private void findSubsequencesBackTracking(int[] nums, int startIndex){
        if (startIndex == nums.length) {// 1.终止条件：遍历完了就停止
            return;
        }
        Set<Integer> used = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++){
            //剪枝优化
            if (used.contains(nums[i])) continue;//对于使用过的直接进行过滤
            used.add(nums[i]);
            //path为空时，直接添加
            if (findSubsequencesPath.isEmpty()){
                findSubsequencesPath.add(nums[i]); // 2.处理结果
                findSubsequencesBackTracking(nums, i + 1); // 3.进入递归
                findSubsequencesPath.remove(findSubsequencesPath.size() - 1); // 4.回溯
            }else if (nums[i] >= findSubsequencesPath.get(findSubsequencesPath.size() - 1)){//当path不为空时，且当前元素大于path中的最后一个元素时，才添加到结果集中【这表明其才是递增的】
                //方法二：对于不是有序的数组，怎么对其同一层进行去重！
                findSubsequencesPath.add(nums[i]); // 2.处理结果
                findSubsequencesResult.add(new ArrayList<>(findSubsequencesPath));
                findSubsequencesBackTracking(nums, i + 1); // 3.进入递归
                findSubsequencesPath.remove(findSubsequencesPath.size() - 1); // 4.回溯
            }
        }
    }

    /**
     * √（15）46. 全排列 time：2023年11月16日14:57:20 -> 2023年11月16日15:16:53
     * 我的思路：可以看到此时就是排列问题了！其实排列就是有顺序的组合 也就是需要遍历叶子节点！
     * 我使用了标记法：遍历过一个数就把他变成12，遇到12就不遍历！退出递归时要还原回溯！
     * 题解使用了used 数组进行标记！一样的！
     */
    List<List<Integer>> permuteResult = new ArrayList<>();
    List<Integer> permutePath = new ArrayList<>();
    boolean[] used  = new boolean[10];
    public List<List<Integer>> permute(int[] nums) {
        permuteBackTracking(nums);
        return permuteResult;
    }
    private void permuteBackTracking(int[] nums){
        if (permutePath.size() == nums.length){ // 1.终止条件：访问到每一个叶子节点
            permuteResult.add(new ArrayList<>(permutePath));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (used[i]) continue;
//            if (nums[i] == 12) continue;
            permutePath.add(nums[i]); // 2.处理结果
            used[i] = true;
//            nums[i] = 12; // 本来路径上访问过的都设置为12， 只要是=12的 都不需要访问
            permuteBackTracking(nums); // 3.递归
            used[i] = false;
//            nums[i] = permutePath.get(permutePath.size() - 1); // 退出时，要还原这个值！
            permutePath.remove(permutePath.size() - 1); // 4.回溯

        }
    }

    /**
     * （16）47. 全排列 II time：2023年11月16日15:24:29 -> 2023年11月16日15:46:30
     * 我的思路：这里的去重就是需要 同一小树层进行去重了
     */
    List<List<Integer>> permuteUniqueResult = new ArrayList<>();
    List<Integer> permuteUniquePath = new ArrayList<>();
    boolean[] permuteUniqueUsed = new boolean[10];
    public List<List<Integer>> permuteUnique(int[] nums) {

        permuteUniqueBackTracking(nums);
        return permuteUniqueResult;
    }
    private void permuteUniqueBackTracking(int[] nums){
        if (permuteUniquePath.size() == nums.length){ // 终止条件
            permuteUniqueResult.add(new ArrayList<>(permuteUniquePath));
            return;
        }
        Set<Integer> set = new HashSet<>();//对同一小树层进行去重
        for (int i = 0; i < nums.length; i++){
            //(1) 先判断此结点在path中有没有使用过
            if (permuteUniqueUsed[i]) continue;

            //(2) 再对同一小树层进行去重
            if (set.contains(nums[i])) continue;
            set.add(nums[i]);
            permuteUniquePath.add(nums[i]); // 1.处理结果
            permuteUniqueUsed[i] = true;//标记为已使用：这是在树枝上的
            permuteUniqueBackTracking(nums); // 2.进入递归
            permuteUniquePath.remove(permuteUniquePath.size() - 1); //3.回溯
            permuteUniqueUsed[i] = false;
        }
    }

    /**
     *  ×【超出时间限制：11 / 81 个通过的测试用例】（19）332. 重新安排行程 time：2023年11月16日18:02:13 -> 2023年11月16日18:54:14
     *  × 最新的：80 / 81 个通过的测试用例（超出时间限制！）
     */
    List<List<String>> findItineraryResult = new ArrayList<>();
    List<String> findItineraryPath = new ArrayList<>();
    boolean[] usedTickets = new boolean[301];
    boolean find = false;
    public List<String> findItinerary(List<List<String>> tickets) {
        //解决不掉死循环！那就先排序 在寻找！找到一个就返回！’
        Collections.sort(tickets, new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.get(1).compareTo(o2.get(1));
            }
        });
        //必须从JFK出发，先把其加入到path中
        findItineraryPath.add("JFK");
        findItineraryBackTracking(tickets);
        return findItineraryResult.get(0);
    }
    private void findItineraryBackTracking(List<List<String>> tickets){
        if (find) return;//使用只找到一个就返回！来解决死循环！
        if (findItineraryPath.size() == tickets.size() + 1){ // 1.终止条件是：所有的票都用光的时候
            findItineraryResult.add(new ArrayList<>(findItineraryPath));
            find = true;
            return;
        }
        for (int i = 0; i < tickets.size(); i++){
            //寻找 tickets票中的出发点为path中的最后一站的起始点
            if (!usedTickets[i] && Objects.equals(tickets.get(i).get(0), findItineraryPath.get(findItineraryPath.size() - 1))){
                usedTickets[i] = true;
                findItineraryPath.add(tickets.get(i).get(1)); // 1.处理结果
                findItineraryBackTracking(tickets); // 2.递归
                usedTickets[i] = false;
                findItineraryPath.remove(findItineraryPath.size() - 1); // 3.回溯
            }
        }
    }

    /**
     *  51. N 皇后
     */
    public List<List<String>> solveNQueens(int n) {
        return null;
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyBackTracking myBackTracking = new MyBackTracking();


        //测试 复原ip
//        System.out.println(myBackTracking.restoreIpAddresses("25525511135"));


    }
}
