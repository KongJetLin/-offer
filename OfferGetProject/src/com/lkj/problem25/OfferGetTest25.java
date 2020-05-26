package com.lkj.problem25;

public class OfferGetTest25
{
    public class RandomListNode
    {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label)
        {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead)
    {
        //注意必须要判断pHead是否为null
        if(pHead == null)
            return null;

        //1、在每个节点的后面插入复制的节点
        RandomListNode cur = pHead;
        while(cur != null)
        {
            RandomListNode clone = new RandomListNode(cur.label);
            //将复制的结点连接到链表上
            clone.next = cur.next;
            cur.next = clone;
            //将cur后移2位
            cur = clone.next;
        }

        //2、对复制节点的 random 链接进行赋值
        cur = pHead;
        while (cur != null)
        {
            RandomListNode clone = cur.next;
            //当cur的随机指针不指向null的时候，将cur复制结点的指针指向相应位置
            if(cur.random != null)
            {
                clone.random = cur.random.next;//复制结点的random指向原来cur结点random指向结点的下一个结点
            }
            cur = clone.next;//将cur后移2位
        }

        //3、拆分
        cur = pHead;
        RandomListNode pHeadClone = pHead.next;//首先定义一个指针指向复制链表的头结点，用于返回
        /**
         这里的功能是将当前结点cur的指针指向下一个结点的下一个结点。
        画图可知，当cur.next（当前结点的下一个结点）存在的时候，才有必要将当前结点的next指针指向下下个结点（不管下下个结点是否为null），
         如果cur.next=null，说明以及到达链表结尾，不需要继续指向。（只有cur.next存在的时候，才有下下个结点！）

         如果不判断 cur.next 存在，那么nextNode.next会出现空指针异常（nodeNode.next = null.null）
         */
        while(cur.next != null)
        {
            RandomListNode nextNode = cur.next;
            cur.next = nextNode.next;//使得当前结点的next指针指向下下个结点
            cur = nextNode;//将当前指针只后移一位，因为链表中每一个结点的指针都需要指向下下个结点（当cur.next=null的时候不需要）
        }
        return pHeadClone;
    }
}
