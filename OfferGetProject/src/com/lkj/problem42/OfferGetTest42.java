package com.lkj.problem42;

import java.util.ArrayList;

public class OfferGetTest42
{
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();

        int i = 0;
        int j = array.length-1;

        //i与j不可以相等，当遍历到i与j相等，数字内所有可能的数字组合全部查找完，说明没有满足条件的组合
        //当 i<j 的时候，持续遍历
        while(i < j)
        {
            int cur = array[i]+array[j];
            if(cur == sum)
            {
                arrayList.add(array[i]);
                arrayList.add(array[j]);
                //注意！！！如果找到，我们必须将数添加到ArrayList，返回ArrayList，否则会一直循环遍历卡在这个地方，产生死循环！
                return arrayList;
            }
            else if(cur > sum)
            {
                j--;//将大数左移变小
            }
            else
            {
                i++;//将小数右移变大
            }
        }

        return arrayList;//没有找到就return null
    }
}
