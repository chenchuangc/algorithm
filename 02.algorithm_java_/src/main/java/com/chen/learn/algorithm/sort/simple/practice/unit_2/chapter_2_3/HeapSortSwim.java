package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3;

import com.chen.learn.algorithm.util.Helper;
import javafx.scene.shape.Path;

/**
 * Created by chencc on 2018/8/15.
 */
public class HeapSortSwim {

    public static void sort(int[] src) {
        for (int i=0;i<src.length;i++) {
            swim(src, i, src.length);
        }
        for (int index=src.length-1;index>0;index--) {
            Helper.exchange(src, 0, index);
            sink(src, 0, index);
        }
    }

    private static void sink(int[] src, int index, int len) {
        while (index < len/2) {
            int child_bigger = index*2+1;
            if (child_bigger + 1 < len && src[child_bigger] < src[child_bigger + 1]) {
                child_bigger++;
            }
            if (src[index] < src[child_bigger]) {
                Helper.exchange(src, index, child_bigger);
                index = child_bigger;
            } else {
                break;
            }
        }

    }

    private static void swim(int[] src, int index, int length) {
        while (index >=1) {
            int parent = (index - 1) / 2;
            if (src[index] > src[parent]) {
                Helper.exchange(src, index, parent);
                index=parent;
            } else {
                break;
            }
        }

    }

    public static void main(String[] args) {
//        int[] src = new int[]{411,985,237,332,170,697,798,653,109,804,954,920,452,651,679,605,375,712,238,108,};
        int[] src = Helper.seed(9);
        Helper.print(src);
        System.out.println("---------");
        sort(src);
        Helper.print(src);

    }
}
