package com.chen.learn.algorithm.sort.simple.practice.unit_5._3_child_pattern.kmp;

import com.chen.learn.algorithm.util.Helper;


public class NextGet {

    /**
     * 这里用来对字符串的模式进行分析
     * @param pat
     * @param nextArr
     */
    public static void getNextArray(String pat, int[] nextArr) {

        int left =0;
//        int begin = -1;
        int right =0;
        int len = pat.length();

        while (right < len) {
            if (left == -1) {
                left++;
                right++;
                nextArr[right]=0;
                continue;
            }
            if (right == 0) {
                nextArr[right] = -1;
                nextArr[right + 1] = 0;
                right++;
            } else {
                if (pat.charAt(right) == pat.charAt(left)) {
                    left++;
                    right++;
                    nextArr[right]=left;
                } else {
                    left = nextArr[left];
                }
            }
        }

    }

    public static void main(String[] args) {

        String pat = "asdfdssdafssadfaafdsafsdasdfdsasd";
        int[] aa = new int[pat.length()+1];
        for (int i = 0; i < aa.length; i++) {
            mprint(i);
            aa[i] = -1;
        }
        System.out.println();
        getNextArray(pat, aa);
        Helper.print(aa);
    }

    private static void mprint(int i) {
        String tre = String.valueOf(i);
        if (tre.length() < 2) {
            tre = tre + " ";
        }
        System.out.print(tre + ",");
    }
}
