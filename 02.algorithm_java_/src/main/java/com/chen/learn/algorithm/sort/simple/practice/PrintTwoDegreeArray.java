package com.chen.learn.algorithm.sort.simple.practice;

/**
 * 1.1.13 编写一段代码，打印出一个 M 行 N 列的二维数组的转置(交换行和列)
 */
public class PrintTwoDegreeArray {
    public static void main(String[] args) {
        int[][] aa= new int[2][3];
        aa[0] = new int[]{1, 2, 3};
        aa[1] = new int[]{4, 5, 6};
        System.out.println("length:" + aa.length);
        normalPrint(aa);
        System.out.println("----------");
        revorsePrint(aa);

    }

    private static void normalPrint(int[][] aa) {
        for (int i = 0; i < aa.length; i++) {
            for (int j = 0; j < aa[i].length; j++) {
                System.out.print(aa[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static void revorsePrint(int[][] aa) {
        int[][] bb = new int[aa[1].length][aa.length];
        for (int i = 0; i < bb.length; i++) {
            for (int j = 0; j < bb[i].length; j++) {
                bb[i][j] = aa[j][i];
            }
        }
        normalPrint(bb);

    }
}
