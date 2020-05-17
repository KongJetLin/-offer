package com.lkj.problem49;

public class OfferGetTest
{
    public static int StrToInt(String str)
    {
        //1、判断字符串是否合法
        if(str == null || str.length() == 0)
            return 0;

        //2、设置一个变量用于判断符号，默认是正数，当字符串的第一个符号是“-”的时候，才将 isNegative 设置为 true
        boolean isNegative = false;
        //这里判断第一位是不是“-”，直接设置值的正负号。这样在循环里面就不需要根据第一位是不是符号来设置值得正负，如果第一位是符号，直接略过即可
        if(str.charAt(0) == '-')
            isNegative = true;

        //记录结果
        long ret = 0;//ret用long表示，防止溢出
        for (int i = 0; i < str.length() ; i++)
        {
            char c = str.charAt(i);
            //首先判断第一位是不是正负号，前面已经设置过值得符号，这里如果第一位是正负号，直接略过即可
            if(i == 0 && (c == '+' || c == '-'))
                continue;
            if(c < '0' || c > '9')
                return 0;//出现非数字字符（除第一位是正负号情况外），直接返回0（非数字字符串）

            //数字字符，则求出当前数字字符的值：c-'0'，将前面的ret*10加上这里的值即可
            /**
            如对于 123，第一次 ret = 0*10 + 1 = 1；第二次 ret = 1*10 + 2 = 12；第三次 ret = 12*10+3 = 123，刚刚好！
             */
            ret = ret*10 + (c-'0');

            //判断是否溢出
            if(isNegative == false && ret > Integer.MAX_VALUE)
                throw new RuntimeException("上溢出");
            if(isNegative == true && -ret < Integer.MIN_VALUE ) //这里注意，ret是正数，我们判断其是否下溢出，将其转换为负数与 Integer.MIN_VALUE 相比！
                throw new RuntimeException("下溢出");
        }

        return isNegative ? (int)-ret : (int)ret;
    }

    public static void main(String[] args)
    {
        System.out.println(StrToInt("-2147483648"));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
