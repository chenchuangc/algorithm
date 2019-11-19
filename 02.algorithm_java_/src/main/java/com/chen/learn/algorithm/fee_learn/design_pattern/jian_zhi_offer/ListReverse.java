package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;


import java.util.ArrayList;

/**
 * Created by chencc on 2018/10/12.
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class ListReverse {

   static class ListNode {
       int val;
       public ListNode next ;
   }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> container = new ArrayList<>();
        collect(container, listNode);
        return container;
    }

    private void collect(ArrayList<Integer> container, ListNode listNode) {
        if (null == listNode) {
            return;
        }
        collect(container, listNode.next);
        container.add(listNode.val);
    }

    public ListNode reverse(ListNode listNode) {
        if (listNode.next == null) {
            return listNode;
        }
        ListNode cur = listNode;
        ListNode next = listNode;
        ListNode head = reverse(next);
        next.next=cur;
        cur.next=null;
        return head;

    }


}
