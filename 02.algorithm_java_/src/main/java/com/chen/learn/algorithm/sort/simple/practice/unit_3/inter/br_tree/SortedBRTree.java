package com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.br_tree;

import com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.SortedSymbleT;

import static com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.br_tree.Color.BLACK;

/**
 * Created by chencc on 2018/8/19.
 * 2-3树的特点在之前没有注意到的是，他是所有的底层节点到root的根节点的黑色链接数是一样的，所以新增的节点添加在底部节点的时候链接必须是红色的。
 * 然后考虑到对应的多种情况的变换，基本上就可以处理这些情况了
 */
public class SortedBRTree implements SortedSymbleT<Integer, String> {

    BTNode root;


    public void put(Integer integer, String s) {

        root = put(root, integer, s);
        root.color=BLACK;
        BTUtil.print(root);
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("--------------");
    }

    private BTNode put(BTNode parent, Integer integer, String s) {

        if (null == parent) {
            return new BTNode(null, null, Color.RED, null, integer, s, 1);
        } else {
            int compare = integer.compareTo(parent.key);
            if (compare > 0) {
                parent.right = put(parent.right, integer, s);
            } else if (compare < 0) {
                parent.left = put(parent.left, integer, s);
            } else {
                parent.val = s;
            }
            parent.setN(size(parent.left) + size(parent.right) + 1);
        }

        return repairNode(parent);

    }

    private BTNode repairNode(BTNode parent) {

        if (parent.left != null && parent.left.left != null) {
            if (parent.left.color == Color.RED && parent.left.left.color == Color.RED) {

                BTNode currentParent = rotateRight(parent);
                currentParent.left.color = BLACK;
                currentParent.right.color = BLACK;
                currentParent.color = Color.RED;
                return currentParent;
            }
        }

        if (parent.right != null && parent.right.color == Color.RED) {
            if (parent.left == null || parent.left.color == BLACK) {
                return rotateLeft(parent);
            } else {
                parent.left.color = BLACK;
                parent.right.color = BLACK;
                parent.color = Color.RED;
                return parent;
            }
        }
        return parent;
    }

    private BTNode rotateRight(BTNode parent) {

        BTNode newP = parent.left;
        Color parentColor = parent.color;
        parent.color = Color.RED;
        newP.n = parent.n;
        newP.color = parentColor;
        parent.left = newP.right;
        newP.right = parent;
        parent.n = size(parent.left) + size(parent.right) + 1;
        return newP;
    }

    private BTNode rotateLeft(BTNode parent) {
        BTNode curP = parent.right;
        Color oldParentColor = parent.color;
        parent.color = curP.color;
        curP.color = oldParentColor;
        curP.n = parent.n;
        parent.right = curP.left;
        curP.left = parent;
        parent.n = size(parent.left) + size(parent.right) + 1;
        return curP;

    }

    private Integer size(BTNode node) {

        if (null == node) {
            return 0;
        } else {
            return node.n;
        }
    }

    public String get(Integer integer) {
        return null;
    }

    public void delete(Integer integer) {

    }

    public boolean contains(Integer integer) {
        return false;
    }

    public boolean isEnpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public Integer min() {
        return null;
    }

    public Integer max() {
        return null;
    }

    public Integer floor(Integer integer) {
        return null;
    }

    public Integer ceiling(Integer integer) {
        return null;
    }

    public int rank(Integer integer) {
        return 0;
    }

    public Integer select(int k) {
        return null;
    }

    public static void main(String[] args) {
        SortedBRTree brTree = new SortedBRTree();
        brTree.put(2,"34s");
        brTree.put(32,"34s");
        brTree.put(33,"33s");
        brTree.put(29,"444s");
        brTree.put(19,"444s");
        brTree.put(99,"444s");
        brTree.put(111,"444s");
        brTree.put(131,"444s");
    }
}
