package com.lkj.problem40;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 题目描述：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 这里传入2个初始化0位置值为0的数组num1与num2，然后我们将找到的2个数存入这2个数组的0位置
 */
public class OfferGetTest40
{
    //方法1，异或
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int length = array.length;
        //如果只有2个数，不需要比较直接返回
        if(length == 2)
        {
            num1[0] = array[0];
            num2[0] = array[1];
            return;
        }

        int res = 0;//用于存储2个不同的数（假设为A、B的异或结果）
        for (int i = 0; i < length ; i++)
        {
            res ^= array[i];
        }

        //找到A、B异或结果中第一个为1的位
        int bitRes = 0;//用于存储res的第几位为1，位数从0开始计算
        for (int i = 0; i < 32 ; i++)//因为传入的是int类型的数，最多只有32位，那么我们最多只需遍历32次就可以找到相应的位
        {
            if((res&1) == 0)//如果没找到1的位，将res继续右移一位
            {
                res >>= 1;
            }
            else
            {
                bitRes = i;//找到第一个1的位，就将这个为数赋予bitRes
                break;//找到后跳出循环
            }

        }

        //将数组按 bitRes是否为1分为2组，并且进行异或运算
        for (int i = 0; i < length ; i++)
        {
            if(((array[i] >> bitRes) & 1) == 1)//将数右移bitRes位，判断这个位是否为1
                num1[0] ^= array[i];//由于数组num1与num2初始化进来0位置必须赋值为0，我们用他们的0位置数来进行异或，最后结果也存储在0位
            else
                num2[0] ^= array[i];
        }
        //运行时间：21ms，占用内存：9624k
    }

    //方法2，HashMap
    public void FindNumsAppearOnce1(int [] array,int num1[] , int num2[]) 
    {
        if(array==null || array.length==0){
            return ;
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        /*
        将数字的值存储为Map的键，键对应的值为数字在数组中出现的次数。
        为什么不把数字出现的次数作为键？因为键必须不同，而值可以相同，数字只出现1或者2次，没办法保存那么多数字。
        因此，将数字设置为键，将1/2设置为值
         */
        for (int i = 0; i < array.length; i++)
        {
            if(hashMap.containsKey(array[i]))
                hashMap.put(array[i] , hashMap.get(array[i])+1);
            else
                hashMap.put(array[i] , 1);
        }

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < array.length ; i++)
        {
            if(hashMap.get(array[i]) == 1)
                arrayList.add(array[i]);
        }
        //注意，找的是只出现1次的数组，不是出现2次
        num1[0] = arrayList.get(0);
        num2[0] = arrayList.get(1);
    }
}

