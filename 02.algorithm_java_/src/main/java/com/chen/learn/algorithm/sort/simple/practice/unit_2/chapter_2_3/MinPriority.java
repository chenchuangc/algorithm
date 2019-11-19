package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3;

/**
 * Created by chencc on 2018/8/15.
 */

import com.chen.learn.algorithm.util.Helper;

import java.util.List;

/**
 *
 */
public class MinPriority<T extends Comparable> {

    T[] container;
    int len;
    int using_index;

    public MinPriority(int len) {
        this.len = len;
        this.using_index=-1;
        container = (T[]) new Comparable[len];
    }

    public MinPriority(List<T> edges) {
        this.len=edges.size();
        this.using_index=-1;
        container = (T[]) new Comparable[len];
        edges.forEach(this::insert);
    }

    public void insert(T obj) {
        container[++using_index]=obj;
        swim(container, using_index);
    }

    private void swim(T[] container, int using_index) {
        while (using_index > 0) {
            int parent= (using_index-1)/2;
            if (container[using_index].compareTo(container[parent]) < 0) {
                Helper.exchange(container, using_index, parent);
                using_index = parent;
            } else {
                break;
            }
        }
    }

    public T delete() {
        Helper.exchange(container, 0, using_index);
        T toReturn = container[using_index];
        using_index--;
        sink(container, 0, using_index);
        return toReturn;
    }

    private void sink(T[] container, int index, int len) {
        while ((index * 2 + 1) <= len) {
            int little = index*2+1;
            if (little + 1 <= len && container[little].compareTo(container[little + 1]) > 0) {
                little++;
            }
            if (container[index].compareTo(container[little]) > 0) {
                Helper.exchange(container, index, little);
                index = little;
            } else {
                break;
            }
        }
    }

    public boolean isEmpty() {
        return using_index<0;
    }

    public static void main(String[] args) {
        MinPriority minPriority = new MinPriority(20);
        minPriority.insert(92);
        minPriority.insert(22);
        minPriority.insert(22);
        minPriority.insert(33);
        minPriority.insert(19);
        minPriority.insert(22);
//        minPriority.insert(22);

        System.out.println(minPriority.delete());
        System.out.println(minPriority.delete());
        System.out.println(minPriority.delete());
        System.out.println(minPriority.delete());
        System.out.println(minPriority.delete());
        System.out.println(minPriority.delete());
        System.out.println(minPriority.isEmpty());
//        System.out.println(minPriority.delete());

    }
}
