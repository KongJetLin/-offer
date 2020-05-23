package com.lkj.problem19;

import java.util.ArrayList;

public class OfferGetTest19
{
    public static ArrayList<Integer> printMatrix(int [][] matrix)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();

        //在行、列分别定义2个指针，指向行列的首尾
        int r1 = 0 , r2 = matrix.length-1;
        int c1 = 0 , c2 = matrix[0].length-1;

        //当 r1>r2 且 c1>c2的时候，这时候所有的圈都遍历完了，结束遍历（这种情况下，最后一圈只有一个数字，即r1=r2 且 c1=c2的情况也考虑到）
        while(r1<=r2 && c1<=c2)
        {
            //从左到右是必须的
            for (int i = c1; i <= c2 ; i++)
            {
                arrayList.add(matrix[r1][i]);
            }

            //当终止行号大于起始行号，从上到下
            if(r2 > r1)
            {
                for (int i = r1+1; i <= r2 ; i++)
                {
                    arrayList.add(matrix[i][c2]);
                }
            }

            //除了终止行号大于起始行号，还要求终止列号大于起始列号，从右到左
            if(r2 > r1 && c2 > c1)
            {
                for (int i = c2-1; i >= c1 ; i--)
                {
                    arrayList.add(matrix[r2][i]);
                }
            }

            //终止行号比起始行号至少大2，同时终止列号大于起始列号
            if(c2 > c1 && r2-2 >= r1)
            {
                for (int i = r2-1; i > r1 ; i--)
                {
                    arrayList.add(matrix[i][c1]);
                }
            }
            //最后，遍历完一圈记得调整行列首尾指针
            r1++; r2--; c1++; c2--;
        }

        return arrayList;
    }

    public static void main(String[] args)
    {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        ArrayList<Integer> arrayList = printMatrix(arr);
        for (Integer integer : arrayList)
        {
            System.out.print(integer+",");
        }
    }
}
