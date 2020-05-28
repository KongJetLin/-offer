package com.lkj.problem31;

/* 31
题目描述：求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 */
public class OfferGetTest31
{
    public static int NumberOf1Between1AndN_Solution(int n)
    {
        int totalNum = 0;//用于存储1的总个数；
        int round = n;
        int base = 1;//每一位的权重
        int former = 0;
        int weight = 0;

        /** 以6374为例
         个位：进循环时round=6374，base=1，更新：weight=4，round=637，former=0，计算totalNum，更新base=10
         十位：进循环时round=637，base=10，更新：weight=7，round=63，former=4，计算totalNum，更新base=100
         百位：进循环时round=63，base=100，更新：weight=3，round=6，former=74，计算totalNum，更新base=1000
         千位：进循环时round=6，base=1000，更新：weight=6，round=0，former=374，计算totalNum，更新base=10000
         跳出循环
         */
        while(round!=0)
        {
            weight = round%10;//先更新weight
            round = round/10;
            former = n%base;

            if(weight == 0)
            {
                totalNum += round*base;
            }
            else if(weight == 1)
            {
                totalNum += (round*base+former+1);
            }
            else //weight>1
            {
                totalNum += (round*base+base);
            }

            //最后再更新base
            base *= 10;
        }

        return totalNum;
    }

    public static void main(String[] args)
    {
        System.out.println(NumberOf1Between1AndN_Solution(18));
    }
}
