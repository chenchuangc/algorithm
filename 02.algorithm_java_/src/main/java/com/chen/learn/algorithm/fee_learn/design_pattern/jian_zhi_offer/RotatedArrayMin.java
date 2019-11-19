package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/14.
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class RotatedArrayMin {

    public static int findMin(int[] array) {
        if (null == array || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        int mark = array[0];
        int low=0;
        int hi=array.length-1;
        int mid=0;
        while (low <= hi) {
            mid=(low+hi)/2;
            if (array[mid] > mark) {
                if (array[mid + 1] < mark) {
                    return array[mid + 1];
                } else {
                    low = mid + 1;
                }
            } else {
                hi=mid-1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3,4,5,1,2}));
    }
}
