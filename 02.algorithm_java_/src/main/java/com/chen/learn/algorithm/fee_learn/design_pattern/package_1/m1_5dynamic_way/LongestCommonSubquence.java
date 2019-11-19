package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;

import static java.lang.Integer.max;

/**
 * Created by chencc on 2018/9/17.
 * 最长子序列问题
 * 求解两个字符串的最长子序列问题
 */
public class LongestCommonSubquence {

    public static int longestCommon(String str_a, String str_b) {
        char[] char_a = str_a.toCharArray();
        char[] char_b = str_b.toCharArray();
        return f(char_a, char_a.length - 1, char_b, str_b.length() - 1);
    }

    /**
     * 这种实际上会导致不必要的重复计算，所以算不上比较好的解法
     * 只是一种简单的迭代
     * @param str_a
     * @param i
     * @param str_b
     * @param j
     * @return
     */
    private static  int f(char[] str_a, int i, char[] str_b, int j) {
        //边界
        if (i < 0 || j < 0) {
            return 0;
            //其中的一种状态
        } else if (str_a[i] == str_b[j]) {
            return 1 + f(str_a, i - 1, str_b, j - 1);
        } else {
            return max(f(str_a, i, str_b, j - 1), f(str_a, i - 1, str_b, j));
        }
    }

    public static void main(String[] args) {
        System.out.println(longestCommon("abbddsdf","aabbmmddsdfg"));
    }
}
