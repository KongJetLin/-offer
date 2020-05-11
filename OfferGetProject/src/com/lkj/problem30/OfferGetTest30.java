package com.lkj.problem30;

/**  连续子数组的最大和
题目描述：
 HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
 当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */

public class OfferGetTest30
{
    //暴力法
    public static int FindGreatestSumOfSubArray1(int[] array) {
        //maxSum不能赋值为0，有可能整个数组都是负数
        //maxSum赋值为最开始的子序列array[0]，如果没有找到比他更大的子序列，array[0]就是最大的子序列，否则就赋值为其他更大的子序列
        int maxSum = array[0];

        //外循环用于调整从哪个下标开始，内循环用于记录以这个下标为开始的所有子序列的和
        for (int i = 0; i < array.length ; i++)
        {
            //thisSUm 循环记录所有以j下标为开头的子序列的值，当找到某一个子序列 thisSum>maxSum 的时候，我们就将这个子序列的值赋予maxSum
            //由于下一个内循环开始之前，开始下标变换，因此thisSum必须重新赋值为0
            int thisSum = 0;
            for (int j = i; j < array.length ; j++)
            {
                thisSum += array[j];
                if(thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
        //运行时间：12ms,占用内存：9412k
    }

    //动态规划
    public static int FindGreatestSumOfSubArray2(int[] array) {
        //注意，maxSum目前也应该记录为array[0]，不能是0，因为可能整个数组值都小于0！
        int maxSum = array[0];//用于记录最大子序列和.
        int thisSum = array[0];//用于记录 以数组当前下标为结尾的最大子序列和，我们将其初始化赋值为dp[0]

        //从dp[1]开始查找
        for (int i = 1; i <array.length ; i++)
        {
            if(thisSum < 0)//如果dp[n-1]<0
                thisSum = array[i];//更新dp，此时dp[n]=array[n]
            else//如果dp[n-1]>0
                thisSum += array[i];//更新dp，此时dp[n]=array[n]+dp[n-1]

            //更新maxSUm值，最后才能得到数组中的最大子序列
            if(maxSum<thisSum)
                maxSum = thisSum;//当当前下标的dp[n]比 以之前下标结尾的最大子序列要大，那么就将maxSum更新为dp[n]
        }

        return maxSum;

        //运行时间：14ms,占用内存：9396k
    }

    public static void main(String[] args)
    {
        int[] arr = {6,-3,-2,7,-15,1,2,2};
        int[] arr1 = {-1,-2,-3,-4};

        long startTime = System.nanoTime();
        int maxSum = FindGreatestSumOfSubArray2(arr);
        long endTime = System.nanoTime();

        System.out.println(maxSum);
        System.out.println("程序运行时间： " + (endTime - startTime) + "ns");
    }
}
