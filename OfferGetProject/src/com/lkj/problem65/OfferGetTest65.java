package com.lkj.problem65;

/** 深度优先遍历+剪枝完成
参考剑指offer的解析：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/solution/mian-shi-ti-12-ju-zhen-zhong-de-lu-jing-shen-du-yo/
 */
public class OfferGetTest65
{
    public static boolean exist(char[][] board, String word)
    {
        char[] words = word.toCharArray();
        //首先，我们遍历二维数组 board，我们可以从board的所有结点开始查找
        for (int i = 0; i < board.length ; i++)
        {
            for (int j = 0; j < board[0].length ; j++)
            {
                //从 board 的i行j结点开始进行深度优先遍历，开始查找的是words的第0个结点
                 if(dfs(board , i , j , words , 0))
                     return true;//如果找到某一个符合，则返回true，没找到继续找下一个！
            }
        }
        //如果全部没有查找到，则return false
        return false;
    }

    /**
    深度优先遍历的方法：char[][] board , int row , int col 用于找到二维数组相应位置的结点进行比较；
     char[] words , int wordIndex 指定的是这一轮递归要比较的words中的那一个字符；
     */
    private static boolean dfs(char[][] board , int row , int col , char[] words , int wordIndex)
    {
        /**
        有3种情况表示查找不到，需要进行剪枝：
         1）数组行或者列越界；2）当前元素与要查找的元素不相符；3）当前元素之前查找过，可以通过设置合并到2）
         */
        if(row<0 || row>=board.length || col<0 || col>=board[0].length || words[wordIndex]!=board[row][col])
            return false;
        //当遍历到words的最后一个元素，且前面不符合的判断不生效，说明最后一个元素也符号，可以直接返回true
        if(wordIndex == words.length-1)
            return true;

        //为了避免当前元素被重复查找，进入下一轮的查找之前，将当前元素设置为“/”
        char temp = board[row][col];
        board[row][col] = '/';
        //当前结点符号，查找当前结点下的其他路径！
        boolean res = dfs(board , row+1 , col , words , wordIndex+1)
                || dfs(board , row-1 , col , words , wordIndex+1)
                || dfs(board , row , col+1 , words , wordIndex+1)
                || dfs(board , row , col-1 , words , wordIndex+1);

        /**
        最后，记得将 board[row][col] 还原，因为如果当前结点下的所有路径都不通，我们就必须要抛弃当前结点的路径，
         返回上一层的递归去查找其他结点，那么当前结点就相当于还没有查找过，必须将board[row][col] 还原
         */
        board[row][col] = temp;

        return res;
    }

    public static void main(String[] args)
    {
        char[][] board = {{'a' , 'b'}};
        System.out.println(exist(board , "ba"));
    }
}
