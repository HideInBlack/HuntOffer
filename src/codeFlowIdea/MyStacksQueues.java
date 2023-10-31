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
    // √ 方法三 题解方法优先队列（大顶堆）（7）239. 滑动窗口最大值 time：2023年10月31日20:07:08 -> 2023年10月31日20:50:03
        //重点笔记：此思路使用大顶堆来解决滑动窗口的问题很巧妙！因为大顶堆可以存储对象（在这里存储的数组），就可以把下标也存进去
        //当每每想取出最大值时，可以判断其下标在不在滑动窗口里，如果不在就一直排出堆顶就可以了！
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int result[] = new int[nums.length - k + 1];
        //先初始化一个大顶堆
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];//降序表示为大顶堆
            }
        });
        for (int i = 0; i <= nums.length - k; i++){

            if ( i == 0){//i=0时 先进行初始化大顶堆
                for (int j = 0; j < k; j++){
                    priorityQueue.add(new int[]{j, nums[j]});
                }
                result[i] = priorityQueue.peek()[1];
            }else {//i != 0时候
                //入堆
                priorityQueue.add(new int[]{i - 1 + k, nums[i - 1 + k]});
                //取最大值：当堆顶元素的key不在窗口内 就一直出堆 直到其在窗口内
                while (priorityQueue.peek()[0] < i || priorityQueue.peek()[0] > i + k - 1){
                    priorityQueue.poll();
                }
                result[i] = priorityQueue.peek()[1];
            }
        }

        return result;
    }

    /**
     * （7.1）76. 最小覆盖子串 较难
     */
    public String minWindow(String s, String t) {
        return "";
    }

    /**
     * √（8）347. 前 K 个高频元素 time：2023年10月30日10:48:31 -> 2023年10月30日11:03:27 2023年10月30日13:51:58 -> 2023年10月30日13:56:14
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        //循环找出前k个
        int result[] = new int[k];
        for (int i = 0; i < k; i++){
            //这里先默认最大的为第一个
            int max = -100009;
            for (int key : map.keySet()){
                if (map.get(key) >= max){
                    max = map.get(key);
                    result[i] = key;
                }
            }
            map.remove(result[i]);
        }
        return result;
    }
    //（8.1）题解方法二 【优先级队列（大小顶堆）】 time：2023年10月30日15:10:54 ->
    //重点笔记：为什么使用大顶堆 而不是直接排序 因为大顶堆带着数组一起来排序（而排序只能排自己）
    public int[] topKFrequent1(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        //先使用map遍历直接进行统计个数
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        //然后下面使用优先权队列（大顶堆找出前k个）
        //先是初始化大顶堆
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {//使用自定义的Comparator进行控制大顶堆还是小顶堆
            @Override
            public int compare(int[] o1, int[] o2) {//这里的精髓就在于所有的数组对象可以根据其中某一个值进行排序
                return o2[1] - o1[1];//这里2、1代表着降序 也就是大顶堆
            }
        });
        //使用增强for循环来创建大顶堆（给大顶堆输入值）
        for (int key : map.keySet()){
            //每次要新建一个数组输入其中 数组的新建赋值初始化！new int[]{1， 2}
            priorityQueue.add(new int[]{key, map.get(key)});//其实就相当于输入了一个二元组
        }
        //再取出前k个 因为要前k个高的
        int result[] = new int[k];
        for (int i = 0; i < k; i++){
            //priorityQueue.poll()每次排出的是他的数组 而我要他的key 他的key在第一个位置
            result[i] = priorityQueue.poll()[0];
        }
        return result;
    }
    /**
     * √ (8.2) 692. 前K个高频单词 time：2023年10月31日15:57:30 -> 2023年10月31日16:31:00
     * 我的思路：1.首选直接使用大顶堆输出前k个即可 2.但是难点在于如果是有相同的出现频率则按照字典顺序排序（直接使用字符串的哈希编码进行排序）
     */
    public List<String> topKFrequentString(String[] words, int k) {
        //先对数组直接进行一个排序
        Map<String, Integer> map = new HashMap<>();
        //先使用map统计一遍个数
        for (int i = 0; i < words.length; i++){
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()){//如果value值相等时按照key升序（也就是字典排序），否则按照value进行降序
                    return o1.getKey().compareTo(o2.getKey());//1 compareTo 2 返回的是1-2
                }else {//否则按照value进行降序
                    return o2.getValue() - o1.getValue();
                }
            }
        });
        //构建大顶堆
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            priorityQueue.add(entry);
        }
        List<String> list = new ArrayList<>();
        //输出前k个
        for (int i = 0; i < k; i++){
            list.add(priorityQueue.poll().getKey());
        }
        return list;

//        //再使用优先级队列（大顶堆）进行输出
//        //首先进行初始化大顶堆
//        PriorityQueue<String[]> priorityQueue = new PriorityQueue<>(new Comparator<String[]>() {
//            @Override
//            public int compare(String[] o1, String[] o2) {
//                return Objects.equals(o1[1], o2[1]) ? o1[0].compareTo(o2[0]) : Integer.parseInt(o2[1]) - Integer.parseInt(o1[1]);//大顶堆是降序
//            }
//        });
//        //构建大顶堆
//        for (String key : map.keySet()){
//            priorityQueue.add(new String[]{key, String.valueOf(map.get(key))});
//        }
//        //输出前k个
//        List<String> result = new ArrayList<>();
//        for (int i = 0; i < k; i++){
//            result.add(priorityQueue.poll()[0]);
//        }
//        return result;
    }

    /**
     * √（8.3）215. 数组中的第K个最大元素 time：2023年10月30日16:01:51 -> 2023年10月30日16:12:32
     * 重点笔记：想到快排了 但是具体忘记什么思想了！
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    //方法二（8.3）215. 数组中的第K个最大元素 正经做法 time：2023年10月30日16:25:30 ->
    //使用堆排序来试试
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;//降序
            }
        });
        //构建大顶堆
        for (int num : nums){
            priorityQueue.add(num);
        }
        //排出大顶堆(排出前四个 那第5个直接取出来)
        for (int i = 1; i <= k - 1; i++){
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
    //方法三 快排的经典思想
    public int findKthLargest3(int[] nums, int k) {
        return 0;
    }

    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyStacksQueues myStacksQueues = new MyStacksQueues();


//        String testString[] = {"i","love","leetcode","i","love","coding"};
//        Arrays.sort(testString);
//        System.out.println(Arrays.toString(testString));
//        myStacksQueues.topKFrequentString(testString, 2);

//        //测试 comparator接口中的compare 升序和降序的返回值
//        //【例子中展示的比较器用在数组排序中，也可以用在优先级队列（大小顶锥）中！】
//        Integer[] nums = new Integer[]{1, 4, 3, 5, 2, 7, 6};
//        Arrays.sort(nums, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;//升序
//            }
//        });//升序[1, 2, 3, 4, 5, 6, 7]
//
//        Arrays.sort(nums, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;//降序
//            }
//        });//降序[7, 6, 5, 4, 3, 2, 1]

//        int nums[] = {1,1,1,2,2,3};
//        System.out.println(Arrays.toString(myStacksQueues.topKFrequent(nums, 2)));

//        System.out.println(Integer.parseInt("201") + 3);

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
