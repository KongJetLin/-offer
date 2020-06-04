package com.lkj;

public class Main {

    public static boolean match(char[] str, char[] pattern){
        /*
        思路：比较前两个字符，递归比较
        */
        if(str==null || pattern==null)
            return false;
        return match(str,0,pattern,0);
    }
    public static boolean match(char[] str,int i,char[] pattern,int j){
        if(i==str.length && j==pattern.length)//都为空
            return true;
        if(i<str.length && j==pattern.length)//模式串为空
            return false;
        //以下j一定是<len
        if(j+1<pattern.length && pattern[j+1]=='*'){ //第二个字符是*
            if((i<str.length && str[i]==pattern[j]) ||(i<str.length && pattern[j]=='.') ) //第一个字符相等，有三种情况
                return match(str,i,pattern,j+2) || match(str,i+1,pattern,j+2) || match(str,i+1,pattern,j);
                //分别代表匹配0个，1个和多个
            else //第一个字符不等
                return match(str,i,pattern,j+2);
        }else{     //第二个字符不是*
            if((i<str.length && str[i]==pattern[j]) || ( pattern[j]=='.'&& i< str.length))
                return match(str,i+1,pattern,j+1);
            else
                return false;
        }

    }

    public static void main(String[] args) {
        char[] str = {'a','b'};
        char[] pattern = {'.','*'};
        System.out.println(match(str,pattern));//true

        String regex = ".*";
        String string = "ab";
        boolean matches = string.matches(regex);
        System.out.println(matches);
    }
}
