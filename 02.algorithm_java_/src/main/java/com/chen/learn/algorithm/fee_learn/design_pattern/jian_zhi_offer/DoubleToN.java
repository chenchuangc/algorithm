package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/10/14.
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class DoubleToN {
    public static double Power(double base, int exponent) {

        if (exponent == 0) {
            return 1;
        }
        int n = (int) Helper.toPositive(exponent);
        double res=base;
        int i=2;
        for(;i<=n;i*=2) {
            res*=res;
        }
        if (i/2 < n) {
            res= res * Power(base, n - (i / 2));
        }
        return exponent>0?res:1/res;
    }

    public static void main(String[] args) {
        System.out.println(Power(2, 3));
    }
}
