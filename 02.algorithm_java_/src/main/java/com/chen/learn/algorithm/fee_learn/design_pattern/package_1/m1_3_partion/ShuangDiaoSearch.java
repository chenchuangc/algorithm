package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_3_partion;

/**
 * Created by chencc on 2018/9/26.
 * 双调数组的查找
 * 这个是典型的分治法
 * 分解 ，解决， 合并
 * 分解：
 * 将一个数组按照中间的顶点分成两个数组进行查找，这个不是典型的分治法么，
 * 不要一上来就着急一下子解决，可以先套套这些模式，看看能不能套上一些
 * 重点在于如何寻找顶点
 *
 * 解决：在每个子数组中使用减治的二分查找，找到了就ok
 * 合并： 无需合并
 *
 * 这里假设是v型数组
 *
 */
public class ShuangDiaoSearch {

    public static int find(int[] src,int target) {
        int button = partion(src);
        if (src[button] == target) {
            return button;
        }
        int r1 = BinarySearch.searchDes(src, target, 0, button - 1);
        if (-1 == r1) {
            return BinarySearch.get(src, target, button + 1, src.length - 1);
        } else {
            return r1;
        }
    }

    private static int partion(int[] src) {

        int low=1;
        int hi = src.length - 2;
        int mid= (low+hi)/2;

        while (low <= hi) {
            if (src[mid] < src[mid + 1] && src[mid] < src[mid - 1]) {
                return mid;
            } else if (src[mid] < src[mid + 1]) {
                hi = mid-1;
                mid = (low + hi) / 2;
            } else {
                low=mid+1;
                mid=(low+hi)/2;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int[] src = {20,18,13,11,8,4,5,7,9,10,20};
        System.out.println(find(src, 1));
    }



}
