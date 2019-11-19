package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chencc on 2018/10/15.
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class ComplexList {

    static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode Clone(RandomListNode pHead) {
        if (null==pHead) return null;
        /**
         * 这一段和循环里面的重复了，主要是为了记录头节点
         */
        Map<RandomListNode, RandomListNode> randomContainer = new HashMap<>();
        RandomListNode copyHead = new RandomListNode(pHead.label);
        RandomListNode copyCur =copyHead;
        if (pHead.random != null) {
            copyHead.random = new RandomListNode(pHead.random.label);
            randomContainer.put(pHead.random, copyHead.random);
        }
        pHead=pHead.next;
        while (pHead != null) {
            RandomListNode node = randomContainer.get(pHead);
            if (null == node) {
                node = new RandomListNode(pHead.label);
            }
            if (pHead.random != null) {
                node.random = new RandomListNode(pHead.random.label);
                randomContainer.put(pHead.random, node.random);
            }
            copyCur.next=node;
            copyCur=node;
            pHead=pHead.next;
        }
        return copyHead;
    }


}
