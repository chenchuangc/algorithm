package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/10/14.
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分。
 * 这种无序的使用双指针是最好的
 */
public class ArrayMoveWithoutOrder {
    public static void reOrderArray(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            while (left < right&&array[right] % 2 == 0) right--;
            while (left < right&&array[left] % 2 == 1) left++;
            if (left < right) {
                Helper.exchange(array, left, right);
            }
        }

    }

    public static void main(String[] args) {
        int[] src=new int[]{1, 2, 3, 4, 5};
        reOrderArray(src);
        Helper.print(src);
    }

}
