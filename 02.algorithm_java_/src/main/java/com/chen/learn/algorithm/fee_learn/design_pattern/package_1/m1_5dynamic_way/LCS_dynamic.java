package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;

import com.chen.learn.algorithm.util.Helper;

import static java.lang.Integer.max;

/**
 * Created by chencc on 2018/9/17.
 * 有些时候下标从1开始会让问题简化不少
 * 动态规划没有办法记录中间的过程状态的选择
 */
public class LCS_dynamic {

    public static int longestCommon(String stra, String stab) {
        stra = "0" + stra;
        stab = "0" + stab;
        char[] a_arr = stra.toCharArray();
        char[] b_arr = stab.toCharArray();
        int i = a_arr.length;
        int j = b_arr.length;
        int[][] common = new int[i][j];
        for (int a = 0; a < i; a++) {
            common[a][0] = 0;
        }
        for (int b = 0; b < j; b++) {
            common[0][b] = 0;
        }

        for (int k = 1; k < i; k++) {
            for (int m = 1; m < j; m++) {
                if (a_arr[k] == b_arr[m]) {
                    common[k][m] = common[k - 1][m - 1] + 1;
                } else {
                    common[k][m] = max(common[k - 1][m], common[k][m - 1]);
                }
            }
            Helper.print(common[k]);

        }
        return common[i - 1][j - 1];
    }

    /**
     * 不能只使用一个数组来完成，最少需要两个数组来完成
     * @param stra
     * @param stab
     * @return
     */
    public static int longestCommonBetter(String stra, String stab) {
        stra = "_" + stra;
        stab = "_" + stab;
        char[] a_arr = stra.toCharArray();
        char[] b_arr = stab.toCharArray();
        int i = a_arr.length;
        int j = b_arr.length;
        int[] pre = new int[j];
        int[] next = new int[j];
        for (int b = 0; b < j; b++) {
            pre[b] = 0;
        }

        for (int k = 1; k < i; k++) {
            //            pre=next;   //最开始只有这一句，没有下面的交换语句，后来发现出问题了
            // 和 com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_4_iterator.Kaifang是不一样的，这个是引用互换，那个是局部互换
            for (int m = 1; m < j; m++) {
                if (a_arr[k] == b_arr[m]) {
                    next[m] = pre[m - 1] + 1;
                } else {
                    next[m] = max(pre[m], next[m - 1]);
                }
            }
            Helper.print(next);
            int[] temp = pre;
            pre=next;
            next=temp;

        }
        return pre[j - 1];
    }

    public static void main(String[] args) {
        System.out.println(longestCommon("edgfettdsafdsf", "edgfettdsafdsfedgfgett"));
        System.out.println("---------------");
        System.out.println("---------------");
        System.out.println(longestCommonBetter("edgfettdsafdsf", "edgfettdsafdsfedgfgett"));
    }
}
