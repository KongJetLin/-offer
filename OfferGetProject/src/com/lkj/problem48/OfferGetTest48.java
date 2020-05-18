package com.lkj.problem48;

public class OfferGetTest48
{
    public int Add(int num1,int num2)
    {
        // 当进位不为0的时候，说明还不是最后的结果，需要继续相加
        while(num2 != 0)
        {
            /**
             * 下一次计算的时候，就是 没有进位的结果 + 进位结果，还是 num1 与 num2的相加，
             * 我们将 没有进位结果存储到 num1，将进位结果存储到 num2即可
             */
            //不能直接将 num1^num2 赋予 num1，因为 num1还不能改变，接下来还需参与这一轮的运算。因此用一个临时变量存储。
            int temp = num1^num2;//异或，得到当前计算不算进位的结果
            // num2可以直接设置为 进位结果，因为num2接下来不需要再参与这一轮的运算
            num2 = (num1&num2)<<1;//与运算左移一位，得到当前计算的进位结果，将这个结果存储到num2
            num1 = temp;//运算完这一轮，将 没有进位的结果 赋予num1，方便进行下一轮运算
        }

        return num1;//最后的结果存储到 没有进位的结果num1中
    }
}
