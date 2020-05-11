package com.lkj.problem6;

public class OfferGetTest6
{
    //方法2
    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0)
            return 0;

        /*
        注意：
        1）由于有i+1，因此i最大只能到array.length-2，否则可能数组越界；
        2）由于原来的数组是非递减排序数组，要么数组全部元素相同，要么数组递增。
            当下面循环没有找到旋转数组 中 array[i] > array[i+1] 的情况，说明整个数组所有元素相同，最后return array[0] 即可；
            如果找到，说明array[i+1]就是最小数字
         */
        for (int i = 0; i < array.length-1 ; i++)
        {
//            if(array[i] <= array[i+1])
//            {
//                continue;
//            }
//            else
//            {
//                return array[i+1];
//            }
            //其实可以写为
            if(array[i+1]<array[i])
                return array[i+1];
        }
        return array[0];
        //运行时间：248ms,占用内存：28400k
    }

    //方法3：二分查找
    public int minNumberInRotateArray1(int [] array)
    {
        if (array == null || array.length == 0)
            return 0;

        int begin = 0;
        int end = array.length-1;

        /*
        说明：我们不在循环内判断哪一个数是最小值，而是一直执行循环，直到 begin=end（不管数组元素个数是奇数还是偶数，循环到最后都有begin=end，不会直接就begin>end），
        此时begin位置（或者说end位置）的值就是最小值。
        当还没有查找到 begin=end的时候，我们不对哪一个值是最小值进行判断（其实此时有一些情况可以判断）。
        如果在循环内讨论某一个值是最小值，会有很多情况，讨论起来很麻烦。

        1）当 array[mid]>array[end]，此时最小值一定在右边区间，begin=mid+1;

        2）当 array[mid]<array[end]，此时有可能array[mid]是最小值，也有可能最小值在左边，为了避免错过array[mid]是最小值的情况，
        我们我们直接将end=mid，而不是end=mid-1，这样便不会错过array[mid]是最小值的情况（因为mid被考虑进来）。

        3）当 array[mid]=array[end]，此时有可能array[mid]是最小值，也有可能最小值在左边。同样，为了避免错过mid位置的值，
        我们设置 end=mid；

        按上面这三种情况进行二分查找区间的缩小，不会错过最小值的点，缩小到begin=end的时候，他们所代表的的位置就是最小值所在位置！

        注意这种思想！！！不在循环内讨论，而是一直遍历，知道begin=end，在循环内讨论的话情况太多且复杂！！（掌握这种技巧）
        另外，循环不能加=，既不能是 begin<=end，否则有可能 begin=end=mid，使得循环陷入死循环。
         */
        int mid = 0;
        while (begin<end)
        {
            mid = (begin+end)/2;
            if(array[mid]>array[end])
                begin = mid+1;
            else
                end = mid;
        }
        return begin;
    }
}
