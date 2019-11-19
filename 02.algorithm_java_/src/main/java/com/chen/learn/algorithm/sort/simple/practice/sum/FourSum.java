package com.chen.learn.algorithm.sort.simple.practice.sum;

import com.chen.learn.algorithm.sort.simple.Merge;

import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencc on 2018/5/28.
 * 这次可能是有了3sum的经验，基本上是一遍过，还是很棒的，加油
 */
public class FourSum {

    public static void main(String[] args) {
        int[] src = new int[]{ -8, -2,0, 0, 0, 0, 2, 5, 8, 9};
        List<List<Integer>> res = improveFind4Sum(src, 0);
        System.out.println(res);
    }


    public static List<List<Integer>> improveFind4Sum(int[] src,int sumTar) {

        Merge.sort(src);
        List<List<Integer>> all = new ArrayList<List<Integer>>();
        for(int index_1=0;index_1<src.length-3;index_1++) {
            for (int index_2=index_1+1;index_2<src.length-2;index_2++) {
                int target = sumTar - (src[index_1] + src[index_2]);
                int low=index_2+1;
                int hi=src.length-1;
                while (low < hi) {
                    int currentSum = src[low] + src[hi];
                    if (currentSum == target) {
                        List<Integer> one = new ArrayList<Integer>();
                        one.add(src[index_1]);
                        one.add(src[index_2]);
                        one.add(src[low]);
                        one.add(src[hi]);
                        all.add(one);
                        while (low < hi && src[low] == src[low + 1]) {
                            low++;
                        }
                        while (low < hi && src[hi] == src[hi - 1]) {
                            hi--;
                        }
                        low++;
                        hi--;
                    } else if (currentSum < target) {
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
                while (index_2 < src.length - 2 && src[index_2] == src[index_2 + 1]) {
                    index_2++;
                }
//                index_2++;  这个地方引入的bug，导致不能完全被找到，多跳了一次
            }
            while (index_1 < src.length - 3 && src[index_1] == src[index_1 + 1]) {
                index_1++;
            }
//            index_1++;
        }


        return all;
    }
}
