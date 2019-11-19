package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_1;

import com.chen.learn.algorithm.util.Helper;

/**
 * 归并排序
 */
public class MergeSort {

    public static void mergeSort(int[] src) {

        int low = 0;
        int high = src.length;
//        int mid = (low+high)/2;
        int[] temp = new int[high];
        sort(src, temp, low, high-1);
    }

    private static void sort(int[] src, int[] temp, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = (low+high)/2;
        sort(src,temp,low,mid);
        sort(src, temp, mid + 1, high);
        merge(src, temp, low, mid, high);
    }

    private static void merge(int[] src, int[] temp, int low, int mid, int high) {
        int lstart = low;
        int rstart = mid + 1;
        int tempLow = low;
        while (lstart <= mid && rstart <= high) {
            if (src[lstart] < src[rstart]) {
                temp[tempLow++] = src[lstart++];
            } else {
                temp[tempLow++] = src[rstart++];
            }
        }
        while (lstart <= mid) {
            temp[tempLow++] = src[lstart++];
        }
        while (rstart <= high) {
            temp[tempLow++] = src[rstart++];
        }

        while (low <= high) {
            src[low] = temp[low++];
        }

    }

    public static void main(String[] args) {
        int[] src = Helper.seed(14);
        Helper.print(src);
        mergeSort(src);
        Helper.print(src);
    }
}
