package com.chen.learn.algorithm.sort.simple.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chencc on 2018/5/29.
 * 求数组中最遥远和最近的元素
 * you can and you will be more powerful
 */
public class NebroAndDistance {

    public static void main(String[] args) {
        double[] temp = {2.3, 3.5, 5.7, 4.3, 8, 6};
        System.out.println(findNebro(temp));
        System.out.println(findMaxDis(temp));
    }

    public static List<Double> findNebro(double[] src) {
        Arrays.sort(src);
//        int distance =0;
        double dis =Double.MAX_VALUE;
        double near_1=0;
        double near_2=0;
        for (int i = 1; i < src.length; i++) {
            double currentDis = abss(src[i], src[i - 1]);
            if (currentDis < dis) {
                dis=currentDis;
                near_1 = src[i];
                near_2 = src[i - 1];
            }
        }
        List<Double> res = new ArrayList<Double>();
        res.add(near_1);
        res.add(near_2);
        return res;

    }

    private static double abss(double v, double v1) {
        return v-v1>0?v-v1:v1-v;
    }


    public static List<Double> findMaxDis(double[] src) {
        double max = src[0];
        double min = src[0];
        for (int i=1;i<src.length;i++) {
            if (src[i]>max) max = src[i];
            else if (src[i]<min) min = src[i];
        }

        List<Double> res = new ArrayList<Double>();
        res.add(max);
        res.add(min);
        return res;


    }
}
