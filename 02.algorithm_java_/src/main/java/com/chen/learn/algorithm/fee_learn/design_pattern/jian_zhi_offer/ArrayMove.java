package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/10/14.
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class ArrayMove {

    public static void reOrderArray(int [] array) {
        int[] temp = new int[array.length];
        int[] tempOu = new int[array.length];

        int beginJi =0;
        int beginOu = 0;
        for(int i=0;i<temp.length;i++){
            if(array[i]%2==1){
                temp[beginJi++]=array[i];
            }else{
                tempOu[beginOu++]=array[i];
            }
        }
        System.arraycopy(temp,0,array,0,beginJi);
        System.arraycopy(tempOu,0,array,beginJi,beginOu);

    }

    public static void main(String[] args) {
        int[] src=new int[]{1, 2, 3, 4, 5};
        reOrderArray(src);
        Helper.print(src);
    }
}
