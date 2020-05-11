package com.lkj.problem15;

import java.util.Stack;

public class OfferGetTest15
{

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }

        /**
         * 功能：创建一个数组，在创建一个结点的时候将数组存入，就可以根据数组值，创建一个以该结点为第一个结点的链表。
         * 为了实现这个功能，我们需要创建一个ListNode类可以接收数组的构造函数
         */
        public ListNode(int nums[])
        {
            //首先判断传入的数组是否合法
            if(nums == null || nums.length == 0)
                throw new IllegalArgumentException("arr can not be empty");

            this.val = nums[0];//当前被创建的结点为链表第一个结点，我们将当前结点指设置为数组第一个值
            ListNode cur = this;
            for (int i = 1; i < nums.length ; i++)
            {
                //创建一个新的结点，结点值为nums[i]，将cur.next指向这个结点
                cur.next = new ListNode(nums[i]);
                cur = cur.next;//将cur后移一位
            }
        }

        //将以当前创建结点为第一个结点的链表值打印出来
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            ListNode cur = this;//创建一个指针，指向当前结点

            while (cur != null)
            {
                sb.append(cur.val+"->");
                cur = cur.next;
            }
            sb.append("NULL");

            return sb.toString();
        }
    }

    //利用栈存储（也可以使用动态数组ArrayList，效果是一样的）
    public ListNode ReverseList1(ListNode head) {
        //特别注意，必须判断输入的特殊情况，对于其他题目也是一样的！！！
        //这里判断head是否为null，否则下面的stack会空抛出异常
        if(head == null)
            return head;

        Stack<Integer> stack = new Stack<Integer>();
        while(head != null)
        {
            stack.push(head.val);
            head = head.next;
        }

        //创建一个新的ListNode结点，先将Stack栈顶的值赋予这个结点，这是newList就是新链表的头结点
        ListNode newList = new ListNode(stack.pop());
        //创建一个指针pre指向结点newList（这样就可以使得pre向后移添加结点，而newList始终指向链表头，用来返回）
        ListNode pre = newList;
        //循环，将Stack中的值构造成为结点，并接到链表中
        while (!stack.isEmpty())
        {
            pre.next = new ListNode(stack.pop());
            pre = pre.next;
        }

        //怕热指针向后移动，知道指向链表的尾部，但是newList指针仍然指向链表头，直接返回newList即可
        return newList;

        //运行时间：19ms,占用内存：9784k
    }

    //递归方法：这个方法不需要考虑链表为null的情况
    //这个方法的涵义是：将以head为头结点的链表反转，输出新链表的表头。
    public static ListNode ReverseList2(ListNode head)
    {
        //最小问题：head移动到原来链表尾部，head=null，返回head
        if(head == null)
            return head;

        //解决较小问题：将将以 head.next 为头结点的链表反转，输出新链表的表头。（暂时不考虑较小的链表怎么反转，只要知道返回的较小的链表已经反转即可）
        ListNode curNode = ReverseList2(head.next);
        //将较小的问题整合成为较大问题的解：较小的已经反转，当前较大链表链表的表头原来在较小链表的头部，现在接到反转后较小链表的尾部。
        /*
        如果返回的是null，说明在当前链表头结点head是原来链表最后一个结点，将curNode设置为当前链表头结点head链表；
        如果返回的是反转后的链表，将当前链表的头结点设置为反转后链表的最后一个结点.
        但是，当前链表的头结点后面还接着原来链表的结点，必须将当前链表头结点后面部分删除：head.next = null;
         */
        if(curNode!=null)
        {
            ListNode pre = curNode;
            //找到返回链表的最后一个结点
            while (pre.next != null)
            {
                pre = pre.next;
            }
            pre.next = head;//将head接到当前链表的尾部
            head.next = null;//这一步很重要！！！
        }
        else
            curNode = head;

        return curNode;
        //运行时间：20ms,占用内存：9536k
    }

    public static void main(String[] args)
    {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head.toString());

        ListNode listNode = ReverseList2(head);
        System.out.println(listNode);
    }
}
