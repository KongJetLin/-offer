package com.lkj.problem10;

public class OfferGetTest10
{
    //递推法
    public int RectCover(int target)
    {
        if(target<=2)
            return target;

        int temp = 0;
        int pre1 = 2;//代表f(n-1)，初始为3-1=2
        int pre2 = 1;//代表f(n-1)，初始为3-2=1

        //从 target = 3 开始递推
        for (int i = 3; i <= target ; i++)
        {
            temp = pre1 + pre2;//这一轮的f(n)，也是下一轮的f(n-1)

            //注意，设置的顺序很重要，必须先将这一轮的f(n-1)设置为下一轮的f(n-2)；再讲这一轮的f(n)设置为下一轮的f(n-1)。否则会出现错误
            pre2 = pre1;//下一轮的 f(n-2) 等于这一轮的 f(n-1)
            pre1 = temp;//temp赋予下一轮的f(n-1)
        }
        return temp;
    }


    public int RectCover1(int target)
    {
        if(target<=2)
            return target;

        return RectCover1(target-1)+RectCover1(target-2);
    }
}
