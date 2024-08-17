package acm;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * 练习ACM模式 time：2024年3月7日10:54:58 ->
 */
public class Exercise {

    //根据数组创建链表
    public static ListNodeACM createList(String[] nodes){
        //根据分割成的数组 创建一个链表
        ListNodeACM fake = new ListNodeACM("-1");
        ListNodeACM pre = fake;//非常关键！一定要用到前置指针
        for (int i = 0; i < nodes.length; i++){
            ListNodeACM node = new ListNodeACM(nodes[i]);
            pre.next = node;
            pre = node;
        }
        return fake.next;
    }

    //打印链表
    public static void printList(ListNodeACM output){
        //使用指针打印！
        ListNodeACM cur = output;
        while (cur != null){
            if (cur.next != null){
                System.out.print(cur.value + "->");
            }else {
                System.out.print(cur.value);
            }

            cur = cur.next;
        }
    }

    public static ListNodeACM deleteSame(ListNodeACM root){
        ListNodeACM pre = root;
        ListNodeACM cur = root.next;
        while (cur != null){
            if (!Objects.equals(pre.value, cur.value)){//1.如果不相等就直接下移
                pre = cur;
                cur = cur.next;
            }else {
                //2.如果相等了，那就删除当前节点，pre不动
                cur = cur.next;
                pre.next = cur;//删除
                //交给下一次...
            }
        }
        return root;
    }

    // 手撕快速排序 time：2024年3月22日14:24:08 -> 2024年3月22日15:01:16
    public static void quickSort(int[] nums){
        quickSortRecursive(nums, 0 , nums.length - 1);
    }
    //左闭 右闭
    public static void quickSortRecursive(int[] nums, int i, int j){
        if (i < j){
            int left = i;
            int right = j;
            int keyValue = nums[i];//此次关注的中心值

            while (left < right){
                //先从right开始
                while (left < right && nums[right] > keyValue){
                    right--;
                }
                //找到第一个比key小的，转换值
                nums[left] = nums[right];
                nums[right] = keyValue;

                if (left < right){//一定要注意这个！！！！！！！！！！！！！！！！！！！！只有满足left<right的时候 才left++，这样就可以保证最后一定是left=right
                    left++;
                }

                //再从left开始找
                while (left < right && nums[left] < keyValue){
                    left++;
                }
                //找到第一个比key大的，转换值
                nums[right] = nums[left];
                nums[left] = keyValue;

                if (left < right){//一定要注意这个！！！！！！！！！！！！！！！！！！！！只有满足left<right的时候 才right--,这样就可以保证最后一定是left=right
                    right--;
                }

            }

            quickSortRecursive(nums, i, right - 1);
            quickSortRecursive(nums, right + 1, j);

        }

    }




    /**
     * main 函数 ------------------------------------------------------------------------------------------------------
     */
    public static void main(String[] args) {
//        // 1.删除链表中的重复元素 time：2024年3月7日10:56:58 ->
//        Scanner input = new Scanner(System.in);
//        String line = input.next();
//        String[] nodes = line.split("->");
//
//        //调用函数
//        ListNodeACM output = deleteSame(createList(nodes));
//
//        //打印结果
//        printList(output);
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 9, 8, 4};
        quickSortRecursive(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }
}
class ListNodeACM{
    String value;
    ListNodeACM next;

    public ListNodeACM(String value) {
        this.value = value;
    }

    public ListNodeACM(String value, ListNodeACM next) {
        this.value = value;
        this.next = next;
    }
}