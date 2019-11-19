package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/15.
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class MinStack {

    Node head;

    static class Node {
        int val;
        Node next;
        Node cur_min;

        public Node(int val) {
            this.val = val;
        }
    }

    public void push(int node) {

        Node cur = new Node(node);
        if (null == head || head.cur_min == null || head.cur_min.val > cur.val) {
            cur.cur_min = cur;
        } else {
            cur.cur_min=head.cur_min;
        }
        cur.next = head;
        head=cur;
    }

    public void pop() {
        Node pop= head;
        if (null != head) {
            head =head.next;
        }
    }

    public int top() {
        if (null != head) {
            return head.val;
        } else {
            return -1;
        }
    }

    public int min() {
        if (null != head) {
            return head.cur_min.val;
        } else {
            return -1;
        }
    }


}
