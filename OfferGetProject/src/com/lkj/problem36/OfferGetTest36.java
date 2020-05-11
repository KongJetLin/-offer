package com.lkj.problem36;

import java.util.Stack;

class ListNode {
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

public class OfferGetTest36 {

    //利用栈的方法
    public static ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2)
    {
        if(pHead1 == null || pHead2 == null)
            return null;

        Stack<ListNode> nodes1 = new Stack<>();
        Stack<ListNode> nodes2 = new Stack<>();

        //将2个链表的结点保存到栈
        while (pHead1 != null)
        {
            nodes1.push(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null)
        {
            nodes2.push(pHead2);
            pHead2 = pHead2.next;
        }

        pHead1 = null;
        pHead2 = null;
        ListNode common = null;

        //当2个栈都不为null的时候，取出栈的元素进行比较
        while((!nodes1.isEmpty()) && (!nodes2.isEmpty()))
        {
            pHead1 = nodes1.pop();
            pHead2 = nodes2.pop();

            if(pHead1 == pHead2)
            {
                common = pHead1;//将当前结点保存
            }
            else
            {
                break;
            }
        }
        return common;
    }

    public static ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2)
    {
        if(pHead1 == null || pHead2 == null)
            return null;

        int length1 = getListLength(pHead1);
        int length2 = getListLength(pHead2);

        ListNode longList = null;
        ListNode shortList = null;

        int diff = length1 - length2;
        if(diff < 0)
        {
            longList = pHead2;
            shortList = pHead1;
            diff = length2 - length1;
        }
        else
        {
            longList = pHead1;
            shortList = pHead2;
        }

        //将较长的链表后移diff长度
        while (diff!=0)
        {
            longList = longList.next;
            diff--;
        }

        while (longList!=null && shortList!=null && longList != shortList)
        {
            longList = longList.next;
            shortList = shortList.next;
        }

        return longList;
    }

    private static int getListLength(ListNode node)
    {
        int length = 0;
        ListNode tempNode = node;
        while(tempNode != null)
        {
            length++;
            tempNode = tempNode.next;
        }
        return length;
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;

        /**
         * p1 遍历过程中，如果不为null，就指向下一个结点；当 p1 遍历完 pHead1 之后，它指向null，此时他肯定还不等于 p2，将 p1 指向pHead2链表头，
         * 继续向下遍历；同样的，p2 在遍历完 pHead2之后，指向 pHead1 继续遍历，这样就会遇到 p1=p2 的情况！
         * p1 与 p2 在遍历第一个链表的时候肯定不会相遇
         */
        while (p1 != p2)
        {
            p1 = (p1==null)?pHead2:p1.next;
            p2 = (p2==null)?pHead1:p2.next;
        }
        return p1;
    }


    public static void main(String[] args)
    {
        int[] nums1 = {9, 2, 3, 4, 5, 6};
        int[] nums2 = {1, 4, 7, 8, 3, 4, 5, 6};
        ListNode pHead1 = new ListNode(nums1);
        ListNode pHead2 = new ListNode(nums2);

//        int listNode = FindFirstCommonNode2(pHead1,pHead2);
//        System.out.println(listNode);
    }
}

