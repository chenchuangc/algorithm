package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_4_iterator;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/9/23.
 * 算术平方根的求法
 * 迭代公式为 xi=1/2 * (x0 + a/x0)
 */
public class Kaifang {

    /**
     * 开平方根的求解
     * @param a
     * @param esp
     * @return
     */
    public static double kai(double a, double esp) {
        int max=50;
        int count=0;
        double x0=a/2;
        double xi;
        do {
            count++;
            xi = (x0 + a / x0)/2;
            double temp = x0;
            x0=xi;
            xi=temp;
            if (count > max) {
                break;
            }

            //这个地方一开始处理的不严禁，没有考虑到负值的情况，导致算法频频失效
        } while (Helper.toPositive( x0 - xi) > esp);

        return x0;
    }

    /**
     * 下面这个代码的简洁力度是大大优于上面的 代码的。加油
     * @param a
     * @param esp
     * @return
     */
    public static double kaiBetter(double a, double esp) {
        int max=50;
        int count=0;
        double x0=a/2;
        double xi;
        do {
            xi = x0;
            x0 = (xi + a / xi) / 2;
            if (count > max) break;
        } while (Helper.toPositive(x0 - xi) > esp);
        return x0;
    }

    public static void main(String[] args) {
        double res = kai(8, 0.01);
        System.out.println(res);
        System.out.println(res * res);
        System.out.println(kaiBetter(8, 0.01));
    }

}
