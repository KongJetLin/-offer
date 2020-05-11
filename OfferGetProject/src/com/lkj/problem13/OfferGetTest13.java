package com.lkj.problem13;

import java.util.Stack;

public class OfferGetTest13
{
    /**
     * 从头到尾遍历
     * @param array
     */
    public static void reOrderArray1(int [] array) {
        if(array==null || array.length<=0)
            return;//如果array数组为null或者没有数据，直接结束

        int temp = -1;//定义一个变量存储值
        int length = array.length;

        for (int i = 0; i < length ;)
        {
            if(array[i]%2 == 0)//如果是偶数
            {
                temp = array[i];//将这个偶数存储起来
                //通过循环将后面的数往前移动
                for (int j = i; j < array.length-1 ; j++)
                {
                    array[j] = array[j+1];
                }
                array[array.length-1] = temp;
                length--;//找到偶数，要让length-1来控制比较次数
            }else {
                /*
                注意：
                1、如果i位置的数是偶数，那么将这个位置的数移动到最后面后，还需要再判断i+1位置移动到i位置的数是不是偶数，
                    知道i位置的数不是偶数，才能继续到i+1位置进行判断。
                    因此，只有i位置的数是奇数的时候，我们将i++；
                2、length用于控制比较次数，最后比较次数必须是length-1，这样才能保证比较到所有数，又不会多。
                    都是我们找到偶数的时候，i并不会+1，因此我们要让当前length-1来控制，这样才能保证最后比较次数是length-1。
                    否则在比较完length-1次之后，还会继续比较，导致所有顺序比打乱
                 */
                i++;//找到奇数，移动到下一位置，控制比较次数
            }
        //运行时间：16ms，占用内存：9300k
        }
    }


    public static void reOrderArray2(int [] array)
    {
        if (array == null || array.length <= 0)
            return;//如果array数组为null或者没有数据，直接结束

        Stack<Integer> stack = new Stack<>();
        //遍历寻找偶数
        for (int i = array.length-1; i >= 0  ; i--)
        {
            if(array[i]%2 == 0)
                stack.push(array[i]);
        }
        //遍历寻找偶数
        for (int i = array.length-1; i >= 0  ; i--)
        {
            if(array[i]%2 == 1)
                stack.push(array[i]);
        }

        int j = 0;
        //当栈还不是空，将里面的元素取出放入数组
        while (!stack.isEmpty())
        {
            array[j] = stack.pop();
            j++;
        }

        //运行时间：17ms,占用内存：9224k
    }

    public static void reOrderArray3(int [] array)
    {
        int begin = 0;//定义一个指针，该指针指向的数值向后移动
        int end = array.length-1;//定义一个指针，该指针指向的数值向前移动
        int temp= -1;//定义类变量临时存储数据

        //先先在整体上begin指针小于end指针才进行遍历
        while (begin<end)
        {
            //遍历找到begin指针指向的偶数，没找到就begin++
            // 这里也需要 begin<end ，因为有可能begin一直没有找到偶数，就会一直加，知道大于end，这样就会出错
            while (begin<end && array[begin]%2 != 0)
            {
                begin++;
            }
            //同样，遍历找到end指针指向奇数
            while(begin<end && array[end]%2 != 1)
            {
                end--;
            }

            //找到相应的奇数偶数后，还需判断begin<end，因为有可能begin>=end（实际上不会出现，因为前面2个循环都在控制，这里判断是谨慎起见）
            if(begin<end)
            {
                //交换奇数偶数的位置
                temp = array[begin];
                array[begin] = array[end];
                array[end] = temp;
            }
        }
    }


    //测试
    public static void main(String[] args) {
        int []array = {1,2,3,4,5,6,7,8,9,10,11};
        long startTime = System.nanoTime();   //获取开始时间
        reOrderArray3(array);
        long endTime = System.nanoTime(); //获取结束时间
        for(int i=0;i<array.length;i++)
        {
            System.out.print(array[i]+" ");
        }
        System.out.println("程序运行时间： " + (endTime - startTime) + "ns");
    }
}
