package com.lkj.problem55;

import java.util.HashSet;

public class OfferGetTest55
{
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        //首先，判断pHead是否合法：想要有一个环，至少有2个结点，即pHead与pHead.next
        if(pHead == null || pHead.next == null)
            return null;

        ListNode fast = pHead;
        ListNode slow = pHead;

        /**
         注意！！不能写作如下这种形式，因为fast与slow一开始就是相同的，这会让循环无法进行。
         我们应该使用 do..while()，使得fast与slow先移动一位变得不同，这样才能进入循环。

        while(fast != slow)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
         */
        do {
            fast = fast.next.next;//2倍速
            slow = slow.next;//一倍速
            //如果不存在环，fast会先移动到链表结尾，此时直接返回null。这里将链表没有换的情况排除，如果下面的代码能执行，就是在链表一定有环的情况下！
            if(fast == null)
                return null;
        }while (fast != slow);

        //出循环 fast 与 slow相遇
        fast = pHead;//fast从新回到起点
        while (slow != fast)
        {
            fast = fast.next;
            slow = slow.next;
        }
        //出循环的时候，fast与slow在环的入口结点相遇
        return slow;
    }

    public ListNode EntryNodeOfLoop1(ListNode pHead)
    {
        HashSet<ListNode> hs = new HashSet<>();
        while (pHead != null)
        {
            if(hs.contains(pHead))
                return pHead;
            hs.add(pHead);
            pHead = pHead.next;
        }
        return null;//不存在相同，没有环
    }
}
