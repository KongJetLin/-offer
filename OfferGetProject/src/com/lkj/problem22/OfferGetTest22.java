package com.lkj.problem22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OfferGetTest22
{
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        //注意，这一题如果root为null的时候，我们直接返回arrayList即可
        //否则因为根结点为空，queue.add(root) 的时候回出现空指针异常
        if(root == null)
            return arrayList;

        Queue<TreeNode> queue = new LinkedList<>();//创建一个队列，注意，LinkedList实现了Queue接口，它也可以算作队列
        queue.add(root);//先将二叉树根结点添加进来
        while (!queue.isEmpty())
        {
            TreeNode cur = queue.remove();
            arrayList.add(cur.val);//将队首结点出队，并将该结点的值添加到ArrayList
            if(cur.left != null)
                queue.add(cur.left);
            if(cur.right != null)
                queue.add(cur.right);
        }
        return arrayList;
        //运行时间：14ms,占用内存：9420k
    }
}
