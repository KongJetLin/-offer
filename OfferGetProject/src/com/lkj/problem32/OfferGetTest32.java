package com.lkj.problem32;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OfferGetTest32
{
    public String PrintMinNumber(int [] numbers)
    {
        if(numbers==null)
            return null;
        //这里必须分为2段，当numbers=null的时候，返回null，当numbers长度为0，即有一个""元素的时候，返回""
        if(numbers.length==0)
            return "";

        //首先，将 numbers 数组内的数字转换成为字符串放入字符串数组
        String[] strs = new String[numbers.length];
        for (int i = 0; i < numbers.length ; i++)
        {
            strs[i] = numbers[i]+"";
        }

        /*
        其次，我们堆字符串数组内的字符串按 字符串表示的数字大小 进行排序。
        我们可以使用 Arrays.sort(array, comp) 方法进行排序，这个方法需要传入一个 Comparator对象，来指定字符串数组排序的规则。
        我们可以使用 匿名内部类 或者 Lambda表达式 的方式实现，当然，也可以创建一个实现 Comparator 接口的类，不管这样太麻烦。
        或者我们可以参加一个方法来比较2个字符串，然后进行排序，其实思想都是一样的！
         */
        Arrays.sort(strs, new Comparator<String>() {
            /*
            说明：我们这里想按照字符串代表数字的大小进行排序，那么compare就是提供排序规则的方法。
            应该比较的是 S1+S2 和 S2+S1 的大小，如果S1+S2 < S2+S1，那么应该把 S1 排在前面，否则应该把 S2 排在前面。
            由于字符串实现 Comparable接口，比较机制就是按照字符串代表数字的大小比较，我们可以利用这个比较机制来比较 s1+s2 与 s2+s1 的大小。

            1） (s1+s2).compareTo(s2+s1)>0，说明 s1+s2 代表的数字大于 s2+s1 代表的数字 ，即s1代表的数字大于s2代表的数字，则字符串数组中排序的时候，
            s1应该排在s2后面，因为Array.sort()方法是从小到大排序，这样最后字符串数组的字符串也是按字符串代表的数字大小，从小到大排序，符合要求。
            此时compare()方法返回正数，刚刚好代表s1 > s2 ，s1在字符串数组中排在s2后面。

            2）(s1+s2).compareTo(s2+s1)<0，说明 s1+s2 代表的数字小于 s2+s1 代表的数字 ，即s1代表的数字小于s2代表的数字，则字符串数组中排序的时候，
            s1应该排在s2前面，因为Array.sort()方法是从小到大排序，这样最后字符串数组的字符串也是按字符串代表的数字大小，从小到大排序，符合要求。
            此时compare()方法返回负数，刚刚好代表s1 < s2 ，s1在字符串数组中排在s2前面。

            3）相等的时候按Arrays.sort内部方法排序，无所谓。

            说明：如果想找字符串排成的最大的数字，return (s2+s1).compareTo(s1+s2); 即可
             */
            @Override
            public int compare(String s1, String s2) {
                return (s1+s2).compareTo(s2+s1);
            }
        });

        /*
        当然，以上过程在JDK8后可以使用Lambda解决
        Arrays.sort(strs , (s1,s2) -> (s1+s2).compareTo(s2+s1) );
        不省略的格式是
        Arrays.sort(strs , (String s1, String s2) -> {return (s1+s2).compareTo(s2+s1);} );
         */


        //此时得到的字符串数组已经排序完成，将其中的字符串重新构建成为一个新的字符串即可
        String ret = "";
        for (String str : strs) {
            ret += str;
        }

        return ret;
    }
}
