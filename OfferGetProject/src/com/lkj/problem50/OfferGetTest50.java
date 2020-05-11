package com.lkj.problem50;

import java.util.Arrays;
import java.util.HashSet;

public class OfferGetTest50
{
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public static boolean duplicate1(int numbers[],int length,int [] duplication)
    {
        //注意，测试用例有一个空的数组：[]，该数组length=0，因此我们需要提前判断数组为null或者数组长度小于等于0（也可以是等于0）
        if(numbers == null || length <= 0)
        {
            duplication[0] = -1;
            return false;
        }

        for (int i = 0; i < length ; i++)
        {
            for (int j = i+1; j < numbers.length ; j++)
            {
                if(numbers[i] == numbers[j])
                {
                    duplication[0] = numbers[i];
                    return true;
                }
            }
        }
        //如果没有查找到重复的，也必须返回false并使得duplication[0] = -1
        duplication[0] = -1;
        return false;

        //运行时间：21ms，占用内存：9584k
    }

    //注意，使用这个方法需要在剑指offer上面导入 java.util.Arrays ，否则无法运行
    public static boolean duplicate2(int numbers[],int length,int [] duplication)
    {
        //同样先排除数组为null或者数组长度为0的情况。
        if(numbers == null || length <= 0)
        {
            duplication[0] = -1;
            return false;
        }

        Arrays.sort(numbers);//对数组进行排序
        //注意！！！，这里i小于length-1，否则遍历到length-1位置，length位置不存在，数组越界
        for (int i = 0; i < length-1 ; i++)
        {
            if(numbers[i] == numbers[i+1])
            {
                duplication[0] = numbers[i];
                return true;
            }
        }
        //没找到重复的
        duplication[0] = -1;
        return false;

        //运行时间：20ms，占用内存：9656k
    }

    public static boolean duplicate3(int numbers[],int length,int [] duplication)
    {
        //同样先排除数组为null或者数组长度为0的情况。
        if(numbers == null || length <= 0)
        {
            duplication[0] = -1;
            return false;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int number : numbers)
        {
            if(!hashSet.contains(number))
            {//如果HashSet不包含这个元素，将其添加到HashSet中
                hashSet.add(number);
            }else{
                //如果包含，返回这个元素
                duplication[0] = number;
                return true;
            }
        }

        //没找到重复的
        duplication[0] = -1;
        return false;

        //运行时间：17ms，占用内存：9552k
    }

    public static boolean duplicate4(int numbers[],int length,int [] duplication)
    {
        //同样先排除数组为null或者数组长度为0的情况。
        if(numbers == null || length <= 0)
        {
            duplication[0] = -1;
            return false;
        }

        for (int i = 0; i < length ; i++)
        {
            /*
                如果当前 i 位置的数字 number[i] 与当前位置下标 i 不对应，我们将数字numbers[i]移动到它对应的下标位置，
                既将 number[i] 位置的数字与 i 位置的数字互换。如果交换后 i 位置的数字仍然与 i 不相等，继续换，
                必须换到 当前 i 位置的数字 number[i]  与当前下标 i 相等，才能使得 i+1 进行下一轮的判断。
                这种换法保证当前位置以及之前的所有位置的下标与数字都相同！
                否则可能出现之前某个位置 i 与它对应的数字 number[i] 不相等的情况。（因此此处使用while，不使用if）
             */
            while(numbers[i] != i)
            {
                //numbers[i]与它对应的下标位置的元素相等，说明之前有相同的数字占了这个位置，那么这个数字 number[i] 就是重复的数字
                if(numbers[i] == numbers[numbers[i]])
                {
                    duplication[0] = numbers[i];
                    return true;
                }
                else
                {
                    //numbers[i]与它对应的下标位置的元素不相等，将 number[i] 位置的数字与 i 位置的数字互换。
//                    int temp = numbers[i];
//                    numbers[i] = numbers[numbers[i]];
//                    numbers[numbers[i]] = temp;
/**
 * 注意！！！这里互换这里很容易出错！！按下面的测试用例分析
 * 比如当 i=0 的时候，第一行将numbers[0]=2 赋予temp=2，第二行 numbers[0] = numbers[numbers[0]] ，既numbers[0] = numbers[2]=1。
 * 最后第三行我们是想将之前index=0的位置的数字赋予index=2的位置，但是此时
 * numbers[0] = 1，变成 numbers[1] = temp = 2，其实应该是 numbers[2] = temp = 2.
 *
 * 之所以会出错，是因为第二行的时候 numbers[0] 的值改变了，不再是2，而是1，而后面我们使用 numbers[numbers[0]] = temp ，
 * 原先是想 numbers[2] = temp ，由于numbers[0] 的值改变了，变成 numbers[1] = temp。因此出错。
 *
 * 那么，此处最开始 numbers[i] 的值赋予 temp，我们使用 temp 来作为数组下标，因为 numbers[i] 变化了，但是 temp 没有变化，
 * 此时 numbers[numbers[i]] = temp 改为 numbers[temp] = temp ，numbers[temp]中的temp是原来的 numbers[i]，
 * 这样就会正确赋值。（一切都是因为第二行的时候 numbers[0] 的值改变了）
 *
 */
//                    int temp = numbers[i];
//                    numbers[i] = numbers[temp];
//                    numbers[temp] = temp;

                    //其实这里写为一个swap()函数，就不需要思考这些角标的问题
                    swap(numbers , numbers[i] , i);
                }
            }
        }

        //遍历完没找到重复的
        duplication[0] = -1;
        return false;

        /*
        运行时间：21ms，占用内存：9576k
        复杂度分析：
        从宏观上来看，虽然代码中有2个循环，但是所有的操作都只是把某个位置的元素移动到它对应的位置，
        既如果有n个数字，最多操作n次可以将他们移动到他们对应来的位置。因此次方法时间复杂度为O(n)，空间复杂度为O(1)。

        调用函数后，运行时间：24ms，占用内存：9600k
         */
    }

    private static void swap(int[] arr , int m , int n)
    {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }


     public static void main(String[] args) {

         int[] nums = new int[]{2, 3, 1, 0, 2, 5};
         int n = nums.length;
         int[] dup = new int[1];
         boolean flag = duplicate4(nums, n, dup);
         System.out.println(flag + "," + dup[0]);
     }
}
