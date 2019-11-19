package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_3_partion;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/9/21.
 * 字符串的所有可能
 *
 * 分解  解决 合并
 *
 * 分解： f(n)= f(n-1)  { 有n个f(n-1)求和，每个都是把其中的一个字符提前得到的 }
 * 解决： 这个过程比较简单，直接就是到边界的时候就结束了，到达边界就是一种可能性产生了
 * 合并：这个也是在边界的时候可以增加一条合并语句用于返回总的记录数
 *
 * 递归的分治，有些时候对于人的直接思维是无解的，所以在设计算法的时候有时候很难想起来应用
 * 所以有些时候要刻意使用一下试试
 * 这里使用了深度优先的搜索方式，也取得了比交好的效果
 * 分治是分解为多个子问题，子问题之间是没有重叠部分的，但是动态规划的子问题是会重复计算的
 */
public class StrPosibility {

    public static int count=0;
    public static void getPosibility(String string) {

        if (null == string) {
            return;
        }
        char[] chars = string.toCharArray();
        int len = chars.length;
        int begin=0;
        int end = len-1;
        deal(chars, begin, end);
    }

    /**
     * 采用的是深度优先的搜索方式
     * @param chars
     * @param begin
     * @param end
     */
    private static void deal(char[] chars, int begin, int end) {
        //这个地方设计的太巧妙了，很多时候觉得这个太难想到了，结果直接是参数
        if (begin == end) {
            count++;
            System.out.println(new String(chars));
            return;
        }
        for (int i=begin;i<=end;i++) {
            Helper.exchange(chars, begin, i);
            deal(chars, begin + 1, end);
            Helper.exchange(chars, begin, i);//后面还要换回来
        }
    }

    public static void main(String[] args) {
        getPosibility("rtyui");
        System.out.println(count);
    }
}
