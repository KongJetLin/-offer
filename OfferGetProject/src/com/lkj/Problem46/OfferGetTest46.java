package com.lkj.Problem46;

public class OfferGetTest46
{
    /** 首先，使用环形链表的方法 */
    //内部类模拟链表结点
    private class ListNode
    {
        int val;
        ListNode next;
        public ListNode(int val)
        {
            this.val = val;
        }
    }

    //方法
    public int LastRemaining_Solution1(int n, int m)
    {
        if(n==0 || m==0)
            return -1;

        ListNode cur = new ListNode(0);//当前结点
        ListNode head = cur;//创建一个指针指向当前结点，也就是环形链表头结点

        //下面通过循环确定环形链表的所有结点
        for (int i = 1; i < n; i++)
        {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        //出循环的时候，cur指向第n个结点，我们使得第n个结点的next指向head，就形成环形链表
        cur.next = head;

        //下面通过2层循环，逐步去除链表中每次循环的 m 个结点（因为每次报数人数为m，我们使得报m-1的人，也就是第m人出列）
        //当 cur.next = cur的时候，说明链表其他结点删除完（圆圈中只有一个小孩，这个结点的值就是小孩的编号），否则继续循环
        /**
        我们使得cur指向每次m循环开始结点的前一个结点，这样方便将第m个结点删除。（m循环是指每次报数从0到m）
         比如我们第一次m循环，此时开始结点为head（0），cur.next = head，cur指向此次m循环开始结点的前一个结点，
         我们使得cur走过m-1步，它就指向这次报数的第m-1个结点，我们就可以将这次报数的第m个结点删除。

         下一次报数（m循环），开始结点为上一层循环的m+1个结点，此时cur指向m-1个结点，m结点被删除，cur刚刚好指向
         这次m循环开始结点的上一个结点，继续进行删除。
         */
        while(cur.next != cur)
        {
            //cur在m循环开始结点的前一个结点，每次循环使得其走过m步，到达m-1结点处
            for (int i = 1; i < m ; i++)
            {
                cur = cur.next;
            }
            cur.next = cur.next.next;//将这次m循环的第m个结点删除
        }
        //出循环，最后剩下的结点就是最后的孩子
        return cur.val;
    }

//----------------------------------------------------------------------------------------------------

    public int LastRemaining_Solution(int n, int m)
    {
        if(n==0 || m==0)
            return -1;

        /** 递推公式：
         f(i,m) = ( f(i-1,m) + m)%i
         */
        int f_1 = 0;//只有一个人的约瑟夫环的解 f(1,m)

        int temp = f_1;
        //下面我们用循环解，其实也可以用递归解
        for (int i = 2; i <=n ; i++)
        {
            temp = (temp+m)%i;
        }
        //出循环的时候，找到约瑟夫环长度为n的时候的解
        return temp;
    }

}
