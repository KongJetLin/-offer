package com.lkj.problem21;

import java.util.ArrayDeque;

public class OfferGetTest21
{
    public static boolean IsPopOrder(int [] pushA,int [] popA)
    {
        //当2个序列数组有一个位null，或者2个序列数组的长度不一致，不满足，直接返回false
        if(pushA == null || popA == null || pushA.length!=popA.length)
            return false;

        //对于栈，java推荐性能更好的ArrayDeque，而不是Stack
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int pushIndex = 0;//压栈序列的下标

        //堆出栈序列 popA进行遍历
        for (int i = 0; i < popA.length ; i++)
        {
            //如果新栈 stack不为null，且栈顶元素是目前出栈序列要出栈的元素，直接出栈
            if(!stack.isEmpty() && popA[i] == stack.peek())//注意，先判断stack是否为null，否则stack.peek()=null.peek()，出现空指针异常
            {
                stack.pop();//出栈栈顶元素
            }
            else
            {
                /**
                如果栈顶元素不是当前出栈序列要出栈的元素，则当 pushIndex<pushA.length的时候，
                 将压栈序列的元素压入stack，直到stack栈顶元素是当前出栈序列要出栈的元素
                 */
                while(pushIndex < pushA.length)
                {
                    stack.push(pushA[pushIndex]);//将压栈序列的元素压入stack
                    //只要压栈序列有一个元素压入stack，不管stack栈顶元素能不能满足pop[i]，pushA下标必须加1，因此需要在break之前加1
                    pushIndex++;
                    if(stack.peek() == popA[i])
                    {//当stack栈顶元素是pop[i]，将栈顶元素出栈，并且跳出while循环，判断下一个pop[i]
                        stack.pop();
                        break;
                    }
                }
                /**
                结束while循环有2种情况：
                 1）stack.peek() == popA[i]，break结束
                 2）pushA元素入栈stack完毕，此时 stack.peek() != popA[i]，pushIndex = pushA.length，角标越界，结束循环
                 */
            }
        }
        /**
        说明：
         1）在while循环中，如果pushA元素入栈stack完毕，此时 stack.peek() != popA[i]，while循环结束，此处说明 popA不是pushA的弹出序列。
         此时for循环就会跳过这一层 pop[i]的判断，判断下一个元素 pop[i+1]是不是stack栈顶元素。
         那么不管怎么样，总体上 stack最后最少少出栈一个元素，假设 pushA与popA的大小都是n，stack里面所有经过的元素数目=PushA元素数目=n，
         这里stack少出栈一个popA[i]，那么stack最后肯定不为empty；

         2）如果popA是pushA的弹出序列，由于 pushA.length=popA.length=stack的所有经过元素数目，stack最后一定为null！

         返回时，stack=empty，返回true，否则返回false
         */
        return stack.isEmpty();
    }

    public static void main(String[] args)
    {
        int[] pushA = {1,2,3,4,5};
        int[] popA = {4,5,3,2,1};

        System.out.println(IsPopOrder(pushA , popA));
    }
}
