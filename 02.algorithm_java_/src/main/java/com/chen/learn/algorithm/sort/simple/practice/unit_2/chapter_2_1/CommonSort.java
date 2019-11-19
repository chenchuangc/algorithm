package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_1;

import com.chen.learn.algorithm.util.Helper;

/**
 * 普通排序排序
 * 这里主要记录
 * 选择排序
 * 冒泡排序
 * 插入排序
 *
 */
public class CommonSort {

    /**
     * 选择排序
     * @param src
     */
    public static void selectedSort(int[] src) {
        for (int i = 0; i < src.length; i++) {
            int tempMin = i;
            for (int j = i + 1; j < src.length; j++) {
                if (src[tempMin] > src[j]) {
                    tempMin=j;
                }
            }
            Helper.exchange(src, i, tempMin);
        }
    }

    /**
     * 冒泡排序
     * @param src
     */
    public static void swimSort(int[] src) {
        for (int j = src.length-1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (src[i] > src[i+1]) {
                    Helper.exchange(src, i, i + 1);
                }
            }
        }
    }

    /**
     * 插入排序，不适用额外的数组空间，只是使用这么多久ok了，原地的数组排序
     * @param src
     */
    public static void insertSort(int[] src) {
        for (int i = 1; i < src.length; i++) {
            for (int j = 0; j < i; j++) {
                if (src[i] < src[j]) {
                    int temp = src[i];
                    while (i > j) {
                        src[i] = src[i - 1];
                        i--;
                    }
                    src[i]=temp;
                }
            }
        }
    }



    public static void main(String[] args) {
        int[] src = Helper.seed(12);
        Helper.print(src);
        insertSort(src);
        Helper.print(src);

    }


}
