package com.lkj.problem29;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

/** 使用堆排序，时间复杂度是O(nlogn)
这里我们使用最大堆实现的优先队列，维护优先队列中有k个元素
 */
public class OfferGetTest29
{
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k)
    {
        ArrayList<Integer> res = new ArrayList<>();

        //注意对特殊情况进行判断。
        if(input == null || input.length == 0 || k > input.length || k <= 0)
            return res;

        //记住java实现最大堆用最小堆加上Lambda表达式：(o1, o2) -> o2-o1  即可
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1 );

        //将数组中的元素放入堆中（注意只放k个元素）
        for (int i = 0; i < input.length; i++)
        {
            /**
            特别注意，这里 queue.size() < k，而不是queue.size() <= k。
             在添加第k个之前，queue.size()=k-1<k，进入循环，添加第k个，随后 queue.size() =k跳出循环，刚刚好添加k个。
             如果 queue.size() <= k，在添加k个后还满足循环，会添加第k+1个！
             */
            if(queue.size() < k)
            {
                queue.add(input[i]);
            }
            else
            {
                if(input[i] < queue.peek())
                {
                    //将队首（堆顶）元素移除，将更小的元素加入堆
                    queue.remove();
                    queue.add(input[i]);
                }
            }
        }

        //将堆（队列中的元素）放入ArrayList中
        while (queue.size()>0)
            res.add(queue.remove());

        return res;
    }
}
