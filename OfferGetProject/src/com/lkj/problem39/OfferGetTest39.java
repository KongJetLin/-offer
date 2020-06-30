package com.lkj.problem39;

/**
平衡二叉树左右子树高度差不超过 1。
 */
public class OfferGetTest39
{
    //先定义结点类
    private class TreeNode
    {
        public TreeNode left;
        public TreeNode right;

        public TreeNode()
        {
            left = null;
            right = null;
        }
    }

    public boolean IsBalanced_Solution(TreeNode root)
    {
        int height = getHeight(root);
        //在计算root结点高度的过程中，如果返回-1，说明树中有不平衡结点，树不是平衡二叉树。
        if(height == -1)
            return false;
        //如果返回root树的高度，则该树是平衡二叉树！
        return true;
    }

    /** 用于获取node结点高度的方法
    1） 这个方法当当前结点左右孩子结点不平衡、或者当前结点不平衡，则不会返回当前结点高度，而是返回-1；
        如果当前结点平衡则会返回当前结点高度。
    2） 只要底层有一个结点不平衡，就会全部返回-1，那么其他上面的结点也不需要计算高度，因为已经直接返回-1了；
    这个方法即在计算当前结点高度之前，先计算左右孩子结点高度，并判断左右孩子结点是否平衡
     */
    private int getHeight(TreeNode node)
    {
        if(node == null)
            return 0;//空结点高度为0

        //先求当前结点左右孩子结点高度，并计算左右孩子结点平衡性（后序遍历会递归到叶子结点开始）
        int leftNodeHeight = getHeight(node.left);
        if(leftNodeHeight == -1)
            return -1;//如果发现子结点不满足平衡性返回-1，整棵树不满足平衡性，那么当前结点的平衡性不需要计算，直接返回-1
        int rightNodeHeight = getHeight(node.right);
        if(rightNodeHeight == -1)
            return -1;

        //如果孩子结点都平衡，我们利用左右孩子结点的高度，计算当前结点的平衡因子
        int balanceFactor = Math.abs(leftNodeHeight - rightNodeHeight);
        if(balanceFactor > 1)
            return -1;//如果当前结点不平衡，直接返回-1
        else
            return 1 + Math.max(rightNodeHeight , leftNodeHeight);
    }
}
