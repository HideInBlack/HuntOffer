package acm;

import java.util.Scanner;

public class HJ48 {
    // HJ48 从单向链表中删除指定值的节点 time：2024年3月7日11:34:51 -> 2024年3月7日12:18:10
    public static void main(String[] args) {
        //1. 6个节点，则一定有五组关系
        //一一对应，则一定使用map
        Scanner input = new Scanner(System.in);
        int mapSize = input.nextInt();
        int headValue = input.nextInt();
        ListNode48 root = new ListNode48(headValue);

        //for循环中建立链表
        for (int i = 0; i < mapSize - 1; i++){
            int x = input.nextInt();
            int y = input.nextInt();
            insertNode(root, x, y);
        }
//        printList(root);

        int deleteValue = input.nextInt();


        //调用删除函数
        ListNode48 result = deleteNode(root, deleteValue);

        printList(result);

    }

    public static ListNode48 deleteNode(ListNode48 root, int deleteValue){
        ListNode48 fakeHead = new ListNode48();
        fakeHead.next = root;

        ListNode48 pre = fakeHead;
        ListNode48 cur = root;
        while (cur != null){
            if (cur.value != deleteValue){
                pre = cur;
                cur = cur.next;
            }else {
                pre.next = cur.next;
                return fakeHead.next;
            }
        }
        return fakeHead.next;
    }

    //查询y， 在其后插入x  （x， y）
    public static void insertNode(ListNode48 root, int x, int y){
//        System.out.println("insertNode...");
        ListNode48 pre = root;

        while (pre != null){
            if (pre.value == y){//插入成功
                ListNode48 newOne = new ListNode48(x);
                newOne.next = pre.next;
                pre.next = newOne;
                return;
            }
            pre = pre.next;
        }

    }

    public static void printList(ListNode48 root){
//        System.out.println("printList...");
        ListNode48 cur = root;
        while (cur != null){
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
    }



}
class ListNode48{
    int value;
    ListNode48 next;

    public ListNode48() {

    }

    public ListNode48(int value) {
        this.value = value;
    }

    public ListNode48(int value, ListNode48 next) {
        this.value = value;
        this.next = next;
    }


}

