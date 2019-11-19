package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/12.
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 * 有些时候总想一口吃个胖子，这种方式也是已经很快了啊，不要总是陷入死角，有时候看看有没有其他特点
 */
public class OrderTwoDegreeSearch {


    /**
     * 你想象的比这个复杂，导致没有做出来，也许没有那么复杂呢
     * @param target
     * @param array
     * @return
     */
    static boolean  find(int target, int[][] array) {
        int col_total = array[0].length-1;
        int row_total = array.length-1;
        int cur_col=col_total;
        int cur_row=0;
        while (cur_col >= 0 && cur_row <= row_total) {
            if (target == array[cur_row][cur_col]) {
                return true;
            } else if (target > array[cur_row][cur_col]) {
                cur_row++;
            } else {
                cur_col--;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[][] src = new int[][]{
                {1,3,7,9},
                {2,8,12,16},
                {5,20,26,39}
        };
        System.out.println(find(12, src));
    }
}
