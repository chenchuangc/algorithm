package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;

import com.chen.learn.algorithm.util.Helper;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.max;

/**
 * Created by chencc on 2018/9/18.
 */
public class BagWeight_dynamic {

    static class Bean{
        int weight;
        int val;
        int bagsize;
        int index;
        boolean hold;
        int current_total;

        public Bean(int val, int weight, int bagsize, int index, boolean hold, int current_total) {
            this.val = val;
            this.weight = weight;
            this.bagsize = bagsize;
            this.index = index;
            this.hold = hold;
            this.current_total = current_total;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "weight=" + weight +
                    ", val=" + val +
                    ", index=" + index +
                    ", current_total=" + current_total +
                    '}';
        }
    }


    public static int getMax(int bagSize, Map.Entry<Integer, Integer>[] goods) {
        bagSize=bagSize+1;
        int[][] calculateMap = new int[goods.length][bagSize];
        for (int i = 0; i < bagSize; i++) {
            calculateMap[0][i] = goods[0].getKey() <= i ?  goods[0].getValue():0;
        }
        for (int index=1;index<goods.length;index++) {
            for (int j = 0; j < bagSize; j++) {
                if (goods[index].getKey() <= j) {
                    calculateMap[index][j] = max(calculateMap[index - 1][j], calculateMap[index - 1][j - goods[index].getKey()]+goods[index].getValue());
                } else {
                    calculateMap[index][j] = calculateMap[index - 1][j];
                }

            }
        }
        for (int i=0;i<goods.length;i++) {
            Helper.print(calculateMap[i]);
        }
        return calculateMap[goods.length - 1][bagSize - 1];


    }


    public static void main(String[] args) {
        Map.Entry<Integer, Integer>[] kk = new Map.Entry[]{
                new HashMap.SimpleEntry<Integer, Integer>(7, 10),
                new HashMap.SimpleEntry<Integer, Integer>(2, 4),
                new HashMap.SimpleEntry<Integer, Integer>(3, 3),
                new HashMap.SimpleEntry<Integer, Integer>(5, 3),
                new HashMap.SimpleEntry<Integer, Integer>(3, 5),
                new HashMap.SimpleEntry<Integer, Integer>(4, 6),
                new HashMap.SimpleEntry<Integer, Integer>(5, 8),
                new HashMap.SimpleEntry<Integer, Integer>(1, 1),
        };

        System.out.println(getMax(21, kk));
    }




}
