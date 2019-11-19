package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;

/**
 * Created by chencc on 2018/9/23.
 * 走楼梯问题，一次一步或者两步，穷举法
 */
public class WalkFloor_search_all {

    public static long count=0;
//    public static int floorAll = 100;

    /**
     * 做了一下测试，当处理的50级楼梯的时候穷举法就一应很慢了，35s钟都计算不出来，所以说压力还是比较大的
     * 之前没有发现过这个问题呢，哈哈哈，改成迭代试试
     * 好像这个递归也是没有办法改成迭代的哈，看来递归和迭代并不是通用的。
     * 应该是迭代都能改成递归，但是递归并不一定都能改成迭代
     * @param floorLeft
     */
    public static void deal(int floorLeft,String str) {
        for (int i=1;i<=2;i++) {
            if (floorLeft - i == 0) {
                count++;
//                System.out.println(str  + i+ ",");
                return;
            } else if (floorLeft < 0) {
                return;
            } else {
                deal(floorLeft - i, str  + i+ ",");
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        deal(40,"");
        System.out.println(count);
        System.out.println("time:"+(System.currentTimeMillis()-start)/1000);
    }
}
