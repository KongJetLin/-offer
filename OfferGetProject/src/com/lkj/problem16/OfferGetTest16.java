package com.lkj.problem16;

public class OfferGetTest16
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

    //1、递归法
    //这个方法的含义是，构建以结点 list1与list2 中值较小的为头结点的链表，
    // 并将list1链表以及list2链表剩下的结点按从小到大的顺序连接在该头结点后面，返回该链表
    public ListNode Merge(ListNode list1,ListNode list2)
    {
        //首先，如果list1已经为null，返回list2；如果list2也为null，返回list1（null），当然此处也可以返回null
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;

        //找出list1与list2中值较小那个，将其作为新链表头结点（假设是list1），那么继续寻找 list1.next 与 list2中较小的结点，接到list1后面
        //最后返回list1，以list1为头结点的链表就会接到上一层较小链表的后面（自己画个图就明白）
        if(list1.val < list2.val)
        {
            list1.next = Merge(list1.next , list2);//寻找 list1.next 与 list2中较小的结点，接到list1后面
            return list1;//返回list1，以list1为头结点的链表就会接到上一层较小链表的后面
        }
        else
        {
            list2.next = Merge(list1 , list2.next);
            return list2;
        }
    }

    //2、迭代法
    public ListNode Merge1(ListNode list1,ListNode list2)
    {
        //首先，构造一个虚拟头结点，这个头结点用于连接最后要返回的链表，值为-1
        ListNode head = new ListNode(-1);
        //其次，我们必须要创建一个指针指向虚拟头结点，这个指针向下移动寻找list1与list2中较小的一个并将其连接到cur上，
        //由于cur与head原来指向同一个结点，那么连接到cur.next的结点，同样是连接到以head为头结点的链表上（画个图就明白）
        //本来使用head就够了，但是最后要返回链表头结点，因此只能再创建一个cur指针来移动。
        ListNode cur = head;

        //当list1与list2 不全为null的时候，我们寻找其中较小的一个，连接到cur上，并将cur后移一位
        while(list1 != null && list2 != null)
        {
            if(list1.val < list2.val)
            {
                cur.next = list1;
//                cur = cur.next;
                list1 = list1.next;//同时，由于list1已经添加到cur链表，我们将其后移一位继续添加
            }
            else
            {
                cur.next = list2;
//                cur = cur.next;//这句可以直接放到外面
                list2 = list2.next;
            }
            cur = cur.next;
        }

        //当list1与list2中一个为null，跳出循环
        if(list1 != null)//如果是list2为null跳出循环，list1不为null，将list1剩下的结点接到cur后面
            cur.next = list1;
        if(list2 != null)//如果是list1为null跳出循环，list2不为null，将list2剩下的结点接到cur后面
            cur.next = list2;
        //不会出现list1与list2都为null的情况

        return head.next;//通过cur指针将结点连接到以head为头结点的链表上，返回head.next为头结点的链表即可！
    }
}
