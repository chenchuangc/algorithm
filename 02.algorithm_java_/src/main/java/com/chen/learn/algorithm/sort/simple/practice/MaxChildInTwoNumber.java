package com.chen.learn.algorithm.sort.simple.practice;

/**
 * 给出使用欧几里德算法计算 105 和 24 的最大公约数的过程中得到的一系列 p 和 q 的值。
 * 扩展该 算法中的代码得到一个程序 Euclid，从命令行接受两个参数，
 * 计算它们的最大公约数并打印出每 次调用递归方法时的两个参数。
 * 使用你的程序计算 1 111 111 和 1 234 567 的最大公约数。
 * <p>
 * <p>
 * 辗转相除法基于如下原理：
 * 两个整数的最大公约数等于其中较小的数和两数的差的最大公约数。
 * 例如，252和105的最大公约数是21（252 = 21 × 12；105 = 21 × 5）；
 * 因为252 − 105 = 21 × (12 − 5) = 147，所以147和105的最大公约数也是21。
 * 在这个过程中，较大的数缩小了，所以继续进行同样的计算可以不断缩小这两个数直至其中一个变成零。
 * 这时，所剩下的还没有变成零的数就是两数的最大公约数。
 */
public class MaxChildInTwoNumber {

    public static void main(String[] args) {
        int a = 1111111;
        int b = 1234567;
        int a_b = maxChild(a, b);
        System.out.println(a_b);

    }

    private static int maxChild(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }

        if (a >= b) {
            return maxChild(a - b, b);
        } else {
            return maxChild(b - a, a);
        }
    }
}
