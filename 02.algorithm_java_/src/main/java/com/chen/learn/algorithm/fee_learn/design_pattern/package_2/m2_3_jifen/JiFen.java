package com.chen.learn.algorithm.fee_learn.design_pattern.package_2.m2_3_jifen;


import java.util.function.Function;

/**
 * Created by chencc on 2018/10/8.
 */
public class JiFen {

    public static double calculate(double a, double b, Function<Double, Double> fx) {
        int n=400000;
        return calculateN(a, b, fx, n);
    }

    private static double calculateN(double a, double b, Function<Double, Double> fx, int n) {

        double step = (b-a) / n;
        double res =0.0;
        double left = fx.apply(a);
        double right = fx.apply(a + step);
        double pos=a;

        /**
         * 由于计算机的精度问题，这个地方需要谨慎处理
         * 其实可以直接使用n作为循环结束条件，那样的话就不需要再进行特殊判断了
         */
        while ((b-pos )>(step/2)) {
            res+=(left+right)/2*step;
            pos+=step;
            left=right;
            right = fx.apply(pos);

        }
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
        System.out.println(calculate(0, 3, x -> x));
        System.out.println(calculate(0, 3, x -> x*x));
        System.out.println(calculate(0, 3, x -> x*x*x));
        System.out.println(calculate(0, 3, x -> x*x*x*x));
        System.out.println(calculateNBetter(0, 3, x -> x*x*x*x,900000000));
    }
}
