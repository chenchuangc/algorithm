package com.chen.learn.algorithm.sort.simple.exam;

import com.chen.learn.algorithm.util.Helper;

public class LastK {

    /**
     * 链表中找倒数第k个元素
     * @param head
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode head, int k) {
        ListNode pre=head;
        ListNode p=head;
        for(int i=1;i<k;i++) {
            if (pre.next != null) {
                pre = pre.next;
            } else return null;
        }
        while(pre.next!=null){
            pre=pre.next;
            p=p.next;
        }
        return p;

    }

    public static void main(String[] args) {
        ListNode listNode= Helper.creatListNode(10);
        Helper.print(listNode);
        ListNode result=FindKthToTail(listNode,3);
        System.out.println(result.getVal());
    }

}
