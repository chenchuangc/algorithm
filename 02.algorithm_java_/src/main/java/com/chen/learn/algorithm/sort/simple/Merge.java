package com.chen.learn.algorithm.sort.simple;

import com.chen.learn.algorithm.util.Helper;

public class Merge {

    /**
     * 归并排序
     * 需要额外的数组作为辅助，
     * 使用递归更加合理
     *
     * 能够使用递归的逻辑是
     * 1.主任务能够分解为更多的相同的子任务
     * 2.要注意终止条件
     *
     * 这一次写又没有成功的写出来，所以还是很有必要进行思路的调整的，
     * 递归，有的时候还是思路很重要
     * 分析
     * 1. 加入是只有一个元素，那么久直接返回了
     * 2. 假如是有两个，
     *  2.1 需要进行子任务划分
     *  2.2 在子任务计算完毕后加一个两个有序数据的合并操作，
     *
     *
     *  在进行任务划分的时候，也许终止条件的任务并不需要做太多操作
     *  比如这里，在终止条件下的最小任务并不再需要进行 mergeArray操作
     *  遇到问题的时候要能够合理的划分问题的逻辑，根据逻辑再写出对应的代码
     * @param source
     */
    public static void sort(int[] source) {

        int[] temp = new int[source.length];
        merge(source, temp, 0, source.length - 1);

    }

    public static void merge(int[] source, int[] temp, int start, int end) {
        if (end == start) {
            return;
        }
        int mid = (start + end) / 2;
        merge(source, temp, start, mid);
        merge(source, temp, mid+1, end);
        mergeArray(source, temp, start, mid, end);
    }

    private static void mergeArray(int[] source, int[] temp, int start, int mid, int end) {
        int tempStart = start;
        int rightStart = mid+1;
        int leftStart = start;
        while (mid >= leftStart && end >= rightStart) {
            if (source[leftStart] > source[rightStart]) {
                temp[tempStart++] = source[rightStart++];
            } else {
                temp[tempStart++] = source[leftStart++];
            }
        }

        while (mid >= leftStart) {
            temp[tempStart++] = source[leftStart++];
        }

        while (end >= rightStart) {
            temp[tempStart++] = source[rightStart++];
        }

        while (start <= end) {
            source[start] = temp[start++];
        }

    }

    public static void main(String[] args) {
        int[] source = Helper.seed(20);
        Helper.print(source);
        sort(source);
        Helper.print(source);
    }
}
