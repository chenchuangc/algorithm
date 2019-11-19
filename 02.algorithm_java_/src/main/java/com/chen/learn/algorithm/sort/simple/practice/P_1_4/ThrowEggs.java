package com.chen.learn.algorithm.sort.simple.practice.P_1_4;

import com.chen.learn.algorithm.util.Helper;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 1. 使用决策树的理论来进行求解
 * 假设最小的是k次，那么第一，按照决策树的方式，肯定是有从第k层进行扔，这样才能保证不浪费
 * 如果没有碎的话第二次肯定是k+(k-1)
 * <p>
 * 2. 使用动态规划来进行求解
 * 就是，找到 边界，最优子结构，状态转移方程就行了
 * w(k,n) 表示有k个鸡蛋，n个楼层
 * <p>
 * 假设第一次扔到k层，有两种情况
 * 1. 鸡蛋碎了：需要的次数为 1+w(k-1,k)
 * 2. 鸡蛋未碎： 1+w(k,n-k)
 * <p>
 * 因为是要保证最坏情况下的求解，所以取 以上两种情况的较大值 f(k) = 1+max(w(k-1,k), w(k,n-k))
 * 但是这个k又有很多取值，从1-n都可以取，所以，这个问题的结果，应该是所有f(k){k=>(1-n)} 中的最小值，
 * 对应的k解就是问题的解
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 边界
 */
public class ThrowEggs {


    public static void main(String[] args) {

        int[] src = new int[200];
        for (int i = 0; i < src.length; i++) {
            src[i] = i;
        }
//        System.out.println(floorEgg(src, 438, false));
        System.out.println(findMinWay(src));
    }

    /**
     * 2. 使用动态规划来进行求解
     * 就是，找到 边界，最优子结构，状态转移方程就行了
     *
     * w(m,n) 表示有m个鸡蛋，n个楼层
     * <p>
     * 假设第一次扔到k层，有两种情况
     * 1. 鸡蛋碎了：需要的次数为 1+w(m-1,k)
     * 2. 鸡蛋未碎： 1+w
     *
     * 因为是要保证最坏情况下的求解，所以取 以上两种情况的较大值 f(k) = 1+max(w(m-1,k), w(m,n-k))
     * 同时呢，真正要求解的是是k ,k的目标是要让f(k)最小
     * 所以 w(m,n)
     * = min {  f(k) {k=>(1-n)}  }
     * = min {  1+max(w(m-1,k), w(m,n-k)) {k=>(1->n)} }
     *
     * 因此，最优子结构的状态转移方程式是
     * w(m,n)= 1+ min {  max(w(m-1,k), w(m,n-k)) {k=>(1->n)} }
     *
     * 在本题中m=2
     * f(2,n)=1+min{ max(w(1,k-1), w(2,n-k))  k=>(1->n)}
     *
     *
     * 边界是
     * f(1,m)=m f(n,0)=0
     *
     *
     */
    public static int findMinWay(int[] floorPower) {

        int[][] res = new int[2][floorPower.length];
        for (int i = 0; i < res[0].length; i++) {
            res[0][i] = i;
        }
        res[1][0]=0;
        res[1][1]=1;
        int index=2;
        for (; index < floorPower.length; index++) {
           int min = Integer.MAX_VALUE;
            for (int k = 1; k <= index; k++) {
                int k_max = res[0][k-1] > res[1][(index - k)] ? res[0][k-1] : res[1][index - k];
                k_max++;
                if (min > k_max) {
                    min = k_max;
                }
            }
            res[1][index]=min;
//            System.out.println(min);
        }
        Helper.print(res[0]);
        Helper.print(res[1]);
        return res[1][floorPower.length - 1];



    }

    /**
     * 1.4.24
     * <p>
     * 扔鸡蛋。
     * 假设你面前有一栋 N 层的大楼和许多鸡蛋，
     * 假设将鸡蛋从 F 层或者更高的地方扔下鸡蛋才会摔碎，否则则不会。
     * 首先，设计一种策略来确定 F 的值，其中扔 ~ lgN 次鸡蛋后摔碎的鸡蛋数量为 ~ lgN。
     * 然后想办法将成本降低到~ 2lgF。
     */
    public static int floorEgg(int[] floorPower, int eggPower, boolean simpleWay) {
        //1. 使用二分查找，对应的时间复杂度为logn, 鸡蛋的坏掉概率为logn
        // 2. 为了减少鸡蛋摔坏的概率，使鸡蛋摔坏的概率为 2logf

        if (simpleWay) {
            return planA(floorPower, eggPower);
        } else {
            return planB(floorPower, eggPower);
        }
    }

    /**
     * 从1，2，4，8一直到2^k>F进行第一轮的查找
     * 然后从2^k-1 + 1,2,4 ~ 2^k 进行查找
     *
     * @param floorPower
     * @param eggPower
     * @return
     */
    private static int planB(int[] floorPower, int eggPower) {

        if (floorPower[0] >= eggPower) {
            return 0;
        }
        int index = 1;
        int step = 1;
        int endIndex = floorPower.length - 1;

        int egg = 0;
        while (true) {
            System.out.println(endIndex);
            if (index + step > endIndex) {
                index = index + step / 2;
//                endIndex = index + step;
                step = 1;//归1 step
            } else if (floorPower[index + step] == eggPower) {
                egg++;
                System.out.println("egg:" + egg);
                return index + step;
            } else if (floorPower[index + step] > eggPower) {
                index = index + step / 2;
                endIndex = index + step;
                step = 1;//归1 step
                egg++;
            } else {
                step *= 2;
            }
        }
    }

    private static int planA(int[] floorPower, int eggPower) {
        //使用二分查找
        return 0;
    }

}
