/**
 * Created by chencc on 2018/9/21.
 * 分治法
 * 分治法主要是将大问题分解为小问题求解
 *
 * 分解  解决  合并
 * 分治法也经常和递归，迭代混用，因为 分治法很多时候不能一步到位，需要在不同的层次上反复使用递归
 *
 *
 *
 */
package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_3_partion;

/**
 * 之前总是对分治和递归理解的不到位，认为二者可以是一体的，也就是分治就是和递归算在一起算是一种解决问题的方式，所以不用去区分分治和递归
 *
 * 在最近的学习中才慢慢明白分治和递归本身是不一样的东西
 *
 * 分治强调的是 ： 父问题能够分解为一个或者多个小问题进行求解，通过这些小问题的解然后再得到这个父问题的解  ，他不关心这些子问题是如何求解的。
 * 就像二分查找法一样，只要子问题能够找到结果，那么就是最终解。 一般情况下，子问题有需要进行分解，这样的话就引入了迭代。
 *
 *
 * 迭代强调的是： 问题需要通过不停的迭代计算。
 * 关于迭代的方式又有两种：
 * 1. 类似递推一样的来找到结果，下一次的迭代一般依赖于本次迭代的结果。
 * 每次迭代是独立的，而且顺序是本次先结算完，然后再计算下一次
 * 比如牛顿迭代法求解非线性方程。这个里面用的就是层层迭代，并没有分治的思想在里面，只是通过迭代来逼近结果。
 *
 * 2. 迭代的时候通过求解子问题来求解父问题，只有子问题解决了才能解决父问题。
 * 比如二分则是子问题解决了，才能解决父问题，方向是相反的。
 *
 * 迭代一般强调的是迭代边界，就是迭代到什么时候才应该终止迭代（牛顿迭代法中的精度控制，二分查找中的最简子问题，
 * 注意二分查找中的查找命中本身不是迭代关注的终止条件，是分治关注的）。
 *
 * 而分治法关心的是 如何分解问题，分解后的问题如何解决（二分查找中的结果命中），结果如何合并。
 *
 *
 *
 */

