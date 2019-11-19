package com.chen.learn.algorithm.sort.simple.practice;

import com.chen.learn.algorithm.util.Helper;

public class RemoveRepeatInOrderArray {

    /**
     * 去除有序数组中的重复项
     * 使用 双指针 解决
     * @param src
     */
    public static int removeRepeated(int[] src) {

        int i=0;
        int j=1;
        if (src.length <= 1) {
            return src.length;
        }
        for (; j < src.length; j++) {
            if (src[i] != src[j]) {
                src[++i] = src[j];
            }
        }
        return i;
    }

    public static void main(String[] args) {

        int[] src = Helper.seedOrder(10);
        Helper.print(src);
        int i = removeRepeated(src);
        Helper.print(src);
        System.out.println(i);
    }
}
