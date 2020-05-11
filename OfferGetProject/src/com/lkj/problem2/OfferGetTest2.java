package com.lkj.problem2;

public class OfferGetTest2
{
    public static String replaceSpace(StringBuffer str)
    {
        //首先寻找第一个" "的下标，如果没有找到，会返回-1
        int index = str.indexOf(" ");
        while(index != -1)
        {
            //如果找到空格，进行替换。
            // 根据上面对StringBuffer的分析，可以将一个空格字符" "替换为3个字符长度的"%20"，后面的字符会自动后移。
            str.replace(index , index+1 , "%20");
            /*
            我看到有人是从index开始寻找空格，既  index = str.indexOf(" ",index);
            其实不需要这样做，因为前面第一个空格已经被替换，那么我们此时寻找当前第一个空格即可，
            这第一个空格就是之前的第二个空格。
             */
            index = str.indexOf(" ");//继续寻找空格

            /*
            这个方法：运行时间：22ms，占用内存：9420k
             */
        }
        return str.toString();
    }

    public static String replaceSpace2(StringBuffer str)
    {
        StringBuffer newSb = new StringBuffer();

        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if(ch == ' ')
            {
                newSb.append("%20");
            }
            else
            {
                newSb.append(ch);
            }
        }
        return newSb.toString();
        //运行时间：20ms，占用内存：9400k
    }

    public static String replaceSpace3(StringBuffer str)
    {
        int length = str.length();//获取原来的StringBuffer的长度
        int spaceNum = 0;//用于保存空格个数
        //遍历获取原来StringBuffer中空格的个数
        for (int i = 0; i < length ; i++)
        {
            if(str.charAt(i) == ' ')
                spaceNum++;
        }

        int arrlen =  spaceNum*3+(length-spaceNum);//计算新数组的长度
        char[] arr = new char[arrlen];

        //遍历原来的StringBuffer，从尾到头遍历，这样就不需要创建新的变量，可以直接利用length与arrLen。
        while(length>0)
        {
            //如果遍历到的字符不是空格，将其直接添加到数组（注意添加后数组角标先减一再赋值）
            if(str.charAt(length-1) != ' ')
            {//注意，数组下标从arrlen-1开始，因此这里arrlen必须先减一再进行赋值，否则会报错
                arr[--arrlen] = str.charAt(length-1);
            }
            else
            {
                //如果是空格，向数组添加"%20"3个字符（注意添加后数组角标应该减一再赋值）
                //注意，由于是从后向前赋值，应该先复制0，再是2，再是%
                arr[--arrlen] = '0';
                arr[--arrlen] = '2';
                arr[--arrlen] = '%';
            }
            length--;//遍历一次记得将length减1，以便取到下一个字符
        }
        return new String(arr);//这里不能返回toSting，否则打印数组地址，而应该返回利用数组构造一个新的字符串

        //运行时间：20ms， 占用内存：9528k
    }

    public static String replaceSpace4(StringBuffer str) {
        /*使用String的replaceAll方法
        replaceAll(String regex, String replacement)
          使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。
         */
        return str.toString().replaceAll(" ","%20");
        //运行时间：23ms，占用内存：9368k
    }

    public static void main(String[] args)
    {
        StringBuffer sb = new StringBuffer("we are world");
        String str = replaceSpace4(sb);
        System.out.println(str);
    }
}
