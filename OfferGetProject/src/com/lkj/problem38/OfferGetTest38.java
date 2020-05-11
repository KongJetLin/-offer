package com.lkj.problem38;

public class OfferGetTest38
{
    public class TreeNode
    {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val)
        {
            this.val = val;
        }
    }

    //查询以root为根的二叉树的深度
    public int TreeDepth(TreeNode root)
    {
        //当遍历到二叉树的末尾的时候，root=null，此时这里没有结点，返回0
        if(root == null)
            return 0;

        //由于二叉树的最深处可能在左子树，也可能在右子树，我们需要分别查询左子树以及右子树的深度，找出最深的深度
        int lDepth = TreeDepth(root.left);
        int rDepth = TreeDepth(root.right);
        //查询到左子树与右子树的最大深度，并加上当前结点的深度1，就是以root为根的树的最大深度
        return 1+ (lDepth>rDepth ? lDepth : rDepth);
    }

    //上面代码可以简化为如下
    public int TreeDepth1(TreeNode root)
    {
        return root==null ? 0 : 1+Math.max(TreeDepth1(root.left) , TreeDepth1(root.right));
    }
}
