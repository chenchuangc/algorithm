package com.chen.learn.algorithm.sort.simple;

import com.chen.learn.algorithm.util.Helper;

public class ShellSort {

    /**
     * 希尔排序是一个很难证明是否快速的算法
     * 是改进后的插入排序，他是将步长拉大，形成子序列进行排序
     * 然后再逐渐缩小步长，最终还是要用一步的长度来进行排序
     * 1.初始步长的确定
     * 2.步长的缩小模式：为了最终能够达到一步的长度
     * 3.边界条件的控制
     * @param source
     */
    public static void sort(int[] source) {

        Helper.print(source);
        int step =1;
        while (step < (source.length / 3)) {
            step = step*3+1;
        }
        while (step >= 1) {
            for (int index=0;index<step;index++) {
                for (int theInsert=index+step;theInsert<source.length;theInsert+=step) {
                    for (int theordered=index;theordered<theInsert;theordered+=step) {
                        if (source[theInsert] < source[theordered]) {
                            change(source,theInsert, theordered, step);
                        }
                    }
                }
            }
            step/=3;
        }
        Helper.print(source);

    }

    private static void change(int[]source,int theInsert, int theordered, int step) {
        int temp = source[theInsert];
        while (theInsert > theordered) {
            source[theInsert] = source[theInsert -= step];
        }
        source[theInsert]=temp;
    }

    public static void main(String[] args) {
        sort(Helper.seed(15));

    }
}
