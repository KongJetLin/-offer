package com.lkj.problem52;

public class OfferGetTest52
{
    public boolean match(char[] str, char[] pattern)
    {
        if(str==null || pattern==null)
            return false;
        //我们从2个数组的0位置开始匹配
        return match(str , 0 , pattern ,0);
    }

    private boolean match(char[] str, int indexStr , char[] pattern , int indexPat)
    {
        //当indexStr=str.length与indexPat=pattern.length，说明2个数组同时到达末尾，匹配成功
        if(indexStr==str.length && indexPat==pattern.length)
            return true;
        //当匹配字符串str还没有匹配完，但是模式字符串结束了，一定匹配失败
        if(indexStr<str.length && indexPat==pattern.length)
            return false;
        /**
        当匹配字符串匹配完，但是模式字符串还没有匹配完，此时可能匹配成功，也看匹配失败。
         如果模式字符串后面是“*”或者“字符*”，“*”可以表示匹配0个，那么就会成功；
         如果模式字符串后面是其他字符串，就会失败。
         我们这里不需要进行判断，直接进行下面的判断，下面就会根据模式字符串是不是“*”或者“字符*”进行判断，从而返回相应的结果。
         */

        /**
        注意，下面不需要再判断 indexPat<pattern.length，此处 indexPat必然小于pattern.length，
         因为当 indexPat 累加到等于 pattern.length 的时候，就会直接进行判断。
         */

        //如果模式字符串下一个字符存在且为“*”，那么当前模式字符串为 "indexPat*"
        if(indexPat+1<pattern.length && pattern[indexPat+1] == '*')
        {
            /**
             如果当前的匹配字符串与模式字符串项匹配，有可以分为3种情况
             “*”表示匹配0个，1个或者多个，只要这几个其中有一个满足即可！
             */
            if((indexStr<str.length && str[indexStr]==pattern[indexPat]) || (indexStr<str.length && pattern[indexPat]=='.'))
            {
                //下面这片注释和代码我理解错误
//                boolean zero = match(str , indexStr , pattern , indexPat+2);//匹配0个的结果
//                boolean one = match(str , indexStr+1 , pattern , indexPat+2);//匹配1个的结果
                /**
                如果“*”要表示匹配多个，那么匹配串的这一个indexStr的字符必须与indexStr+1的字符相同，否则可能出错。如下：

                 比如我们匹配 “ab”(匹配串str)与“.*”(模式串pattern).我们知道最终结果是匹配失败。
                 第一次比较：indexPat(0)+1=1<2，此时“a”与“.”相匹配，如果选择模式串的“*”匹配多个，此时 indexStr+1=1，而indexPat=0不变继续匹配。
                 第二层比较：indexPat(0)+1=1<2，此时“b”与“.”相匹配，如果选择模式串的“*”匹配一个，indexStr+1=2，indexPat+2=2，结束
                 那么最后就会返回true，与实际结果相悖。
                 */
//                boolean many = false;
//                //当然，模式串的“*”匹配多个匹配串字符，前提是匹配串的下一个字符存在
//                if(indexStr+1<str.length && str[indexStr]==str[indexStr+1])
//                {
//                    many = match(str , indexStr+1 , pattern , indexPat);
//                }
//
//                return zero || one || many;

                /**
                此处牛客网测试用例："ab",".*" 返回true，即“.*”不是表示任意字符出现一次或者多次，如“aaaa”；而是表示出现0个或者多个任意字符，如“abc”。（上面代码我理解错误！）
                 如果是“ab”,"a*"，那么"*"无法表示匹配多个“a”，因为匹配串第二个字符是“b”

                 那么上面的代码就可以简化。
                 即这里模式串“*”匹配多次的时候，匹配串的这一个indexStr的字符不一定需要与indexStr+1的字符相同。
                 */
                return match(str , indexStr , pattern , indexPat+2)//0个
                        || match(str , indexStr+1 , pattern , indexPat+2)//1个
                        || match(str , indexStr+1 , pattern , indexPat);//多个
            }
            else
            {
                /**
                 如果当前的匹配字符串与模式字符串项不匹配，那么此时 模式字符串"indexPat*"的“*”只能表示为 indexPat 出现0次。
                 那么将模式字符串后移2位，匹配字符串不动，继续递归比较，注意返回递归的结果
                 */
                return match(str , indexStr , pattern , indexPat+2);
            }

        }
        else
        {
            /**
            如果模式字符串下一个字符存在且不为“*”，或者模式字符串的下一个字符不存在，
             那么此时下一个字符串肯定不是“*”，对当前模式字符的判断不会造成影响，
             那么我们可以直接判断当前匹配字符与模式字符。（此时当前匹配字符必然存在，只需要判断当前模式字符是否存在即可）
             */
            //我们可以直接判断这一个匹配字符串与模式字符串是否匹配
            if((indexStr<str.length && str[indexStr]==pattern[indexPat]) || (indexStr<str.length && pattern[indexPat]=='.'))
            {
                //当前的匹配字符串与模式字符串项匹配，则递归匹配他们的下一个字符串，注意返回递归的结果
                return match(str , indexStr+1 , pattern , indexPat+1);
            }
            else
                return false;//匹配失败直接返回false
        }
    }
}
