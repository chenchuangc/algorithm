package com.chen.learn.algorithm.sort.simple.practice.queue;

/**
 * Created by chencc on 2018/5/13.
 *
 * 环形链表，只是需要使用一个last就足以保存了
 * 用来实现链表的基本功能
 * 他其实对应了一定的实际场景的求解
 */
public class SimpleCircleQueue<T> {

    private Node last ;


    public  void enQueue(T val) {
        Node cur = new Node(val);
        if (null == last) {
            last = cur;
            last.next = cur;
        } else {
            Node first = last.next;
            cur.next=first;
            last.next=cur;
            last=cur;
        }
    }

    public T deQueue() {
        if (null == last) {
            return null;
        } else {
            Node first = last.next;
            if (first == last) {
                last = null;
                return first.getItem();
            } else {
                last.next=first.next;
                return first.getItem();
            }
        }
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

        public void setItem(T item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public Node getLast() {
        return last;
    }



    public static void main(String[] args) {
        SimpleCircleQueue<String> queue = new SimpleCircleQueue<String>();
        queue.enQueue("aa");
        queue.enQueue("aa1");
        queue.enQueue("aa2");
        queue.enQueue("aa3");
        queue.enQueue("aa5");
        queue.enQueue("aa6");
        queue.enQueue("aa7");
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
