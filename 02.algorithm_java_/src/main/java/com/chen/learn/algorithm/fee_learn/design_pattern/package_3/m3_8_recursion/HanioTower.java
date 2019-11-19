package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_8_recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencc on 2018/11/17.
 */
public class HanioTower {


    public static void move(int n, int one, int two, int third, List<String> stringStack) {
        if (n == 1) {
            stringStack.add("move :" + one + " -> " + third);
            return;
        }
        move(n - 1, one, third, two, stringStack);
        stringStack.add("move : " + one + " -> " + third);
        move(n - 1, two, one, third, stringStack);

    }

    public static void main(String[] args) {
        List<String> collector = new ArrayList<>();
        move(4, 1, 2, 3, collector);
       /* while (!collector.isEmpty()) {
            System.out.println(collector.pop());
        }*/
       collector.forEach(System.out::println);
    }

}
