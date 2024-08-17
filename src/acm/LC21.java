package acm;

import java.util.Scanner;

/**
 * 合并两个有序链表 time：2024年3月24日13:42:08 -> 2024年3月24日14:21:33
 */
public class LC21 {

    public static void main(String[] args) {
        ListNode21 a1 = new ListNode21(1);
        ListNode21 a2 = new ListNode21(2);
        ListNode21 a3 = new ListNode21(4);
        a1.next = a2;
        a2.next = a3;

        ListNode21 b1 = new ListNode21(1);
        ListNode21 b2 = new ListNode21(3);
        ListNode21 b3 = new ListNode21(4);
        b1.next = b2;
        b2.next = b3;

        ListNode21 listNode21 = mergeTwoLists(a1, b1);
        printList(listNode21);
    }

    public static void printList(ListNode21 list){
        ListNode21 cur = list;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }

    public static ListNode21 mergeTwoLists(ListNode21 a, ListNode21 b){
        //我的思想，把b合并到a上，因此需要创建a的头结点
        ListNode21 head = new ListNode21(-1);
        head.next = a;
        //定义遍历指针
        ListNode21 preA = head;
        ListNode21 curB = b;
        ListNode21 temp;

        //开始遍历，挨个遍历b把其插入到a上面
        while (curB != null){
            while (preA.next != null && preA.next.value < curB.value){
                preA = preA.next;
            }
            //先判断preA是否走到最后了
            if (preA.next == null){
                //直接所有的一起移动过去
                preA.next = curB;
                break;
            }

            //如果不为空，就只插入一个
            if (preA.next != null){
                temp = curB;
                //后移一位
                curB = curB.next;
                //开始插入
                temp.next = preA.next;
                preA.next = temp;
                //插入完成后移一位
                preA = preA.next;
            }

        }
        return head.next;

    }

}
class ListNode21{
    int value;
    ListNode21 next;

    ListNode21(int value) {
        this.value = value;
    }

    ListNode21(int value, ListNode21 next){
        this.value = value;
        this.next = next;
    }


}
