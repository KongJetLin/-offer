package com.lkj.problem44;

public class OfferGetTest44
{
    public String ReverseSentence(String str)
    {
        if(str == null || str.length() == 0)
            return str;

        int length = str.length();
        char[] chars = str.toCharArray();

        //使用2个指针来指向字符串的首尾
        int start = 0;
        int end = 0;//end指向字符串尾部的后一个元素
        /*
        当 字符串尾部 end<length-1 的时候，我们持续寻找单词.
         但是，如果如下这样寻找，对于最后一个单词，str.charAt(end) 永远不会指向空格字符，也就是说，最后一个字符还没有翻转，遍历便结束。
         因此，我们应该遍历到 length位置，判断到length位置的时候，同样将start到end-1位置的字符，此时end=length
        while(end<length)
        {
            if(str.charAt(end) == ' ')
            {
                //当end的位置是空格字符的时候，start位置到end-1位置是一个字符串，将其字符翻转
                reverse(chars , start , end-1);
                start = end+1;//此时，将start指向下一个字符的起始位置
            }
            end++;//不管当前end位置是不是空格字符，每一次循环都要将end+1
        }
        */

        while(end<=length)
        {
            //end会加到length（到达数组尾部），此时同样将start到end-1位置的字符翻转。
            //另外，str.charAt(end) == ' '，同样将start到end-1位置的字符翻转。
            //注意，end == length 必须在 str.charAt(end) == ' ' 之前，否则 str.charAt(end)数组越界
            if(end == length || str.charAt(end) == ' ')
            {
                //当end的位置是空格字符的时候，start位置到end-1位置是一个字符串，将其字符翻转
                reverse(chars , start , end-1);
                start = end+1;//此时，将start指向下一个字符的起始位置
            }
            end++;//不管当前end位置是不是空格字符，每一次循环都要将end+1
        }
        //最后，将整个数组翻转
        reverse(chars ,0 , length-1);

        return new String(chars);
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
