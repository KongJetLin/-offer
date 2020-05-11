package com.lkj.problem33;

public class OfferGetTest33
{
    public int GetUglyNumber_Solution(int index) {
        //首先，1-6这个6个数都是丑数，获取从小到大的顺序的第N个丑数，当N<=6的时候，我们直接返回N
        if(index <= 6)
            return index;

        //定义一个保存丑数的数组，大小为N(index)
        int[] dp = new int[index];
        dp[0] = 1;//初始丑数数组的第一个元素是1

        //定义三个队列在丑数数组中的指针，这个指针指向的数组元素乘以每个队列的对应数，就得到这个队列当前的最小值。
        int p2=0 , p3=0 , p5=0; // 初始指向丑数数组的第一个元素，3个队列此时最小元素分别是2、3、5

        //使用遍历，每一轮都向丑数数组放一个3个队列中最小的元素，直到数组放满
        for (int i = 1; i < dp.length ; i++) {
            //根据3个队列在丑数数组的指针，分别求出3个队列在这一轮的最小值
            int queue2 = dp[p2]*2 , queue3 = dp[p3]*3 , queue5 = dp[p5]*5;

            //求出3个队列最小元素中，最小的那一个
            int uglyNumCur = Math.min(queue2 , Math.min(queue3 , queue5));

            //将这个最小元素放入丑数数组
            dp[i] = uglyNumCur;

            //判断这个最小的元素属于哪个队列，将这个队列的最小元素弹出，其实就是这个队列的最小元素后移一位
            // 那么这个队列在丑数数组中的指针必须后移一位
            if(uglyNumCur == queue2)
                p2++;
            if(uglyNumCur == queue3)
                p3++;
            if(uglyNumCur == queue5)
                p5++;
        }
        return dp[index-1];//返回数组的最后一个元素，就是最大的第index个丑数

        //运行时间：13ms,占用内存：9320k
    }
}
