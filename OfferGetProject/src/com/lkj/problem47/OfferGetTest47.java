package com.lkj.problem47;

/**
题目描述：求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class OfferGetTest47
{
    //递归
    public static int Sum_Solution1(int n) {
        int sum = 0;
        //最小规模的问题
        if(n == 0)
            return 0;

        //解决较小规模的问题：求解1+2+..+n-1
        //将较小问题的解整合成为较大问题的解：n+(1+2+..+n-1)
        sum = n + Sum_Solution1(n-1);
        return sum;

        //运行时间：18ms,占用内存：9372k
    }

    //公式法
    public static int Sum_Solution2(int n)
    {
        int result = (int)Math.pow(n,2)+n;
        return result/2;
        //运行时间：12ms,占用内存：9540k
    }

    //递归加短路
    public static int Sum_Solution3(int n)
    {
        int res = n;
        /**
         拿n=4来举例说明：
         当给flag进行赋值操作时，首先判断4>0是true还是false，发现4>0为true，此时并没有办法知道flag为true还是false，所以还要执行&&后面的部分，进行递归：res += Sum_Solution3(n-1)
         更小的问题，判断3>0是否正确....

         其中flag是设置来让程序不出错，实际上没有意义。(res += Sum_Solution3(n-1))>0)，也是使得程序不出错。
         */
        boolean flag = (n>0) && ((res += Sum_Solution3(n-1))>0);
        return res;
        //运行时间：13ms,占用内存：9204k
    }

    public static void main(String[] args)
    {
        System.out.println(Sum_Solution3(100));
    }
}
