package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;


import com.chen.learn.algorithm.util.Helper;


/**
 * Created by chencc on 2018/9/3.
 */
public class MSD {

    static int C = 256 + 2;

    public static void sort(String[] args) {
        int low = 0;
        int hi = args.length;
        int index = 0;
        String[] temp = new String[args.length];
        sort(args, temp, index, low, hi);
    }

    private static void sort(String[] args, String[] temp, int index, int low, int hi) {
        if (low >= hi-1) {
            return;
        }
        int[] count = new int[C];
        //计数
        for (int i = low; i < hi; i++) {
            count[charAt(args[i], index) + 2]++;
        }

        //转化
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        //移动
        for (int i = low; i < hi; i++) {
            temp[low + count[charAt(args[i], index) + 1]] = args[i];
            count[charAt(args[i], index) + 1]++;
        }

        for (int i=low;i<hi;i++) {
            args[i] = temp[i];
        }

        Helper.print(args);

        for (int i=0;i<count.length-1;i++) {
            int a = count[i];
            int b = count[i + 1];
            if (count[i+1] == 2) {
//                System.out.println();
            }
            sort(args,temp,index+1,low+count[i],low+count[i+1]);
        }


    }

    private static int charAt(String a, int index) {
        if (index >= a.length()) {
            return -1;
        } else {
            return a.charAt(index);
        }
    }



    public static void main(String[] args) {
        String[] src = new String[]{
                "baab",
                "baaa",
                "maaa",
                "aea",
                "aa",
                "feaf",
                "a",
                "faaf",
                "yyyg"
        };
        sort(src);
        for (String ele : src) {
            System.out.println(ele);
        }
    }


}
