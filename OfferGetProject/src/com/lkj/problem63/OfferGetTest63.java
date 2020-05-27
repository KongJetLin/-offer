package com.lkj.problem63;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OfferGetTest63
{
    private int count = 0;//定义一个变量由于计算插入数据的数量
    /**
     创建左优先队列（最大堆实现），队首（堆顶）元素是最大的；创建右优先队列（最小堆实现），队首（堆顶）元素是最小的。
     左堆用于存放较小的数字，右堆用于存放较大的数字，且左右2个堆元素数量差不能大于1.
     我们拿到数据，先放入左堆，再放入右堆。即count为奇数的时候，将数字放入左堆，count为偶数的时候，将数据放入右堆。

     1）count为偶数：此时将元素放入右堆，因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大，
     因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边。
     2）count为奇数：此时将元素放入左堆，同理，先将元素放入右堆，取右堆堆顶元素（最小），放入左堆。
     */
    private PriorityQueue<Integer> right = new PriorityQueue<>();//右堆，最小堆
    private PriorityQueue<Integer> left = new PriorityQueue<>((num1 , num2) -> num2 - num1);//注意，返回 num2-num1就是变成最大堆（直接用就可以）

    /* 也可以写为下面
    private PriorityQueue<Integer> left = new PriorityQueue<>(new Comparator<Integer>()
    {
        @Override
        public int compare(Integer o1, Integer o2)
        {
            return o2 - o1;
        }
    });
    */

    public void Insert(Integer num)
    {
        count++;//每次插入一个数据 count +1
        if(count%2 == 0)//偶数，插入右堆
        {
            left.add(num);
            right.add(left.poll());
        }
        else//偶数，插入左堆
        {
            right.add(num);
            left.add(right.poll());
        }
    }

    public Double GetMedian()
    {
        if(count%2 == 0)
            return (double)(left.peek()+right.peek())/2;
        else
            return (double)left.peek();
    }
}
