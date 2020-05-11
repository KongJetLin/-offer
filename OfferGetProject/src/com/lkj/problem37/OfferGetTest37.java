package com.lkj.problem37;

/**
 * 题目描述：统计一个数字在排序数组中出现的次数。
 */
public class OfferGetTest37
{
    public static int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0)
            return 0;

        //使用循环二分查找，找到k在数组中第一次出现的位置——从0到array.length-1 开始查找
        int firstPlace = getFirstPlace(array , k , 0 , array.length-1);

        //使用递归二分查找，找到k在数组中最后一次出现的位置——从0到array.length-1 开始查找
        int lastPlace = getLastPlace(array , k , 0 , array.length-1);

        if(firstPlace!=-1 && lastPlace!=-1)
            return lastPlace-firstPlace+1;//这个就是k在数组中出现的次数
        return 0;//如果firstPlace、lastPlace某一个为-1，说明查找失败，返回0
        // 查找失败的原因，要么是上面的数组不对，要么是k在数组中不存在，找不到它出现的第一位和最后一位
    }

    //k在数组中第一次出现的位置——循环
    public static int getFirstPlace(int[] array , int k , int begin , int end)
    {
        if(begin > end)
            return -1;//说明数组长度为0，直接返回-1，查找失败（其实前面判断过，这里可以省略）

        int middle = 0;

        while (begin<=end)//begin=end 的时候，仍然需要进行查找，此时 mid=(begin+end)/2
        {
            middle = (begin+end)/2;
            if(array[middle] > k)
            {
                end = middle-1;
            }
            else if(array[middle] < k)
            {
                begin = middle+1;
            }
            /*
            此处，排除 array[middle] > k 与 array[middle] < k，就剩下array[middle] = k，此时需要判断middle-1位置是否为k，
            但是，在这之前，循环有可能遍历到数组首位，middle-1可能不存在，因此需要讨论middle-1是否存在。

            middle-1不存在，此时array[middle] = k，那么middle就是k第一次出现的位置，也是数组首位；
            middle-1存在，如果 array[middle-1] != k，那么middle就是k第一次出现的位置；
            middle-1存在，如果 array[middle-1] = k，说明middle不是k第一次出现的位置，需要继续向左二分查询，end=middle-1；

            那么我们只要排除 middle-1 存在且 array[middle] = k ，此时继续二分查询；剩下的情况直接返回middle即可！
             */
            else if(middle-1>=0 && array[middle-1] == k)
            {
                end = middle-1;
            }
            else
            {
                return middle;
            }
        }
        //当begin>end的时候，就会跳出循环
        return -1;//如果循环完没有找到，说明数组中不存在k这个数，返回-1表示 数组不存在k
    }

    //k在数组中最后一次出现的位置——递归，递归方法涵义：查找数组array在 begin到end范围内 k在数组中最后一次出现的位置
    public static int getLastPlace(int[] array , int k , int begin , int end)
    {
        //1、解决规模最小问题——递归到最后 begin>end 的时候，还没有返回middle，说明查找的k在数组中不存在，返回-1（这里不能省略）
        if(begin>end)
            return -1;//规模最小问题（数组无法查找到k的情况）

        int middle = (begin+end)/2;
        //2、解决规模较小的问题——注意递归方法的涵义。
        // 这里我们通过缩小数组内部的查找范围来缩小问题规模，而数组本身大小不变。
        if(array[middle] > k)
        {
            //当 array[middle] > k 的时候，我们调用 getLastPlace 查找数组array在 begin到middle-1范围内 k在数组中最后一次出现的位置（这个是规模较小的问题）
            //3、将较小问题的解整合成为较大问题的解：当较小问题查找到后，直接返回查找结果，这个查找结果也是较大问题的查找结果。
            return getLastPlace(array , k , begin , middle-1);
        }
        else if(array[middle] < k)
        {
            return getLastPlace(array , k , middle+1 , end);
        }
        else if(middle+1<array.length && array[middle+1] == k)//middle+1<array.length：middle+1存在
        {
            return getLastPlace(array , k , middle+1 , end);//继续递归
        }
        else
        {
            return middle;//这个也是规模最小问题（能查找到k最后一次出现位置的情况）
        }
        //当递归到规模最小完问题，结果就会一级一级递归返回，直到返回最大问题的结果。
    }

    public static void main(String[] args)
    {
        int arr[] = null;
        System.out.println(GetNumberOfK(arr , 7));
    }
}
