package com.lkj.Problem27;

import java.util.ArrayList;
import java.util.Collections;

public class OfferGetTest27
{
    //将返回结果的ArrayList定义在外面，避免下面的函数传递太多的参数
    ArrayList<String> result = new ArrayList<>();

    public ArrayList<String> Permutation(String str)
    {
        if(str==null || str.length()==0)
            return result;
        //注意，这里返回result这个ArrayList，不要返回null。因为当 str=""的时候，返回ArrayList，里面也是""，而不是null！
        Permutation(str.toCharArray() , 0);//index从0开始
        Collections.sort(result);//最后，将ArrayList的字符串排列一下，因为测试用例的字符串是有顺序的，不排列最后无法通过！

        return result;
    }

    //从strArr字符串数组的index位置开始置换字符
    private void Permutation(char[] strArr , int index)
    {
        /**
        当 index=strArr.length，说明递归到字符数组的结尾，需要将这个排列的字符数组转换为字符串，
         当然，由于题目给定的字符串里面可能有重复的字符，我们这里获取字符有可能与前面获取到的字符重复，
         因此在放入 result 之前需要先判断 result 里面是否存在这个字符串。
         */
        if(index == strArr.length-1)//遍历到最后一个字符，不需要再替换后面的字符（也不需要替换自己）
        {
            String str = String.valueOf(strArr);//将这个顺序的字符数组变为字符串
            if(!result.contains(str))
                result.add(str);//字符串不存在才将其加入result
        }
        else
        {
            /**
            如果没有递归到字符数组的末尾，我们将index位置的字符逐个与后面的字符替换，
             然后将当前index位置的字符固定，通过 Permutation() 方法，递归寻找index+1位置开始的所有字符的可能排列，
             直到递归到字符数组的末尾。
             注意：
             1）这里需要从index位置开始替换，即index位置的字符保持不变，因为这也是一种情况；
             2）当index+1位置递归返回后，我们需要将原来的替换复原，如果不一层一层递归在替换后都复原，
                递归后的字符数组就是乱序的，从而影响其他分支的替换。（这个过程参考解析的图）
             */
            for (int i = index; i <= strArr.length-1 ; i++)
            {
                //index位置的字符在循环中逐个替换 i=index,index+1,...,strArr.length-1位置的字符
                swap(strArr , index , i);
                //替换一个字符后，将当前index位置的字符固定，从index+1位置开始递归替换
                Permutation(strArr , index+1);
                /**
                为了不影响其他分支，我们在递归搜索完 index+1 开始位置所有可能的字符排列情况后，将替换还原，
                 而递归中也会将替换还原，那么到这里，就变成没有替换 index与i 位置字符之前的情况，
                 那么我们下面就可以将 index与i+1 进行替换，这样就可以找到index位置所有可能的字符（想象一下这个过程！）
                 */
                swap(strArr , index , i);
                //另外，这里不需要考虑重复，因为递归到最后，重复的字符串会被剔除
            }
        }
    }
    //替换方法
    private void swap(char[] arr , int p1 , int p2)
    {
        char temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;
    }

    public static void main(String[] args)
    {
        ArrayList<String> ab = new OfferGetTest27().Permutation("ab");
        for (String s : ab)
        {
            System.out.println(s);
        }
    }
}
