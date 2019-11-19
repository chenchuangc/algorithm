package com.chen.learn.algorithm.sort.simple.practice.stack;

/**
 * Created by chencc on 2018/5/12.
 */
public class MiniListStack <T extends Comparable<T>>{

    private Node first;
//    private Node<T> currentMin;

    public MiniListStack() {
    }

    public void push(T element) {
        Node newEle = new Node(element, first);
        if (element.compareTo(first.getVal()) < 0) {
            newEle.next = newEle;
        } else {

        }
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

    private  class Node{
        private T val;
        private Node next;
        private Node currentMin;

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
