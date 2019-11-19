package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/12.
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * <p>
 * <p>
 * 这个在考试中也见过，就是二叉树的重建
 * 这里的技巧是
 * 1. 对于前序遍历，p[0] 是根元素
 * 2. 对于中序遍历，根元素（也就是前序中的p[0]） 是左右子树的分界线
 * 3. 根据这个其实就可以得到左边的元素有多少，右边的有多少了，得到前序遍历中哪些是左边的，哪些是右边的。
 * 4. 然后分别进行递归就可以了
 * <p>
 * 前序和中序只是根元素调换了方向，总体没有掺杂（左子树和右子树之间）
 */
public class TwoTreeRebuild {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        int treeLen = pre.length - 1;
        return build(pre, 0, treeLen, in, 0, treeLen);
    }

    private static TreeNode build(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {

        if (preStart > preEnd||inStart>inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);

        int rootIndexIn;
        int num=0;
        for (rootIndexIn = inStart; rootIndexIn <= inEnd; rootIndexIn++) {
            num++;
            if (in[rootIndexIn] == pre[preStart]) {
                break;
            }
        }
        root.left = build(pre, preStart + 1, preStart+num-1, in, inStart, rootIndexIn - 1);
        root.right = build(pre, preStart+num, preEnd, in, rootIndexIn + 1, inEnd);

        return root;

    }

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 5, 6, 7, 3};
        int[] in = new int[]{4, 5, 2, 7, 6, 1, 3};
        TreeNode node = reConstructBinaryTree(pre, in);
        System.out.println("ok");

    }
}



















