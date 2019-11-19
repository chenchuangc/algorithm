package com.chen.learn.algorithm.sort.simple.practice.sum;

import com.chen.learn.algorithm.sort.simple.Merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要求是元素组不可重复，并不是元素不能重复，比如 如果数组是[0,1,2,0,-2] 那么(0,0)应该也是可以的，所以你的解法是有问题的
 */
public class TwoSumCalculate {

    public static void main(String[] args) {
        int[] src = new int[]{0, 0, 0,1, -2, -1, 4};
        System.out.println(towSum(src));
        System.out.println(anotherTwoSum(src));

    }


    /**
     * twosum 使用map的一种解法，时间复杂度是很小的o(N)
     * 空间复杂度是 o(N)
     *
     * 这个算法是有bug的，对于 new int[]{0, 0, 0,1, -2, -1, 4};  结果只是会有[1, -1]
     * 实际上应该有 [[-1, 1], [0, 0]]
     * 原因是因为使用hashmap掩盖了元素重复的可能
     * @param src
     * @return
     */
    @Deprecated
    public static List<List<Integer>> towSum(int[] src) {

        List<List<Integer>> all = new ArrayList<List<Integer>>();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer ele : src) {
            if (null == map.get(ele)) {
                map.put(ele, ele);
            }
        }
        for (Integer aEle : src) {
            int reflect = -aEle;
            map.remove(aEle);
            if (map.get(reflect) != null) {
                map.remove(reflect);
                ArrayList<Integer> one = new ArrayList<Integer>();
                one.add(aEle);
                one.add(reflect);
                all.add(one);
            }
        }
        return all;
    }



    /**
     * 使用有序结合
     * ~~二分查找来做~~ 这个效率相对来说更慢，
     * 有序以后使用双指针的方式相对来说更快  O(NlogN)
     * 主要的效率是在排序的时间上面了
     * 这个算法还是可以的，也就是元素重复是ok的，但是不会出现元组重复
     *
     * @param src
     * @return
     */
    public static List<List<Integer>> anotherTwoSum(int[] src) {
        Merge.sort(src);
        List<List<Integer>> all = new ArrayList<List<Integer>>();

        int low = 0;
        int hi = src.length - 1;
        while (low < hi) {
            int res = src[low] + src[hi];
            if (res == 0) {
                while (low < hi && src[low] == src[low + 1]) low++;
                while (low < hi && src[hi] == src[hi - 1]) hi--;
                all.add(buildList(src[low++], src[hi--]));
            } else if (res > 0) {
                while (low < hi && src[hi] == src[hi - 1]) hi--;
                hi--;
            } else {
                while (low < hi && src[low] == src[low + 1]) low++;
                low++;
            }
        }
        return all;
    }

    private static List<Integer> buildList(int a, int b) {
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(a);
        arr.add(b);
        return arr;
    }

}
