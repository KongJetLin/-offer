package com.lkj.problem26;

public class OfferGetTest26
{
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    private TreeNode head;//创建一个头结点，用于返回双向链表头结点
    //由于插入一个链表，我们只需要考虑当前结点左指针 和 前一个结点右指针，那么我们只需要创建一个代表前一个结点的pre即可
    private TreeNode pre;

    public TreeNode Convert(TreeNode pRootOfTree)
    {
        //使用中序遍历方式找出二叉搜索树所有结点，并一个个连接到双向链表上
        inOrder(pRootOfTree);
        return head;//返回双向链表头结点
    }

    private void inOrder(TreeNode node)
    {
        if(node == null)
            return;//如果二叉搜索树结点遍历完，不需要再向双向链表添加结点，直接结束递归

        //中序遍历左子树
        inOrder(node.left);

        /*
        对于遍历到的当前结点，我们先判断前一个结点pre是否存在，
        如果不存在，说明在双向链表中，当前结点是第一个结点，将当前结点赋予pre；
        如果存在，说明在双向链表中，当前结点前面有结点，将pre.right指向当前结点node，将node.left指向pre，这样当前结点就连接到双向链表，
        此时，对于下一个插入的结点当前结点就是前一个结点，因此将pre指向node，便于插入下一个结点。

        另外，如果pre=null，说明node是双向链表第一个结点，将其赋予head
         */
        if(pre != null)
        {
            pre.right = node;
            node.left = pre;
            pre = node;
        }
        else
        {
            pre = node;
            head = node;
        }

        //中序遍历右子树
        inOrder(node.right);

    }
}
