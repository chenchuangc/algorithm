package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;

/**
 * Created by chencc on 2018/12/10.
 */
public class BagRepeatable {

    public static void main(String[] args) {
        int[] weight = {2, 2, 6, 5, 4,1,3};//物品重量
        int[] value = {6, 3, 25, 4, 6,4,10};//物品价值
        int volume = 15;//背包容量
        //int maxValue = max(weight, value, volume);//求背包能装的最大价值
        int maxValue = dynamicMax(weight, value, volume);//求背包能装的最大价值
        System.out.println(maxValue);
    }

    /**
     * 感觉这个地方应该也有很多有问题的地方，这里应该也是有重复的子问题，进行了重复的求解
     * @param weight
     * @param value
     * @param volume
     * @return
     */
    private static int max(int[] weight, int[] value, int volume) {
        int maxV = 0;
        for (int i = 0; i < weight.length; i++) {
            if (weight[i] <= volume) {
                int curMaxV = max(weight, value, volume - weight[i]) + value[i];
                if (curMaxV > maxV) {
                    maxV = curMaxV;
                }
            }
        }
        return maxV;
    }

    /**
     * this is the way of dynamic
     * go baby
     * @param weight
     * @param value
     * @param volume
     * @return
     */
    public static int dynamicMax(int[] weight, int[] value, int volume) {
        int[] stepVolume = new int[volume + 1];
        for(int i=1;i<=volume;i++) {
            for (int j=0;j<weight.length;j++) {
                if (weight[j] <= i) {
                    stepVolume[i] = Math.max(stepVolume[i], stepVolume[i - weight[j]] + value[j]);
                }
            }
        }
        return stepVolume[volume];
    }


}
