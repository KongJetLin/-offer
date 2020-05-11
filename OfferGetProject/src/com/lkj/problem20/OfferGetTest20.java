package com.lkj.problem20;

import java.util.Stack;

public class OfferGetTest20
{
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    public void push(int node)
    {
        //入栈的时候，2个栈都入栈
        dataStack.push(node);
        //minStack为空，直接入栈node，否则入栈node与栈顶元素较小的一个。
        minStack.push( minStack.isEmpty() ? node : Math.min(node , minStack.peek()));
    }

    public void pop()
    {
        if(dataStack.size() > 0 && minStack.size() > 0)
        {
            //当2个栈不为空，才可以出栈元素（其实2个栈元素个数一样，判断一个即可）
            dataStack.pop();
            minStack.pop();
        }
    }

    public int top()
    {
        return dataStack.peek();//直接读取dataStack栈顶元素即可（不会讲dataStack栈顶元素弹出，只是读取）
    }

    public int min()
    {
        return minStack.peek();//直接读取minStack栈顶元素即可（不会讲mintack栈顶元素弹出，只是读取）
    }
}
