package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.max;

/**
 * Created by chencc on 2018/9/17.
 */
public class LCS_memory {

    public static int longestCommon(String str_a, String str_b) {
        Map<String, Integer> memoryData = new HashMap<>();
        char[] a_array = str_a.toCharArray();
        char[] b_array = str_b.toCharArray();
        return f(a_array, a_array.length - 1, b_array, b_array.length - 1, memoryData);


    }

    /**
     * 这里使用may加了一个备忘录，用来简化算法，使得算过的子集不用再计算了，节约计算资源
     * @param a_array
     * @param i
     * @param b_array
     * @param j
     * @param memoryData
     * @return
     */
    private static int f(char[] a_array, int i, char[] b_array, int j, Map<String, Integer> memoryData) {
        if (i < 0 || j < 0) {
            return 0;
        }
        String key = i + "_" + j;
        Integer val = memoryData.get(key);
        if (null != val) {
            return val;
        } else {
            if (a_array[i] == b_array[j]) {
                val= 1 + f(a_array, i - 1, b_array, j - 1,memoryData);
            } else {
                val= max(f(a_array, i, b_array, j - 1,memoryData), f(a_array, i - 1, b_array, j,memoryData));
            }
            memoryData.put(key, val);
            return val;
        }
    }

    public static void main(String[] args) {
        System.out.println(longestCommon("kkmhwodasfdsdetest","wodetestnidetestnihaotestdewomen"));
    }
}
