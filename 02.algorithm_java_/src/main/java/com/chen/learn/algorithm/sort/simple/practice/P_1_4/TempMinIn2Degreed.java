package com.chen.learn.algorithm.sort.simple.practice.P_1_4;

import com.chen.learn.algorithm.util.Helper;
import com.sun.org.apache.regexp.internal.RE;

/**
 * 这个题是求二维矩阵的局部最小值
 * 听说有两个算法，
 * 1. 滚下山算法，
 * 先求出每列的最小值，然后就像求一维数组的最小值那样进行二分迭代，这个设计也是挺有意思的，就是要满足
 *
 *这个感觉都是求取多解中的一个，并不是有最优解之类的，所以还是要对实际的问题进行分析
 *
 */
public class TempMinIn2Degreed {


    public static void main(String[] args) {

        int[][] src = new int[][]{
                {50, 10, 90},
                {30, 9, 60},
                {12, 89, 14},
                {45, 56, 34}};
        Helper.print(src[1]);
        System.out.println(src.length);
        System.out.println("------------");
        System.out.println(findTempMinHillWay(src));

    }
    /**
     * 该算法的实现是
     * 1. 选取中间的一列，找到
     * @param src
     * @return
     */
    public static PathIndex findTempMinHillWay(int[][] src) {

        int row_low=1;
        int row_hi=src.length-2;
        while (row_low <= row_hi) {
            int row_mid_index = (row_hi+row_low)/2;
            int min_index = findMinOneIndex(src[row_mid_index]);
            System.out.println("row:"+row_mid_index+"   column:"+min_index);
            int top = src[row_mid_index-1][min_index];
            int bottom = src[row_mid_index+1][min_index];
            int current = src[row_mid_index][min_index];

            if (current > top) {
                row_hi = row_mid_index-1;
            } else if (current > bottom) {
                row_low = row_mid_index + 1;
            } else {
                return new PathIndex(row_mid_index, min_index);
            }

        }

        return null;

    }

    private static int findMinOneIndex(int[] ints) {
        int min_index = 0;
        for (int i=1;i<ints.length;i++ ) {
            if (ints[i] < ints[min_index]) {
                min_index=i;
            }
        }
        return min_index;
    }


    private static class PathIndex {
        public PathIndex(int row, int column) {
            this.row = row;
            this.column = column;
        }

        int row;
        int column;

        @Override
        public String toString() {
            return "PathIndex{" +
                    "row=" + row +
                    ", column=" + column +
                    '}';
        }
    }
}
