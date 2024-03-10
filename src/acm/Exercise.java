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

    public static void main(String[] args) {
        // 1.删除链表中的重复元素 time：2024年3月7日10:56:58 ->
        Scanner input = new Scanner(System.in);
        String line = input.next();
        String[] nodes = line.split("->");

        //调用函数
        ListNodeACM output = deleteSame(createList(nodes));

        //打印结果
        printList(output);

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