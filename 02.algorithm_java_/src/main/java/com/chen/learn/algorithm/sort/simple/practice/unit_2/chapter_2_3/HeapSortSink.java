package com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/8/14.
 */
public class HeapSortSink {

    /**
     * 堆的构建，使用堆的话可以构建大顶堆和小顶堆，用于构造优先队列
     * that is it https://blog.csdn.net/hjimce/article/details/79185412
     * @param src
     */
    public static void sort(int src[]) {

        int len = src.length-2;

        int sinklen= len/2;
        while (sinklen >= 0) {
            sink(src, sinklen,src.length);
            sinklen--;
            Helper.print(src);
        }
        for(int index=src.length-1;index>=0;index--) {
            Helper.exchange(src, 0, index);
            sink(src, 0, index);
        }


    }

    /**
     * 从一半的地方进行下沉
     * @param src
     * @param sink_index
     */
    private static void sink(int[] src, int sink_index,int length) {
        while (sink_index < length / 2) {
            int little = sink_index*2+1;
            if (little+1<length && src[little] < src[little + 1]) {
                little++;
            }
            if (src[sink_index] < src[little]) {
                Helper.exchange(src, sink_index, little);
                sink_index = little;
            } else {
                break;
            }

        }
    }

    public static void main(String[] args) {
//        int[] src = Helper.seed(20);
        int[] src = new int[]{411,985,237,332,170,697,798,653,109,804,954,920,452,651,679,605,375,712,238,108,
        };
        Helper.print(src);
        System.out.println("---------");
        sort(src);
        Helper.print(src);

    }
}
