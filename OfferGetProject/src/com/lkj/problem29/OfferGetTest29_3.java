package com.lkj.problem29;

import java.util.ArrayList;

/** 计数排序，此时时间复杂度为O(n+k)，k为数组的长度

 */
public class OfferGetTest29_3
{
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k)
    {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || input.length == 0 || k > input.length || k <= 0) return res;

        //1、先找出input最大值最小值
        int min = input[0];
        int max = input[0];

        for (int i = 1; i < input.length ; i++)
        {
            if(input[i] < min)
                min = input[i];
            if(input[i] > max)
                max = input[i];
        }

        //2、随后，求出临时数组的大小，并构建临时数组
        int length = max-min+1;
        int[] temp = new int[length];

        //3、遍历input数组，将input数组元素作为temp数组下标，个数为特莫数组下标元素对应的值
        for (int i = 0; i < input.length ; i++)
        {
            //注意，藤牌数组区间从 0到max-min，而input数组元素从 min到max，因此应该减去min
            temp[input[i]-min]++;
        }

        int index = 0;
        //将temp元素取出放入input
        for (int i = 0; i < temp.length ; i++)
        {
            while (temp[i]>0)
            {
                input[index++] = i + min;//注意将min加回去
                temp[i] --;
            }
        }

        for (int i = 0; i < k ; i++)
        {
            res.add(input[i]);
        }

        return res;
    }

    public static void main(String[] args)
    {
        int[] arr = {4,5,1,6,2,7,3,8};
        ArrayList<Integer> arrayList = GetLeastNumbers_Solution(arr, 4);
        System.out.println(arrayList.size());
    }
}
