package com.chen.learn.algorithm.sort.simple.practice.list;

/**
 * Created by chencc on 2018/5/12.
 */
public class Node<T> {
    T item;
    Node<T> next;

    public Node(T item, Node<T> next) {
        this.item = item;
        this.next = next;
    }
}
