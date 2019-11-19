package com.chen.learn.algorithm.sort.simple;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/4/3.
 */
public class MergeAgain {


    public static void sort(int[] src) {
        int[] temp = new int[src.length];
        merge(src, temp, 0, src.length - 1);
    }

    public static void merge(int[] src, int[] temp, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        merge(src, temp, start, mid);
        merge(src, temp, mid + 1, end);
        mergeArr(src, temp, start, mid, end);
    }

    private static void mergeArr(int[] src, int[] temp, int start, int mid, int end) {
        int leftStart = start;
        int leftEnd = mid;
        int rightStart = mid+1;
        int rightEnd = end;
        int tempStart = start;
        while (leftEnd >= leftStart && rightEnd >= rightStart) {
            if (src[leftStart] <src[rightStart]) {
                temp[tempStart++] = src[leftStart++];
            } else {
                temp[tempStart++] = src[rightStart++];
            }
        }
        while (leftEnd >= leftStart) {
            temp[tempStart++] = src[leftStart++];
        }
        while (rightEnd >= rightStart) {
            temp[tempStart++] = src[rightStart++];
        }

        while (start <= end) {
            src[start] = temp[start++];
        }
    }

    public static void main(String[] args) {
        int[] source = Helper.seed(20);
        Helper.print(source);
        sort(source);
        Helper.print(source);
    }
}
