package com.lkj.problem1;

import java.util.Scanner;

/*  题目描述：
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class OfferGetTest1
{
    //根据分析，我们从右上角开始查找
    public static boolean Find(int target, int [][] array)
    {
        int rowNum = array.length;//获取矩阵的行数
        int colNum = array[0].length;//获取矩阵的列数

        int row = 0;//从第一行开始
        int col = colNum - 1;//从最后一列开始
        //不确定变量如何变化（因为有row与col2个变量，并且他们不会同时变化），使用while循环
        while(row<rowNum && col>=0)
        {
            if(array[row][col] == target)
                return true;//找到返回true
            if(array[row][col] > target)
            {
                col--;//如果target比当前的数小，左移，列数减一
                continue;
            }
            if(array[row][col] < target)
            {
                row++;//如果target比当前数大，下移，行数加一
                continue;
            }
        }
        return false;//循环后没有找到则返回false
    }

    //测试
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入数组行数和列数\n");
        int x = s.nextInt();
        int y = s.nextInt();
        int[][] array = new int[x][y];// 初始化数组
        System.out.println("请输入数组元素\n");
        for (int i = 0; i < x; i++)
        {// 循环输入
            for (int j = 0; j < y; j++)
            {
                array[i][j] = s.nextInt();
            }
        }

        Scanner sc = new Scanner(System.in);
        while (true)
        {
            System.out.println("请输入一个整数\n");
            int t = sc.nextInt();
            Find(t, array);
            if (Find(t, array) == false) {
                System.out.println("数组中不含该元素");
            } else
                System.out.println("数组中含有该元素");
        }
    }

}
