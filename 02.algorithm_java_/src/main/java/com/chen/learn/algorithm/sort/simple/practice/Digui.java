package com.chen.learn.algorithm.sort.simple.practice;

/**
 * Created by chencc on 2018/5/2.
 */
public class Digui {

    public static void main(String[] args) {

//        System.out.println(mystery(3, 26));
        System.out.println(exR1(6));

    }

    private static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b/2);
        }
        return mystery(a + a, b / 2) + a;
    }

    public static String exR1(int n)
    {
        if (n <= 0) return "";
        return exR1(n-3) + n + exR1(n-2) + n;
    }
}
