package com.chen.learn.algorithm.fee_learn.design_pattern.self_practice;

import com.chen.learn.algorithm.util.Helper;

import java.util.ArrayList;

/**
 * Created by chencc on 2018/9/24.
 */
public class TwoDegreeaArrayRightRoated_Iterator {
//
//    public static void see(Integer[][] arr, int left_col, int top, int right_col, int button, List<Integer> collector) {
//
//        if (left_col > right_col || top > button) {
//            return;
//        }
//        int templeft = left_col;
//        while (templeft <= right_col) {
//            collector.add(arr[top][templeft++]);
//        }
//        int tempTop = top;
//        while (tempTop < button) {
//            collector.add(arr[++tempTop][right_col]);
//        }
//
//        int tempright = right_col - 1;
//        /**
//         * 一开始忘掉了子数组只有一行或者一列的情况，导致调试结果失败
//         */
//        while (button != top && tempright >= left_col) {
//            collector.add(arr[button][tempright--]);
//        }
//
//        int tempbutton = button - 1;
//        while (left_col != right_col && tempbutton > top) {
//            collector.add(arr[tempbutton--][left_col]);
//        }
//        see(arr, left_col + 1, top + 1, right_col - 1, button - 1, collector);
//    }


    public static void see(int[][] arr, int left_col, int top, int right_col, int button, ArrayList<Integer> collector)
    {

        if (left_col > right_col || top > button) {
            return;
        }
        int templeft = left_col;
        while (templeft <= right_col) {
            collector.add(arr[top][templeft++]);
        }
        int tempTop = top;
        while (tempTop < button) {
            collector.add(arr[++tempTop][right_col]);
        }

        int tempright = right_col - 1;
        /**
         * 一开始忘掉了子数组只有一行或者一列的情况，导致调试结果失败
         */
        while (button != top && tempright >= left_col) {
            collector.add(arr[button][tempright--]);
        }

        int tempbutton = button - 1;
        while (left_col != right_col && tempbutton > top) {
            collector.add(arr[tempbutton--][left_col]);
        }
        see(arr, left_col + 1, top + 1, right_col - 1, button - 1, collector);
    }


    public static void main(String[] args) {
//        int[][] aa = {
//                {3, 2, 1, 8}, {2, 3, 4, 7}, {4, 5, 6, 9},{8,9,6,7},{4,5,6,7}
//        };
        int[][] aa={{1}};
        for (int[] integers : aa) {
            Helper.print(integers);
        }
        ArrayList<Integer> kk = new ArrayList<>();

        see(aa, 0, 0, aa[0].length-1, aa.length-1, kk);
        kk.forEach(e-> System.out.print(e+" ,"));

    }

}
