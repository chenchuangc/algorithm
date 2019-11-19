package com.chen.learn.algorithm.sort.simple.practice.stack;

import java.util.Iterator;

/**
 * 试图实现一个java的STACK
 */
public class ArrayStack<T> implements Iterator {

    int N;
    T[] ele;

    public ArrayStack(int cap) {
        N = 0;
        ele = (T[]) new Object[cap];
    }

    public void push(T e) {
        if (N >= ele.length) {
            resize();
        }
        ele[N++] = e;
    }

    public T pop() {

        if (N > 0) {
            T res = ele[--N];
            ele[N] = null;
            return res;
        } else {
            return null;
        }

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

    public static void main(String[] args) {
//        String[] mm = (String[]) new Object[10];
        ArrayStack<String> arrayStack = new ArrayStack<String>(2);
        arrayStack.push("aaaa");
        arrayStack.push("bb");
        arrayStack.push("cc");
        arrayStack.push("dd");
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());

        //这一句会报错，说明他是实际上会擦除掉泛型相关的信息，里面保存的是object类型的
        String[] aa = arrayStack.getEle();
        System.out.println("------");
//        System.out.println(arrayStack.pop());

    }

    public T peek() {
        if (N > 0) {
            T res = ele[N-1];
            return res;
        } else {
            return null;
        }
    }
}
