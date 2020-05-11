package com.lkj.problem11;

public class OfferGetTest11
{
    public static int NumberOf1_1(int n) {
        //如果n=0，不进入循环，直接返回count=0
        int count = 0;//计数的变量
        while(n != 0)//当n右移到还不为0的时候，继续右移
        {
            if((n&1) != 0)//n当前遍历到的二进制位如果不为0（为1），就将计算器加1
            {
                count++;
            }
            n >>>= 1;//无符号右移
        }
        return count;
        //运行时间：14ms，占用内存：9016k
    }

    public static int NumberOf1_2(int n)
    {
        //将int类型的n转换为二进制的字符串
        String str = Integer.toBinaryString(n);
        //将字符串转换为字符数组
        char[] chars = str.toCharArray();

        int count = 0;
        //循环计算1的个数
        for (int i = 0; i < chars.length ; i++)
        {
            if(chars[i] == '1')//注意这里应该比较字符‘1’
                count++;
        }
        return count;

        //运行时间：18ms,占用内存：9060k
    }

    public static int NumberOf1_3(int n)
    {
        return Integer.bitCount(n);
        //运行时间：20ms,9396k
    }

    public static void main(String[] args)
    {
        long startTime = System.nanoTime();   //获取开始时间
        int num = NumberOf1_3(-475);//注意输入的数是int类型32位
        long endTime = System.nanoTime(); //获取结束时间
        System.out.println(num);
        System.out.println("程序运行时间： " + (endTime - startTime) + "ns");
    }
}
