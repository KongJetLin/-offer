package com.lkj.problem23;

public class OfferGetTest23
{
    public boolean VerifySquenceOfBST(int [] sequence)
    {
        //先判断数组是否合法
        if(sequence == null || sequence.length == 0)
            return false;
        //验证以 数组中下标为 0 到 sequence.length-1的数 是否二分搜索树的后序遍历序列
        //其中，sequence.length-1 为最大的二分搜索树的根
        return verify(sequence , 0 , sequence.length-1);
    }

    //验证以 数组中下标为 start 到 end 的数 是否二分搜索树的后序遍历序列
    private boolean verify(int[] sequence , int start , int end)
    {
        /*
        最后的情况：
        当遍历到的数组有3个数，即 end - start = 2，此时二叉树可能是：
                    （1）      （2）        （3）          （4）
         n1          n1         n1           n1            n1
        / \   或者  /      或者   \   或者    /    或者       \
      n2  n3      n2              n2        n2               n2
                  /                \         \              /
                 n3                 n3        n3           n3
        此时二叉搜索树还有可能不是后序遍历排列，我们还需将序列继续判断拆分；

       拆分后如果是第一种，对于左右子树都是 end - start=0，只有最后一个叶子结点，此时必然是后序遍历；
       如果是第二种，左子树或者右子树 end-start=1，结构如下：

        n2        n2
       /    或者    \
      n3            n3
      此时，注意到数组的每个数都不一样，那么n2!=n3，此时不管 n2>n3 或者 n3>n2 ，都可能对应某一个结构的后序遍历数列，
      n2>n3 对应上面（1）（4）， n3>n2 对应上面（2）、（3）。
      即当数组中只有2个数的时候，他们组成的二叉树必然满足某一结构二叉树的后序遍历序列，不需要继续拆分。

      当然，还有可能出现下面 while中 cutIndex=end的情况，即没有右子树，此时右子树的判断中：end-start<0
      那么，我们判断的时候判断 end-start<=1即可，即end-start可能为0或者1或者-1<0！

      当然，对于这里2个数的二叉树结构，也可以继续拆分，可能出现 end-start<=0的情况，下面判断 end-start<=0即可！！
      这里去除1的含义是将2个数的二叉树结构继续拆分。

       总结：当 end-start <=1 的时候，整个树满足二叉搜索树的后序遍历序列，返回true
         */
        if(end - start <=1)
            return true;

        //找到当前二分搜索树的根结点的值（中间值）
        int midValue = sequence[end];
        //定义一个指针，来将 [ 小数序列  大数序列  中间的数（唯一）] 小数序列与大数序列分开，这个值从start开始寻找
        int cutIndex = start;

        //当指针没有指向数组最后一个值：二分搜索树的根结点，且指针指向的数组的数小于根结点的值得时候，持续向下遍历
        while(cutIndex < end && sequence[cutIndex] < midValue)
            cutIndex++;
        //当跳出循环，要么cutIndex=end，此时说明没有大数序列，但是还需向下判断，此时大数序列 end(end-1)-start(cutIndex=end)=-1<0，返回true。而小数序列需要继续判断；
        // 要么 sequence[cutIndex] > midValue ，遍历完小数序列

        //cutIndex位置为大数序列的第一个数，当cutIndex<end，持续判断 sequence[cutIndex]是否全部大于midValue
        //如果全部大于，说明大数序列存在，继续判断子树；
        // 如果找到一个不大于，那么说明大数序列不存在，数组是 “小数 大数 小数 大数...”循环的模式，不是后序遍历序列，返回false
        for (int i = cutIndex; i < end ; i++)
        {
            if(sequence[i] < midValue)//注意这里是 sequence[i] ，不是sequence[cutIndex]
                return false;
        }

        //如果当前的序列满足，那么继续判断大数序列和小数序列（即当前根结点的左右子树）是否满足后序遍历，一次递归只能判断一层
        // 大数序列（右子树）的数组字段是：cutIndex —— end-1；小数序列（左子树）的数组字段是：0 —— cutIndex-1
        return verify(sequence , 0 , cutIndex-1) && verify(sequence , cutIndex , end-1);
    }
}
