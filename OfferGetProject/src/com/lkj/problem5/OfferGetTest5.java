package com.lkj.problem5;

import java.util.Stack;

public class OfferGetTest5
{
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        /*
            入队列，元素正常进入第一个栈。
            这里不需要判断队列是否已经满了，因为如果模拟队列满了，就是栈1满了，也会提示。
         */

        stack1.push(node);
    }

    public int pop() {
        int result = 0;
        //出队列，首先如果栈2还有之前栈1转移过来的元素，直接将元素出栈
        if(!stack2.isEmpty())
        {
            result = stack2.pop();
        }
        else
        {//如果栈1，栈2都为空，那么在运行时出栈失败，抛出运行时异常。
            if(stack1.isEmpty())
            {
                throw new RuntimeException("queue is empty");
            }
            //如果栈1不为空，将栈1所有的元素转到栈2
            while(!stack1.isEmpty())
            {
                stack2.push(stack1.pop());
            }
            //此时栈2必然有元素，将出栈元素赋予result即可
            result = stack2.pop();
        }
        return result;
    }

    public static void main(String Args[]) throws Exception{
        OfferGetTest5 q1=new OfferGetTest5();
        q1.push(1);
        q1.push(2);
        q1.push(3);
        System.out.println(q1.pop());
        System.out.println(q1.pop());
        System.out.println(q1.pop());
//        System.out.println(q1.pop());
    }
}
