package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import java.util.Stack;

/**
 * Created by chencc on 2018/10/15.
 */
public class IsPopOrder {

    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<>();
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while ((!stack.isEmpty()) && (stack.peek() == popA[popIndex])) {
                stack.pop();
                popIndex++;
            }
        }
        if (popIndex == popA.length) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int[] pushA = new int[]{1, 2, 3, 4, 5};
        int[] popA = new int[]{4, 3, 5, 2, 1};
        System.out.println(IsPopOrder(pushA, popA));
    }
}
