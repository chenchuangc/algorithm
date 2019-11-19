package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import java.util.Optional;

/**
 * Created by chencc on 2018/10/15.
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * <p>
 * 可以使用中序遍历啊，中序遍历本身就是有序的。
 */
public class TreeToListBetter2 {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 引入全局变量，这种方式对你来说好像容易理解的多了。
     * 理解起来顺畅多了
     */
    static TreeNode last ;

    /**
     * 可以使用中序遍历，啊啊啊，中序遍历本身就是有序的
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode head = getLeft(pRootOfTree);
        midIterator(pRootOfTree);

        return head;
    }

    private TreeNode getLeft(TreeNode pRootOfTree) {
        TreeNode head = pRootOfTree;
        while (head.left != null) {
            head=head.left;
        }
        return head;
    }

    /**
     * 这里的使用方法传入参数的过程可以认为是很精巧的一个操作，
     * 在正常的程序中应该不会这样用吧，可以归集为一种思想么，这个last，算是一个全局共享的变量一样，一直影响到最后，
     *
     * 可不可以认为引入一个全局变量也能够达到这样的效果呢
     * @param root
     */
    public void midIterator(TreeNode root) {
        if (null == root) {
            return;
        }
        midIterator(root.left);
        Optional.ofNullable(last).ifPresent(e -> {
            e.right = root;
            root.left = e;
        });
        last=root;
        midIterator(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        printA(new TreeToListBetter2().Convert(root));

    }

    private static void printA(TreeNode convert) {
        while (convert != null) {
            System.out.print(convert.val + ",");
            convert=convert.right;
        }
    }
}
