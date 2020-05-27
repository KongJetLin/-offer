package com.lkj.problem4;

import java.util.HashMap;

public class OfferGetTest4
{
     public class TreeNode
     {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x)
          {
              val = x;
          }
     }

    /**
     * 这里从前序遍历中取出结点用于构建二叉树；
     * 中序遍历用于分割确定左右子树的结点数目，从而在前序遍历数组中，确定左右子树的范围！
     */

    //定义一个Map集合，用于存储中序遍历结点值及其在中序遍历数组中的下标
    private HashMap<Integer, Integer> hm = new HashMap<>();

    public TreeNode reConstructBinaryTree(int [] pre,int [] in)
    {
        if(pre == null || in == null)
            return null;
        //首先将中序遍历所有结点的值及其在中序遍历数组中的下标存储到map集合
        for (int i = 0; i < in.length ; i++)
        {
            hm.put(in[i] , i);
        }
        //当前树是最大的子树，在pre数组的范围是 0-pre.length-1，在in数组的左指针下标是0
        return reConstructBinaryTree(pre , 0 , pre.length-1 , 0);
    }
    /**
    下面递归，将当前子树在前序遍历数组中的结点构建成为二叉树，返回当前子树的根结点
     其中，pre代表当前遍历的是前序遍历数组；preL与preR代表当前子树的所有结点在前序遍历数组中的范围的左右指针；
     inL代表当前子树所有结点在中序遍历数组中的范围的左指针（用于计算当前结点左右子树范围的大小）
     */
    private TreeNode reConstructBinaryTree(int[] pre , int preL , int preR , int inL)
    {
        /**
         对于如下情况：
                node （叶子结点）
               /    \
              null  null
         如果结束条件 preL=preR，此时遍历到叶子结点，如果直接return null，那么叶子结点就会被忽略；
         应该设置 preL>preR，当preL=preR的时候，将叶子结点返回，并继续递归其左右孩子结点（都为null），
         下一次递归中，叶子结点的左右孩子结点： preL>preR，就可以直接返回null。

         而对于如下情况：
              node1          node1
             /    \         /   \
           null  node2   node2  null
         node1的左子树中，preL>preR，此时直接返回null;
         node1的右子树中，preL=preR，继续递归，避免错过node2，node2的左右子树就 preL>preR，直接返回null

         因此，对于所有情况，为了避免错过叶子结点，用preL>preR，不可以用preL=preR或者preL>=preR
         */

        if(preL>preR)
            return null;

        //首先，确定当前子树的根结点，就是当前前序遍历左指针指向的结点
        TreeNode root = new TreeNode(pre[preL]);
        //其次，找出当前根结点在中序遍历数组中的下标
        int index = hm.get(pre[preL]);
        //通过当前根结点的下标index，以及当前子树在中序遍历数组中范围的左指针（左下标），算出当前结点左子树结点的数目
        int leftTreeNodesNum = index - inL;

        //这样，就可以确定当前结点左右子树在前序遍历数组中的范围，使用reConstructBinaryTree方法递归构建二叉树，连接到当前结点上
        //左子树在中序遍历数组的左指针还是inL； 左子树在pre范围：preL+1 ——> preL+leftTreeNodesNum
        root.left = reConstructBinaryTree(pre , preL+1 , preL+leftTreeNodesNum , inL);
        //右子树在中序遍历数组的左指针变成：inL+leftTreeNodesNum+1 ；（inL+leftTreeNodesNum 是当前根结点在in数组的下标，下一个开始就是右子树的范围）
        // 右子树在pre范围：preL+leftTreeNodesNum+1 ——> preR
        root.right = reConstructBinaryTree(pre , preL+leftTreeNodesNum+1 , preR , inL+leftTreeNodesNum+1);

        return root;

    }
}
