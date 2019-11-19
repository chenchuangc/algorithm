package com.chen.learn.algorithm.fee_learn.design_pattern.self_practice;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/9/25.
 * 快排也可以看做是分治法
 * 分解，解决，合并
 * 分解的过程比较复杂，是要进行一定的移动，最后确定子问题的规模
 * 解决的过程是递归解决的
 * 合并的过程也省略了，分解的过程中已经保持了有序。
 */
public class QuickSort {

    public static void sort(int[] src) {
        int low = 0;
        int hi = src.length - 1;
        quickSort(src, low, hi);
    }

    private static void quickSort(int[] src, int low, int hi) {
        if (low >= hi) {
            return;
        }
        int partion = partion2(src, low, hi);
        quickSort(src, low, partion - 1);
        quickSort(src, partion + 1, hi);
    }

    /**
     * 这种方案实际上是经过了多次的debug得出的，
     * 并不容易直接从逻辑上进行推理，一旦做了特殊化处理，就引入了不一致性，就容易出现bug
     * @param src
     * @param low
     * @param hi
     * @return
     */
    private static int partion(int[] src, int low, int hi) {
        int target = low;
        System.out.println(src[target] + " low:" + low + " hi:" + hi);
        low++;

        while (low <= hi) {
            while (low <= hi && src[low] <= src[target]) low++ ;
            while (hi >= low && src[hi] > src[target]) hi-- ;
            if (hi > low) {
                Helper.exchange(src, low, hi);
            }
        }
        Helper.exchange(src, target, hi);
        Helper.print(src);
        return hi;
    }

    /**
     * 感觉这个的边界控制的更好，考虑一次while循环，
     * 内部的while循环的边界肯定是 hi==low 所以只能是 hi>low  low<hi 而不应该带等号
     * 所以迭代到最后肯定是low==hi 这个时候就要结束迭代了 所以外部循环的边界条件是 low<hi 而不是 low<=hi
     * @param src
     * @param low
     * @param hi
     * @return
     */
    private static int partion2(int[] src, int low, int hi) {
        int target = low;
//        System.out.println(src[target] + " low:" + low + " hi:" + hi);

        while (low < hi) {
            while (hi > low && src[hi] > src[target]) hi-- ;
            while (low < hi && src[low] <= src[target]) low++ ;
            if (hi > low) {
                Helper.exchange(src, low, hi);
            }
        }
        Helper.exchange(src, target, hi);
//        Helper.print(src);
        return hi;
    }


    /**
     * 这个方法是行不通的，不能保证low-1会不会越界
     * @param src
     * @param low
     * @param hi
     * @return
     */
    private static int partion3(int[] src, int low, int hi) {
        int target = low;
//        System.out.println(src[target] + " low:" + low + " hi:" + hi);

        while (low < hi) {
            while (low < hi && src[low] <= src[target]) low++ ;
            while (hi > low && src[hi] > src[target]) hi-- ;
            if (hi > low) {
                Helper.exchange(src, low, hi);
            }
        }
        //这个地方不能保证low-1会不会越出当前返回
        Helper.exchange(src, target, low-1);
//        Helper.print(src);
        return hi;
    }


    public static void main(String[] args) {
        int[] source = Helper.seed(20);
//        int[] source = new int[]{489,0 ,73,367,173,0 ,100};
        Helper.print(source);
        sort(source);
        Helper.print(source);
    }
}
