package com.lkj.problem9;

/**
 * 题目描述：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class OfferGetTest9
{
    //1、基于公式的递归法
    public static int JumpFloorII1(int target) {
        //不需要考虑f(0)=0的影响
        if(target<=0)
            return -1;
        if(target==1)
            return 1;

        return 2*JumpFloorII1(target-1);
        //运行时间：12ms,占用内存：9404k
    }

    //2、基于公式的循环法
    public static int JumpFloorII2(int target) {
        //不需要考虑f(0)=0的影响
        if(target<=0)
            return -1;
        if(target==1)
            return 1;

        int res = 1;//用于保存第n级台阶的方案数，初始化为第一级台阶的方案数
        for (int i = 2; i <= target ; i++)
        {
            res = res*2;
        }
        return res;
        //运行时间：19ms,占用内存：9372k
    }

    //3、暴力递归
    public static int JumpFloorII3(int target) {
        /*
        根据公式：f(n) = f(n-1)+f(n-2)+…+f(1)+f(0)
        为了避免遗漏从0 位置跳转n步到n位置这种情况，设置f(0)=1
         */
        if(target==0)
            return 1;
        if(target==1)
            return 1;

        int sum = 0;
        while(target>=1)
        {
            sum += JumpFloorII3(target-1);
            target--;
        }
        return sum;
        //运行时间：19ms,占用内存：9284k
    }

    public static void main(String[] args)
    {
        System.out.println(JumpFloorII3(4));
    }
}
