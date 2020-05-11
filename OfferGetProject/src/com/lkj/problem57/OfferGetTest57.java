package com.lkj.problem57;

public class OfferGetTest57
{
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        //首先，如果pNode=null，说明当前结点不存在，那么下一个结点肯定也不存在，直接返回null
        if(pNode == null)
            return null;

        //第一种情况，pNode存在右子树，找到右子树最左边的结点
        if(pNode.right != null)
        {
            pNode = pNode.right;//将指针移动到右子树根结点
            if(pNode.left != null)
                pNode = pNode.left;//一直将指针移动到当前结点的左孩子结点，直到左孩子结点不存在，那么当前结点就是下一个结点（右子树的最左结点）
            return pNode;
        }

        //第二种情况，pNode不存在右子树，那么一直找他的父节点，直到当前结点是其父结点的左孩子结点（父节点是当前结点的右父节点）
        //如果父亲结点存在，一直寻找符合条件的父亲结点
        while(pNode.next != null)
        {
            if(pNode.next.left == pNode)
                return pNode.next;//找到“右父节点”，那么这个“右父节点”就是下一个结点
            pNode = pNode.next;//没有找到“右父节点”，就一直将指针移动到父节点向上寻找
        }
        //如果遍历到父亲结点不存在还是没有满足条件的结点，说明pNode是最后一个结点你，直接返回null
        return null;
    }
}
