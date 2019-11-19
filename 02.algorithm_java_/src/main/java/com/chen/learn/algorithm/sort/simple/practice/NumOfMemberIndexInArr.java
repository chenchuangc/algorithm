package com.chen.learn.algorithm.sort.simple.practice;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/5/9.
 * 编写一个静态方法 histogram()，接受一个整型数组 a[] 和一个整数 M 为参数并返回一个大小
 为M的数组，其中第i个元素的值为整数i在参数数组中出现的次数。如果a[]中的值均在0到M-1
 之间，返回数组中所有元素之和应该和 a.length 相等。
 */
public class NumOfMemberIndexInArr {
    public static void main(String[] args) {
        int[] src = Helper.seed(20);
        int[] res = histogram(src, 30);
        Helper.print(src);
        Helper.print(res);
    }

    private static int[] histogram(int[] src, int i) {

        int[] arr = new int[i];
        int len = i-1;
        for (int temp : src) {
            if ((temp <= len) && temp >= 0) {
                arr[temp]++;
            }
        }
        return arr;
    }
}
