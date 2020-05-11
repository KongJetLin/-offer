package com.lkj.problem60;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OfferGetTest60
{
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**这一题注意对比第22题的解法*/
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot)
    {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

        //注意，这一题如果pRoot为null的时候，我们直接返回arrayList即可
        //否则因为根结点为空，queue.add(pRoot) 的时候回出现空指针异常
        if(pRoot == null)
            return arrayLists;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while(!queue.isEmpty())
        {
            //用一个变量记录队列保存的上一层的结点的数量
            int count = queue.size();
            ArrayList<Integer> temp = new ArrayList<>();
            //把上一层放入队列的结点的值全部取出放入ArrayList，同时将这些结点的左右孩子结点放入队列
            while(count>0)
            {
                TreeNode cur = queue.remove();
                temp.add(cur.val);
                count--;//每取出一个count-1，这样就能保证不打印这一层放入的新的结点

                //将队列取出的结点的孩子结点放入队列，这些结点是下一层的结点，用于下一层的打印
                if(cur.left != null)
                    queue.add(cur.left);
                if(cur.right != null)
                    queue.add(cur.right);
            }
            //将添加了这一层所有结点的ArrayList放入最大的ArrayList
            arrayLists.add(temp);
        }
        //添加完所有ArrayList以后，将最大的ArrayList返回
        return arrayLists;
    }
}
