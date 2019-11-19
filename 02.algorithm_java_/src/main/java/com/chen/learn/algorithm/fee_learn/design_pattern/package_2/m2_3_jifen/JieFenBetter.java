package com.chen.learn.algorithm.fee_learn.design_pattern.package_2.m2_3_jifen;

import com.chen.learn.algorithm.util.Helper;

import java.util.function.Function;

/**
 * Created by chencc on 2018/10/9.
 */
public class JieFenBetter {
    public static double calculate(double a, double b, Function<Double, Double> function) {

        int step =10000;
        double precise=0.0000002;
        double res = calculateNBetter(a, b, function, step);
        double diff=0;
        do {
            double min = calculateNBetter(a, b, function, step * 2);
            diff = Helper.toPositive(min - res);
            res = min;
            step *= 2;
        } while (diff > precise);

        return res;
    }


    private static double calculateNBetter(double a, double b, Function<Double, Double> fx, int n) {
        double step = (b-a) / n;
        double res =0.0;
        double left = fx.apply(a);
        double right = fx.apply(a + step);
        /**
         * 由于计算机的精度问题，这个地方需要谨慎处理
         * 其实可以直接使用n作为循环结束条件，那样的话就不需要再进行特殊判断了
         */
        int i=1;
        while (i < n) {

            res += (left + right) / 2 * step;
            i++;
            left = right;
            right = fx.apply(a + i * step);

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculate(0, 3, x -> x*x*x*x));

    }
}
