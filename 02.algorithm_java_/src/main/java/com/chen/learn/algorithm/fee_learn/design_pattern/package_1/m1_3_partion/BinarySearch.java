package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_3_partion;


/**
 * Created by chencc on 2018/9/21.
 * 二分查找，也算是分治法的典型案例
 * <p>
 * 分解 解决  合并
 * 分解：取中间点，分成两段
 * 解决：命中即解决
 * 合并：无
 * <p>
 * 边界是：low>hi 这个时候就不用再进行计算了，延后性边界
 * <p>
 * 需要叠加递归进行多个层次的搜索
 *
 * 二分查找又被称为减治法，效率相对更高。
 */
public class BinarySearch {

    /**
     * 二分查找，看来是记住了
     *
     * @param src
     * @param target
     * @return
     */
    public static int get(int[] src, int target) {
        int low = 0;
        int hi = src.length - 1;
        //边界必须要特别注意才行
        while (low <= hi) {
            int mid = (low + hi) / 2;
            int cp = src[mid] - target;
            if (cp == 0) {
                return mid;
            } else if (cp > 0) {
                hi = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 递归的实现方式
     * @param src
     * @param target
     * @param low
     * @param hi
     * @return
     */
    public static int get(int[] src, int target, int low, int hi) {
        if (low > hi) {
            return -1;
        }
        if (low == hi) {
            if (src[low] == target) {
                return low;
            } else {
                return -1;
            }
        }
        int mid = (low + hi) / 2;
        if (src[mid] == target) {
            return mid;
        } else if (src[mid] > target) {
            return get(src, target, low, mid - 1);
        } else {
            return get(src, target, mid + 1, hi);
        }

    }


    public static int searchDes(int[] src, int target, int low, int hi) {
        while (low <= hi) {
            int mid = (hi+low)/2;
            if (src[mid] == target) {
                return mid;
            } else if (src[mid] < target) {
                hi = mid - 1;
            } else {
                low=mid+1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] src = new int[]{12, 15, 56, 78, 231, 789, 1234, 4567};
        System.out.println(get(src, 456));
        System.out.println(get(src, 15, 0, src.length - 1));
    }
}
