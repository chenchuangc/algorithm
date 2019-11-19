package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3;

/**
 * Created by chencc on 2018/8/24.
 */
public class IndexPriorityElement implements Comparable<IndexPriorityElement> {


    private Comparable key;

    public IndexPriorityElement(Comparable key) {
        this.key = key;
    }


    @Override
    public int compareTo(IndexPriorityElement o) {
        return key.compareTo(o.getKey());
    }

    public Comparable getKey() {
        return key;
    }

    public void setKey(Comparable key) {
        this.key = key;
    }
}
