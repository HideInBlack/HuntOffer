package codeFlowIdea;

import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年12月29日14:50:50
 * author：董政宇
 * 第十部分 单调栈部分：MyMonotonicStack
 */
public class MyMonotonicStack {

    /**
     * ×【47 / 48 个通过的测试用例】（1） 739. 每日温度 time：2023年12月29日14:54:59 -> 2023年12月29日15:03:40
     * 我的思路：暴力求解即可【47 / 48 个通过的测试用例】 暴力解最后一个测试用例超出限制！
     */
    public int[] dailyTemperatures(int[] temperatures) {
        for (int i = 0; i < temperatures.length; i++){
            int curT = temperatures[i]; //临时保存当前i的温度
            temperatures[i] = 0;//默认设置为0
            for (int j = i + 1; j < temperatures.length; j++){
                if (temperatures[j] > curT){
                    temperatures[i] = j - i;
                    break;
                }
            }
        }
        return temperatures;
    }

    /**
     * √ 单调栈
     * 题目求得是右边第一个比自己大的数 则从栈顶到栈底的顺序应该是 递增的！
     * 1.满足递增则入栈
     * 2.不满足则出栈，一直出栈到满足递增或栈空为止
     */
    //题解方法：单调栈 739. 每日温度 time：2023年12月29日15:53:20 -> 2023年12月29日16:17:12
    public int[] dailyTemperatures2(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i < temperatures.length; i++){ //对每一个温度进行入栈操作
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){ //2.不满足要求，出栈 知道满足递增要求
                int curIndex = stack.pop();
                result[curIndex] = i - curIndex;
            }
            stack.push(i); //1.满足递增要求直接入栈 2.不满足最后操作完也要入栈
        }
        return result;
    }

    /**
     * √（2） 496. 下一个更大元素 I time：2023年12月29日16:24:18 -> 2023年12月29日16:42:04
     * 我的思路：先使用单调栈遍历一遍nums2 求出右侧第一个大的元素数组！注意是元素而不是下标！
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();//先使用map 保存元素与结果（右侧第一个大的值）的对应关系
        Deque<Integer> stack = new LinkedList<>(); //使用双端队列表示栈 会更快！
        for (int i = 0; i < nums2.length; i++){
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {//1.不满足要求的递增的
                int curIndex = stack.pop();
                map.put(nums2[curIndex], nums2[i]);//把右侧第一个最大值保存
            }
            //无论如何最后都要入栈
            stack.push(i); //无论何时都要入栈下标
        }
        for (int i = 0; i < nums1.length; i++){
            if (map.keySet().contains(nums1[i])){
                nums1[i] = map.get(nums1[i]);
            }else {
                nums1[i] = -1;
            }
        }
        return nums1;
    }

    /**
     * √（3）下一个更大元素 II time：2023年12月29日16:43:32 -> 2023年12月29日17:02:49
     * 我的思路：也很简单：其实就是单调栈来求右侧第一个大的数的活用
     * 最重要的思路：把两个数组连起来找！nums + nums，如果找到的下标j-i>nums.length则无效！【好像根本不会发生！】
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        //数组要初始化为-1！
        Arrays.fill(result, -1);

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++){
            while (!deque.isEmpty() && nums[i] > nums[deque.peek()]){//2.不满足条件 则出栈 插入结果
                int curIndex = deque.pop();
                result[curIndex] = nums[i];
            }
            //最后都要入栈
            deque.push(i);
        }
        for (int i = 0; i < nums.length; i++){
            while (!deque.isEmpty() && nums[i] > nums[deque.peek()]){//2.不满足条件 则出栈 插入结果
                int curIndex = deque.pop();
                result[curIndex] = nums[i];
            }
            //最后都要入栈
            deque.push(i);
        }
        return result;
    }


    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {

    }
}
