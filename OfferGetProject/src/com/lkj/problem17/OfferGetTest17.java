package com.lkj.problem17;

import java.util.HashMap;

public class OfferGetTest17
{
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    //这个方法用于判断 树root2是不是树root1的子结构（子树）
    public boolean HasSubtree(TreeNode root1,TreeNode root2)
    {
        /*
        进方法的时候，有几种情况：
        1）root1=null,root2=null/root1!=null,root2=null，由于约定空树不是任意一个树的子结构
            即root2进来就是null，那么直接返回false；
        2）root1=null，root2！=null，说明root1递归到叶子结点，但是root2还有下面的结点，同样不满足，返回false

        即只有 root1，root2均不为null的时候，才需要继续判断；揉揉腿1或者root2有一个是null，终止判断返回false
         */
        if(root1 == null || root2 == null)
            return false;


        //定义变量flag，用于保存最后的结果
        boolean flag = false;

        //对比A当前结点与B根结点是否相同，相同调用 isSubTree() 方法递归地判断它们各自的左右孩子节点的值是不是相同
        if(root1.val == root2.val)
        {
            //判断 以root2为根的树 是不是 以root1为根的树 的子树，保存判断结果
            flag = isSubTree(root1 , root2);
        }
        //如果当前 以root2为根的树 不是 以root1为根的树 的子树，flag=false，
        // 递归判断 树root2是不是 树root1.left 或者 树root1.right 的子结构，保存递归结果。（此处 树root1 表示以root1为根的子树）
        if(!flag)
        {
            //只要有一个子结构满足即可，因此用“或”
            flag = HasSubtree(root1.left , root2) || HasSubtree(root1.right , root2);
        }

        return flag;
    }

    //这个方法用于判断根值相同的2个树node1，node2，node2是不是node1的子树
    private boolean isSubTree(TreeNode node1 , TreeNode node2)
    {
        /*
        遍历到最后有几种情况：
        1）node1!=null,node2=null，说明node2递归到底端，但是node1没有到底端，前面父结点满足，返回true；
        2）node1=null，node2=null，说明node1，node2递归到底端，前面父结点满足，返回true；
        3）node1=null，node2！=null，说明说明node1递归到底端，但是node2还没，那么不满足，返回false
        4）node1!=null，node2！=null，继续判断。

        即，只要node2=null，返回true；node1=null，node2！=null，返回false，否则继续执行
         */
        if(node2 == null)
            return true;
        if(node1==null && node2!=null)
            return false;

        //如果node1，node2值相同，继续递归判断他们对应的子结点是否相同
        if(node1.val == node2.val)
        {
            //左右子树必须全部相同才可以返回true，用“与”
            return isSubTree(node1.left , node2.left) && isSubTree(node1.right , node2.right);
        }
        else//这里else可以不加，因为前面如果满足则return，不会执行下面的return；不满足一定会return这里
        {
            //当有一个结点值不同，说明当前子结构不满足，返回false
            return false;
        }
    }
}
