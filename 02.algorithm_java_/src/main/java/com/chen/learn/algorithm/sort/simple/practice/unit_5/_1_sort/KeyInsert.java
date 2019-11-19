package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

/**
 * Created by chencc on 2018/9/3.
 */
public class KeyInsert {
    public static void sort(String[] args, int index, int low, int hi) {
        if (low >= hi - 1) {
            return;
        }
        for (int i = low+1; i <hi;i++) {
            for (int j=i-1;j>=low;j--) {
                if (char_at(args[j], index) > char_at(args[i], index)) {
                    exchange(args, j, i);
                }
            }
        }

    }

    private static int char_at(String arg, int index) {
        if (arg.length() <= index) {
            return -1;
        } else {
            return arg.charAt(index);
        }

    }

    public static void exchange(String[] args, int j, int i) {
        String temp = args[i];
        while (i > j) {
            args[i] = args[i - 1];
            i--;
        }
        args[j]=temp;
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
        sort(src,0,0,src.length);
        for (String ele : src) {
            System.out.println(ele);
        }
    }
}
