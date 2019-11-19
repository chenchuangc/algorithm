package com.chen.learn.algorithm.sort.simple;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/4/3.
 */
public class QuickSort {

    /**
     * 快速排序的逻辑是
     * 1.任意选择一个座位对比标的，比他小的在他左边，比他大的在他右边
     * 2.往子任务方向进行划分
     * 3.终止条件是什么呢
     * @param src
     *
     * https://blog.csdn.net/fantasy0126/article/details/41714321
     */
    public static void sort(int[] src) {
        quick(src, 0, src.length - 1);
    }


    public static void quick(int[] src, int start, int end) {
        if (start >= end) {
            return;
        }
        int targetIndex= partition(src, start,  end);
        quick(src,start,targetIndex-1);
        quick(src, targetIndex + 1, end);
    }

    private static int partition(int[] src, int start, int end) {

        int target = src[start];
        int targetIndex = start;
        start++;
        while (end >= start) {
            while (src[end] >= target && end-- > start);
            while (src[start] < target && start++ < end);
            if (end > start) {
                Helper.exchange(src, end, start);
            }
        }
        Helper.exchange(src, targetIndex, end);
       return end;
    }

    public static void main(String[] args) {
        int[] source = Helper.seed(20);
//        source = new int[]{1992, 1992, 1992, 1992, 1081, 1400, 639, 1715, 184, 358, 1942, 1767, 378, 1409, 677, 1443, 1466, 190, 1503, 259};
        source = new int[]{1992, 1993};
        Helper.print(source);
        sort(source);
        Helper.print(source);
    }

}
