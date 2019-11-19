package com.chen.learn.algorithm.sort.simple;

import com.chen.learn.algorithm.util.Helper;

public class MaoPaoSort {

    /**
     * 冒泡排序的基本思想是
     * 每次比较找到现有的最大的
     * 有n个数的话，需要 (n-1)+...+1=n*n
     *
     *
     * @param source
     */
    public static void sort(int[] source) {

        int len = source.length;
        for (int i=len-1;i>0;i--) {
            for(int j=0;j<i;j++) {
                if (source[j] < source[j + 1]) {
                    Helper.exchange(source, j, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] source = Helper.seed(9);
        Helper.print(source);
        sort(source);
        Helper.print(source);
    }


}
