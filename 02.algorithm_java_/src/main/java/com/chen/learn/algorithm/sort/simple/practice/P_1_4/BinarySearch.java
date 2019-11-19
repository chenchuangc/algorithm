package com.chen.learn.algorithm.sort.simple.practice.P_1_4;

/**
 * Created by chencc on 2018/6/17.
 */
public class BinarySearch {
    public static int searchAsc(int[] src, int target, int low, int hi) {

        while (low <= hi) {
            int mid = (low+hi)/2;
            if (src[mid] == target) {
                return mid;
            } else if (src[mid] < target) {
                low = mid + 1;
            } else {
                hi=mid-1;
            }
        }
        return -1;
    }
    public static int searchDesc(int[] src, int target, int low, int hi) {

        while (low <= hi) {
            int mid = (low+hi)/2;
            if (src[mid] == target) {
                return mid;
            } else if (src[mid] < target) {
                hi=mid-1;

            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
