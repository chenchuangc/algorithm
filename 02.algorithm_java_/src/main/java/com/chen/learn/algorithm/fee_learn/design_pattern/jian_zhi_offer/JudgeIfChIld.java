package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/14.
 * 刚开始忽略了二叉树中有重复元素的情况，导致出了问题。
 */
public class JudgeIfChIld {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
//        TreeNode motherNodeToChildRoot = haveRoot(root1, root2);
//        if (motherNodeToChildRoot == null) {
//            return  false;
//        }
//        return iteratorJudge(motherNodeToChildRoot, root2);
        return haveRoot(root1, root2);

    }

    private boolean iteratorJudge(TreeNode motherNodeToChildRoot, TreeNode root2) {

        if (motherNodeToChildRoot == null && root2 == null) {
            return true;
        }
        if (motherNodeToChildRoot == null && root2 != null) {
            return false;
        }
        if (motherNodeToChildRoot != null && root2 == null) {
            return true;
        }

        if (motherNodeToChildRoot.val != root2.val) {
            return false;
        }
        boolean left = iteratorJudge(motherNodeToChildRoot.left, root2.left);
        if (!left) {
            return false;
        }
        return iteratorJudge(motherNodeToChildRoot.right, root2.right);
    }

    private boolean haveRoot(TreeNode root1, TreeNode root2) {
        boolean res=false;
        if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            res = iteratorJudge(root1, root2);
        }
        if (!res) {
            if (root1.val > root2.val) {

                res = haveRoot(root1.left, root2);
            } else {
                res = haveRoot(root1.right, root2);

            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(3);
        root1.left.left = new TreeNode(5);

        root1.right = new TreeNode((4));
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(7);
        root2.right = new TreeNode(8);

        System.out.println(new JudgeIfChIld().HasSubtree(root1,root2));
    }


}
