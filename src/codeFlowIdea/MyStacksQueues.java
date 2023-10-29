package codeFlowIdea;

import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年10月26日19:30:45 ->
 * author：董政宇
 * 第六部分 栈与队列部分：MyStacksQueues
 */
public class MyStacksQueues {

    /**
     * √（4）有效的括号 20. time：2023年10月28日14:52:53 -> 2023年10月28日15:13:01
     * 我的思想：这个只需要用栈的思想就可以 遇到左边入栈 遇到右边若出栈元素与之对应则出栈 最后栈空则是有效
     * 重点笔记：1.在出栈时（或取栈顶元素时）需要判空！2.出栈元素与右边元素对应才出栈
     */
    public boolean isValid(String s) {
        //先使用map把左右字符进行对应
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        //使用双端队列实现栈（只能加栈顶）
        Deque<Character> stack = new LinkedList<>();
        char string[] = s.toCharArray();
        for (int i = 0; i < string.length; i++){
            if (map.containsValue(string[i])){//如果在左边里面直接进行入栈
                stack.addFirst(string[i]);
            }else {//否则此字符就是右边的字符
                //首先取值验证 先别出栈
                if (!stack.isEmpty() && stack.getFirst() == map.get(string[i])){//如果相等则再出栈
                    stack.removeFirst();
                }else {//如果不等直接返回错误
                    return false;
                }
            }
        }
        //最后如果栈空则返回成功
        return stack.isEmpty();
    }

    /**
     * ×【完全没思路】（4.1）22. 括号生成 time：2023年10月28日15:27:49 -> 2023年10月28日15:48:46
     */
    public List<String> generateParenthesis0(int n) {
        return null;
    }
    // √ 题解方法一：暴力解法 先使用递归生成所有的括号的组合 再进行判断有效与否 time：2023年10月28日15:50:19 ->
    //重要笔记：递归的思想：括号的生成就是长度为 n 的序列在长度为 n−1 的序列前加一个 ‘(’ 或 ‘)’。画一个图就可以体现 这就是一个二叉树图，而二叉树图正是经典的递归！所以递归的体现！
    //我的思路改进：我能否在递归生成括号的同时就进行判断其是否满足？
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
//        String brackets = "";
        StringBuilder brackets = new StringBuilder();
        //首先递归生成所有括号
        generateAll(brackets, "(", list, n * 2);
        return list;
    }
    //递归生成所有的括号bracket字符串
    public void generateAll(StringBuilder brackets, String current, List<String> list, int length){
        //操作本层【拼接起来当前括号】
//        brackets += current;
        brackets.append(current);

        //判断是否本层是最后一层 最后一层才做判断
        if (brackets.length() == length){
            if (isValid(brackets.toString())){
                list.add(brackets.toString());
            }
        }else{
            //往下递归 注意！！！由于StringBuilder是一个对象！只是一个！所以每次都要new
            generateAll(new StringBuilder(brackets), "(", list, length);
            generateAll(new StringBuilder(brackets), ")", list, length);
        }
    }
    /**
     * √（4.2）17. 电话号码的字母组合 time：2023年10月28日18:58:51 -> 2023年10月28日19:35:41
     * 我的思路：使用递归从第一个解往下组合每一个解
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (Objects.equals(digits, "")){
            return list;
        }
        Map<Character, Character[]> map = new HashMap<>();
        map.put('2', new Character[]{'a', 'b', 'c'});
        map.put('3', new Character[]{'d', 'e', 'f'});
        map.put('4', new Character[]{'g', 'h', 'i'});
        map.put('5', new Character[]{'j', 'k', 'l'});
        map.put('6', new Character[]{'m', 'n', 'o'});
        map.put('7', new Character[]{'p', 'q', 'r', 's'});
        map.put('8', new Character[]{'t', 'u', 'v'});
        map.put('9', new Character[]{'w', 'x', 'y', 'z'});

        letterComb(digits, 0, "",list, map);
        return list;
    }
    //递归进行组合键上的字母
    private void letterComb(String digits, int current, String result, List<String> list, Map<Character, Character[]> map){
        if (result.length() == digits.length()){//此if也是递归的终止条件
            list.add(result);
        }else {
            //取出数组
            Character[] characters = map.get(digits.charAt(current));
            current++;
            for (Character character : characters) {
                letterComb(digits, current, result + character, list, map);
            }
        }
    }

    /**
     * √（5）1047. 删除字符串中的所有相邻重复项 time：2023年10月28日19:45:08 -> 2023年10月28日20:11:30
     * 我的思路：1.首先使用StringBuilder + 双指针来做
     * 2.但其实只是删除相邻的两个字母 应该是用栈的思想（用双端队列）
     */
    public String removeDuplicates(String s) {
        if(s.length() < 2){
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        //left要遍历到倒第二个
        for (int left = 0; left < sb.length() - 1; left++){
            if (sb.charAt(left) == sb.charAt(left + 1)){
                sb.delete(left, left + 2);
                //对left的处理十分重要！！
                if (left > 0){//如果 >0 表明left要回到前一个重新进行判断，但第一个0是没有前一个的！
                    //注意这里为什么要-2！
                    left = left -2;
                }else if (left == 0){//但是第一个没有前一个所以要特殊判断 第一个只需要回到第一个！
                    left--;
                }
            }
        }
        return sb.toString();
    }
    // √ 方法二1047. 删除字符串中的所有相邻重复项 双端队列实现 time：2023年10月28日20:18:28 -> 2023年10月28日20:28:38
    //【还可以更快 那就是使用StringBuilder来实现栈】
    public String removeDuplicates2(String s) {
        //先用deque实现栈 最后输入用到队列的先进先出即可
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); i++){
            if (!deque.isEmpty() && deque.getFirst() == s.charAt(i)){//不为空并且相等
                deque.removeFirst();
            }else {//否则入栈
                deque.addFirst(s.charAt(i));
            }
        }
        //最后用队列的先进先出输出出来
        String result = "";
        while (!deque.isEmpty()){
            //这里需要用先进先出的队列输出
            result += deque.removeLast();
        }
        return result;
    }
    /**
     * （6）150. 逆波兰表达式求值 time：2023年10月29日09:28:59 -> 2023年10月29日09:47:25
     * 我的思路：其实就是用栈的思想来进行计算：1.遇到数字就输入 遇到计算符号就排出两个字符进行计算（第一个排出放计算符右边 第二个排出放左边）
     *  2.计算之后再压入到栈中 直到栈空产生结果
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        set.add("+"); set.add("-"); set.add("*"); set.add("/");
        int x; int y; int result;
        for (String token : tokens) {
            if (set.contains(token)) {//代表是字符 此时要排除两个元素进行计算
                y = stack.pop();
                x = stack.pop();
                if (Objects.equals(token, "+")) {
                    result = x + y;
                } else if (Objects.equals(token, "-")) {
                    result = x - y;
                } else if (Objects.equals(token, "*")) {
                    result = x * y;
                } else {
                    result = x / y;
                }
                stack.push(result);
            } else {//否则是数字直接进行压入到栈中
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    /**
     * ×【超出时间限制】（7）239. 滑动窗口最大值 time：2023年10月29日10:18:12 -> 2023年10月29日10:40:06
     * 我的思路：试一试直接进行k个的排序然后得出最大值
     * 无优化之前：37 / 51 个通过的测试用例【超出时间限制】
     * 优化之后：47 / 51 个通过的测试用例【超出时间限制】
     */
    public int[] maxSlidingWindow0(int[] nums, int k) {
        if (k >= nums.length){
            int[] result = new int[1];
            Arrays.sort(nums);
            result[0] = nums[nums.length - 1];
            return result;
        }
        int[] result = new int[nums.length + 1 - k];
        int[] temp = new int[k];
        for (int i = 0; i <= nums.length - k; i++){

            //搞一手优化
            if (i != 0 && i + k -1 < nums.length){
                if (nums[i + k -1] >= result[i -1]){
                    result[i] = nums[i + k -1];
                }else if (nums[i - 1] != result[i - 1] && nums[i + k -1] < result[i - 1]) {
                    //划走的值不是最大值 并且新加入的值比最大值还小
                    result[i] = result[i - 1];
                }else {
                    int count = 0;
                    for (int j = i; j < i + k; j++){
                        temp[count++] = nums[j];
                    }
                    Arrays.sort(temp);
                    result[i] = temp[k - 1];
                }
            }else {
                int count = 0;
                for (int j = i; j < i + k; j++){
                    temp[count++] = nums[j];
                }
                Arrays.sort(temp);
                result[i] = temp[k - 1];
            }

        }
        return result;
    }
    //方法二 题解方法【单调队列】 （7）239. 滑动窗口最大值 time：2023年10月29日14:21:31 -> 2023年10月29日15:01:50
    //整体方法：1.先进行移动（除第一个元素之外） 2.再进行push 3.最后取出最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == nums.length){
            int[] result = new int[1];
            Arrays.sort(nums);
            result[0] = nums[nums.length - 1];
            return result;
        }
        int result[] = new int[nums.length - k + 1];
        //单调队列 永远存储着从大到小的元素 直接访问第一个元素 就是最大值
        Deque<Integer> deque = new LinkedList<>();
        //先把i=0的初始化:先进行入队列
        for (int i = 0; i < k; i++){
            pushQueue(deque, nums[i]);
        }
        result[0] = deque.getFirst();

        //从i=1开始
        for (int i = 1; i <= nums.length - k; i++){
            //先进行移动 如果移动元素就是最大值 就出队
            if (nums[i - 1] == deque.getFirst()){
                deque.removeFirst();
            }
            //再进行对新加入的i+k-1进行push操作
            pushQueue(deque, nums[i + k - 1]);

            result[i] = deque.getFirst();
        }
        return result;
    }
    //push当前元素进入单调递减队列【单调队列的入队操作！抽象出来就可以了】
    private void pushQueue(Deque<Integer> deque, int current){
        if (deque.isEmpty()){
            deque.addLast(current);
        }else {
            if (deque.getLast() >= current){
                deque.addLast(current);
            }else {//尾元素小于要入队元素 则出栈直到其大于等于
                while (!deque.isEmpty() && deque.getLast() < current){
                    deque.removeLast();
                }
                deque.addLast(current);
            }
        }
    }
    //方法三 题解方法优先队列（大顶堆）
    public int[] maxSlidingWindow2(int[] nums, int k) {
        return new int[]{1, 2};
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyStacksQueues myStacksQueues = new MyStacksQueues();
        System.out.println(Integer.parseInt("201") + 3);

//        System.out.println(myStacksQueues.removeDuplicates2("aaaa"));
        //测试
//        Character[] characters = new Character[]{'a', 'b', 'c'};
//        System.out.println(characters.length);

        // 测试（4.1）
//        System.out.println(myStacksQueues.generateParenthesis(3).toString());

        // 测试（4）有效的括号
//        System.out.println(myStacksQueues.isValid("()[]{}"));
    }
}
