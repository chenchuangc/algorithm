package com.chen.learn.algorithm.sort.simple.practice.queue;

import com.chen.learn.algorithm.sort.simple.practice.stack.ArrayStack;

/**
 * 用有两个栈实现一个队列操作
 */
public class StackForQueue<T> {
    ArrayStack<T> pushStack = new ArrayStack<T>(50);
    ArrayStack<T> popStack = new ArrayStack<T>(50);

    public void enqueue(T ele) {
        pushStack.push(ele);
    }

    public T pop() {
        if (popStack.getSize() == 0) {
            while (pushStack.getSize() != 0) {
                popStack.push(pushStack.pop());
            }

        }
        return popStack.pop();
    }

}
