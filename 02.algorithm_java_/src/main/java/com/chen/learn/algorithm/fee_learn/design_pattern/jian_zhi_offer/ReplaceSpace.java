package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/12.
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy
 */
public class ReplaceSpace {
    public static String replaceSpace(StringBuffer str) {
        char[] array = str.toString().toCharArray();
        char[] new_arr = new char[array.length * 3];
        int ia=0;
        int in=0;
        for (;ia<array.length;ia++) {
            if (array[ia] == ' ') {
                new_arr[in] = '%';
                new_arr[in + 1] = '2';
                new_arr[in + 2] = '0';
                in += 3;
            } else {
                new_arr[in] =array[ia];
                in++;
            }
        }
        return new String(new_arr, 0, in );
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace(new StringBuffer(" ")));

    }
}
