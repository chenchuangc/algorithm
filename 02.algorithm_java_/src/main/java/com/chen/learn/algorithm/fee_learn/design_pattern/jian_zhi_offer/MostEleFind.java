package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import java.util.Stack;

/**
 * Created by chencc on 2018/10/31.
 */
public class MostEleFind {

    public static int find(int[] ele) {
        Stack<Integer> container = new Stack<>();
        for (int e : ele) {
            if (container.isEmpty()) {
                container.push(e);
                continue;
            }
            if (container.peek().equals(e)) {
                container.push(e);
            } else {
                container.pop();
            }
        }
        return container.peek();
    }

    public static void main(String[] args) {
        System.out.println(find(new int[]{3,4,5,6,5,7,7,7,7,7,5,5,5,5,5,5,5}));
    }
}
