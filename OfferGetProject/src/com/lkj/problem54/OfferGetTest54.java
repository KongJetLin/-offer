package com.lkj.problem54;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class OfferGetTest54
{
    //定义一个HashMap
    HashMap<Character , Integer> hashMap = new HashMap();
    /*
    这里我们还需要一个 StringBuilder，因为我们将元素存储到HashMap的key，
    HashMap是会根据key的hashCode再进行hash计算，然后按照hash值放入集合，并不是按照元素在字符流中出现的先后放入HashMap，
    即 HashMap 中 key 的顺序与字符流中key的顺序不同，因此，我们需一个 StringBuilder 来记录字符流中元素出现的顺序。
     */
    StringBuilder sb = new StringBuilder();

    //Insert one char from stringstream（从字符流取出一个字符插入我们的HashMap）
    public void Insert(char ch)
    {
        sb.append(ch);//取出一个元素就向 StringBuilder 插入一个元素
        if(!hashMap.containsKey(ch))
        {
            hashMap.put(ch , 1);
        }
        else
        {
            hashMap.put(ch , hashMap.get(ch)+1);
        }

    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for (int i = 0; i < sb.length() ; i++)
        {
            if(hashMap.get(sb.charAt(i)) == 1)
                return sb.charAt(i);
        }
        return '#';
    }
}

class Method2
{
    /*
    我们使用 ch 作为二维数组的第一个下标，二维数组第二个下标只有2个：0,1。
    0位置存储字符出现次数，1位置存储该字符第一次在字符流中出现的位置。

    其实如果测试的字符出现的次数小于 2^7，我们使用 byte[][] charArr = new byte[128][2]; 即可，byte 位置可以存储的数字最大为2^7。（测试通过）
     */
    int[][] charArr = new int[128][2];
    int time = 0;//用于记录字符在字符流中出现的位置

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        if(charArr[ch][0] == 0)//字符第一次出现
        {
            charArr[ch][0] = 1;
            charArr[ch][1] = time++;//记录ch第一次在字符流出现的位置
        }
        else
        {
            charArr[ch][0] = -1;//如果出现多次，将其值设置为-1。不需要改变该字符第一次出现的位置
        }

    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        /*
        为什么将 first 设置为 Integer 最大值？
        first用于记录满足出现一次的字符在字符流中第一次出现的位置。可能有多个满足的字符。
        如果后面遍历到的字符出现位置比前面的first前，即<first，那么将first设置为当前更小的位置。
        因此需要加：charArr[i][1] < first。
        但是，第一个出现的满足条件的字符也需要判断 charArr[i][1] < first ，因此，将 first 设置为Integer 最大值，
        第一个字符出现的位置一定小于 Integer 最大值，，就可以将 first 设置为第一个字符出现的位置。
        下面再次出现满足条件的字符，就可以直接比较！
         */
        int first = Integer.MAX_VALUE;
        char retCh = 0;
        for (int i = 0; i < charArr.length ; i++)
        {
            //如果当前字符满足出现一次，且第一次出现的位置小于前面满足的字符第一次出现的位置
            if(charArr[i][0] == 1 && charArr[i][1] < first)
            {
                retCh = (char)i;
                first = charArr[i][1];//更新first
            }
        }

        if(retCh == 0)
            return '#';
        return retCh;
    }
}

class Method3
{
    int[] arr = new int[256];
    Queue<Character> queue = new LinkedList<Character>();

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        //先将元素添加到队列
        queue.add(ch);
        //数组相应 ‘ch’ 位置+1，表示该元素在字符流出现次数+1
        arr[ch]++;
        /*
        当队列不为null的时候，将队列中出现次数大于1的元素出队。
        这里不需要判断元素出现次数为0的情况，因为能在队列中出现的元素次数可能大于等于1.
        移除队列前面不满足的元素，剩下的就是满足的元素，只需要将第一个满足的元素出队即可！
        当然，这部分判断也可以在 FirstAppearingOnce() 方法中判断。
         */
        while (!queue.isEmpty() && arr[queue.peek()]>1)
            queue.remove();
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
