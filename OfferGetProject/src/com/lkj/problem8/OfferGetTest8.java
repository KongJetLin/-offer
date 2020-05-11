package com.lkj.problem8;

public class OfferGetTest8
{
    //1、裴波那契数列法
    public static int JumpFloor1(int target) {
        if(target<=2)
            return target;

        int res = 0;//用于记录第target个台阶的方案数
        int n1 = 2;//用于记录F(n-1)级台阶的方案数，初始化为第2及台阶的方案数：2
        int n2 = 1;//用于记录F(n-2)级台阶的方案数，初始化为第2及台阶的方案数：1

        //从第3级台阶开始，使用 F(n) = F(n-1)+F(n-2) 递推法
        for (int i = 3; i <= target ; i++)
        {
            res = n1+n2;//F(n)
            //更新F(n-1)与F(n-2)，使得F(n-2)=F(n-1)，F(n-1)=F(n)，用于下一轮循环计算F(n+1)
            n2 = n1;
            n1 = res;
        }

        return res;
        //运行时间：13ms，占用内存：9408k
    }


    //2、动态规划
    public static int JumpFloor2(int target)
    {
        if(target<=2)
            return target;

        //定义动态规划数组：为了方便表示，我们抛弃数组0位置，使用数组的 1-target位置，那么数组长度为 target+1
        int[] dp = new int[target+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= target ; i++)
        {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[target];
        //运行时间：13ms,占用内存：9320k

    }

    //3、递推法
    public static int JumpFloor3(int target)
    {
        //1、解决规模最小的问题：target =0,1,2 的情况，其他情况都可以由这些情况组合。
//        if(target==0)
//            return 0;//有可能出现 F(2) = F(1)+F(0)，F(0)时方案数为0，返回0
//        if(target==1)
//            return 1;
//        if(target==2)
//            return 2;
        if(target<=2)
            return target;


        //2/3、解决规模较小问题：求解F(n-1)与F(n-2)；将较小问题整合成为较大问题的解：F(n)=F(n-1)+F(n-2)
        return JumpFloor2(target-1)+JumpFloor2(target-2);
        //运行时间：504ms,占用内存：9440k
    }

    public static void main(String[] args)
    {
        System.out.println(JumpFloor2(3));
    }
}
