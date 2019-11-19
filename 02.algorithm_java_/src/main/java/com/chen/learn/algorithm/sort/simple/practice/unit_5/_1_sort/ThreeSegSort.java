package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

/**
 * Created by chencc on 2018/9/3.
 */
public class ThreeSegSort {

    public static void sort(String[] args) {
        int low =0;
        int hi=args.length;
        int index=0;

        sort(args, low, hi, index);
    }

    private static void sort(String[] args, int low, int hi, int index) {
        if (low >= hi - 1) {
            return;
        }
        int lt=low;
        int rt = hi-1;
        for (int i=low+1;i<=rt;i++) {
            if (char_at(args[i], index) < char_at(args[lt], index)) {
                exchange(args, i, lt);
                lt++;
            } else if (char_at(args[i], index) > char_at(args[lt], index)) {
                exchange(args, i, rt);
                rt--;
                i=i-1;
            }
        }
//        System.out.println(" :" + lt + "  : " + rt);
        sort(args, low, lt , index);
        sort(args, lt, rt+1, index + 1);
        sort(args, rt+1, hi, index);

    }


    public static void exchange(String[] args, int j, int i) {
        String temp = args[i];
        args[i] = args[j];
        args[j]=temp;
    }

    private static int char_at(String arg, int index) {
        if (arg.length() <= index) {
            return -1;
        } else {
            return arg.charAt(index);
        }

    }


    public static void main(String[] args) {
        String[] src = new String[]{

                "bmab",
                "aaac",
                "aaaa",
                "aeaf",
                "feaf",
                " sometime i see some one go to the tomb of marry ",

        };
        sort(src);
        for (String ele : src) {
            System.out.println(ele);
        }
    }
}
