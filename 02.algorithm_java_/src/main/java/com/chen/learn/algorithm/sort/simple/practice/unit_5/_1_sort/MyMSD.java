package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

import sun.java2d.SurfaceDataProxy;

/**
 * Created by chencc on 2018/8/30.
 */
public class MyMSD {

    public static void sort(String[] src,int index_char, int low, int hi) {

        String[] temp = new String[src.length];
        int index = index_char;
        sortAchar(src, index, temp, low, hi);
        int start = low;
        int end = low;
        for (int i = low; i < hi; i++) {
            try {
                if (src[i].length() <= index) {
                    start = i+1;
                    end = i+1;
                    continue;
                }
                if (src[i].charAt(index) == src[start].charAt(index)) {
                    end = i;
                } else {
                    sort(src, index + 1, start, end+1);
                    start = i;
                    end = i;
                }
                if ((i == hi - 1) && end != start) {
                    sort(src, index + 1, start, end+1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sortAchar(String[] src, int index, String[] temp, int low, int hi) {
        if (low == hi) {
            return;
        }

        int[] count = new int[256];
        count[0]=low;

        for (int i=low;i<hi;i++) {
            if (src[i].length() <= index) {
                count[0]++;
            } else {
                count[src[i].charAt(index)+1]++;
            }
        }
        for (int j=1;j<256;j++) {
            count[j] += count[j - 1];
        }
        int tem_low=low;
        for(int k=low;k<hi;k++) {
            if (src[k].length() <= index) {
                temp[tem_low++] = src[k];
            } else {
                temp[count[src[k].charAt(index)]++] = src[k];
            }
        }
        for (int m=low;m<hi;m++) {
            src[m] = temp[m];
        }
    }


    public static void main(String[] args) {
        String[] src = new String[]{
                "baab",
                "baaa",
                "maaa",
                "aea",
                "ae",
                "feaf",
                "yyyg"
        };
        sort(src,0,0,src.length);
        for (String ele : src) {
            System.out.println(ele);
        }
    }
}
