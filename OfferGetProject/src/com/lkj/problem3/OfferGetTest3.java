package com.lkj.problem3;

import java.util.ArrayList;
import java.util.Stack;

//从头到尾打印链表
/*
题目描述：输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
注意：ArrayList的add()方法是添加到ArrayList的尾部。（ArrayList是数组实现，有下标，可以直接使用下标访问）
     因此，我们想使得ArrayList存储的是 链表从尾到头的顺序 的值，必须从链表的尾部开始添加
 */
public class OfferGetTest3
{
    public class ListNode
    {
        int val;
        ListNode next = null;

        ListNode(int val)
        {
            this.val = val;
        }
    }

    //递归方法（分为3步骤：求解最基本问题，求解当前问题的下一个较小问题，将较小问题的解整合为当前较大问题的解）
    //方法涵义：将以listNode开头的链表的元素从尾到头赋值给ArrayList
    //这种方法内部不需要考虑链表为null的情况，因为如果链表为空，代码中也会覆盖这种情况：if(listNode == null)，链表为空返回空的ArrayList
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {

        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        //1、最基本问题：当遍历到链表头结点是null，不给ArrayList添加，直接返回空的ArrayList
        if(listNode == null)
            return arrayList;

        //如果链表头结点不是null

        //求解较小的问题：较小链表将其所有元素从尾到头赋值给ArrayList，返回这个ArrayList
        arrayList = printListFromTailToHead1(listNode.next);
        //将较小问题的解整合为当前较大问题的解：将当前较大链表的头结点的元素添加到ArrayList的尾部
        // 因为头结点后面的元素已经通过较小的链表的添加过程，添加到ArrayList中，因此当前链表只需要将头结点添加到ArrayList中即可！
        arrayList.add(listNode.val);

        return arrayList;//返回添加完毕的ArrayList

        //运行时间：20ms,占用内存：8988k
    }

    //使用栈
    //这种方法内部不需要考虑链表为null的情况，因为如果链表为空，代码中也会覆盖这种情况：while(listNode != null)，链表不为空才进行添加
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode)
    {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        while(listNode != null)
        {
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        while(!stack.isEmpty())
        {
            arrayList.add(stack.pop());
        }
        return arrayList;

        //运行时间：18ms，占用内存：9148k
    }
}
