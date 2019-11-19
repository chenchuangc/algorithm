package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_7skills;

/**
 * Created by chencc on 2018/9/24.
 */
public class ListReverse {
    static class Node{
        Object val;
        Node next;

        public Node(Object val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    /**
     * 这个思路其实很简单就是递归的分解子问题，算是递推吧。感觉也算是分治的思想的一种体现。
     * 把当前问题分解为子问题的解决，然后当前问题在这个基础上再解决
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (null == head) {
            return null;
        }
        if (null == head.next) {
            return head;
        }
        // 这一点很巧妙，这个值就是最后一个节点，一层一层的传上来了
        Node reverse = reverse(head.next);
        head.next.next=head;
        head.next=null;
        return reverse;
    }


    /**
     * 这个算法更加难以想到，但是一定要想到需要一个引用来保存新的头节点
     * @param node
     * @return
     */
    public static Node reverseIterator(Node node) {

        Node reverse =node;
        Node temp =node.next;
        node.next=null;
        while (temp != null) {
            Node next = temp.next;
            temp.next=reverse;
            reverse=temp;
            temp=next;
        }
        return reverse;
    }


    /**
     * 这个算法相对上面的是不是又精简了不少啊，
     * 做了一致性处理，这样的话代码的整洁度和安全性都有提升
     * @param node
     * @return
     */
    public static Node reverseIteratorBetter(Node node) {

        Node reverse =null;
        Node temp =node;
        while (temp != null) {
            Node next = temp.next;
            temp.next=reverse;
            reverse=temp;
            temp=next;
        }
        return reverse;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        Sout(head);
        Sout(reverseIterator(head));
    }

    private static void Sout(Node head) {
        while (head != null) {
            System.out.print(head.toString()+" ,");
            head=head.next;
        }
        System.out.println();
    }

}
