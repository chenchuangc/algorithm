package com.chen.learn.algorithm.util;

import java.util.ArrayList;
import java.util.Random;

public class TwoDegreeArrayHelper {
    static Random random = new Random();

    public static int[][] seed(int weidth, int length) {
        int[][] aa = new int[weidth][length];
        for (int i = 0; i < weidth; i++) {
            for (int j=0;j<length;j++) {
                aa[i][j] = creatOneElement(aa, i, j);
            }
        }
        return aa;
    }

    private static int creatOneElement(int[][] aa, int i, int j) {

        if (i == 0) {
            return creatBigThanJ(aa, i, j);
        } else {
            return creatBigThanJAndI(aa, i, j);
        }
    }

    private static int creatBigThanJAndI(int[][] aa, int i, int j) {
        if (j == 0) {
            return (aa[i - 1][j] + random.nextInt(10));
        } else {
            return (max(aa[i - 1][j], aa[i][j - 1]) + random.nextInt(10));
        }

    }

    private static int max(int i, int j) {
        return i>=j?i:j;
    }

    private static int creatBigThanJ(int[][] aa, int i, int j) {
        if (j == 0) {
            return random.nextInt(10);
        } else {
            return (aa[i][j - 1] + random.nextInt(10));
        }
    }


    public void print(int[][] kk) {
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<kk[0].length;i++) {
            for (int j=0;j<kk.length;j++) {
            }
        }
    }


    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(0, 4);
        list.add(0, 6);
        System.out.println(list.toString());
    }
}
