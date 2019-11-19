package com.chen.learn.algorithm.sort.simple.practice.P_1_4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencc on 2018/5/30.
 *
 * 求解局部最小
 * 这个求解要求最坏是 2logN
 * 但是题目的要求并不是那么的严格，也就是说并不一定是 a[i-1]>a[i]<a[i+1]
 * 也有可能是 a[0]<a[1] | a[len-1]<a[len-2]
 * 这样的话，整个题的难度下降了很多，当然，这个题本身还是有很有意思的，需要仔细思考
 * 可以画一个折线图，辅助理解
 *
 * 也就是说这个题肯定是有解的
 *
 * 使用的是二分法进行的求解
 */
public class TempMin {


    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3};
        System.out.println(tempMin(test));

    }

    /**
     * 这个是你写的局部最小值的求解，怎么说呢，感觉边界控制的不好
     * 代码不够干脆利落，很多地方都增加了res，所以觉得做的不好
     * 参考一下下面的做法，更干脆
     * @param src
     * @return
     */
    public static List<Integer> tempMin(int[] src) {

        int low=0;
        int hi = src.length-1;


        List<Integer> res = new ArrayList<Integer>();
        while (low < hi) {
            int mid = (hi + low) / 2;
            if (mid > 0 && mid < src.length - 1) {
                if ((src[mid - 1] > src[mid]) && (src[mid] < src[mid + 1])) {
                    res.add(src[mid - 1]);
                    res.add(src[mid]);
                    res.add(src[mid + 1]);
                    return res;
                } else if (src[mid] > src[mid - 1]) {
                    hi = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                res.add(src[mid]);
                return res;
            }
        }
        res.add(src[low]);
        return res;
    }

    /**
     * 这个的边界条件有待理解
     * 进行这样的操作在while循环中就已经确定了确实是存在 a[i-1]>a[i]<a[i+1]
     * 那么按照推演的话
     * @param src
     * @return
     */
    public static int improveTempMin(int[] src) {

        if (null == src) return -1;
        int len = src.length;
        if (len==1||src[0]<src[1]) return 0;
        if (src[len-1]<src[len-2]) return len-1;
        int low = 1 ,hi=len-2;
        while (low <= hi) {
            int mid=(low+hi)/2;
            if (src[mid - 1] < src[mid]) {
                hi=mid-1;
            } else if (src[mid] > src[mid + 1]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }
}

// [9,6,5,4,8]