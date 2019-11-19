package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_6search_all;

/**
 * Created by chencc on 2018/9/20.
 * 鸡兔同笼
 * 50个头，120个脚
 */
public class RabbitAndChicken {

    public static void rabbitAndChicken(int headNum, int feet) {
        int maxRabbit = headNum < feet / 4 ? headNum : feet / 4;
        int maxChicken = headNum < feet / 2 ? headNum : feet / 2;
        for (int r=0;r<maxRabbit;r++) {
            int ch = headNum-r;
            if (ch * 2 == (feet - r * 4)) {
                System.out.println("兔子：" + r + " 🐔：" + ch);
                break;
            }
        }
    }

    public static void main(String[] args) {
        rabbitAndChicken(50, 120);
    }
}
