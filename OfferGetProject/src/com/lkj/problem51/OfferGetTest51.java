package com.lkj.problem51;

public class OfferGetTest51
{
    public static int[] multiply(int[] A)
    {
        if(A == null || A.length<2)
            return null;//要构成乘积数组，A最少得有2个元素

        int[] res = new int[A.length];//乘积数组的长度与A数组长度相同

        //定义一个变量存储 i 左边数的乘积。对于 res[i]，left = res[0]*res[1]*...*res[i-1]
        // 对于乘积数组第一个元素 res[0] = res[1]*res[2]*...*res[A.length-1]，无左边乘积，因此left初始值设置为1
        int left = 1;
        for (int i = 0; i < A.length ; i++)
        {
            res[i] = left;// left = 1*res[0]*res[1]*...*res[i-1]
            //当 i=A.length-1，left不需要在乘以 A[A.length-1]，因为此时left若乘以A[A.length-1]，left没有对应的乘积数组的数
            if(i != A.length-1)
                left *= A[i];//更新left
        }

        // 同样，义一个变量存储右边数的乘积。对于 res[i]，right = res[i+1]*res[i+2]*...*res[A.length-1]
        // 对于乘积数组最后一个元素 res[A.length-1] = res[0]*res[2]*...*res[A.length-2]，无右边乘积，因此right初始值设置为1
        int right = 1;
        for (int i = A.length-1; i >= 0 ; i--)
        {
            res[i] *= right;//将 res[i] 左边的乘积乘以右边的乘积
            if(i != 0)
                right *= A[i];//更新right
        }

        return res;
    }

    public static void main(String[] args)
    {
        int[] arr = {1,2,3,4,5};
        int[] multiply = multiply(arr);
        for (int i : multiply)
        {
            System.out.println(i);
        }
    }
}
