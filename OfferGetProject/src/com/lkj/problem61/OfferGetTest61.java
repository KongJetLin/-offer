package com.lkj.problem61;

public class OfferGetTest61
{
    public class TreeNode
    {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val)
        {
            this.val = val;

        }

    }


    //序列化：前序遍历
    String Serialize(TreeNode root)
    {
        if(root == null)
            return "#,";//读取到null的时候，返回“#,”

        String str = root.val+",";//先读取当前结点值
        str += Serialize(root.left);//再读取左子树的前序遍历结果
        str += Serialize(root.right);//最后读取右子树的后序遍历结果

        return str;
        //这几句可以合并为：return root.val+","+Serialize(root.left)+Serialize(root.right);
    }

    //反序列化需要将字符串转换为字符串数组，因此需要一个成员变量来标记数组下标
    private int index = -1;

    //反序列化
    TreeNode Deserialize(String str)
    {
        if(str == null || str.length() == 0 || str == "#,")
            return null;//这3种情况说明传入的str都为null

        String[] strArr = str.split(",");//将不为null的字符串数组根据 “!” 切割开来
        return Deserialize(strArr);
    }

    //根据数组的值递归重建二叉树的方法
    private TreeNode Deserialize(String[] strArr)
    {
        //首先，将数组下标设置为当前结点值在数组中的索引位置
        index++;

        //如果当前索引在数组范围内，即当前结点值存在。且当前结点值不为null
        // 将当前结点值构造成为 TreeNode ，并连接左右子树的结点，返回以当前结点为根结点的树
        /**1）注意，这里应该是if，不是while，这里只是判断，不需要用到循环！当然，用while也可以通过！因为最后不会死循环，都是return*/
        if (index < strArr.length && !strArr[index].equals("#")) /**2）注意，String是引用数据类型“==”比较地址，要使用equals()比较值，并且这里是不等于！*/
        {
            TreeNode curNode = new TreeNode(Integer.parseInt(strArr[index]));//前序遍历，先找当前结点
            curNode.left = Deserialize(strArr);//左指针指向左子树返回的结果
            curNode.right = Deserialize(strArr);//右指针指向右子树返回的结果

            return curNode;/**3）注意，最后记得将正确结果返回！*/
        }
        //如果当前结点值为“#”，或者index超出数组下标（事实上不会超出，因为遍历到最后一个null指针，直接返回null，结束递归，不会使得index超标）
        return null;
    }
}
