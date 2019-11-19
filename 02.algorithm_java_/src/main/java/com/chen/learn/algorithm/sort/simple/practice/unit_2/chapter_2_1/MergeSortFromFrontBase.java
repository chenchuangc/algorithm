package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_1;

/**
 * Created by chencc on 2018/8/13.
 */

import com.chen.learn.algorithm.util.Helper;

/**
 * 动态规划的归并排序
 */
public class MergeSortFromFrontBase {

    public static void sort(int[] src) {
        int group_len=1;
        int[] temp = new int[src.length];
        while (group_len < src.length) {

            int index=0;
            while (index < src.length) {
                int temp_end = index+2*group_len;
                if (index + group_len >= src.length) {
                    index=src.length;
                    continue;
                }
                if (temp_end > src.length) {
                    temp_end=src.length;
                }
                merge(src, index, index + group_len, temp_end,temp);
                index+=group_len*2;
            }
            group_len=group_len*2;
        }
    }

    private static void merge(int[] src, int lfstart, int ristart, int temp_end,int[] temp) {

        int low = lfstart;
        int start = lfstart;
        int lfend = ristart-1;
        while (lfstart <= lfend && ristart < temp_end) {
            if (src[lfstart] < src[ristart]) {
                temp[start++] = src[lfstart++];
            } else {
                temp[start++] = src[ristart++];
            }
        }
        while (lfstart <= lfend) {
//            System.out.println(start);
            temp[start++] = src[lfstart++];
        }
        while (ristart < temp_end) {
            temp[start++] = src[ristart++];
        }
        while (low < temp_end) {
            src[low] = temp[low++];
        }

    }

    public static void main(String[] args) {
//        int[] src = Helper.seed(18);
        int[] src = new int[]{24,1672,1643,1643,1643,1643,1643,1643,1643,2286,2146,2294,874,2117,982,1382,102,548,417,678,1582,1978,950,815,2033,1782,307,683,299,1032,1643,1496,1364,1610,1830,1958,1852,2437,169,909,811,182,2457,262,1497,177,1216,1125,1263,2203,221};
        Helper.print(src);
        sort(src);
        Helper.print(src);
    }
}
