package codeFlowIdea;

import java.util.*;

/**
 * codeFlowIdea 代码随想录学习记录 time：2023年10月8日15:53:28 -> 2023年10月19日18:01:45
 * author：董政宇
 * 第二部分 链表部分：MyChain
 */
public class MyChain {

    /**
     * √（2）移除链表元素 203. time：2023年10月8日18:30:05 -> 2023年10月8日18:37:15
     * 重要笔记：1.妙处：新建一个结点接上头结点，这样就可以直接比较头结点 记住返回时返回新建结点的下一个结点
     *          2.删除时和普通过滤时的pre now指针移动不一样！请注意！
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode newNode = new ListNode();
        newNode.next = head;
        ListNode pre = newNode; ListNode now = newNode.next;
        while(now != null){
            if (now.val == val){//相等则删除
                pre.next = now.next;
            }else{//不等 则下一个
                pre = pre.next;
            }
            now = now.next;
        }
        return newNode.next;
    }
    // √（2）-（1）删除链表中的节点 time：2023年10月8日18:47:17 -> 2023年10月8日18:54:22
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * √（3）设计链表 707.设计链表 time：2023年10月9日10:12:11 -> 2023年10月9日12:04:52
     *  我的实现：双链表 这一题答案在dataStructure中
     *  我的笔记：考察双链表的基础知识  1.头插、尾插、中间插 2.头删除、尾删除、中间删除 都要特别注意！ 另外虚拟头节点yyds
     */

    // √（3）-（1） 删除排序链表中的重复元素 time：2023年10月9日13:01:28 -> 2023年10月9日13:19:13
    // 我的思路：用一个小参数缓存 删除即可
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){//一定不要忘记先判断空的
            return null;
        }
        //定义一个小缓存器一直往后判断
        int temp = head.val;
        //定义一个访问当前结点now、和前置结点pre
        ListNode pre = head;
        ListNode now = head.next;//从第二个结点开始 因为第一个结点是肯定不会被删除的
        while (now != null){
            if (temp == now.val){
                pre.next = now.next;
                now = pre.next;
            }else {
                temp = now.val;
                pre = now;
                now = pre.next;
            }
        }
        return head;
    }
    // √（3）-（2）删除排序链表中的重复元素2（所有的都删除） time：2023年10月9日13:20:00 -> 2023年10月9日14:11:38
    // 我的思路：这一题不同之处是：要删除所有的，所以pre指针停的位置是关键
    // 重点笔记：pre的位置难点！我做的太麻烦了
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null){//为空或者只有一个结点 都可以直接返回
            return head;
        }
        //定义一个小缓存器一直往后判断
        int temp = head.val;
        int count = 1;
        //定义虚拟头结点
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        //定义一个访问当前指针now、和前置指针pre
        ListNode pre = fakeHead;
        ListNode now = fakeHead.next.next;
        while (now != null){
            if (temp == now.val){
                count++;
                now = now.next;
            }else {//删除都是在不等于的时候删除的
                temp = now.val;
                if (count != 1){//如果不为1 证明需要删除
                    pre.next = now;
                    count = 1;//删除后要重置1
                    now = now.next;
                }else {
                    pre = pre.next;
                    now = now.next;
                }
            }
        }
        if (count != 1){
            pre.next = null;
        }
        return fakeHead.next;
    }
    // √ 方法二 删除排序链表中重复元素 time：2023年10月9日14:32:39 -> 2023年10月9日14:51:31
    // 我的思路：访问当前结点cur 和下一个结点next 来进行比较 当然还需要一个前置结点pre
    // 重要解题思路：1.拿当前节点和下一个节点比较！ 2.发现当前结点是重复数x时，从pre节点开始删除所有为x的节点
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null){//为空或者只有一个结点 都可以直接返回
            return head;
        }
        int temp = head.val;
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode cur = fakeHead.next;
        while (cur != null && cur.next != null){
            if (cur.val == cur.next.val){
                temp = cur.val;
                //把后面连着的等于此值的都删除掉
                while (cur != null){
                    if (cur.val == temp){
                        pre.next = cur.next;
                        cur = pre.next;
                    }else {
                        break;
                    }
                }
            }else {
                //否则就都往下移动即可
                pre = pre.next;
                cur = cur.next;
            }
        }
        return fakeHead.next;
    }

    /**
     * √（4）反转链表 206. 得快起来了！别浪费时间！搞快点！30分钟写不出就放弃！ time：2023年10月12日09:41:02 -> 2023年10月12日09:54:53
     * 我的思路：遍历链表 然后取值存储 在遍历一遍然后赋值操作
     */
    public ListNode reverseList(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode cur = head;//定义一个遍历指针
        int count = 1;
        while(cur != null){
            map.put(count++, cur.val);
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            cur.val = map.get(--count);
            cur = cur.next;
        }
        return head;

    }
    // √（4）方法二【看完题解分析后的作答】 反转链表 不是赋值操作 而是直接改动链表 time：2023年10月12日10:05:04 -> 2023年10月12日10:10:35
    // 重要笔记：重点关注三个指针的移动情况！ 移动时机！
    public ListNode reverseList2(ListNode head) {
        ListNode pre = head;
        ListNode cur = head;
        head = null;
        while (cur != null){
            pre = cur;
            cur = cur.next;
            pre.next = head;
            head = pre;
        }
        return head;
    }

    /**
     * ***** √（4）-（1）翻转链表二 time：2023年10月12日10:40:06 -> 2023年10月12日11:08:13
     * 我的思路：要处理的连接节点有点多 不如直接改变值 来的简单
     * 重点笔记：此题要处理的边界节点有点多 不如直接改变值（巧妙利用hash）
     * 【但是据说 一般而言面试（机试）的时候不允许我们修改节点的值，而只能修改节点的指向操作。！！！】
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode cur = head;//定义一个遍历指针
        int count = 1;
        while(cur != null){
            if (count >= left && count <= right){
                map.put(count, cur.val);
            }
            if (count == right){break;}
            count++;
            cur = cur.next;
        }
        cur = head;
        count = 1;
        int temp = right;
        while(cur != null){
            if (count >= left && count <= right){
                cur.val = map.get(temp--);
            }
            if (count == right){break;}
            count++;
            cur = cur.next;
        }
        return head;
    }
    // ×（4）-（1）方法二 翻转链表二 【头插法】不修改值 直接遍历一遍 time：2023年10月12日14:09:53 ->
    // 重点笔记：翻转链表不管是部分翻转还是全部翻转 都要求要有三个遍历指针 pre head cur
    // 这是一次遍历的穿针引线 没有做出来!!! 下面这个是错的 而且非常的麻烦 请尝试正经的方法一 那个方法是清晰地！
    public ListNode reverseBetween2(ListNode head, int left, int right) {

        ListNode pre = null;
        ListNode childHead = null;
        ListNode cur = head;
        ListNode temp = null;
        ListNode end = null;
        int count = 1;
        if (left == 1){//要反转的在第一个
            ListNode fakeHead = new ListNode();
            fakeHead.next = head;
            pre = fakeHead;
            childHead = head;
            end = head;
            while (cur != null){
                if (count == right + 1){break;}
                if (count > left && count <= right){
                    //进行头插法
                    temp = cur.next;
                    end.next = cur.next;
                    pre.next = cur;
                    cur.next = childHead;
                    //重新分配指针
                    childHead = cur;
                    cur = temp;
                }else {
                    cur = cur.next;
                }
                count++;
            }
            return head;
        }else {//要反转的不在第一个
            while (cur != null){
                if (count == right + 1){break;}
                if (count == left - 1){
                    pre = cur;
                    childHead = cur.next;
                    end = cur.next;
                }
                if (count > left && count <= right){
                    //进行头插法
                    temp = cur.next;
                    end.next = cur.next;
                    pre.next = cur;
                    cur.next = childHead;

                    //重新分配指针
                    childHead = cur;
                    cur = temp;
                }else {
                    cur = cur.next;
                }

                count++;
            }
            return head;
        }
    }

    // √（4）-（1）方法三 反转链表II 先进行局部翻转 再拼接起来 即先找到pre、left、right、next 这四个遍历指针！ time：2023年10月15日10:19:15 -> 2023年10月15日11:23:46
    // 重要笔记：就是childHead指针本来是头 childEnd指针本来是尾巴，但是翻转之后指针还是跟着走的！！！ 一定记住指针跟着节点走的！所有指针还是可以用的！！！
    public ListNode reverseBetween3(ListNode head, int left, int right) {
        //初始化
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode cur = fakeHead;
        ListNode pre = null; ListNode childHead = null; ListNode childEnd = null; ListNode next = null;
        int count = 0;
        //找到四个边界指针
        while (cur != null){
            if (count == left - 1){
                pre = cur;
                childHead = cur.next;
            }
            if (count == right){
                childEnd = cur;
                next = cur.next;
                break;
            }
            cur = cur.next;
            count++;
        }
        //先进行断开链子
        pre.next = null;
        childEnd.next = null;
        //局部翻转
        reverseAll(childHead);
        //拼接
        pre.next = childEnd;
        childHead.next = next;

        return fakeHead.next;
    }
    //就还是这个思路就可以！翻转全部链表
    public ListNode reverseAll(ListNode head){
        //为空的特殊情况
        if (head == null){return null;}
        //初始化
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head.next;
        //开始反转
        while (true){
            cur.next = pre;
            pre = cur;
            cur = next;
            if (next == null){
                break;
            }
            next = next.next;
        }
        return pre;
    }

    //（4）-（1）方法二 反转链表II  继续头插法！！！一次遍历
    public ListNode reverseBetween4(ListNode head, int left, int right) {

        return null;
    }

    /**
     * √（5）两两交换链表中的节点 24. time：2023年10月13日09:40:47 -> 2023年10月13日09:57:31
     * 重点笔记：这一题经过昨天的摧残 就很清晰做题思路了 1.就是你一定要明确交换节点的指针的位置也跟着走到了哪里从而发现规律
     * 2.以及交换的时候 先在纸上画下来看看谁先赋值给谁？不能找不到指针了
     * 3.自定义的指针越少越好！因为越少越容易判断 就越清晰！（不是越多越好 而是越清晰越好！）
     */
    public ListNode swapPairs(ListNode head) {
        //经典先创建虚拟头结点
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode cur = head;
        while (cur != null && cur.next != null){
            //交换节点操作
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = cur;
            //往下走指针
            pre = cur;
            cur = cur.next;
        }
        return fakeHead.next;
    }

    /**
     * √（6）删除链表的倒数第N个节点 19. time：2023年10月13日10:15:04 -> 2023年10月13日10:33:12
     * 我的思路：(首先最重要的是 要删除倒数第n个 就要找到倒数第n+1个)
     * 先是for往前走固定住窗口大小为n+1时，此时 pre 和 cur一起往前走 所以当cur节点走到最后的时候 pre就是倒数第n+1个节点
     * 注意：pre的初始化一定要是fakeHead才可以！因为头结点是有可能删除的！并且一定要找到倒数第n+1个节点 这样意味着要多走了两步！因为最后是遍历到了链表最后为null的时候！
     * 双指针思路！ 快指针cur 和 慢指针pre
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null){
            return null;
        }
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        ListNode cur = head;
        //想想这里为什么是 n!!!因为要走到n+1个才可以删除！！！
        for (int i = 1; i <= n; i++){
            cur = cur.next;
        }
        //此时pre-cur之间就是n+1个 再一起往后移动
        while (cur != null){
            //因为这里是最后cur在最后一个结点的后面 多走了一个！所以要移动n+1个！才可以删除
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return fakeHead.next;
    }

    /**
     * √（7）面试题 02.07. 链表相交 time：2023年10月13日10:50:48 -> 2023年10月13日11:17:39
     * 我的思路：双指针 两个指针a b分别遍历两个链表一直往后走即可 a走一步后 则b走一步
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //试一试哈希编码
        Map<Integer, Integer> map = new HashMap<>();
        ListNode curA = headA;
        ListNode curB = headB;
        ListNode result = null;
        while (curA != null){
            map.put(curA.hashCode(), 1);
            curA = curA.next;
        }
        while (curB != null){
            map.put(curB.hashCode(), map.getOrDefault(curB.hashCode(), 0) + 1);
            //如果此节点出现过 直接进行赋值 停止
            if (map.get(curB.hashCode()) == 2){
                result = curB;
                break;
            }
            curB = curB.next;
        }
        return result;
    }
    // √ 方法二！使用长度来做！取他俩同时一样长的地方开始比较！（快慢双指针？）time：2023年10月15日11:33:29 -> 2023年10月15日11:46:28
    //重点笔记：牛逼!一遍就过！注意：不是节点的值相等！而是节点是同一个节点！这个不一样！ 其次可以用哈希值来判断是不是一个结点！！！
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int countA = 0; int countB = 0;
        while (curA != null){
            countA++;
            curA = curA.next;
        }
        while (curB != null){
            countB++;
            curB = curB.next;
        }
        //重置指针
        curA = headA;
        curB = headB;
        if (countA < countB){//A小 B先走countB-A 步
            for (int i = 1; i <= countB - countA; i++){
                curB = curB.next;
            }
        }else {
            for (int i = 1; i <= countA - countB; i++){
                curA = curA.next;
            }
        }
        //都找到相同位置开始的节点了 开始进行比较遍历了
        while(curA != curB){
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

    /**
     * √（8）环形链表 II 142. time：2023年10月15日13:48:29 -> 2023年10月15日14:03:05
     * 我的思路：一直往下走 走一个存储一个结点的 hashCode() 如果此值是存在过的 则返回此节点，此节点便是环的入口
     * 重点笔记：巧妙利用链表节点的hashCode的唯一值！！！ （不可以用节点值表示唯一，就一定要用hashCode()表示唯一！）
     */
    public ListNode detectCycle(ListNode head) {
        ListNode cur = head;
        Map<Integer, Integer> map = new HashMap<>();
        while (cur != null){
            //如果map中不存在这个key 则让其key=1，如果已经存在了 就在其基础上+1.
            map.put(cur.hashCode(), map.getOrDefault(cur.hashCode(), 0) + 1);
            if (map.get(cur.hashCode()) == 2){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    //（8）√ 方法二 环形链表 II 【快慢指针方法】 time：2023年10月16日09:33:45 -> 2023年10月16日09:51:14
    //重点笔记：①首先是快慢指针 fast一次走两步 slow一次走一步如果有环则一定相遇 ②其次是当相遇时 则一个指针从头结点开始 一个从当前相遇结点开始 第一次相遇的时候就是环形入口！
    public ListNode detectCycle2(ListNode head) {
        //第一步：首先使用快慢指针判断是否有环
        ListNode fast = head;
        ListNode slow = head;
        while (true){
            //先判断fast是否为空
            //因为fast要一次走两步！所以这里一定是fast和fast.next一起判断！
            if (fast == null || fast.next == null){//如果fast或者fast.next为空都可以直接返回没有环了
                return null;
            }
            //再往下移动
            fast = fast.next.next;
            slow = slow.next;
            //移动后判断如果相等：就继续往下走找入口点
            if (fast == slow){
                //此时一个指针从head开始走 一个开始从当前接结点走 寻找入口结点
                fast = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
    }
    //（8）-（1） 环形链表 141. time：2023年10月19日09:37:01 -> 2023年10月19日09:47:28
    //我的思路：快慢指针 fast一次走两步 slow一次走一步 如果可以相遇 则电脑有环存在
    //重点笔记：尽量不要在判断条件外进行初始化 要在判断条件内（循环内）先进行移动，然后再进行判断！
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }
    //（8）-（2）快乐数 time：2023年10月19日10:00:57 -> 2023年10月19日10:30:49
    //我的思路：确实和环形链表一样 因为要判断这个数是不是出现过，如果出现过直接返回false 否则一直往下走看是不是会出现true
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);

        //取每个位置上数的方法：①可以用字符串 ②可以直接进行取余 但是怎么才可以获得总共有多少位呢
        while (true){
            //求各位置之和
            String str = "";
            int sum = 0;
            str = str + n;
            for (int i = 0; i < str.length(); i++){
                sum = sum + Integer.parseInt(str.charAt(i)+"")*Integer.parseInt(str.charAt(i)+"");
            }
            n = sum;
            //等于1 其快乐数直接返回
            if (n == 1){
                return true;
            }
            //进行判断n是否已经存在
            if (set.contains(n)){
                return false;
            }else {
                set.add(n);
            }

        }
    }

    // 21. 合并两个有序链表 (思路：把b中的点合并到a上面去)
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        //我的思想，把b合并到a上，因此需要创建a的头结点
        ListNode head = new ListNode(-1);
        head.next = a;
        //定义遍历指针
        ListNode preA = head;
        ListNode curB = b;
        ListNode temp;

        //开始遍历，挨个遍历b把其插入到a上面
        while (curB != null){
            while (preA.next != null && preA.next.val < curB.val){
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

    // 21. 合并两个有序链表 (思路：谁小就创建谁，新建一个新的链表) time：2024年3月24日14:28:35 -> 2024年3月24日14:36:48
    // 离谱 明明这样是最简单的！直接创建一个新的头结点的，遍历a，b谁小就插入到新节点来。最后对剩下的进行批量操作！
    public ListNode mergeTwoLists2(ListNode a, ListNode b) {
        ListNode newHead = new ListNode(-1);
        ListNode newCur = newHead;

        ListNode curA = a;
        ListNode curB = b;
        ListNode temp;

        while (curA != null && curB != null){
            if (curA.val <= curB.val){
                temp = curA;
                curA = curA.next;
            }else {
                temp = curB;
                curB = curB.next;
            }
            //插入temp到新序列即可
            newCur.next = temp;
            newCur = newCur.next;
            newCur.next = null;
        }

        if (curA != null){
            newCur.next = curA;
        }
        if (curB != null){
            newCur.next = curB;
        }

        return newHead.next;
    }

    // LCR 024. 反转链表 time：2024年3月24日14:43:11 -> 2024年3月24日14:56:30
    public ListNode reverseList1Dzy(ListNode head) {
        if (head == null) return null;
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;

        ListNode cur = fakeHead.next;
        ListNode temp;//临时节点
        while (cur.next != null){
            temp = cur.next;
            cur.next = cur.next.next;

            //开始插入 不断头插
            temp.next = fakeHead.next;
            fakeHead.next = temp;
        }
        return fakeHead.next;

    }

    public static void printList(ListNode listNode){
        ListNode cur = listNode;
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }



    /**
     * -----------------------------------------------测试-----------------------------------------------
     */
    public static void main(String[] args) {
        MyChain myChain = new MyChain();

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        printList(listNode1);


        ListNode listNode = myChain.reverseList1Dzy(listNode1);
        System.out.println("\n翻转之后------------");
        printList(listNode);

        //（8）-（2）快乐数
//        System.out.println(myChain.isHappy(2));

        //（4）反转链表 206.
//        ListNode listNode1 = new ListNode(3);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(0);
//        ListNode listNode4 = new ListNode(-4);
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode2;

        //（8）测试环形链表
//        ListNode cur = myChain.detectCycle(listNode1);
//        System.out.println(cur.val);

        //（4）测试反转链表
//        ListNode cur = myChain.reverseAll(listNode1);
//        while(cur != null){
//            System.out.println(cur.val);
//            cur = cur.next;
//        }

        //(4) 测试反转链表2
//        ListNode cur = myChain.reverseBetween3(listNode1, 2, 4);
//        while(cur != null){
//            System.out.println(cur.val);
//            cur = cur.next;
//        }

        //（6）删除链表的倒数第N个节点
//        myChain.removeNthFromEnd(listNode1, 2);
//        ListNode cur = listNode1;
//        while(cur != null){
//            System.out.println(cur.val);
//            cur = cur.next;
//        }



    }
}

