package com.lkj.problem62;

public class OfferGetTest62
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

    //首先，我们定义2个全局变量
    private int count = 0;//用于标记结点的位置
    private TreeNode ret = null;//用于保存第k个结点

    TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot == null || k <= 0)
            return ret;//直接返回ret=null，表示满意找到相应结点
        //进行中序遍历，找到第k个结点，将其赋予ret
        inOrder(pRoot , k);
        return ret;
    }

    //找到以node为根结点的树的某个结点，使得count=k
    private void inOrder(TreeNode node , int k)
    {
        if(node == null)
            return;//node=null，说明到达树的末尾，结束递归

        //先中序遍历node左子树
        inOrder(node.left , k);
        //再遍历node
        count++;//遍历到一个结点，使得count计数+1
        //如果找到第k个结点，就将其赋予ret
        if(count == k)
            ret = node;
        //前面没找到，继续遍历右子树
        inOrder(node.right , k);
        /**
         * 1）如果元素个数小于k，最后ret不会被赋值，ret=null，表示没有找到相应结点
         * 2）如果元素个数大于等于k，最后一定会有count=k，就会把第k个结点赋予ret，找到！
         * 3）pRoot=null或者k<=0的情况前面已经判断
         */
    }
}
