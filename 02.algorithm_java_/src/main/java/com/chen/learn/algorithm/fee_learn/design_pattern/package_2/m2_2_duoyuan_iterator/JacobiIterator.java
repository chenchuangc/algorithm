package com.chen.learn.algorithm.fee_learn.design_pattern.package_2.m2_2_duoyuan_iterator;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/9/25.
 *
 * ## 这个案例不收敛
 * x+y=34;  ->  x=34-y;
 * x-y =20; ->  y=x-20;
 *
 *## 这个案例也不收敛
 * −2x+y+5z=15 x=0.5y+2.5z-7.5
 * 4x−8y+z=−21  y=0.5x+0.125z+21/8
 * 4x−y+z=7     z=y-4x+7
 *
 *## 这个收敛
 * 4x−y+z=7   x=7/4+0.25y-0.25z
 * 4x−8y+z=−21  y=21/8+0.5x+0.125z
 * −2x+y+5z=15  z=3+0.4x-0.2y
 */
public class JacobiIterator {

    public static double[] getRes(double[] seed, double[][] factor) {
        double[] next = new double[seed.length];
        double precise = 0.00000000000000001;
        int count=0;//用于记录总共迭代了多少次
        while (true) {
            count++;
            for (int i=1;i<seed.length;i++) {
                next[i]=factor[i-1][0];
                for (int j=1;j<seed.length;j++) {
                    if (j != i) {
                        next[i] += seed[j] * factor[i - 1][j];
                    }
                }
            }
            boolean ok=true;
            for (int i=1;i<seed.length;i++) {
                if (Helper.toPositive(next[i] - seed[i]) > precise) {
                    ok=false;
                    break;
                }
            }
            if (ok) {
                System.out.println("total----> "+count);
                break;
            }
            Helper.print(next);
            double[] temp = next;
            next=seed;
            seed=temp;
        }
        return next;
    }

    public static void main(String[] args) {
        Helper.print(getRes(new double[]{0, 1, 2, 2}, new double[][]{{(7/4d), 0, 0.25, -0.25}, {(21 / 8d), 0.5, 0, 0.125}, {3, 0.4, -0.2, 0}}));
    }
}
