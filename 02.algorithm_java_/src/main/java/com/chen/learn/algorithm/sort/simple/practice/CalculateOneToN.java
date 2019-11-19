package com.chen.learn.algorithm.sort.simple.practice;

/**
 * Created by chencc on 2018/5/8.
 * 计算累乘
 */
public class CalculateOneToN {
    public static void main(String[] args) {
        int n = 5;
        int res = calculate(n);
        System.out.println(res);
    }

    private static int calculate(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * calculate(n - 1);
    }
}
