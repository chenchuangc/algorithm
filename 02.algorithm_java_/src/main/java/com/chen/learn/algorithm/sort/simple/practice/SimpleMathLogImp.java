package com.chen.learn.algorithm.sort.simple.practice;

/**
 * Created by chencc on 2018/5/9.
 *  编写一个静态方法 lg()，接受一个整型参数 N，返回不大于 log2N 的最大整数。不要使用 Math 库。
 */
public class SimpleMathLogImp {
    public static void main(String[] args) {
        int n= 50;
        int result = log2(n);
        System.out.println(result);
        System.out.println(Math.log(50));

    }

    /**
     * 挺有意思哈，这样来做，感觉挺好的
     * @param n
     * @return
     */
    private static int log2(int n) {
        int l=0;
        while ((n=(n >> 1)) > 0) {
            l++;
        }
        return l;
    }
}
