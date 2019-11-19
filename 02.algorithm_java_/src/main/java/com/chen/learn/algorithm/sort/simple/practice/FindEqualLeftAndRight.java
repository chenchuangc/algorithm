package com.chen.learn.algorithm.sort.simple.practice;

import com.chen.learn.algorithm.util.Helper;


/**
 * Created by chencc on 2018/5/9.
 * 找到有序数组中的 指定元素 target 在数组中最左边的元素以及最右边的元素
 */
public class FindEqualLeftAndRight {
    public static void main(String[] args) {
        int[] src = Helper.seedOrder(9);
        Helper.print(src);
        int target = src[6] ;
//        int left = findLeft(src, target);
//        int left = findLeftPos(src, src.length, target);
        int left = findMinLeft(src, target);
//        int right = findRight(src, target);
        int right = findRightPos(src, src.length, target);
        System.out.println("target: " + target + "  left:" + left + " rignt:" + right);
    }

    private static int findRight(int[] src, int target) {

        return findRight(src, 0, src.length - 1, target);
    }

    private static int findLeft(int[] src, int target) {

        return findLeft(src, 0, src.length - 1, target);
    }

    /**
     * r如何控制边界条件
     * 事实证明，这种边界条件控制其实是很难的
     *
     * @param src
     * @param low
     * @param high
     * @param target
     * @return
     */
    private static int findLeft(int[] src, int low, int high, int target) {

        if (low >= high) {
            if (src[high] == target) {
                return high;
            } else {
                return -1;
            }
        }
        int mid = (low + high) / 2;
        if (src[mid] < target) {
            return findLeft(src, mid + 1, high, target);
        } else {
            return findLeft(src, low, mid, target);
        }
    }

    /**
     * r如何控制边界条件
     * 在这里大概相似的边界条件失效了，
     * if (low >= high)
     * 原因是高位加地位，是无效的
     * (low+high)/2 < high在默写情况下是恒成立的
     * 考虑到如何更好的触发终止条件
     *
     * @param src
     * @param low
     * @param high
     * @param target
     * @return
     */
    private static int findRight(int[] src, int low, int high, int target) {

        if (low >= high) {
            if (src[low] == target) {
                return high;
            } else {
                return -1;
            }
        }
        if (low == (high - 1)) {
            if (src[low] == target && src[high] > target) {
                return low;
            }
        }
        int mid = (low + high) / 2;
        if (src[mid] > target) {
            return findRight(src, low, mid - 1, target);
        } else {
            System.out.println("low:" + mid + " high:" + high);
            return findRight(src, mid, high, target);
        }
    }

    /**
     * 从网上看到的
     *
     * @param arr
     * @param n
     * @param tar
     * @return
     */
    static int findLeftPos(int[] arr, int n, int tar) {

        int pos = -1;
        int lo = 0;
        int hi = n - 1;
        int mid = 0;
        if (lo < hi) {
            mid = (lo + hi) / 2;
            if (arr[mid] == tar) {
                pos = mid;
                hi = mid - 1;
            } else if (arr[mid] < tar) {
                lo = mid + 1;
            } else hi = mid - 1;
        }


//        // write code here
//        if(0 == n) return -1;
////        if(num == arr[0]) return 0;
//        int l = 0, r = n - 1, res = -1;
//        while(l <= r){
//            int mid = l + (r - l) / 2;
//            if(arr[mid] == num){
//                res = mid;
//                r = mid - 1;
//            }else if(arr[mid] < num){
//                l = mid + 1;
//            }else{
//                r = mid -1;
//            }
//        }
//        return res;
        return pos;
    }

    /**
     * 从网上看到的
     * 这个使用的是for循环的方式，引入了一个循环外面的变量，pos，
     * 这样挺好的，就把情况隔离开了，可以确定性的触发 lo>=hi 这个条件了
     * 像你用的迭代没有这个功能终止条件有可能达不到
     * 覆盖的也有可能不全面
     *
     * @param arr
     * @param n
     * @param num
     * @return
     */
    static public int findRightPos(int[] arr, int n, int num) {
        int pos = -1;
        if (n == 0) return pos;
        int lo = 0, hi = n - 1;
        int mid = 0;

        while (lo < hi) {
            mid = lo + (hi - lo) / 2; // avoid overflow
            if (arr[mid] == num) {
                pos = mid;
                lo = mid + 1;
            } else if (arr[mid] > num) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
            System.out.println("lo:" + lo + " hi:" + hi);
        }
        return pos;
    }


    public static int findMinLeft(int[] src, int target) {
        int pos = -1;
        int low = 0;
        int hi = src.length - 1;
        while (low <= hi) {
            int mid=(low+hi)/2;
            if (src[mid] == target) {
                pos=mid;
                hi=mid-1;
            } else if (src[mid] > target) {
                hi = mid - 1;
            } else {
                low=mid+1;
            }
        }
        return pos;
    }


}
