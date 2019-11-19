package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/8/24.
 */
public class StanderdIndexMinPriority<Key extends Comparable> {

    private Key[] container;

    private int[] orderedArr;

    private int[] keyIndexInorderedArr;

    private int cap;

    private int eleNum;

    private int usingIdex;

    public StanderdIndexMinPriority(int n) {
        eleNum=0;
        usingIdex=-1;
        cap = n;
        this.container = (Key[]) new Comparable[n];
        this.orderedArr = new int[n];
        this.keyIndexInorderedArr = new int[n];
    }

    public void insert(int index, Key val) {
        container[index]=val;
        usingIdex++;
        orderedArr[usingIdex]=index;
        keyIndexInorderedArr[index]=usingIdex;
        swim(usingIdex);

    }

    public int delete() {
        Key ret = container[orderedArr[0]];
        int index = orderedArr[0];
        Helper.exchange(orderedArr, 0, usingIdex);
        Helper.exchange(keyIndexInorderedArr, orderedArr[0], orderedArr[usingIdex]);
        usingIdex--;
        sink(0);
        return index;

    }

    private void swim(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (container[orderedArr[index]].compareTo(container[orderedArr[parent]]) < 0) {
                Helper.exchange(keyIndexInorderedArr, orderedArr[index], orderedArr[parent]);
                Helper.exchange(orderedArr, index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    public void change(int index, Key val) {
        container[index]=val;
        int orderOfKey = keyIndexInorderedArr[index];
        sink(orderOfKey);
        swim(orderOfKey);
    }

    private void sink(int index) {
        while ((index * 2 + 1) <= usingIdex) {
            int little = index*2+1;
            if (little + 1 <= usingIdex && container[orderedArr[ little]].compareTo(container[orderedArr[little + 1]]) > 0) {
                little++;
            }
            if (container[orderedArr[index]].compareTo(container[orderedArr[little]]) > 0) {
                Helper.exchange(keyIndexInorderedArr, orderedArr[index], orderedArr[little]);
                Helper.exchange(orderedArr, index, little);
                index = little;
            } else {
                break;
            }
        }

    }

    public boolean contains(int index) {
        return container[index]!=null;
    }

    public boolean isEmpty() {
        return usingIdex<=-1;
    }

    public static void main(String[] args) {
        StanderdIndexMinPriority<String> test = new StanderdIndexMinPriority(10);
        test.insert(5,"aaa");
        test.insert(2,"aab");
        test.insert(3,"accc");
        test.insert(1,"eeee");
        test.insert(3,"accc");
        test.insert(4,"addd");

        test.change(1,"a");

        System.out.println(test.delete());
        System.out.println(test.delete());
        System.out.println(test.delete());
    }
}
