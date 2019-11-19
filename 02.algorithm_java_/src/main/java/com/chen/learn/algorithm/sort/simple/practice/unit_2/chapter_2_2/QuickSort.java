package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_2;

/**
 * Created by chencc on 2018/8/14.
 */

import com.chen.learn.algorithm.util.Helper;

/**
 * 快速排序
 */
public class QuickSort {

    public static void sort(int[] src) {
        quickSort(src, 0, src.length-1);


    }

    private static void quickSort(int[] src, int low, int hi) {
        if (low >= hi) {
            return;
        }
        int parttiion = partition(src, low, hi);
        quickSort(src,low,parttiion-1);
        quickSort(src,parttiion + 1, hi);
    }

    /**
     * 算法的路子真的有点千奇百怪，但是认真思考敢于去写，还是有收获的
     * 刚开始没有注意处理等于的情况,要注意处理等于的情况。
     * @param src
     * @param low
     * @param hi
     * @return
     */
    private static int partition(int[] src, int low, int hi) {

        int target_index=low;
        while (hi > low) {
            while (hi > low && src[hi] >= src[target_index]) {
                hi--;
            }
            while (hi > low && src[low] <= src[target_index]) {
                low++;
            }
            if (hi > low ) {
                Helper.exchange(src, hi, low);
            }
        }
        Helper.exchange(src, target_index, hi);
        return hi;

    }

    public static void main(String[] args) {
        int[] src = Helper.seed(5);
//        int[] src = new int[]{24,1672,1643,1643,1643,1643,1643,1643,1643,2286,2146,2294,874,2117,982,1382,102,548,417,678,1582,1978,950,815,2033,1782,307,683,299,1032,1643,1496,1364,1610,1830,1958,1852,2437,169,909,811,182,2457,262,1497,177,1216,1125,1263,2203,221};
        Helper.print(src);
        sort(src);
        Helper.print(src);
    }

}
