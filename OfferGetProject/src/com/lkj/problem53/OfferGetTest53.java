package com.lkj.problem53;

public class OfferGetTest53
{
    public boolean isNumeric(char[] str)
    {
        if(str==null || str.length==0)
            return false;

        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
        /**
         为什么是两个反斜杠？我们以“\\.”的转义过程来说明
         第一步，编译器将字符串转变为“正则表达式”
            此时，将字符中的“\\”解析为“\”，整体被解析为正则表达式“\.”
         第二步，才开始把第一步的结果当做是正则表达式，开始进行匹配！
		    作为正则表达式，“\.”又被正则表达式引擎解释为“.”
		如果在字符串里只写\.的话，第一步就被直接解释为.，之后作为正则表达式被解释时就变成匹配任意字符了
         */
    }
}
