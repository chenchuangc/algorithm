package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_6_same_line;

import com.chen.learn.algorithm.util.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chencc on 2018/11/2.
 * 求共线的点的最多数
 */
public class MostSameLine {

    static class Ponit{
        double x;
        double y;

        public Ponit(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Ponit{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class PointK implements Comparable<PointK>{
        int index;
        double k;

        public PointK(int index, double k) {
            this.index = index;
            this.k = k;
        }

        @Override
        public int compareTo(PointK o) {
            if (Helper.toPositive(k - o.k) < 0.00001) {
                return 0;
            }
            return k-o.k>0?1:-1;
        }
    }

    public static List<Ponit> getMost(Ponit[] src) {

        int preMostNum=0;
        List<Ponit> sameLinePoints = new ArrayList<>();
        List<Integer> sameLinePointsPositions = new ArrayList<>();


        int len = src.length;
        for (int i=0;i<len;i++) {
            ArrayList<PointK> current = new ArrayList<>();

            for (int j=i+1;j<len;j++) {
                if (i == j) {
                    continue;
                }
                double k = calculateK(src[i], src[j]);
                current.add(new PointK(j, k));
            }
            PointK[] curArray = (PointK[]) current.toArray(new PointK[]{});
//            PointK[] curArray = (PointK[]) current.toArray();
            List<Integer> curMost = new ArrayList<>();
            curMost.add(i);
            curMost.addAll(getCurMost(curArray));
            if (curMost.size() > preMostNum) {
                sameLinePointsPositions.clear();
                sameLinePointsPositions.addAll(curMost);
                preMostNum=curMost.size();
            }
        }
        for (int index : sameLinePointsPositions) {
            sameLinePoints.add(src[index]);
        }
        return sameLinePoints;

    }

    private static List<Integer> getCurMost(PointK[] curArray) {
        Arrays.sort(curArray);
        int mostStartPosition=0;
        int mostNum=0;
        int curPosition=0;
        int curNum =0;
        List<Integer> pointIndex = new ArrayList<>();
        for (int index=0;index<curArray.length;index++) {
            if (isDoubleEqual(curArray[index].k,curArray[curPosition].k)) {
                curNum++;
            } else {
                if (curNum > mostNum) {
                    mostStartPosition = curPosition;
                    mostNum = curNum;
                }
                curPosition = index;
                curNum = 1;
            }
        }
        //这个地方如果所有的元素的k都是相等的，或者最后一组元素是最多的，就会出现问题，所以加上下面的if来完善
        if (curNum > mostNum) {
            mostStartPosition = curPosition;
            mostNum = curNum;
        }
        for (int i=mostStartPosition;i<mostStartPosition+mostNum;i++) {
            pointIndex.add(curArray[i].index);
        }
        return pointIndex;
    }

    private static double calculateK(Ponit ponit, Ponit ponit1) {

        return Helper.toPositive((ponit.y - ponit1.y) / (ponit.x - ponit1.x));

    }

    public static boolean isDoubleEqual(double a, double b) {
        return Helper.toPositive(a-b)<0.00001;
    }


    public static void main(String[] args) {

        Ponit[] src = new Ponit[]{
                new Ponit(1, 1),
                new Ponit(2, 2),
                new Ponit(3, 3),
                new Ponit(4, 4),
                new Ponit(5, 5),
                new Ponit(5, 2),
                new Ponit(4, 5),
                new Ponit(1, 2),
                new Ponit(2, 4),
                new Ponit(3, 6),
                new Ponit(4, 8),
                new Ponit(5, 10),
                new Ponit(6, 12),
                new Ponit(-1, -2),
        };
        System.out.println(getMost(src));

    }

}
