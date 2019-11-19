package com.chen.learn.algorithm.sort.simple.practice.bag;

import javax.swing.*;
import java.util.Iterator;
import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.random;

/**
 * Created by chencc on 2018/5/13.
 * 这个内部还是只能用数组来实现，要不然的话，没办法实现随机的特性
 */
public class RandomBag<T> implements Iterable<T>{

    int N;
    T[] ele;

    public RandomBag(int cap) {
        N = 0;
        ele = (T[]) new Object[cap];
    }

    public void add(T e) {
        if (N >= ele.length) {
            resize();
        }
        ele[N++] = e;
    }


    public int getSize() {
        return N;
    }

    private void resize() {
        int size = ele.length;
        T[] newOne = (T[]) new Object[size * 2];
        for (int i = 0; i < N; i++) {
            newOne[i] = ele[i];

        }
        ele = newOne;
    }


    public boolean hasNext() {
        return N > 0;
    }

    public Object next() {
        return ele[--N];
    }

    public void remove() {

    }

    public T[] getEle() {
        return ele;
    }

    public Iterator<T> iterator() {
        return new RandomBagIterator();
    }

    public class RandomBagIterator implements Iterator {
        Random random = new Random();
        int n;

        int[] randomIndexArray;
        public RandomBagIterator() {
            randomIndexArray = new int[getSize()];
            n=getSize();
            for (int i=0;i<randomIndexArray.length;i++) {
                randomIndexArray[i] = i;
            }
            randomArray(randomIndexArray);

        }

        private void randomArray(int[] randomIndexArray) {
            int len = randomIndexArray.length-1;
            for (int i=len;i>0;i--) {
                int j = random.nextInt(i);
                int temp = randomIndexArray[j];
                randomIndexArray[j]=randomIndexArray[i];
                randomIndexArray[i]=temp;
            }
        }

        public boolean hasNext() {
            return n>0;
        }

        public T next() {
            return ele[randomIndexArray[--n]];
        }

        public void remove() {

        }
    }


    public static void main(String[] args) {
        RandomBag<String> bag = new RandomBag<String>(3);
        bag.add("aa");
        bag.add("bb");
        bag.add("cc");
        bag.add("dd");
        Iterator<String> iterator = bag.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}
