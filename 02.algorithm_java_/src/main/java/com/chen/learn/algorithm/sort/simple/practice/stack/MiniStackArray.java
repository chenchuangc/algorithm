package com.chen.learn.algorithm.sort.simple.practice.stack;

/**
 * Created by chencc on 2018/5/12.
 */
public class MiniStackArray<T extends Comparable<T>> {
    T[] ele ;
    int N;
    int currentMinIndex ;
    ArrayStack<Integer> minStack;

    public MiniStackArray(int cap) {
        ele = (T[]) new Comparable[cap];
        N=0;
        minStack = new ArrayStack<Integer>(cap);
        currentMinIndex=-1;
    }

    public void push(T e) {
        if (N >= ele.length) {
            resize();
        }
        ele[N++] = e;
        if (-1 == currentMinIndex) {
            currentMinIndex = N-1;
            minStack.push(currentMinIndex);
        } else {
            if (ele[currentMinIndex].compareTo(e) >= 0) {
                minStack.push(currentMinIndex);
                currentMinIndex=N-1;
            }
        }
    }

    public T pop() {

        if (N > 0) {
            T res = ele[--N];
            ele[N] = null;
            if (N <= currentMinIndex) {
                currentMinIndex=minStack.pop();
            }
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

    public T getMini() {
        return ele[currentMinIndex];
    }


    public static void main(String[] args) {
        MiniStackArray<String> stackArray = new MiniStackArray<String>(10);
        stackArray.push("dd ");
        stackArray.push("cd ");
        stackArray.push("ab ");
        stackArray.push("ed ");
        stackArray.push("aa ");
        stackArray.push("ad ");
        System.out.println(stackArray.getMini());
        stackArray.pop();
        System.out.println(stackArray.getMini());
        stackArray.pop();
        System.out.println(stackArray.getMini());
        stackArray.pop();
        System.out.println(stackArray.getMini());
        stackArray.pop();

    }

}
