package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/15.
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class TreeToList {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 这个解法是运行超时的，感觉是因为找最左最右太耗时，
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode min = pRootOfTree;
        TreeNode cur = pRootOfTree;
        while (cur != null) {
            min=cur;
        }
        resolve(pRootOfTree);
        return min;
    }

    private void resolve(TreeNode pRootOfTree) {

        if (null == pRootOfTree) {
            return ;
        }
        TreeNode leftMax = findMax(pRootOfTree.left);
        TreeNode rightMin = findMin(pRootOfTree.right);
        resolve(pRootOfTree.left);
        resolve(pRootOfTree.right);
        if (null != leftMax) {

            leftMax.right=pRootOfTree;
            pRootOfTree.left=leftMax;
        }
        if (null != rightMin) {
            pRootOfTree.right=rightMin;
            rightMin.left = pRootOfTree;
        }

    }

    private TreeNode findMin(TreeNode right) {

        TreeNode min = right;
        while (right  != null) {
            min=right;
            right=right.left;
        }
        return min;
    }

    private TreeNode findMax(TreeNode left) {
        TreeNode max=left;
        while (left  != null) {
            max=left;
            left=left.right;
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println(new TreeToList().Convert(null));

    }
}
