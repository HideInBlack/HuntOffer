package codeFlowIdea;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年11月17日21:33:13 ->
 * author：董政宇
 * 第九部分 贪心部分：MyGreedy
 */
public class MyGreedy {

    /**
     * （2） 455. 分发饼干 time：2023年11月17日21:36:11 -> 2023年11月17日21:56:15
     */
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0) return 0;
        //先直接对g，s进行排序【两边都从最小的开始】
        Arrays.sort(g);
        Arrays.sort(s);
        //下面使用双指针来做
        int curCake = 0; int count = 0;
        for (int i = 0; i < g.length; i++){//以孩子为根本进行遍历！因为目的是满足尽可能多的孩子
            if (curCake >= s.length) return count;
            //找到能够满足当前需求最小孩子的 最小cake
            while (s[curCake] < g[i]){
                curCake++;
                if (curCake >= s.length) return count;
            }
            //找到s了！: s[curCake] >= g[i]
            count++;
            curCake++; // cake消耗
        }
        return count;
    }

    /**
     * ×【整个逻辑是对的，但是回溯的复杂度太高！】（3）摆动序列 time：2023年11月18日09:55:32 -> 2023年11月18日10:49:13
     *  我的思路：看到是数组中的子序列！记住子序列和子串是不一样的！前者是可以跳着取值得！
     *  【看到子序列！立马想到回溯！】错！ 这个要看问题的维度的！100、1000维度的问题，一定不要去尝试回溯！直接使用贪心或者动态规划！
     *  【就当练习一下回溯的思想了！后面做题！一定超过20分钟看不出错误就去看题解！不需要死缠烂打浪费时间！】
     */
    List<Integer> wigglePath = new ArrayList<>();
    PriorityQueue<Integer> pathLength = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    public int wiggleMaxLength0(int[] nums) {
        wiggleBackTracking(nums, 0, 0);
        return pathLength.peek();
    }
    private void wiggleBackTracking(int[] nums, int startIndex, int preDiff){
        if (startIndex >= nums.length){ // 终止条件
            pathLength.add(wigglePath.size()); // 记录当前长度退出
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            //1.首先处理path为空的情况
            if (wigglePath.size() == 0){
                wigglePath.add(nums[i]); // 1.处理结果
                wiggleBackTracking(nums, i + 1, 0); // 2.进入递归
                wigglePath.remove(wigglePath.size() - 1);
            }else if (wigglePath.size() == 1){
                int curDiff = nums[i] - wigglePath.get(0); //反正就一个 直接取出第一个
                if (curDiff == 0) {
                    pathLength.add(wigglePath.size());
                    return; // 只有两个数时，不可以相等
                }
                wigglePath.add(nums[i]); // 1.处理结果
                wiggleBackTracking(nums, i + 1, curDiff); // 2.进入递归
                wigglePath.remove(wigglePath.size() - 1); // 3.回溯
            }else { // 此时是至少两个数的
                int curDiff = nums[i] - wigglePath.get(wigglePath.size() - 1);
                if (curDiff == 0) {
                    pathLength.add(wigglePath.size()); // 记录当前长度退出
                    return;
                }
                if (preDiff > 0 && curDiff > 0) {
                    pathLength.add(wigglePath.size()); // 记录当前长度退出
                    return;
                }
                if (preDiff < 0 && curDiff < 0) {
                    pathLength.add(wigglePath.size()); // 记录当前长度退出
                    return;
                }
                //此时走到这里的，都是相反符号的
                wigglePath.add(nums[i]); // 1.处理结果
                wiggleBackTracking(nums, i + 1, curDiff); // 2.进入递归
                wigglePath.remove(wigglePath.size() - 1); // 3.回溯

            }
        }
    }
    //题解方法：贪心做法！time：2023年11月20日15:59:40 -> 2023年11月20日16:22:45
    //重要笔记：贪心的思想：那就是每一个坡度上只保留两个峰值，所有的峰值加起来
    // √ 我的思路：先对数组进行去重，连续的相同的都进行去掉，保存到list中，然后找真正的峰值，也就是比两边都大的，或者比两边都小的，最后再+2
    //此题的关键在于能否把题目抽象成波浪线、峰值！
    public int wiggleMaxLength(int[] nums) {
        //总之找真正的峰值
        List<Integer> list = new ArrayList<>();
        //对连续相等元素的去重操作
        for (int i = 0; i < nums.length; i++){
            //对连续相同元素的后面的进行去重
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
        }
        //特殊值处理
        if (list.size() == 1) return 1;
        //统计峰值
        int peakValue = 0;
        for (int i = 0; i < list.size(); i++){
            //(list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i)) < 0 这个表明i 要么是最高峰值，要么是最低峰值
            if (i != 0 && i != list.size() - 1 && (list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i)) < 0){
                peakValue++;
            }
        }
        peakValue = peakValue + 2;//只统计了中间的峰值，但其中最后还有两边的端点。
        return peakValue;
    }

    /**
     * ×【无思路！】（4） 53. 最大子数组和 time：2023年11月20日16:29:27 -> 2023年11月20日16:53:53
     */
    // √ 题解方法 time：2023年11月20日17:47:35 -> 2023年11月20日18:02:21
    //主要笔记：算法确实很简单，但是这一题确实难！很难想
    public int maxSubArray(int[] nums) {
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i =0; i < nums.length; i++){
            curSum += nums[i];
            maxSum = Math.max(curSum, maxSum);
            if (curSum <= 0) curSum = 0; //如果curSum比0还小，那就重新计算count！重新计算其位置
        }
        return maxSum;
    }

    /**
     * √（6） 122. 买卖股票的最佳时机 II time：2023年11月21日19:38:09 -> 2023年11月21日20:05:31
     * 我的思路：贪心的思想：我每一个能获得利的过程我都不放过，这样我就可以利益最大化
     * 其实此题感觉和摆动序列也很像：我只需要找到序列中每一个最低谷底值和每一个最高峰值即可！
     * 步骤：1.第一步需要去重，把整个序列都变成只有谷底和峰值的序列 2.求出每一个上升方向的峰值-谷底值 求和
     */
    public int maxProfit(int[] prices) {
        List<Integer> list = new ArrayList<>();
        //1.先进行去重，形成只有峰值、谷底值的摆动序列
        for (int i = 0; i < prices.length; i++){
            if ( i != 0 && prices[i] == prices[i - 1]) continue;
            list.add(prices[i]);
        }

        //2.对每一个上升方向的差值进行求和
        int sumProfit = 0;
        for (int i = 0; i < list.size(); i++){
            //首先是有两种计算方式的：第一种是只需要计算max-min，第二种就是如果当前比前一个值大那就直接大的-小的 求和（每一个）
            //选择第2种，更好实现和理解
            if (i != 0 && list.get(i) > list.get(i - 1)){
                sumProfit += list.get(i) - list.get(i - 1);
            }
        }
        return sumProfit;
    }
    // √ 方法二【不需要去重的方式】 （6） 122. 买卖股票的最佳时机 II time：2023年11月21日20:06:06 -> 2023年11月21日20:08:46
    //啊？ 一个for循环搞定了？？？还真就那么简单
    public int maxProfit2(int[] prices) {
        int sumProfit = 0;
        for (int i = 0; i < prices.length; i++){
            if ( i != 0 && prices[i] > prices[i - 1]){
                sumProfit += prices[i] - prices[i - 1];
            }
        }
        return sumProfit;
    }

    /**
     * √（7） 55. 跳跃游戏 time：2023年11月21日20:11:18 -> 2023年11月21日20:25:54
     *  我的思路：定义一个curJumpStep，不断让curJumpStep取最大的，然后一直往后
     *  这一题目的关键是：可以跳的最大步数，但并不是一定要跳的步数，你只需要往后每一个都遍历，然后贪心的每一个都选择当前可以跳的最大步数就可以了！
     */
    public boolean canJump(int[] nums) {
        //还剩余可以跳的步数
        int curJumpStep = nums[0];
        for (int i = 0; i < nums.length; i++){
            if (i == 0){
                curJumpStep = nums[i];
            }else {
                curJumpStep--;//可以跳的步数首先减一个
                if (curJumpStep < 0) return false;//如果跳到当前步数为负的，那直接失败
                curJumpStep = Math.max(curJumpStep, nums[i]);
            }
        }
        return true;
    }

    /**
     * ×【41 / 109 个通过的测试用例】（8） 45. 跳跃游戏 II time：2023年11月21日20:33:35 -> 2023年11月21日21:01:43
     * 错误：并无法保证怎样能够确定跳最小次数呢
     */
    public int jump(int[] nums) {
        int curStep = nums[0];
        int jumpCount = 0;
        for (int i = 0; i < nums.length; i++){
            if (i == 0){
                curStep = nums[i];
            }else {
                curStep--;
                if (i == nums.length - 1){
                    return ++jumpCount;//到最后一个了，不管大不大都要跳一次
                }
                if (curStep < nums[i]){
                    jumpCount++;//这里才表明跳了一次
                    curStep = nums[i];
                }
            }
        }
        return jumpCount;
    }
    // √ 方法二 题解方法 time：2023年11月21日21:18:50 -> 2023年11月21日21:32:54
    //题解方法的思路：当超出当前最大范围的时候就需要步数+1
    //重要笔记：其关键思想就是只要超过当前的最大范围就+1；同时也要记住下一步的最大范围下标；以及最后一个位置的特殊处理！
    public int jump2(int[] nums) {
        //nums中只有一个数的情况的特殊处理！！
        if (nums.length == 1) return 0;

        int jumpCount = 0;
        int curMaxIndex = nums[0];
        int nextMaxIndex = 0;
        for (int i = 0; i < nums.length; i++){
            //往前走的每一步都需要计算下一步的最大范围
            nextMaxIndex = Math.max(nums[i] + i, nextMaxIndex); // 这一步也很神奇，不断着在更新着下一步的最大步数

            //这一步是最后一个节点的特殊处理(因为就算最后一个结点不超过，也需要+1并返回)
            if (i == nums.length - 1) return ++jumpCount;

            //如果i等于当前的最大范围，则需要步数加1，并更新当前步数
            if (i == curMaxIndex){
                jumpCount++;
                curMaxIndex = nextMaxIndex;
            }
        }
        return jumpCount;
    }

    /**
     * √（9） 1005. K 次取反后最大化的数组和 time：2023年11月21日21:56:41 -> 2023年11月21日22:32:02
     * 我的思路：1.先对数组进行排序 2.从前往后每次都取最小的数变成正数，这样转正后就是最大的 3.如果没有负数了，就把所有的转变正负的机会留给最小的正数或者0
     * 简单是挺简单的，但是坑有点多
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        //1.首先数组排序
        Arrays.sort(nums);
        //2.开始遍历操作
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] < 0 && k > 0){ // 前面所有k个负数进行取反求和的操作
                sum += (-nums[i]);
                k--;
            }else if (k == 0){ //如果k=0，表示没有翻转机会了
                sum += nums[i];
            } else if (k > 0 && nums[i] >= 0) { //如果k没用完，但是负数已经被翻转完了
                if (k % 2 == 0) sum += nums[i];//双数为原值
                if (k % 2 == 1) {
                    //！！！正数的第一个其实需要特殊判断，看一下负数的最后一个和正数的最后一个到底应该让谁为负数
                    if (i == 0){
                        sum += (-nums[i]);//双数为其相反值
                    }else if (i != 0 && nums[i] > Math.abs(nums[i - 1])){
                        sum += nums[i];//当前正数取原值
                        sum -= 2 * Math.abs(nums[i - 1]);
                    } else if (i != 0 && nums[i] <= Math.abs(nums[i - 1])) {
                        sum += (-nums[i]);//双数为其相反值
                    }
                }
                k = 0;//操作完后直接令其为0
            }
        }
        //3.如果遍历完了 k却还没用完
        if (k > 0){
            if (k % 2 == 0) sum += nums[nums.length - 1];//双数为原值
            if (k % 2 == 1) sum -= 2 * Math.abs(nums[nums.length - 1]);
        }
        return sum;
    }
    // √ 方法二 题解方法 按照绝对值进行排序！这个十分方便了就
    public int largestSumAfterKNegations2(int[] nums, int k) {
        Integer[] numsArray = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(numsArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o2) - Math.abs(o1);
            }
        });
        int sum = 0;
        for (int i = 0; i < numsArray.length; i++){
            if (numsArray[i] < 0 && k > 0){
                sum += (-numsArray[i]);
                k--;
            }else {
                sum += numsArray[i];
            }
        }
        //如果遍历完了 k却还没用完
        if (k % 2 == 1){
            if(numsArray[numsArray.length - 1] > 0){//需要先判断最后一个数字是正的还是负的
                sum = sum - 2 * numsArray[numsArray.length - 1];//这里就是正的
            }else{
                sum = sum + 2 * numsArray[numsArray.length - 1];//这里就是负的
            }

        }
        return sum;
    }

    /**
     *  ×【超时 34 / 40 个通过的测试用例】（11） 134. 加油站 time：2023年11月22日12:42:49 -> 2023年11月22日13:05:37
     * 我的思路：暴力解
     *  √ 题解方法：i从0开始累加rest[i]，和记为curSum，一旦curSum小于零，说明[0, i]区间都不能作为起始位置，因为这个区间选择任何一个位置作为起点，
     * 到i这里都会断油，那么起始位置从i+1算起，再从0计算curSum。
     * 此题关键：！！！一旦[0，i] 区间和为负数，起始位置就可以是i+1
     */
    int endIndex = -1;//1.endIndex = -1表示初始化 2.endIndex = -2表示可以宣布直接失败了 3.endIndex != -1, != -2,那就是下一步新开始查询的点！
    public int canCompleteCircuit(int[] gas, int[] cost) {
//        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) return -1;
        for (int i = 0; i < gas.length; i++){
            if (canCircuit(gas, cost, i)){
                return i;
            }else {
                if (endIndex != -1) i = endIndex;//从当前0-i范围都不能作为起始位置，从endIndex的下一个值开始
                if (endIndex == -2) return -1;
            }
        }
        return -1;
    }
    private boolean canCircuit(int[] gas, int[] cost, int startIndex){
        int allGas = 0;
        //判断后半段路是否可以走通
        for (int i = startIndex; i < gas.length; i++){
            allGas += gas[i];//先计算总共剩余多少油
            allGas -= cost[i];//在这里判断是否可以往下走一步
            if(allGas < 0) {
                //这里是最关键的一步！
                endIndex = i;
                return false;
            }
        }
        //判断前半段路是否可以走通
        for (int i = 0; i < startIndex; i++){
            allGas += gas[i];//先计算总共剩余多少油
            allGas -= cost[i];//在这里判断是否可以往下走一步
            if(allGas < 0) {
                endIndex = -2;//都饶了一圈重新回到开始了，所以如果还没找到，那就是整个都找不到，直接返回-2，代表可以表示失败了！
                return false;
            }
        }
        return true;
    }

    /**
     * ×【无思路】（12）135. 分发糖果 time：2023年11月22日19:42:45 -> 2023年11月22日20:24:16
     * 题解方法：最关键的思路：要保证相邻的孩子获得评分最高，1.先从左往右得出右孩子比左孩子大的结果（先只看一个方向） 2.再从右往左得出左孩子比右孩子大的结果（这是第二个方向）
     *  3. 最后只需要两个里面去最大的就可以两边都兼顾了！
     */
    // √ 题解方法 两边兼顾法
    public int candy(int[] ratings) {
        int[] leftToRight = new int[ratings.length];
        int[] rightToLeft = new int[ratings.length];
        //1.从左往右遍历，找到比较右孩子比左孩子大的
        for (int i = 0; i < ratings.length; i++){
            if (i != 0 && ratings[i] > ratings[i - 1]){
                leftToRight[i] = leftToRight[i - 1] + 1;
            }else {
                leftToRight[i] = 1;
            }
        }
        //2.从右往左遍历，找到比较左孩子比右孩子大的
        for (int i = ratings.length - 1; i >= 0; i--){
            if (i != ratings.length - 1 && ratings[i] > ratings[i + 1]){
                rightToLeft[i] = rightToLeft[i + 1] + 1;
            }else {
                rightToLeft[i] = 1;
            }
        }
        //3.第三步：分别取出两个里面最大的 总和就是最少糖果数目
        int sum = 0;
        for (int i = 0; i < ratings.length; i++){
            sum += Math.max(leftToRight[i], rightToLeft[i]);
        }
        return sum;
    }
    //方法二 省掉一遍遍历的时间
    public int candy2(int[] ratings) {
        int[] leftToRight = new int[ratings.length];
        int[] rightToLeft = new int[ratings.length];
        int sum = 0;
        //1.从左往右遍历，找到比较右孩子比左孩子大的
        for (int i = 0; i < ratings.length; i++){
            if (i != 0 && ratings[i] > ratings[i - 1]){
                leftToRight[i] = leftToRight[i - 1] + 1;
            }else {
                leftToRight[i] = 1;
            }
        }
        //2.从右往左遍历，找到比较左孩子比右孩子大的
        for (int i = ratings.length - 1; i >= 0; i--){
            if (i != ratings.length - 1 && ratings[i] > ratings[i + 1]){
                rightToLeft[i] = rightToLeft[i + 1] + 1;
            }else {
                rightToLeft[i] = 1;
            }
            sum += Math.max(rightToLeft[i], leftToRight[i]);
        }
        return sum;
    }

    /**
     * √（13）860. 柠檬水找零 time：2023年11月22日20:46:41 -> 2023年11月22日21:05:08
     * 我的思想：贪心思想：每次找零的时候有大的给大的，没大的给小的，小的也没有那就没了
     */
    public boolean lemonadeChange(int[] bills) {
        //似乎用数组会更加的简单！
        int[] money = new int[2];//5 10 20
        for (int bill : bills) {
            if (bill == 5) {
                money[0] += 1;
            } else if (bill == 10) {
                money[1] += 1; //入账10
                //找零-只需要找1个5元
                money[0] -= 1;
                if (money[0] < 0) return false;
            } else if (bill == 20) {
                //找零-两种情况
                if (money[1] > 0) {
                    money[1] -= 1;
                    money[0] -= 1;
                    if (money[0] < 0) return false;
                } else if (money[1] == 0 && money[0] >= 3) {
                    money[0] -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ×【无思路】（14） 406. 根据身高重建队列 time：2023年11月22日21:06:29 -> 2023年11月22日21:19:34
     * √ 题解思路：像这种有两个维度的题目：都是需要先确定其中一条的维度！再进行插入排序
     */
    // √ 题解方法：优先按照身高高的进行排序，因为k表示的是前面大于等于其身高的个数，所以要先排序高的，这样后面的都是比其矮的！不受影响（就算他们插入到前面，也是比它矮的，不加入到计算中）
    //time：2023年11月22日21:36:11 -> 2023年11月22日22:15:32
    public int[][] reconstructQueue(int[][] people) {
        //先进行双重条件下的数组排序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]){
                    return o1[1] - o2[1];//相等的时候按照第二个的升序
                }else {//否则还是按照第一个的降序
                    return o2[0] -o1[0];
                }
            }
        });
        //重建队列！
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < people.length; i++){
            //看其people[1],其在什么位置 就插入到什么位置！因为前面的都是比其大的
            list.add(people[i][1], people[i]);
        }
        return list.toArray(new int[0][]);
    }







    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyGreedy myGreedy = new MyGreedy();

        //测试二维数组的排序问题
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{7, 0});
        list.add(new int[]{4, 4});
        list.add(new int[]{7, 1});
        list.add(new int[]{5, 0});
        list.add(new int[]{6, 1});
        list.add(new int[]{5, 2});
        for (int[] array : list){
            System.out.print(Arrays.toString(array) + " ");
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]){
                    return o1[1] - o2[1];
                }else {
                    return o2[0] - o1[0];
                }
            }
        });

        for (int[] array : list){
            System.out.print(Arrays.toString(array) + " ");
        }

//        //按照数值的绝对值，对数组进行排序！
//        int nums[] = {2,-3,-1,5,-4};
//        Integer[]  integers = Arrays.stream(nums).boxed().toArray(Integer[]::new);
//        Arrays.sort(integers, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return Math.abs(o2) - Math.abs(o1);
//            }
//        });
//        System.out.println(Arrays.toString(integers));

//        //方法一 int数组 -> integer数组 转化
//        int[] numsTest = {1,2,3,4,5,6};
//        IntStream stream = Arrays.stream(numsTest);//将int数组转换为数值流
//        Stream<Integer> integerStream = stream.boxed();//流中的元素全部装箱，转换为Integer流
//        Integer[] integers = integerStream.toArray(Integer[]::new);//将流转换为数组
//        System.out.println(Arrays.toString(integers));
//
//        //方法二 int数组 -> integer数组 转化
//        Integer integersNew[] = Arrays.stream(numsTest).boxed().toArray(Integer[]::new);
//        System.out.println(Arrays.toString(integersNew));

    }
}
