package com.lkj.problem58;

public class OfferGetTest58
{
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    boolean isSymmetrical(TreeNode pRoot)
    {
        if(pRoot == null)
            return true;//如果根结点为null，表示二叉树是对称的，返回true

        return isSymmetrical(pRoot.left , pRoot.right);
    }

    //用于比较 以node1为根的左子树 与 以node2位根的右子树 的对应位置的结点是否全部相等，返回比较的Boolean结果
    private boolean isSymmetrical(TreeNode node1 , TreeNode node2)
    {
        //---------------------------------------首先比较2个子树的根结点
        //如果node1与node2都是null，说明到了二叉树的末尾，此时应该返回true
        if(node1 == null && node2 == null)
            return true;
        //如果node1与node2不都是null，但是node1或者node2有一个是null，此时说明二叉树不是对称的
        if(node1 == null || node2 == null)//前面已经把node1与node2都是null的情况排除，现在这里只有可能出现 node1或者node2有一个是null
            return false;
        //如果 node1与node2都存在，且他们的值不相等，二叉树不对称，返回false
        if(node1.val != node2.val)
            return false;

        //-----------------------------------------其次比较2个子树的子树对应位置的结点是否相等
        //如果2个结点相等，且还没有到达二叉树末尾，那么比较 以node1的左孩子结点为根的树 与 以node2右孩子结点为根的树 是否相等，
        //以及 以node1右孩子结点为根的树 与 以node2的左孩子结点为根的树 是否相等，只有他们全部相等才会返回true（这部分画个图就知道了）
        return isSymmetrical(node1.left , node2.right) && isSymmetrical(node1.right , node2.left);
    }
}
