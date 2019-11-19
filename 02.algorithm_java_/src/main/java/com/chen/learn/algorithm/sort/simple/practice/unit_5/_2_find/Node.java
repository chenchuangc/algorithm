package com.chen.learn.algorithm.sort.simple.practice.unit_5._2_find;

/**
 * Created by chencc on 2018/9/4.
 */
public class Node {

    Object value;
    Node[] child;

    public Node(Object value, int R) {
        this.value = value;
        this.child = new Node[R];
    }

    public Node( int R) {
        this.value = value;
        this.child = new Node[R];
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node[] getChild() {
        return child;
    }

    public void setChild(Node[] child) {
        this.child = child;
    }
}
