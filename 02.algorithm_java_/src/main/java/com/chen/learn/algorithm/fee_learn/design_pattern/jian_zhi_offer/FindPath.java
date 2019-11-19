package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by chencc on 2018/10/15.
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class FindPath {


    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> container = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        resolve(root, target, list, container);
        Collections.sort(container,(a,b)->b.size()-a.size());
        return container;
    }

    private void resolve(TreeNode root, int target, LinkedList<Integer> list, ArrayList<ArrayList<Integer>> container) {

        if (root == null) {
            return;
        }
        if (target == root.val && root.left==null && root.right==null) {
            list.addLast(root.val);
            ArrayList<Integer> temp = new ArrayList<>();
            temp.addAll(list);
            container.add(temp);
            list.removeLast();
            return;
        }
        list.addLast(root.val);
        resolve(root.left, target - root.val, list, container);
        resolve(root.right, target - root.val, list, container);
        list.removeLast();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(9);
        root.right = new TreeNode(17);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(14);
        root.left.right.left = new TreeNode(10);

        root.right = new TreeNode(17);
        root.right.left = new TreeNode(16);
        root.right.right = new TreeNode(19);
        new FindPath().FindPath(root, 48);
    }
}
