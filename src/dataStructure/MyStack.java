package dataStructure;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * （3）225. 用队列实现栈 time：2023年10月28日10:43:35 -> 2023年10月28日11:32:44
 *  √ Deque实现
 *  √ Queue实现【双队列】
 *  √ Queue实现【单个队列】
 */
public class MyStack {

//    //方法一 直接用双端队列进行操作 十分的方便
//    Deque<Integer> queue = new LinkedList<>();
//    public MyStack() {
//
//    }
//
//    public void push(int x) {
//        queue.addFirst(x);
//    }
//
//    public int pop() {
//        return queue.removeFirst();
//    }
//
//    public int top() {
//        return queue.getFirst();
//    }
//
//    public boolean empty() {
//        return queue.isEmpty();
//    }

    //方法二 （双队列）下面要用正经的队列来实现
//    Queue<Integer> queue1 = new LinkedList<>();
//    Queue<Integer> queue2 = new LinkedList<>();
//
//    public MyStack() {
//
//    }
//
//    public void push(int x) {
//        queue1.add(x);
//    }
//
//    public int pop() {
//        //要先把queue1中的最后一个元素之前的所有元素备份到queue2中 排出去之后再备份回来queue1
//        while (true){
//            int result = queue1.poll();
//            if (queue1.isEmpty()){//如果出栈之后为空 代表其是最后一个元素 需要返回输出
//                //先再把queue2中的备份回来到queue1中
//                while (!queue2.isEmpty()){
//                    queue1.add(queue2.poll());
//                }
//                //备份完后进行返回
//                return result;
//            }else {//否则需要输入到queue2中进行备份
//                queue2.add(result);
//            }
//        }
//    }
//
//    public int top() {
//        //要先把queue1中的最后一个元素之前的所有元素备份到queue2中 排出去之后再备份回来queue1
//        while (true){
//            int result = queue1.poll();
//            if (queue1.isEmpty()){//如果出栈之后为空 代表其是最后一个元素 需要返回输出
//                //先再把queue2中的备份回来到queue1中
//                while (!queue2.isEmpty()){
//                    queue1.add(queue2.poll());
//                }
//                queue1.add(result);
//                //备份完后进行返回
//                return result;
//            }else {//否则需要输入到queue2中进行备份
//                queue2.add(result);
//            }
//        }
//    }
//
//    public boolean empty() {
//        return queue1.isEmpty();
//    }

    //方法三 只用一个队列来实现一下
    //重点思路：利用队列size的属性 把前size-1个都排出 然后新插入到队列中 然后最后输出最后一个
    Queue<Integer> queue = new LinkedList<>();
    public MyStack() {}

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        //这个要先提出来
        int size = queue.size();
        for (int i = 1; i < size; i++){
            queue.add(queue.poll());
        }
        return queue.poll();
    }

    public int top() {
        //这个就是靠着size单纯的遍历一遍 一边遍历一边插入到自己中
        int size = queue.size();
        int result = 0;
        for (int i = 1; i <= size; i++){
            result = queue.poll();
            queue.add(result);
        }
        return result;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
