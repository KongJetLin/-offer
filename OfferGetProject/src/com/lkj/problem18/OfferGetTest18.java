package com.lkj.problem18;

public class OfferGetTest18
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

    public void Mirror(TreeNode root)
    {
        //如果当前结点为null，那么当前结点就没有左右孩子结点，不需要镜像
        if(root == null )
            return;
        else
        {//当当前结点不为null，如果当前结点的左右孩子结点都为null，也不需要镜像（但是其实镜像也没有关系，如果判断，最优的情况下可以减少接近一半的镜像！）
            if((root.left==null && root.right==null))
                return;
        }
        //先将当前结点的左右子树交换
        swap(root);
        //下面交换左右孩子结点的左右子树
        Mirror(root.left);
        Mirror(root.right);
    }

    private void swap(TreeNode node)
    {
        TreeNode temp = node.right;
        node.right = node.left;
        node.left = temp;
    }
}
