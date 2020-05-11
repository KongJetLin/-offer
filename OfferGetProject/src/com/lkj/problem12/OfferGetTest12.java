package com.lkj.problem12;

public class OfferGetTest12
{
    //直接方法：直接连续累乘。
    public static double Power1(double base, int exponent) {
        /**
         * 首先需要指出，java中0的0次方是1.0，既java中的所有数的0次方都是1.0
         * 那么我们下面需要先判断指数是否为0，指数为0，全部返回1.0（哪怕底数是0，也返回1.0）
         * 在指数不为0的情况下，底数为0，则结果是0.0
         */

        //首先，对2种特殊情况进行考虑
        //情况1：指数为0，底数不管是什么，结果都是1.0
        if(exponent == 0)
            return 1.0;
        //情况2：底数为0（此时指数不为0），结果都是0.0
        if(base == 0.0)
            return 0.0;

        //排除上面2种情况，既指数与底数都不为0后，其他情况我们的下面的方法都可以覆盖，不需要再考虑特殊情况

        //创建一个result变量记录结果
        double result = 1;
        if(exponent > 0.0)
        {
            while (exponent != 0.0)
            {
                result *= base;
                exponent--;
            }
            return result;
        }
        else //此时只有exponent<0.0的情况
        {
            while (exponent != 0.0)
            {
                result *= base;
                exponent++;
            }
            return 1/result;
        }

        //运行时间：53ms,占用内存：10216k
    }

    //快速幂运算
    public static double Power2(double base, int exponent)
    {
        //同样按顺序排除2类特殊情况
        if(exponent == 0)
            return 1.0;
        if(base == 0.0)
            return 0.0;

        //下面，先将exponent转换为正数，使用临时变量tempExp保存
        int tempExp = Math.abs(exponent);

        double res = 1.0;//定义一个数保存值

        //当tempExp右移到为0的时候，它的每一个二进制位比较完毕！（外层循环判断tempExp是否为0）
        while (tempExp != 0)
        {
            //取tempExp某一 二进制位的值，如果这个二进制位上的值不为0，就乘以这一位对应的base
            if((tempExp&1) != 0)//（内层if判断tempExp的某一二进制位是否为0）
            {
                res *= base;
            }
            //tempExp的这一个二进制位比较完之后，需要比较下一个二进制位，右移一位，且base的值应该变为base^2，既base*base
            //由于tempExp为正数，不需要无符号右移
            tempExp >>= 1;
            base = base*base;//base需要根据二进制位的位数进行变化
        }

        if(exponent < 0)
        {
            return 1/res;
        }
        return res;

        //运行时间：38ms,占用内存：10388k
    }


    public static void main(String[] args)
    {
        double result = Power2(-2,-3);
        System.out.println(result);
    }
}
