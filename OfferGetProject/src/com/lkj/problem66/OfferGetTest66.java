package com.lkj.problem66;

/** 同样使用深度优先遍历和剪枝（对比65题）
参考文章：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/mian-shi-ti-13-ji-qi-ren-de-yun-dong-fan-wei-dfs-b/
 时间复杂度：O(mn)，即能到达所有的结点！
 */
public class OfferGetTest66
{
    int m = 0 , n = 0 , k = 0;
    boolean[][] visited = null;//用于记录某个位置是否被访问过的数组

    public int movingCount(int m, int n, int k)
    {
        this.m = m;
        this.n = n;
        this.k = k;
        visited = new boolean[m][n];

        //从坐标为0,0位置开始查找
        return dfs(0 , 0);
    }

    //判断当前结点是否可达的方法，如果可达，进入下一个结点的判断，同时将总的可达结点数+1
    private int dfs(int row , int col)
    {
        //计算当前横纵坐标每一位数字的和
        int rowDigitSum = getDigitSum(row);
        int colDigitSum = getDigitSum(col);

        /** 如果当前结点不可达，直接返回0即可。当前结点不可达有几类情况：
        1）行列越界；2）当前结点被访问过（没有必要重复添加）；3）当前结点不可达！
         */
        if(row >= m || col >= n || visited[row][col] || (rowDigitSum+colDigitSum) > k)
            return 0;

        /**
         根据题解的分析，我们只需要向右或者向下递归查找，就可以到达所有的可达结点，
         因此，在这里，末尾只需要向右或者向下查找即可！
         另外，如果当前结点被访问一次，将 visited[row][col] 设置为true，我们不需要像65那样遍历完后重新将 visited[row][col] 设置为false，
         因为如果当前结点可达，那么可达结点数就会+1，会向下一直找到不可达的结点，不会向65那样回到这个结点再去访问其他结点！
         其他还未到达的结点会由其他递归查找！（想象这个过程！）
         65是要找符合的路径，如果当前结点下的某一条路径不可达，会再次回答当前结点查找其他路径！
         */
        visited[row][col] = true;
        //将所有遍历可以到达的所有结点数加起来！
        return 1 + dfs(row+1 , col) + dfs(row , col+1);
    }

    //用于数字num计算各位和的方法
    private int getDigitSum(int num)
    {
        int sum = 0;
        while(num!=0)
        {
            sum += num%10;
            num = num/10;
        }
        return sum;
    }
}
