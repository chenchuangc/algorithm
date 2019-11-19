package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3;


import com.chen.learn.algorithm.util.Helper;

import java.util.HashMap;
import java.util.Map;

public class IndexMinPriority<Key extends Comparable> {

    private IndexPriorityElement[] keyArrCopy;
    private IndexPriorityElement[] keyArr;
    private int eleNum = 0;
    private int indexUsing;
    private int capacity;
    private Map<IndexPriorityElement, Integer> positionOfEle;


    public IndexMinPriority(int capacity) {
        positionOfEle = new HashMap<>();
        this.keyArrCopy = new IndexPriorityElement[capacity];
        this.keyArr = new IndexPriorityElement[capacity];
        this.eleNum = 0;
        this.capacity = capacity;
        this.indexUsing = -1;
    }

    public void insert(int index, Key key) {
        IndexPriorityElement element = new IndexPriorityElement(key);
        keyArrCopy[index] = element;
        keyArr[++indexUsing] = element;
        positionOfEle.put(element, indexUsing);
        eleNum++;
        swim(indexUsing);
    }

    private void swim(int using_index) {

        while (using_index > 0) {
            int parent = (using_index - 1) / 2;
            if (keyArr[using_index].compareTo(keyArr[parent]) < 0) {
                Helper.exchange(keyArr, using_index, parent);
                //exchange the index
                positionOfEle.put(keyArr[using_index], using_index);
                positionOfEle.put(keyArr[parent], parent);
                using_index = parent;
            } else {
                break;
            }
        }
    }


    public void change(int index, Key key) {
        keyArrCopy[index].setKey(key);
        sink(keyArr, positionOfEle.get(keyArrCopy[index]), indexUsing);
        swim(positionOfEle.get(keyArrCopy[index]));

    }

    public Key delete() {
        Helper.exchange(keyArr, 0, indexUsing);
        positionOfEle.put(keyArr[0], 0);
        positionOfEle.remove(keyArr[indexUsing]);
        Key toReturn = (Key) keyArr[indexUsing].getKey();
        indexUsing--;
        sink(keyArr, 0, indexUsing);
        return toReturn;
    }

    private void sink(IndexPriorityElement[] container, int index, int len) {
        while ((index * 2 + 1) <= len) {
            int little = index * 2 + 1;
            if (little + 1 <= len && container[little].compareTo(container[little + 1]) > 0) {
                little++;
            }
            if (container[index].compareTo(container[little]) > 0) {
                Helper.exchange(container, index, little);
                positionOfEle.put(container[index], index);
                positionOfEle.put(container[little], little);
                index = little;
            } else {
                break;
            }
        }
    }

    public boolean isEmpty() {
        return indexUsing < 0;
    }

    public static void main(String[] args) {
        IndexMinPriority<Integer> indexMin = new IndexMinPriority<>(10);
        indexMin.insert(0, 3);
        indexMin.insert(1, 345);
        indexMin.insert(2, 25);
        indexMin.insert(5, 215);
        indexMin.insert(6, 445);
        System.out.println(indexMin.delete());
        System.out.println(indexMin.delete());
        indexMin.insert(3, 65);
        indexMin.insert(8, 95);

        indexMin.change(6, 1);
        indexMin.change(8, 34);


        System.out.println(indexMin.delete());
        System.out.println(indexMin.delete());
        System.out.println(indexMin.delete());
        System.out.println(indexMin.delete());
        System.out.println(indexMin.delete());
//        System.out.println(indexMin.delete());

    }

}


//
//
//
/// **
//        * Created by chencc on 2018/8/24.
//        * 索引优先队列有一个什么特点呢，就是
//        * 1. 使用方会在insert的同时传进来一个 index值来追踪其传进来的值，方便后续可能产生的修改等操作
//        * 2. 所以要将这个index 和传进来的key进行关联性最终
//        * 3. 要保存的东西有三个 index 需要保存；   key 需要保存； 同时 index--> key的关系需要保存
//        *
//        * 因为index是 int型的，所以直接使用数组进行 存储  ， 同时还有一个类似数据库的关联表一样的，关联index 和 key ，这个其实可以使用反向索引，也就是一个素组 arr[index]= key 的数组下表
//        *
//        * 理解了逻辑，实现应该还好，之前总是囫囵吞枣，这次多用点心
//        *
//        * 在 更新key的时候需要通过key所在的cur找到 对应的 index ,从而更改他的值
//        *
//        */
// container[index]=key
// order[m]=index  //对order进行上浮，下沉
// reflect[index]=m  //这个用于反向的记录，在进行change操作的时候 对应的排序状态

//上面的这种是从网上看的那种，比较巧妙的思想，现在推理的话也能够推理出来了

//记录一下自己在当前类中的实现逻辑吧
// 需要将key 包装一下，成为一个引用类型
//container[m]=key  //直接在这个里面排序
// containerCopy[index]=key  // 在这个里面找对应关系 ，修改的时候直接在这里修改就行了
//这个时候主要是change面临了下沉操作，没有办法直接进行下沉操作了,除非去遍历container 找出来对应的下标 m  然后进行一次下沉或者上浮
// map : key=>m // 这个主要就是记录了 反向的数据 从 key=>m的一个记录 方便修改后进行再排序
//当然，我的复杂程度是大于人家的，但是好歹也算自己想出来的，加油





// orderIndex[n]=index
// indexArr[index]=n
//争取把这个想清楚
//
