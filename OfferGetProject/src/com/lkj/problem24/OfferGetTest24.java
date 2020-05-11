package com.lkj.problem24;

import java.util.ArrayList;

public class OfferGetTest24
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

    /**
     * 这一题需要注意：打印出二叉树中结点值的和为输入整数的所有路径
     * 是要找到所有的路径，不是单单找到某一个满足的路径
     */
    //首先，我们创建一个成员的ArrayList：ret，ret用于存储 保存了各个路径结点的ArrayList:temp
    private ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

    //这个方法用于 找到所有满足的路径
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target)
    {
        ArrayList<Integer> temp = new ArrayList<>();//用于保存路径结点的ArrayList
        //回溯/折回 保存结点的方法：这个方法用于找出以 root 为结点的满足条件的路径，并将路径保存到temp，再将 temp 保存到ret
        backtracking(root , target , temp);

        //将添加了结点的ret返回
        return ret;
    }

    private void backtracking(TreeNode node, int target , ArrayList<Integer> temp)
    {
        //首先，如果递归到叶子结点后面，已经没有其他结点了，我们不需要再添加结点到 temp，直接结束函数，结束添加
        if(node == null)
            return;

        int val = node.val;
        temp.add(val);//将结点的值添加到temp中
        target -= val;//target减去node结点的值

        /**
         * 1）当递归到叶子结点，且target=0，此时这一路径 temp 满足条件，将其添加到ret
         * 2）如果 target=0，但是没有递归到叶子结点，会继续往 node 的左右子树递归，直到递归到node=null结束递归，此时target<0，下面的路径都不会添加到ret；
         *     如果target！=0，递归到叶子结点，不会讲temp添加到ret，再次进入node的左右结点（都为null），直接结束递归；
         *     如果 target！=0且没有递归到叶子结点，持续向左右子树进行结点的添加；
         *
         *  另外，不管是添加此路径到ret，还是向左右子结点递归，我们都要在temp中删除当前结点，前面已经将包含当前结点的路径添加到ret（满足的话，不满足也遍历过包含当前结点的路径）
         *  我们将当前结点从temp中剔除，返回上一级递归，上一级递归就可以添加上一级node的右孩子结点，形成新的路径；或者上一级node的右子树也遍历完毕，
         *  就会删除上一级的node，继续向上回递归。
         *  这样，我们就可以通过一个temp，就将以root为根的所有路径都遍历一遍，并将满足的路径添加到ret（画个图就明白，重要的是最后一步，删除temp中的当前结点）
         */
        if(target == 0 && node.left == null && node.right == null)
        {
            /*
            注意！！，这里我们不能直接将temp放入ret中，因为最后可能有多条路径，如果直接将temp放入ret中，
            那么下一次我们再次找到新的路径，将temp放入ret中，由于是同一个temp引用，后面的路径就会覆盖前面的路径，
            因此我们需要使用 ArrayList(Collection<? extends E> c) 这个构造方法，将temp内部的元素new成为一个新的ArrayList，再添加到ret
             */
            ret.add(new ArrayList(temp));
        }
        else
        {
            backtracking(node.left , target , temp);
            backtracking(node.right , target , temp);
        }

        //当回递归到这里，node结点的值就保存在temp的尾部（因为后面的node也删除了），那么删除temp的最后一个元素即可
        temp.remove(temp.size()-1);
    }
}
