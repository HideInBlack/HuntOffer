package dataStructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * (2) 232. 用栈实现队列 time：2023年10月28日10:07:53 -> 2023年10月28日10:23:34
 *  √ Deque方法做对
 *  √ Stack方法也做对
 */
public class MyQueue {
//    //双端队列做法太简单了 试一下stack做法
//    Deque<Integer> stack = new LinkedList<>();
//
//    public MyQueue() {}
//
//    public void push(int x) {
//        stack.addLast(x);
//    }
//
//    public int pop() {
//        return stack.removeFirst();
//    }
//
//    public int peek() {
//        return stack.getFirst();
//    }
//
//    public boolean empty() {
//        return stack.isEmpty();
//    }

    //stack 栈的做法
    //队列可以由两个栈来实现 一个作为输入栈 一个作为输出栈 输入栈就可以直接输入 而输出栈就需要先把输入栈全部输入到输出栈中 在进行输出即可
    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();


    public MyQueue() {}

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        //如果输出栈不为空就先出栈
        if (!output.isEmpty()){
            return output.pop();
        }
        //如果输出栈为空 那就需要先进行把输入全部倒进输出栈再进行输出
        while (!input.isEmpty()){
            output.push(input.pop());
        }
        return output.pop();
    }

    public int peek() {
        //如果输出栈不为空就先出栈
        if (!output.isEmpty()){
            return output.peek();
        }
        //如果输出栈为空 那就需要先进行把输入全部倒进输出栈再进行输出
        while (!input.isEmpty()){
            output.push(input.pop());
        }
        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }


}
