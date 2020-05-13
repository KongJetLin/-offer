package com.lkj.problem45;

import java.util.Arrays;

public class OfferGetTest45
{
    public boolean isContinuous(int [] numbers)
    {
        //首先，顺子的元素个数最少为5，当数组长度小于5，直接返回false，不是顺子！
        if(numbers.length < 5)
            return false;

        Arrays.sort(numbers);//堆数组元素进行排序
        int cnt = 0;//用于统计0的个数

        for (int i = 0; i < numbers.length ; i++)
        {
            if(numbers[i] == 0)
                cnt++;
            else
                break;
        }

        // 使用癞子去补全不连续的顺子
        /**
         * 从第一个不是0的元素开始查找，就是从数组 cnt 位置开始查找.
         * 1）如果有2个元素相同，说明不是顺子，直接返回false；
         * 2）当 numbers[i+1]>numbers[i]的时候，统计2个元素之间的“间隙”，用0补充。（数组已经排序，不会有 numbers[i+1]<numbers[i] 的情况）
         */
        for (int i = cnt; i < numbers.length-1 ; i++)
        {
            if(numbers[i+1] == numbers[i])
                return false;
            //numbers[i+1]与numbers[i]之间差值为1，连续；大于1（n,n=numbers[i+1]-numbers[i]），则cnt需要取n-1个0补充“间隙”,cnt-（n-1）
            //即cnt = cnt - (numbers[i+1]-numbers[i]-1)
            cnt -= numbers[i+1]-numbers[i]-1;
        }

        //当剩余癞子（0）的数目大于等于0，说明癞子够用，多余的部分可以补充在数组头部或者尾部，返回true
        return cnt>=0;
    }
}
