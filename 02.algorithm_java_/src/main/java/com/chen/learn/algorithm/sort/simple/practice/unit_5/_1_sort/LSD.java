package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

/**
 * Created by chencc on 2018/8/30.
 */
public class LSD {

    public static void sort(String[] src) {

        String[] temp = new String[src.length];

        int len = src[0].length();
        for (int i=len-1;i>=0;i--) {

            int[] count = new int[256];

            for (int j=0;j<src.length;j++) {
                count[src[j].charAt(i)+1]++;
            }
            for(int m=1;m<count.length;m++) {
                count[m] += count[m - 1];
            }
            for (int l=0;l<src.length;l++) {
                temp[count[src[l].charAt(i)]++] = src[l];
            }
            for(int g=0;g<src.length;g++) {
                src[g] = temp[g];
            }
        }

    }


    public static void main(String[] args) {
        String[] src = new String[]{
                "baab",
                "aaac",
                "aaaa",
                "aeaf",
                "feaf",
                "aeaf",
                "ayyg"
        };
        sort(src);
        for (String ele : src) {
            System.out.println(ele);
        }
    }
}
