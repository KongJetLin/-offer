package com.lkj.problem34;

import java.util.BitSet;

public class OfferGetTest34
{
    public int FirstNotRepeatingChar(String str) {
        //ASCII有256个字符，创建一个长度为256的数组，用字符在ASCII中的位置表示数组中的位置，该位置存储字符在字符串中出现的次数
        int[] arr = new int[256];

        for (int i = 0; i < str.length() ; i++)
        {
            arr[str.charAt(i)]++;//将该位置的字符出现次数+1
        }
        for (int i = 0; i < str.length() ; i++)
        {
            //从头到尾遍历字符串字符，寻找出现次数为1的字符
            if(arr[str.charAt(i)] == 1)
                return i;
        }
        return -1;
    }

    public static int FirstNotRepeatingChar1(String str)
    {
        //创建2个长度为256的BitSet，一个BitSet值只有 true与false2个位，相比之前的int，int一个值有32位。节省空间
        BitSet bitSet1 = new BitSet(256);
        BitSet bitSet2 = new BitSet(256);

        char[] chars = str.toCharArray();
        for (char ch : chars)
        {
            //如果 bitSet1 与 bitSet2 的ch为都为 false，那么说明之前ch没有出现，将 bitSet1 的ch为设置为true
            if(!bitSet1.get(ch) && !bitSet2.get(ch))
                bitSet1.set(ch);// 0 0 -> 0 1
            //bitSet1 ch位为true，但是 bitSet2 ch位为false，说明ch之前出现一次，将 bitSet2 ch位设置为true
            /**
             * 注意，此处必须加 else if，如果不加else，进来如果 ch 之前没有出现，bitSet1的 ch 位置设置为true，
             * 此时又会判断第二个if，bitSet1的ch位置为true（赋值过）， bitSet2 的ch位置为false，就会给bitSet2 的ch位置赋值。
             *
             * 如果加了else，那么bitSet1 ch位置设置为true后，由于 是else if，第一个语句满足，不会再判断下面else if的第二个语句，
             * 这样就不会出现重复添加的情况！
             */
            else if(bitSet1.get(ch) && !bitSet2.get(ch))
                bitSet2.set(ch);// 0 1 -> 1 1
        }

        for (int i = 0; i < chars.length  ; i++)
        {
            //注意，是i位置字符ch在 BitSet中的ch位置，
            char ch = chars[i];
            //如果 ch 位置的 bitSet1 是true，bitSet2是false，那么说明 ch 位置的字符出现一次
            if(bitSet1.get(ch) && !bitSet2.get(ch))// 0 1
                return i;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        String str = "google";
        int i = FirstNotRepeatingChar1(str);
        System.out.println(i);
    }
}
