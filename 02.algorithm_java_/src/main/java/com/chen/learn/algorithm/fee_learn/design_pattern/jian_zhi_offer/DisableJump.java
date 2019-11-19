package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/14.
 * 变态跳动
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 用数学归纳法可以得到 f(n)=2*f(n-1)
 */
public class DisableJump {

    public static int JumpFloorII(int target) {
        if(target==1){
            return 1;
        }

        int k=1;
        int start=2;
        while(start<=target){
            k*=2;
            start++;
        }
        return k;
    }
}
