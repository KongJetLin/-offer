package com.lkj.problem59;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class OfferGetTest59
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

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot)
    {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        //注意，这一题如果pRoot为null的时候，我们直接返回arrayList即可
        //否则因为根结点为空，queue.add(pRoot) 的时候回出现空指针异常
        if(pRoot == null)
            return arrayLists;
        //创建一个队列用于存储结点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);//先将根结点存储进来
        boolean reverse = false;//创建一个变量判断反转，第一行从左到右不需要反转，设置初始值为false

        while(!queue.isEmpty())
        {
            ArrayList<Integer> temp = new ArrayList<>();//用于存储某一行结点值的临时ArrayList
            //用一个变量记录队列保存的上一层的存储到队列中的结点值的数量
            //这里必须将上一层存储的结点数量先取出来，不能一边减少一边取，因为我们在取出的上一层结点值同时，这一层的结点值也在存储进去
            int count = queue.size();
            while (count>0)
            {
                TreeNode node = queue.remove();
                temp.add(node.val);
                count--;

                //取出上一层的某一个结点后，如果这个结点右左右孩子结点，这些孩子结点属于这一层，将他们存储到队列中
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            if(reverse)
                Collections.reverse(temp);
            reverse = !reverse;//将标志反转

            arrayLists.add(temp);
        }
        return arrayLists;
    }
}
