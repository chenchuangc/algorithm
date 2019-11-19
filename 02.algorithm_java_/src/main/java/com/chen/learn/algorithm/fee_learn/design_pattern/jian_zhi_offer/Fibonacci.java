package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/14.
 */
public class Fibonacci {

    public static int get(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return get(n - 1) + get(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(get(4));

    }
}
