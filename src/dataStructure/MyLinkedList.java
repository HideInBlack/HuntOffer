package dataStructure;

/**
 *  707. 设计链表 time：2023年10月9日10:12:11 -> 2023年10月9日12:04:52
 *  我的实现：双链表
 *  我的笔记：考察双链表的基础知识  1.头插、尾插、中间插 2.头删除、尾删除、中间删除 都要特别注意！ 另外虚拟头节点yyds
 */
class MyLinkedList {

    class DoubleListNode{

        int val;

        DoubleListNode prev;

        DoubleListNode next;

        public DoubleListNode(){

        }

        public DoubleListNode(int val){
            this.val = val;
        }

        public DoubleListNode(int val, DoubleListNode prev, DoubleListNode next){
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

    }

    //虚拟头结点
    DoubleListNode fakeHead;

    public MyLinkedList() {
        fakeHead = new DoubleListNode();
        fakeHead.val = -1; //-1表明是虚拟头结点
    }

    public int get(int index) {
        int count = 0;
        DoubleListNode now = fakeHead.next;//now 从真正的头结点开始
        while (now != null){
            if (count == index){
                return now.val;
            }
            now = now.next;
            count++;
        }
        //当前结点为空的时候（走到最后了）都没有找到你的下标那就是无效下标
        return -1;
    }

    public void addAtHead(int val) {
        DoubleListNode newNode = new DoubleListNode(val);
        if (fakeHead.val == -1){ //说明一个结点都没有 直接先初始化头结点
            fakeHead.val = -2; //-2表明已经有头结点了
            fakeHead.next = newNode;
            newNode.prev = fakeHead;
            newNode.next = null;
        }else { //如果已经有头结点 这里就需要采用头插法
            newNode.next = fakeHead.next;
            newNode.prev = fakeHead;
            fakeHead.next.prev = newNode;
            fakeHead.next = newNode;
        }
    }

    public void addAtTail(int val) {
        DoubleListNode newNode = new DoubleListNode(val);
        DoubleListNode now = fakeHead;//now 从虚拟头结点开始
        while (true){// 思考一下
            if (now.next == null){//一直遍历到最后一个结点
                now.next = newNode;
                newNode.prev = now;
                break;
            }
            now = now.next;
        }
    }

    //插入到链表中下标为 index 的节点之前
    public void addAtIndex(int index, int val) {
        int count = 0;
        DoubleListNode newNode = new DoubleListNode(val);
        DoubleListNode now = fakeHead.next;//now 从真正的头结点开始
        DoubleListNode end = fakeHead;
        while (now != null){
            if (count == index){
                count = -1;
                //要插入到前面
                DoubleListNode pre = now.prev;
                newNode.prev = pre;
                newNode.next = now;
                now.prev = newNode;
                pre.next = newNode;
                break;
            }
            //保存最后一个结点以便最后的末端插入
            end = now;
            now = now.next;
            count++;
        }
        //判断一下是不是在最后 即是否index == 链表的长度
        if (count == index){//直接进行一个尾插法
            end.next = newNode;
            newNode.prev = end;
        }

    }
    //注意：第一个头结点、中间节点、末端节点 这三种删除方式不一样
    public void deleteAtIndex(int index) {
        int count = -1;
        DoubleListNode now = fakeHead;//now 从虚拟的头结点开始（为了方便删除头结点）
        //这里是当now.next不为空时 意思是不判断最后一个末端节点（留到循环外进行处理）
        while (now.next != null){
            if (count == index){//找到了 直接进行删除此节点
                count = -1;
                DoubleListNode pre = now.prev;
                DoubleListNode next = now.next;
                next.prev = pre;
                pre.next = next;
                break;
            }
            now = now.next;
            count++;
        }
        //此时再判断最后一个节点是否该删除
        if (count == index){
            DoubleListNode pre = now.prev;
            pre.next = null;
        }
    }

//    public void printList(){
//        DoubleListNode now = fakeHead.next;
//        System.out.println();
//        while (now != null){
//            System.out.print(now.val + ",");
//            now = now.next;
//        }
//        System.out.println();
//    }


//    /**
//     * 测试一下 2023年10月9日11:09:48
//     * @param args
//     */
//    public static void main(String[] args) {
//        MyLinkedList myLinkedList = new MyLinkedList();
//        myLinkedList.addAtHead(1);
//        myLinkedList.addAtTail(3);
//        myLinkedList.addAtIndex(1, 2);
//        System.out.println(myLinkedList.get(1));
//        myLinkedList.deleteAtIndex(1);
//        myLinkedList.printList();
//
//
//    }
}

