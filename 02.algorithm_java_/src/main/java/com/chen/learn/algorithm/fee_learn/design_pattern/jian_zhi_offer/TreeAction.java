package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by chencc on 2018/10/12.
 * 对二叉树的操作，非递归的方式有些困难，
 * 看来对树形结构还是使用递归的方式更靠谱
 */
public class TreeAction {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 前序跟优先
     *
     * @param treeNode
     */
    public static void prePrint(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        System.out.print(treeNode.val + ",");
        prePrint(treeNode.left);
        prePrint(treeNode.right);
    }

    /**
     * 前序跟优先
     *
     * @param treeNode
     */
    public static void prePrintNoIterator(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (treeNode != null) {
            System.out.print(treeNode.val + ",");
            Optional.ofNullable(treeNode.right).ifPresent(stack::push);
            treeNode = treeNode.left;
            if (treeNode == null) {
                treeNode = stack.isEmpty() ? null : stack.pop();
            }
        }
    }

    /**
     * 中序左边优先，根在中间
     *
     * @param treeNode
     */
    public static void midPrint(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        midPrint(treeNode.left);
        System.out.print(treeNode.val + ",");
        midPrint(treeNode.right);
    }

    /**
     * 中序左边优先，根在中间
     *
     * @param treeNode
     */
    public static void midPrintWithoutIterator(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (null != treeNode||!stack.isEmpty()) {
            while (null != treeNode) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }
            treeNode = stack.pop();
            System.out.print(treeNode.val + ",");
            treeNode = treeNode.right;
        }
    }

    /**
     * 后序左边优先，根在最后
     *
     * @param treeNode
     */
    public static void behindPrint(TreeNode treeNode) {
        if (null == treeNode) {
            return;
        }
        behindPrint(treeNode.left);
        behindPrint(treeNode.right);
        System.out.print(treeNode.val + ",");
    }


    /**
     * 广度遍历，按照层级遍历
     *
     * @param treeNode
     */
    public static void byFloor(TreeNode treeNode) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + ",");
            Optional.ofNullable(node.left).ifPresent(queue::offer);
            Optional.ofNullable(node.right).ifPresent(queue::offer);
        }
    }


    public static TreeNode init() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.left.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        return root;
    }

    public static void main(String[] args) {
        prePrint(init());
        System.out.println("********");
        prePrintNoIterator(init());
        System.out.println();
        midPrint(init());
        System.out.println("^^^^^^");
        midPrintWithoutIterator(init());
        System.out.println();
        behindPrint(init());
        System.out.println();
        byFloor(init());

    }

}
