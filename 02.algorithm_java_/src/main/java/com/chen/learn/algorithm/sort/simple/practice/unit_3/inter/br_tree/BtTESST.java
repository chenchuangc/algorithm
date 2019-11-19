package com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.br_tree;

/**
 * Created by chencc on 2018/8/20.
 */
public class BtTESST {

    BTNode root;

    public void put(Integer key, String val) {
        root = put(root, key, val);
        root.color=Color.BLACK;
        BTUtil.print(root);
        System.out.println("--------------------");
        System.out.println("--------------------");
        System.out.println("--------------------");
    }

    private BTNode put(BTNode node, Integer key, String val) {
        if (null == node) {
            return new BTNode(null, null, Color.RED, null, key, val, 1);
        } else {
            int compare = key.compareTo(node.getKey());
            if (compare == 0) {
                node.setVal(val);
                return node;
            }
            if (compare > 0) {
                node.right = put(node.right, key, val);
            }
            if (compare < 0) {
                node.left = put(node.left, key, val);
            }
            return repaireNode(node);
        }
    }

    private BTNode repaireNode(BTNode pnode) {
        BTNode node = pnode;
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(pnode);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
           node= rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            node.left.color=Color.BLACK;
            node.right.color=Color.BLACK;
            node.color=Color.RED;
        }
        return node;
    }

    private BTNode rotateRight(BTNode node) {
        BTNode newp = node.left;
        node.left=newp.right;
        newp.right=node;
        newp.color=node.color;
        node.color=Color.RED;

        return newp;
    }

    private BTNode rotateLeft(BTNode node) {
        BTNode newp = node.right;
        newp.color=node.color;
        node.color=Color.RED;

        node.right=newp.left;
        newp.left=node;
        return newp;
    }

    private boolean isRed(BTNode node) {
        if (null == node) {
            return false;
        }
        return node.color==Color.RED;
    }

    public static void main(String[] args) {
        BtTESST test = new BtTESST();
        test.put(23,"23");
        test.put(2,"23");
        test.put(3,"23");
        test.put(56,"23");
        test.put(32,"23");
        test.put(11,"23");
        test.put(76,"23");
        test.put(86,"23");
    }
}
