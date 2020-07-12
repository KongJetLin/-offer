package com.lkj.problem29;

import java.util.ArrayList;

/** 使用快速排序，时间复杂度同样为 nlogn

 */
public class OfferGetTest29_2
{
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k)
    {
        ArrayList<Integer> res = new ArrayList<>();
        if(input == null || input.length==0 || k>input.length || k<=0)
            return res;
        quickSort(input , 0 ,input.length-1);

        for (int i = 0; i < k ; i++)
        {
            res.add(input[i]);
        }

        return res;
    }

    private void quickSort(int[] arr , int left , int right)
    {
        //当 left>= right 的时候，结束递归
        if(left >= right)
            return;
        if(left < right)
        {
            //找去区间 left到right的中轴元素下标mid，并将小于中轴元素的元素放在mid左边，大于他的放在mid右边
            int mid = partition(arr , left , right);
            quickSort(arr , left , mid-1);
            quickSort(arr , mid+1 , right);
        }
    }

    private int partition(int[] arr , int left , int right)
    {
        int pivot = arr[left];//随机取最左边的元素为中轴元素
        int start = left+1;
        int end = right;

        while (true)
        {
            //找到左区间第一个大于 pivot 的元素
            while (start<=end && arr[start]<=pivot)
                start++;
            //找到右区间第一个小于于 pivot 的元素
            while (start<=end && arr[end]>pivot)
                end--;

            //若start>end ，说明中轴元素左右两边元素摆放完毕，结束循环
            if(start>end)
                break;
            //如果没有跳出循环，则交换start与end的元素
            swap(arr , start ,end);
        }

        //跳出循环后，此时end为中轴元素应该放的位置，将left与end互换元素，并放回中轴元素下标：end
        swap(arr , left ,end);
        return end;
    }

    private void swap(int[] arr , int i , int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
