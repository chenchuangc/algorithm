package com.chen.learn.algorithm.sort.simple.practice.list;

/**
 * Created by chencc on 2018/5/13.
 */
public class DoubleNodeOperate {


    public static DoubleNode insertHead(DoubleNode head, DoubleNode node) {
        node.next=head;
        if (null != head) {
            head.pre=node;
        }
        return node;
    }

    public static DoubleNode inserrTail(DoubleNode head, DoubleNode node) {
        DoubleNode cur=head;
        if (cur == null) {
            head=node;
            return head;
        }
        for (;cur!=null && cur.next!=null;cur=cur.next) {

        }
        cur.next=node;
        node.pre=cur;
        return cur;
    }
}
