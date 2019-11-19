package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_1design_module;

/**
 * Created by chencc on 2018/9/23.
 * a： 我不是小偷   x != 1
 * b: c是小偷      x == 3
 * c: 小偷是d      x == 4
 * d: c 存在冤枉人  !(x == 4)
 *
 *  已知只有一个人说了谎话，小偷是谁呢     表达式的结果有三个为真
 *
 * 如何把这个抽象为数学模型呢
 * abcd是固定下来的已知，可以用1，2，3，4进行代替
 * 小偷则是未知，使用变量 x 来进行代替
 * 你一开始使用变量来代替4个人进行思考，那肯定无法转化了，已知肯定是用常量来表达，未知才会采用变量来表达
 */
public class WhoIsThief {

    public static int whoIs() {
        for (int i=1;i<=4;i++) {
            int x=i;
            int x1 = x !=1?1:0;
            int x2 = x==3?1:0;
            int x3 = x==4 ?1:0;
            int x4 = !(x==4)?1:0;
            if ((x1 + x2 + x3 + x4) == 3) {
                return x;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(whoIs());
    }

}
