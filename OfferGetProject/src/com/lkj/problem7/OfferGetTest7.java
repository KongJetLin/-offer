package com.lkj.problem7;

/**
题目描述
大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
 F(1)=1，F(2)=1, F(n)=F(n-1)+F(n-2)
 */
public class OfferGetTest7
{
    /**
     * 注意，这个数列从第0项开始，因此数列实际上为：0,1,1,2,3,5,8,13....
     */
    public static int Fibonacci(int n) {
        //当取第0,1项的时候，直接返回n（注意第0项不可忽略）
        if(n<=1)
            return n;

        int res = 0;//存储第n个数的值，初始化为0
        int n1 = 1;//F(n-1)，初始化设置为 F(1)的值
        int n2 = 0;//F(n-2)，初始化设置为 F(0)的值

        //由于0,1项已经确定，从第二项开始计算
        for (int i = 2; i <= n ; i++)
        {
            res = n1+n2;
            //更新，获取下一个循环的n1与n2的值（既计算下一个n的F(n-1)与F(n-2)）
            n2 = n1;
            n1 = res;
        }
        return res;
        //运行时间：13ms,占用内存：9332k
    }

    //使用递推法完成
    public static int Fibonacci1(int n)
    {
        //1、解决规模最小的问题：n =0,1的情况，其他情况都可以由这些情况组合。
        if(n<=1)
            return n;

        //2/3、解决规模较小问题：求解F(n-1)与F(n-2)；将较小问题整合成为较大问题的解：F(n)=F(n-1)+F(n-2)
        return Fibonacci1(n-1)+Fibonacci1(n-2);
        //运行时间：1089ms，占用内存：9408k
    }

    public static void main(String[] args)
    {
        System.out.println(Fibonacci1(4));
    }
}
