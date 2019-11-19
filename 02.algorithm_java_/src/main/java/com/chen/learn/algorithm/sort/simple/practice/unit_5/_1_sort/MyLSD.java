package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

/**
 * Created by chencc on 2018/8/30.
 * this way need the str to be same length
 */
public class MyLSD {

    public static void sort(String[] src) {
        int len = src[0].length();
        KeyIndex.Ele[] wrappers = new KeyIndex.Ele[src.length];

        for (int i=0;i<src.length;i++) {
            wrappers[i] = new KeyIndex.Ele((int) src[i].charAt(len - 1), src[i]);
        }
        KeyIndex.sort(wrappers);
        for (int i=len-2;i>=0;i--) {
            for (KeyIndex.Ele temp : wrappers) {
                temp.setKey((int) temp.getContent().charAt(i));
            }
            KeyIndex.sort(wrappers);
        }
        for(int i=0;i<src.length;i++) {
            src[i] = wrappers[i].getContent();
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
