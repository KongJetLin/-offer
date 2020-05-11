package com.lkj.problem64;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OfferGetTest64
{
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();

        //注意排除 size=0 与 size大于数组长度的情况
        if(size==0 || size>num.length)
            return arrayList;
        /*
        分析：我们需要取出每个窗口的最大值，我们优先队列的队首必须是最大值，就是要使用最大堆实现优先队列。
        但是java提供的优先队列是最小堆实现，为了将其转换为最大堆，我们还需要传入一个 Comparator 对象来控制
         */

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>()
        {
            /*
            这里，如果num1>num2，返回-1，说明定义在队列中 num1的小于num2，即定义优先队列中，比较的时候 较大数小于比较小的数，
            即数 “实际大小” 与 “比较大小” 相反。
            由于最小堆实现的优先队列会将最小的元素放在队首，即数越小在优先队列中优先级越高。（这里的大小指的是比较大小）
            那么“比较大小”中最小的数就会被放到队首，而这个数“实际大小”则是最大的！

            技巧：
            1）其实这种做题的时候，如果 num2-num1 不对，换成 num1-num2 就肯定没错。
            2）使用java最小堆实现的优先队列，如果是求最小值可以直接使用；
                如果是求最大值，实现 Comparator，使得 “实际大小” 与 “比较大小” 相反。
             */
            @Override
            public int compare(Integer num1, Integer num2)
            {
                return num2-num1;
            }
        });
        /**
         * 这里也可以直接使用 Lambda表达式
         * PriorityQueue<Integer> queue = new PriorityQueue<>( (num1 , num2)-> num2-num1);
         */

        //先将第一个窗口的值添加到优先队列
        for (int i = 0; i < size ; i++)
        {
            queue.add(num[i]);
        }
        //将优先队列队首最大的元素（第一个窗口最大元素）添加到ArrayList，O(1).
        //注意只是获取队列队首最大值，不要出队队首元素
        arrayList.add(queue.peek());

        //下面定义2个指针，分别指向当前窗口的第一个位置的前一个位置（即上一个窗口的第一个位置）和当前窗口的最后位置，遍历下面所有窗口。
        //这个过程画图便可知
        for (int i = 0 , j = i+size;  j < num.length ; i++ , j++)
        {
            queue.remove(num[i]);//将上一个窗口的第一个位置的元素移出优先队列，O(logn)
            queue.add(num[j]);//将当前窗口的最后一个位置的元素添加到优先队列，O(logn)
            arrayList.add(queue.peek());//将当前优先队列的最大值存储到ArrayList，同样是获取队首元素，不是出队队首元素，O(1).
        }
        //最后总体复杂度是 nlogn
        return arrayList;
    }
}
