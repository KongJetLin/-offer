package com.lkj.problem35;

/** 利用归并排序实现
我们这里采用计算右区间的数的逆序对的方式，对于一次归并，只需计算左右区间中一个区间的逆序对即可（整段程序必须选择相同的区间），
 如果同时计算左右两个区间的逆序对，会造成重复计算。

 时间复杂度是：O(logn)
 */
public class OfferGetTest35
{
    public int InversePairs(int [] array)
    {
        //先将0个逆序对的方式排出
        if(array == null || array.length < 2)
            return 0;

        //在这里创建一个临时数组，传递到 getCrossPairs 方法中，避免每一次都要创建新的数组占用过多空间
        //这个数组用于临时保存排序后的元素
        int[] temp = new int[array.length];

        int totalCrossPairs = mergeSort(array, 0, array.length - 1 , temp);
        return totalCrossPairs;
    }

    //归并排序，并计算区间left到right中逆序对的数量
    private int mergeSort(int[] array , int left , int right , int[] temp)
    {
        //当 left>right 越界，或者left=right只有一个元素的时候，没有逆序对，直接返回0
        if(left>=right)
            return 0;
        // if(left<right)

        int mid = left + (right - left) / 2;//取的分割区间的元素；

        int leftCrossPairs = mergeSort(array , left , mid , temp);//计算左区间逆序对，并将左区间排序
        int rightCrossPairs = mergeSort(array , mid+1 , right , temp);

         /**
         计算当前左右两个区间的逆序对，当然，因为左右两个区间已经排序，我们可以判断整体区间 left-right 是否排序完毕，
          如果 left-right 排序完毕，则不需要计算 mid+1-right 区间元素对 left-mid 区间元素的逆序对。
          */
         if(array[mid+1]>=array[mid])
             return leftCrossPairs+rightCrossPairs;

         //计算 mid+1-right 区间元素对 left-mid 区间元素的逆序对
        int curCrossPairs = getCrossPairs(array, left, mid, right , temp);

        return leftCrossPairs+rightCrossPairs+curCrossPairs;
    }

    //合并区间 left-mid 与 mid+1-right，使得他们合并后的 left-right 区间有序，
    // 并计算右区间 mid+1-right 区间元素 对左区间 left-mid 元素的逆序对数
    private int getCrossPairs(int[] array , int left , int mid , int right , int[] temp)
    {
        //临时数组 temp 用于保存排序后的数组元素

        //left-right 中左右区间的开始索引
        int i = left;
        int j = mid+1;
        int count = 0;//用于保存逆序对数

        //下面，将 array 数组 left到right 区间内的每一个元素，进行左右区间的比较（就是排序），放入新数组temp，并计算逆序对数
        for (int k = left; k <= right ; k++)
        {
            /**
            首先，当i移动出左区间，j移动出右区间，此时说明左区间/右区间 的数已经移动完毕，
             此时不需要再计算右区间元素对左区间元素的逆序对数，直接将相应元素放入temp即可。
             注意！越界需要先判断，否则逆序对计算数错误！
             */
            if(i > mid)
            {
                //左区间越界，直接将右区间元素放入temp，并将右区间索引+1
                //注意两个区间不会同时越界，因为k控制着总区间的大小！
                temp[k] = array[j];
                j++;
            }
            else if(j > right)
            {//右区间越界同样
                temp[k] = array[i];
                i++;
            }
            else if(array[i] <= array[j])
            {
                /**
                如果左区间元素array[i]小于等于（注意必须要等于，这样后面才会是大于的情况，大于才能计算逆序对）右区间元素array[j]，
                 那么此时无法计算j位置元素的逆序对，将左区间元素array[i]放入temp，同时将i加一，移动到下一位判断
                 */
                temp[k] = array[i];
                i++;
            }
            else //array[i] > array[j]
            {
                //最后剩下的情况必须计算array[j]对左区间元素的逆序对
                count += mid-i+1;//加上这个元素的逆序对数
                temp[k] = array[j];
                j++;
            }
        }

        //最后，需要加临时数组temp的排序后的元素，重新赋予array
        for (int k = left; k <= right ; k++)
        {
            array[k] = temp[k];
        }

        return count;//返回逆序对数
    }
}
