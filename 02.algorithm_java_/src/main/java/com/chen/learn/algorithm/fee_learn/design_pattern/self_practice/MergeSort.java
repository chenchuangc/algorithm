package com.chen.learn.algorithm.fee_learn.design_pattern.self_practice;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/9/25.
 * 理解归并
 * 使用了分治的设计模式
 * 分解，解决，合并结果 三个阶段
 * 分解简单，解决使用了多层递归， 合并结果复杂
 * 然后在多个层次上进行分治，形成递归
 *
 * 关于时间复杂度可以理解为
 * 总共分解了 log2->n 层，每一层的merge耗时都是n  所以时间复杂度是 nlgn
 *
 */
public class MergeSort {


    public static void mergeSort(int[] src) {
        int low=0;
        int hi=src.length-1;
        int[] temp = new int[src.length];
        mergeSort(src, low, hi,temp);
    }

    private static void mergeSort(int[] src, int low, int hi, int[] temp) {

        if (low >= hi) {
            return;
        }
        int mid=(low+hi)/2;
        mergeSort(src, low, mid, temp);
        mergeSort(src, mid + 1, hi, temp);
        merge(src, low, mid, hi, temp);

    }

    private static void merge(int[] src, int low, int mid, int hi, int[] temp) {
        int leftLow = low;
        int rLow = mid+1;
        int tempLow = low;
        while (leftLow <= mid && rLow <= hi) {
            if (src[leftLow] < src[rLow]) {
                temp[tempLow++] = src[leftLow++];
            } else {
                temp[tempLow++] = src[rLow++];
            }
        }
        while (leftLow <= mid) {
            temp[tempLow++] = src[leftLow++];
        }
        while (rLow <= hi) {
            temp[tempLow++] = src[rLow++];
        }
        //细节很重要，该死，在这个地方又把等号给遗漏了
        for (int i=low;i<=hi;i++) {
            src[i] = temp[i];
        }
    }


    public static void main(String[] args) {
        int[] source = Helper.seed(20);
        Helper.print(source);
        mergeSort(source);
        Helper.print(source);
    }
}
