package com.chen.learn.algorithm.sort.simple.practice;

import com.chen.learn.algorithm.util.Helper;

public class FindSmallAndEqualNum {

    public static void main(String[] args) {
        int[] src = Helper.seedOrder(34);
        Helper.print(src);

        int a = getSmallNum(src, 40);
        int b = getEqualNum(src, 50);
        System.out.println(a);
        System.out.println(b);

    }

    /**
     * 先找到等于的最左边的一个
     * @param src
     * @param i
     * @return
     */
    private static int getEqualNum(int[] src, int i) {
        return 7;

    }

    private static int getSmallNum(int[] src, int target) {

        return getSmallNum(src, 0, src.length - 1, target);
    }

    private static int getSmallNum(int[] src, int low, int high, int target) {
        if (low > high) {
           return high+1;
        }
        int mid = (low+high)/2;
        if (src[mid] >= target) {
            return getSmallNum(src, low, mid - 1, target);
        } else {
            return getSmallNum(src, mid + 1, high, target);
        }
    }
}
