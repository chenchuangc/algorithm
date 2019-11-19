package com.chen.learn.algorithm.fee_learn.design_pattern.package_2.m2_1_niudun;


import java.util.function.Function;

/**
 * Created by chencc on 2018/9/25.
 */
public class BinaryIterator {

    static double fun(double x) {
        return  (2*x*x +3.2*x - 1.8);
    }

    static double find(double low, double hi, Function<Double,Double> function) {
        double precise = 0.00001;
        double mid = (low+hi)/2;
        while (hi - low > precise) {
            if (function.apply(mid) * function.apply(low)>0) {
                low=mid;
                mid=(low+hi)/2;
            } else if (function.apply(mid) * function.apply(low) < 0) {
                hi = mid;
                mid = (low + hi) / 2;
            } else {
                return mid;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println(find(0, 1, BinaryIterator::fun));
        System.out.println(find(0,3,x-> x*x+x-6));
    }
}
