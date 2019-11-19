package com.chen.learn.algorithm.sort.simple.practice.queue;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by chencc on 2018/5/11.
 * 通过链表实现的话，只能是头部删除元素，尾部添加元素
 * 反过来就是有问题的，因为如果倒着删除的话，引用信息丢失，就不太好处理了
 */
public class QueueInList<T> implements Iterable<T>{

    private Node first;
    private Node last;

    public void enQueue(T ele) {
        Node cur = new Node(ele);
        if (null == last) {
            last = cur;
            first = cur;
        } else {
            last.next = cur;
            last = cur;
        }
    }

    public T deQueue() {
        if (null == last) {
            return null;
        } else {
            Node res = first;
            Node newFirst = first.next;
            first = newFirst;
            if (newFirst == null) {
                last = null;
            }
            return res.getItem();
        }
    }

    public Iterator<T> iterator() {
        return new QueueIterator(first);
    }


    public class Node {
        T item;
        Node next;

        public Node(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }

    public class QueueIterator implements Iterator<T>{
        private Node first;

        public boolean hasNext() {
          return null != first ;
        }

        public T next() {
            Node cur = first;
            first=first.next;
            return cur.item;
        }

        public void remove() {

        }

        public QueueIterator(Node first) {
            this.first = first;
        }
    }


    public static void main(String[] args) {
        QueueInList<String> queue = new QueueInList<String>();
        System.out.println(queue.deQueue());
        queue.enQueue("aa");
        queue.enQueue("aa1");
        queue.enQueue("aa2");
        queue.enQueue("aa3");
        queue.enQueue("aa3");
        queue.enQueue("aa3");

        for (String ele : queue) {
            System.out.println(ele);
        }
//        ArrayList
    }
}
