package com.lkj.problem28;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

public class OfferGetTest28 {

    public int MoreThanHalfNum_Solution(int [] array)
    {
        //我们使用数组第一个元素作为比较元素
        int comNum = array[0];

        /**
         * 使用count来计算当前比较元素的个数，初始值为1个，设数组长度是n
         * 1）当count=0的时候，说明前i+1个数组元素中（数组元素从0开始），比较元素的个数等于其他元素的个数。此时比较元素可能是majority，也有可能不是majority。
         * 如果比较元素是 majority，由于 majority 大于n/2，那么后面的 n-i-1 个元素中，majority的数量肯定大于一半；
         * 如果比较元素不是 majority，其他元素（包括majority）个数小于此时的比较元素，那么后面的 n-i-1 个元素中，majority的数量肯定大于一半；
         * 因此我们可以将比较元素进行替换：comNum = array[i]; ，继续从 i+1 开始，进行下面的遍历。
         * 遍历到数组最后，一定会有count一直大于0的情况，此时的比较元素就是majority。
         *
         * 2）替换的时候，为什么不把比较元素替换为:array[i+1]，不是说从第 i+1 遍历比较吗？
         * 其实这里可以替换为 comNum = array[i+1]，但是我们下一次循环又会遇到 array[i+1]，再次比较就出错。（其实可以设置）
         * 因此我们此时将比较元素设置为：array[i]，
         * 其实我这里考虑的是，会不会因为从 i 开始而不是从 i+1 开始，导致从i到n-1的元素中majority个数小于一半。
         *  其实不会，因为如果array[i]是majority，那么从i到n-1的元素中，majority的个数肯定大于一半；
         * 如果array[i] 不是majority，很快它遇到与他不同的元素（遇到的有可能是majority，也有可能不是majority），
         * 遇到的元素会使得比较元素再次替换，那么剩下的元素中majority的数量肯定大于一半。
         * 例如：
         * i+1 与 i 不同，那么此时比较元素替换为 array[i+1]，那么从i+1到n-1的元素中，majority的个数肯定大于一半；
         * 如果 i+1 与 i 相同，很快就会大量不同的其他元素，那么剩下的元素中majority的个数还是大于一半！！！
         */
        for (int i = 1,count = 1; i < array.length ; i++)
        {
            if(array[i] == comNum)
                count++;
            else
                count--;

            if(count==0)
            {
                //将array[i]设置为比较元素，注意设置count=1，即此时比较元素个数为1
                comNum = array[i];
                count = 1;
            }
        }
        /*
        结束循环的时候，我们会获取到最后的comNum，此时这个comNum的count>=1，但是我们无法提供count的值进行判断；
        此时如果原来的数组中存在majority，那么comNum在数组中的数量肯定大于 n/2，且comNum就是majority；
        如果如果原来的数组中不存在majority，那么comNum在数组中的数量肯定不于 n/2；
        我们计算 comNum在数组中的数目，进行比较即可。
         */

        int num = 0;
        for (int i : array) {
            if(i == comNum)
                num++;
        }
        return num>array.length/2 ? comNum : 0;
    }
}
