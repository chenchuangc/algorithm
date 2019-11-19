package com.chen.learn.algorithm.sort.simple.practice;

/**
 * Created by chencc on 2018/5/8.
 */
public class IntegerToBinaryT {
    public static void main(String[] args) {
        int test =8;
        String s = toBinary(test);
        System.out.println(s);
    }

    private static String toBinary(int test) {
        StringBuilder builder = new StringBuilder();
        for (int i=test;i>0;i/=2) {
            builder.append(i % 2);
        }
        return builder.reverse().toString();
    }
}
