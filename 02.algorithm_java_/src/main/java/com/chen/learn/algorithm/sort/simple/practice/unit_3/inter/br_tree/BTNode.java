package com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.br_tree;

/**
 * Created by chencc on 2018/8/19.
 */
public class BTNode {
    BTNode left;
    BTNode right;
    Color color;//指向父链接的颜色
    BTNode parent;
    Integer key;
    String val;
    Integer n;

    public BTNode(BTNode left, BTNode right, Color color, BTNode parent, Integer key, String s, int n) {

        this.left = left;
        this.right = right;
        this.color = color;
        this.parent = parent;
        this.key = key;
        this.val = s;
        this.n=n;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public BTNode getLeft() {
        return left;
    }

    public void setLeft(BTNode left) {
        this.left = left;
    }

    public BTNode getRight() {
        return right;
    }

    public void setRight(BTNode right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BTNode getParent() {
        return parent;
    }

    public void setParent(BTNode parent)
    {
        this.parent = parent;
    }
}
