package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_1;

/**
 * Created by chencc on 2018/7/30.
 */

import com.chen.learn.algorithm.util.Helper;

/**
 * 希尔排序
 */
public class ShellSort {

    /**
     * shell中如何确定步长是一个问题
     * 现在看书中的应该也不是最优解，因为step定义的不是很对，可能导致多算
     *
     * @param src
     */
    public static void shellSort(int[] src) {
        int step = src.length;
        while (step > 1) {
            step = step / 3 + 1;//这一步确定了step最终是可以衰减到1的
            for (int i = 0; i < step; i++) {
                for (int j = i + step; j < src.length; j = j + step) {
                    for (int t = i; t < j; t = t + step) {
                        if (src[t] > src[j]) {
                            int temp = src[j];
                            while (j > t) {
                                src[j] = src[j = j - step];
                            }
                            src[t]=temp;
                        }
                    }
                }
            }

        }
    }

/*
复习一遍而已
    public static void tempRepeat(int[] src) {
        int step = src.length;
        while (step > 1) {
            step = step / 3 + 1;
            for (int i=0;i<step;i++) {//对应的是要进行多少组插入排序
                for (int j=i+step;j<src.length;j=j+step) {//这边是对于每一个插入排序的操作
                    for (int t=i;t<j;t=t+step) {//插入排序中每一个元素需要做的遍历
                        if (src[t] > src[j]) {
                            int temp = src[j];
                            while (j > t) {
                                src[j] = src[j = j - step];
                            }
                            src[t]=temp;
                        }
                    }
                }

            }
        }
    }
*/


    public static void temp(int[] src) {
        int len = src.length;
        while (len > 1) {
            len = len / 3 + 1;
            for (int begin=0;begin<len;begin++) {
                for (int j=begin+len;j<src.length;j+=len) {
                    for (int i=begin;i<j;i+=len) {
                        if (src[i] > src[j]) {
                            int tempMin = src[j];
                            while (j > i) {
                                src[j] = src[j - 1];
                                j--;
                            }
                            src[i]=tempMin;
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] src = Helper.seed(20);
        Helper.print(src);
        temp(src);
        Helper.print(src);
    }
}
