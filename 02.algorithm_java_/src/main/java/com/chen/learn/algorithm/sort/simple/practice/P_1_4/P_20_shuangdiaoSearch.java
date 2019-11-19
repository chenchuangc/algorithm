package com.chen.learn.algorithm.sort.simple.practice.P_1_4;

/**
 * Created by chencc on 2018/6/17.
 * 双调查找，
 * 就是先增加后减少或者先减少后增加的数据对一个数进行查找
 *
 * 第一步，找出中间极值，然后分别对两段运用二分查找法
 *
 *
 * 这个是要用分治法来实现的，分治的点就是双调
 *
 */
public class P_20_shuangdiaoSearch {

    public static void main(String[] args) {
        int[] src = {20,18,13,11,8,4,5,7,9,10,20};
        System.out.println(find(src, 11));
    }

    /**
     * 这里为v型数组
     *
     * @param src
     * @param target
     * @return
     */
    public static int find(int[] src, int target) {
        int index = findMinIndex(src);
        if (target < src[index]) {
            return -1;
        }
        int targetIndex = BinarySearch.searchDesc(src,target, 0, index);
        if (targetIndex == -1) {
            return BinarySearch.searchAsc(src, target,index + 1, src.length - 1);
        } else {
            return targetIndex;
        }

    }

    private static int findMinIndex(int[] src) {
        int low =1;
        int hi=src.length-2;
        while (low <= hi) {
            int mid = (low + hi) / 2;
            if (src[mid] < src[mid + 1] && src[mid] < src[mid - 1]) {
                return mid;
            } else if (src[mid] > src[mid + 1]) {
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        throw new RuntimeException("CAN BE THIS");
    }
}
