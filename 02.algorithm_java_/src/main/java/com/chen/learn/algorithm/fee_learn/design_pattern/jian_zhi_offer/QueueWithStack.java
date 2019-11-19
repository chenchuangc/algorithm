package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import java.util.Stack;

/**
 * Created by chencc on 2018/10/14.
 */
public class QueueWithStack {

     Stack<Integer> stackin = new Stack<>();
     Stack<Integer> stackout = new Stack<>();

    public void push(Integer ele) {
        stackin.push(ele);
    }

    public Integer pop() {
        if (stackout.isEmpty()) {
            while (!stackin.isEmpty()) {
                stackout.push(stackin.pop());
            }
        }
        return stackout.pop();
    }


}
