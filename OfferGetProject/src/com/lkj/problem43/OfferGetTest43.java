package com.lkj.problem43;

public class OfferGetTest43
{
    public String LeftRotateString(String str,int n)
    {
        //下面这个时候才是真正的不需要翻转。
        //注意，这里的判断应该放在 n>=str.length() 之前，都在下面的取余预算出现 java.lang.ArithmeticException: / by zero 异常
        if(str == null || str.length()==0 || n<=0)
            return str;

        /**
         * 很多答案忽略了一点，他们都是当 n >= str.length() 的时候，不翻转直接返回str，其实这样是有问题的，因为这个操作是循环左移，
         * 当 n >= str.length() 的时候，应该用 n除以字符串的长度，求得的余数就是真正的要左移的位数
         */
        if(n>=str.length())
        {
            //n对数组长度取余
            n = n%str.length();
        }


        char[] arr = str.toCharArray();

        //转换0到n-1位置的字符
        reverse(arr , 0 , n-1);
        //转换n到arr.length-1位置的字符
        reverse(arr , n , arr.length-1);
        //转换0到arr.length-1位置的字符
        reverse(arr , 0 , arr.length-1);

        //得到转换后的数组
        return new String(arr);
    }

    //翻转字符数组 start到end 位置的字符
    private void reverse(char arr[] , int start , int end)
    {
        //当start<end的时候，持续交换，直到start=end
        for (; start < end ; start++,end--)
        {
            swap(arr , start , end);
        }
    }
    //交换字符数组 arr的n位置与m位置的租房
    private void swap(char arr[] , int n , int m)
    {
        char temp = arr[n];
        arr[n] = arr[m];
        arr[m] = temp;
    }
}
