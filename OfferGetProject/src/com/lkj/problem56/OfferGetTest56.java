package com.lkj.problem56;

public class OfferGetTest56
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

    /**
     * 这一题需要注意，如果出现重复的结点，所有重复的结点都不要保留，全部删除！
     * @param pHead
     * @return
     */
    //删除以 pHead 为根结点的
    public ListNode deleteDuplication(ListNode pHead)
    {
        /*
        1）当链表第一个结点为null，直接返回这个结点
        2）当遍历到链表的最后一个结点，下一个结点为null，此时不会有与他相同的下一个结点，返回这个最后的结点
        注意，必须要判断 pHead.next 不为null，这是下面操作的前提！！
         */

        if(pHead == null || pHead.next == null)
            return pHead;

        //当 pHead.next 存在的时候，将其取出进行判断（这里 pHead.next存在 是下面操作的前提）
        ListNode nextNode = pHead.next;

        //当 pHead.val == nextNode.val 的时候，我们需要删除pHead结点以及后面值与他相同的结点
        if(pHead.val == nextNode.val)//nextNode不存在的时候，这里会报空指针异常，因此前面必须要排除 nextNode不存在的情况
        {
            //如果 pHead.val == nextNode.val，我们持续将 nextNode 的指针后移，直到 nextNode=null 或者 pHead.val ！= nextNode.val
            while(nextNode != null && pHead.val == nextNode.val)
            {
                nextNode = nextNode.next;
            }
            //注意，由于 pHead 的值与nextNode 的值相同，pHead 也必须删除
            //当 nextNode=null 的时候，deleteDuplication(pHead) 返回 null，正确；
            //当 nextNode!=null 的时候，deleteDuplication(pHead) 返回以nextNode为头结点的删除了重复元素的链表，正确
            return deleteDuplication(nextNode);//这样返回就将 pHead 也删除了
        }
        else
        {
            //当 pHead.val ！= nextNode.val 的时候，我们不需要删除pHead，删除以 pHead.next为根的链表的重复结点并返回
            pHead.next = deleteDuplication(pHead.next);//注意将pHead连接到返回的链表上
            return pHead;
        }
    }
}
