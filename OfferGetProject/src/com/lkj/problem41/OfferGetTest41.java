package com.lkj.problem41;

import java.util.ArrayList;

public class OfferGetTest41
{
    //1、暴力穷举法
    public ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

        //当sum<3的时候，sum=1或者sum=2，正数序列最少包括2个数，不存在满足 和=1或2 的正数序列！
        if(sum<3)
            return ret;

        int limitNum = sum/2+1;
        /*
        我们的序列从1开始，序列中数的最大值必然小于 sum/2+1。
        那么我们用 i 来表示序列的数，当i<sum/2+1的时候，我们可以持续寻找序列
         */
        for (int i = 1; i < limitNum ; i++)
        {
            //再次回到这里，又创建一个新的ArrayList，序列和重新赋值为0，起始值加1，重新开始查找起始值不同的新的序列
            ArrayList<Integer> arrayList = new ArrayList<>();
            int curSum = 0;//首先，开始的时候序列和为0
            int curNum= i;//开始的时候，序列的初始值为i，i从1开始（对于所有满足条件的序列，每个i只能作为其中唯一一个序列的起始值）
            while(curSum < sum)
            {
                curSum += curNum;
                arrayList.add(curNum);
                curNum++;

                /*
                如果以i为起始值的序列，有一个序列满足 序列和=sum，将这个序列 arrayList添加到ret
                如果找到 curSum>sum，即从curSum<sum的时候，再加一个curNum，此时curSum>sum，说明以i为起始值的序列没有满足的，
                那么我们跳出内层循环，继续将i+1，遍历以i+1为起始值的序列。
                 */
                if(curSum == sum)//当找到，curSum=sum，下一次自然会跳出循环，不需要我们手动跳出
                    ret.add(arrayList);
            }
        }
        return ret;
    }

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum)
    {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

        if(sum<3)
            return ret;

        int start = 1;
        int end = 2;
        while (start<end)//start不能等于end，因为序列要求最少2个数字
        {
            ArrayList<Integer> arrayList = new ArrayList<>();
            int tempSum = (start+end)*(end-start+1)/2;//求 start->end 的序列和

            if(tempSum == sum)
            {
                //将序列的数字添加到arrayList
                for (int i = start; i <=end ; i++)
                {
                    arrayList.add(i);
                }
                ret.add(arrayList);//记得将找到的序列添加到ret
                //此时我们可以将start或者end右移，来寻找下一个满足条件的序列
                // 当前的start或者end不可能为下一个满足条件序列的其实或者终点，因此必须改变这两个指针的值（改变一个另外的也会跟着改变）
                start++;
            }
            else if(tempSum > sum)
            {
                start++;//右移start以减少序列和
            }
            else //tempSum<sum
            {
                end++;//右移end以增大序列和
            }
        }
        return ret;
    }
}
