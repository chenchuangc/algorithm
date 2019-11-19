package com.chen.learn.algorithm.sort.simple.practice.stack;

/**
 * 基于链表实现stack
 *
 */
public class ListStack<T> {

    private Node first;

    public ListStack() {
    }

    public void push(T element) {
        Node newEle = new Node(element, first);
        first=newEle;
    }

    public T pop() {
        Node current = first;
        if (null != first) {
            first = first.next;
            return (T) current.getVal();
        }
        return null;

    }

    private static class Node<T>{
        private T val;
        private Node next;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }

        public T getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }
    }
}
