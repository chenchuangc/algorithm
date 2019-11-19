package com.chen.learn.algorithm.sort.simple.practice.stack;

/**
 * Created by chencc on 2018/5/10.
 */
public class FixCapacityStack {

    private String[] container;
    private int n;//当前数组中的数据

    public FixCapacityStack(int cap) {
        container = new String[cap];
        n=-1;
    }

    public void push(String ele) {
        container[++n]=ele;
    }

    public String pop() {
        return container[n--];
    }

    public boolean isEmpty() {
        return n == -1;
    }


    public static void main(String[] args) {
        FixCapacityStack stack = new FixCapacityStack(8);
        stack.push("aaa");
        stack.push("bbb");
        stack.push("ccc");
        stack.push("eee");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
