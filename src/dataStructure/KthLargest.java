package dataStructure;

import java.util.*;

/**
 * √ 快排扩展 703.数据流中的第 K 大元素 time：2023年11月1日17:56:23 -> 2023年11月1日18:57:21
 */
public class KthLargest {

    private int k;

    //只需要维护前k个最大的元素即可
    private PriorityQueue<Integer> priorityQueue;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        //只需要使用小顶堆就可以 这样每次从小顶堆里弹出的就是最小的 已然还能保留前k个最大的元素
        this.priorityQueue = new PriorityQueue<>();
        for (int num : nums){
            priorityQueue.add(num);
        }
    }

    public int add(int val) {
        //先进行入堆
        priorityQueue.add(val);
        //再控制堆内的数量为k个
        while (priorityQueue.size() > k){//当堆内的数量大于k个时候 就一直出堆就完事了
            priorityQueue.poll();
        }
        return priorityQueue.peek();
    }

//    //先有序的存储起来
//    private List<Integer> list;
//    private int k;
//
//    public KthLargest(int k, int[] nums) {
//        Arrays.sort(nums);
//        List<Integer> arrayList = new ArrayList<>();
//        for (int i = nums.length - 1; i >= 0; i--) {
//            arrayList.add(nums[i]);
//        }
//        this.list = arrayList;
//        this.k = k;
//    }
//
//    public int add(int val) {
//        if (list.isEmpty()){
//            list.add(val);
//            return list.get(k - 1);
//        }
//        //直接插入到其排序后的位置
//        for (int i = 0; i < list.size(); i++){
//            //第一个头插和最后一个尾插做特殊处理
//            if (i == 0 && val >= list.get(0)){
//                list.add(0, val);
//                break;
//            }else if (i == list.size() - 1 && val <= list.get(list.size() - 1)){
//                list.add(val);
//                break;
//            }else if (i != 0){//否则所有的都是查是否在当前和当前结点的前面之间
//                if (val <= list.get(i - 1) && val >= list.get(i)){
//                    list.add(i, val);
//                    break;
//                }
//            }
//        }
//
//        return list.get(k - 1);
//
//    }


    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(1, new int[]{});
        System.out.println(kthLargest.add(-3));
    }
}
