package com.lkj.problem67;

public class OfferGetTest67
{
    public int cutRope(int target)
    {
        //首先，绳子长度必须大于1，否则说明绳子长度不合法，直接返回0即可
        if(target < 2)
            return 0;

        /*
        由f(2)、f(3)知道，当绳子长度为2或者3的时候，我们不需要再剪，因为再剪下去，子段的乘积也不会大于父段的长度。
        但是，由于m>1，即我们至少剪一刀，对于绳子长度为2或者3的情况，我们还是需要剪一刀，此时f(2)=1,f(3)=2。
        对于绳子长度大于等于4的情况，我们分隔到子段长度为2或者3的时候，我们不要再分隔！

        结论：我们分为2种情况
        1）绳子长度为2、3的时候，直接返回f(x)的值；
        2）绳子长度大于等于4的时候，我们从头开始计算每一个子段的：f(i)，并存储到一个数组中，这样遍历到f(n)时，f(1)-f(n-1)的值都知道，那么就很容易求得：f(n) = Max{ f(i) *f(n-i) }

        问题：为什么数组中 2,3 位置存储的不是 1,2？
        因为 f(2)=1,f(3)=2 是绳子长度为 2、3时我们不得不分隔的情况！这种情况我们直接得出结果。
        绳子长度大于等于4时，子段长度为2、3时不分割（再分割子段的乘积也不会大于父段的长度）！而数组是用来计算绳子长度大于等于4的情况，
        那么此时用于计算的 f(1)=1,f(2)=2,f(3)=3，但是他们只是用于计算绳子长度大于等于4的f(n)，并不是真正的f(n).
        */
        if(target == 2)
            return 1;
        if(target == 3)
            return 2;

        int[] dp = new int[target+1];//数组target下标保存f(target)，那么数组长度设置为 target+1

        //设置用于计算绳子长度大于等于4的f(0-3)的值，但是这些不是真正的f(0-3)，只是用于计算
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        int temp = 0;
        //从绳子长度为4开始，计算每一个 4-n-1 每一个子段的f(x)，直到 f(n)，target就是n
        for (int i = 4; i <= target ; i++)
        {
            //j长度到 i/2 的时候不需要继续累加，下面的情况都是反过来的，比如对于i=5: 1*4,2*3（j=2）就够了，继续计算就是 3*2,4*1是重复的！
            for (int j = 1; j <=i/2 ; j++)
            {
                temp = dp[j] * dp[i-j];
                if(temp > dp[i])
                    dp[i] = temp;
            }
        }
        //出循环，得到 dp[target]
        return dp[target];
    }

    public static int cutRope1(int target)
    {
        //同样，先排除n<2，n=2，n=3 的段
        if(target < 2)
            return 0;
        if(target == 2)
            return 1;
        if(target == 3)
            return 2;

        /*
        当n>=4的时候，我们尽量将n剪为3的段，当出现子段长度为4的时候，我们不应该剪为 1+3 的段，而应该剪为 2+2 的段。
         */

        /*
        先计算出最多能剪的3的段，这里的余数可能是：0,1,2。
        余数是0：target刚刚好分为多段3的段；
        余数是1：target中出现一个4的子段，我们这里将4分为 1+3 的段，实际上应该分为 2+2 的段。我们将 3 的段减一，再除以2，得出2的段；
        余数是2：target中某一4的段，直接计算2的数量即可。
         */
        int timesOf3 = target/3;

        if(target - 3*timesOf3 == 1)//出现4的段
            timesOf3--;//将3的段减一，把剩下的4子段分为2+2
        //不管剩下的子段是2，还是4，此时timeOf3都是确定的，我们可以直接计算剩下的2的子段数量
        int timesOf2 = (target - timesOf3*3)/2;// target - timesOf3*3 =2（没有4的子段，只有一个2子段）或者 4（有4的子段，有2个2子段）

        return (int)(Math.pow(3,timesOf3) * Math.pow(2,timesOf2));//注意，这里是3子段的乘积 乘以 2子段的乘积，不是加！！！
    }

    public static void main(String[] args)
    {
        System.out.println(cutRope1(4));
    }
}
