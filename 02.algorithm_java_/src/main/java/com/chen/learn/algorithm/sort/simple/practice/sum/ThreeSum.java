package com.chen.learn.algorithm.sort.simple.practice.sum;

import com.chen.learn.algorithm.sort.simple.Merge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencc on 2018/5/24.
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] seed = new int[]{1,  -1,0,0,0, 2, 5, -3, 8, -9, 6, 7,  -4, 3};

        System.out.println(find3Sum(seed));
        System.out.println(improveFind3Sum(seed));
    }

    /**
     * 达到了平方级别的求解方式
     * <p>
     * 需要注意的是为了避免重复和加快迭代而增强的功能而加入的 数组游标移动 不能出现越界的情况
     * <p>
     * 看来有序有时候能够起到很大的作用啊
     * <p>
     * 这个是有bug的，产生的结果会导致[0,0,0]这种情况会被忽略掉
     *
     * @param src
     * @return
     */
    public static List<List<Integer>> find3Sum(int[] src) {
        Merge.sort(src);
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        for (int i = 0; i < src.length - 2; i++) {
            while (src[i] == src[i + 1]) {
                i++;
            }
            int current = src[i];
            int low = i + 1;
            int hi = src.length - 1;
            while (low < hi) {
                int oneSum = src[low] + src[hi];
                if (oneSum == -current) {
                    List<Integer> one = new ArrayList<Integer>();
                    one.add(src[low]);
                    one.add(src[hi]);
                    one.add(current);
                    all.add(one);
                    while (low < hi && src[low] == src[low + 1]) {
                        low++;
                    }
                    low++;
                    while (low < hi && src[hi] == src[hi - 1]) {
                        hi--;
                    }
                    hi--;
                } else if (oneSum < -current) {
                    while (low < hi && src[low] == src[low + 1]) {
                        low++;
                    }
                    low++;
                } else {
                    while (low < hi && src[hi] == src[hi - 1]) {
                        hi--;
                    }
                    hi--;
                }
            }
        }
        return all;
    }

    /**
     * 这个只是把过滤移动了一下，就完成了任务，[0,0,0]会被收录进来了
     * @param src
     * @return
     */
    public static List<List<Integer>> improveFind3Sum(int[] src) {

        Merge.sort(src);
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        for (int i = 0; i < src.length - 2; i++) {
            int current = src[i];
            int low = i + 1;
            int hi = src.length - 1;
            while (low < hi) {
                int oneSum = src[low] + src[hi];
                if (oneSum == -current) {
                    List<Integer> one = new ArrayList<Integer>();
                    one.add(src[low]);
                    one.add(src[hi]);
                    one.add(current);
                    all.add(one);
                    while (low < hi && src[low] == src[low + 1]) {
                        low++;
                    }
                    low++;
                    while (low < hi && src[hi] == src[hi - 1]) {
                        hi--;
                    }
                    hi--;
                } else if (oneSum < -current) {
                    while (low < hi && src[low] == src[low + 1]) {
                        low++;
                    }
                    low++;
                } else {
                    while (low < hi && src[hi] == src[hi - 1]) {
                        hi--;
                    }
                    hi--;
                }
            }
            while (src[i] == src[i + 1]) {
                i++;//这个地方写的很好，对比4sum的话写的很好
            }
        }
        return all;
    }
}



