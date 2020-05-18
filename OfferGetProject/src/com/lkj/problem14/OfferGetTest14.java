package com.lkj.problem14;

public class OfferGetTest14
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

    public ListNode FindKthToTail(ListNode head,int k)
    {
        if(head == null)
            return null;

        ListNode p1 = head;

        /*
        当 p1 != null ，且 k>0的时候，我们持续向后遍历。
        1）链表有倒数第k个结点的临界条件是：k = 0，且 p1 = null，那么如果是在临界条件下结束循环，此时 p1=null，k刚刚好等于0。
        2）链表没有倒数第k个结点的时候，当 p1 = null的时候，k>0。移动不到 k 次p1就指向链表结尾的null，此时必然没有倒数第k个结点！
        3）链表有倒数第k个结点的其他情况，当 k = 0 的时候，p1!=null。

        因此，出循环的时候，链表有倒数第k个结点时，p1可能为null，也可能不为null，但是 k 必然等于0。
        因此出循环的时候，判断 k 是否大于0，如果大于0，说明没有倒数第k个结点，返回null
         */
        while (p1 != null && k>0)
        {
            p1 = p1.next;
            k--;//k的值记得变化
        }
        if(k > 0)
            return null;

        ListNode p2 = head;
        //当有倒数第k个结点的时候，使得 p1、p2 持续后移，直到 p1、p2 移动了n-k 次，p1 一共移动 n次，到达链表末尾的null，结束后移
        //此时 p2 指向链表第 n-k+1 个结点，也就是链表的倒数第 k 个结点
        while(p1 != null)
        {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }
}
