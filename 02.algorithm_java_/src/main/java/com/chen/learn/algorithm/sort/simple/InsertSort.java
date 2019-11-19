package com.chen.learn.algorithm.sort.simple;

import com.chen.learn.algorithm.util.Helper;

public class InsertSort {

    /**
     * 插入排序的思想是
     * 从第一个元素开始，依次和后面的比较，一轮比较下来确定该元素在数组中的正确位置，
     * 也就是：该元素前面的都比他小，后面的都比他大
     * 错误，上面是说法是错误的，
     * 插入的逻辑是当前子序列是有序的，（第一个元素为初始有序子序列）
     * 后面每次插入一个，依然保证序列的有序性 n*n
     * @param source
     */
    public static void sort(int[] source) {

        Helper.print(source);
        for(int i=1;i<source.length;i++) {
            //这个地方可能会降低效率，很多都要比较一遍
            for (int j=0;j<i;j++) {
                if (source[j] > source[i]) {
                    insert(source, j, i);
                }
            }
        }
        Helper.print(source);
    }

    private static void insert(int[] source, int j, int i) {

        int temp = source[i];
        while (i > j) {
            source[i--] = source[i];
        }
        source[j]=temp;
    }

    public static void main(String[] args) {
        sort(Helper.seed(18));
    }
}
