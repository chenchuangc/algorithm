package com.chen.learn.algorithm.fee_learn.design_pattern.package_2.m2_1_niudun;

import com.chen.learn.algorithm.util.Helper;

import java.util.function.Function;

/**
 * Created by chencc on 2018/9/25.
 * niudu 迭代
 * k(x0-x)=y0  x=x0-y0/k
 * k=(y0-y0.$)/(x0-x0.$)
 */
public class NiudunIterator {


    static double fun(double x) {
        return (2 * x * x + 3.2 * x - 1.8);
    }

    static double getK(double x0, Function<Double, Double> function) {
        double step = 0.000005;
        return ((function.apply(x0 + step) - function.apply(x0 - step)) / (step * 2));
    }

    public static double find(double start, Function<Double, Double> function) {
        double precise = 0.00000001;
        double pre = start;
        double next = 0;
        while (true) {
            double k = getK(pre, function);
            next = pre - (function.apply(pre) / k);
            if (Helper.toPositive(next - pre) < precise) {
                break;
            }
            pre=next;
        }
        return next;
    }


    public static void main(String[] args) {
        System.out.println(find(0.5,NiudunIterator::fun));
        System.out.println(find(0.5,x->x*x+x-6));
        System.out.println(find(0.5,x->x*x+x-12));
    }
}
