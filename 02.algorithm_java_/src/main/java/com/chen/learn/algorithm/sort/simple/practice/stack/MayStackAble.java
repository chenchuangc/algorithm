package com.chen.learn.algorithm.sort.simple.practice.stack;

import com.chen.learn.algorithm.util.Helper;

/**
 * 解决的问题是，入栈的顺序是从 0-k（有序状态）
 * 中间穿插了出栈操作（乱序状态），
 * 求解一下这样的乱序状态out[]是否是可能存在的
 * <p>
 * 求解的思路是什么样的呢
 * 对stack做入栈操作，入栈的顺序是从0-k,每次入栈前，看看是不是等于out[]中对应被弹出的数据的值
 */
public class MayStackAble {

    public static void main(String[] args) {
//        String[] aa = {"2", "1", "0", "3", "4", "5"};
        String[] aa = {"1", "0", "2", "3", "4", "5"};
        String[] all = wholeSequence(aa);
        Helper.print(all);
    }

    /**
     * 这个玩意儿，完全写乱了，思路不行,还是要从数据本身的规律去理解
     *
     * @param out
     * @return
     */
    public static boolean mayStack(int[] out) {
        int k = 0;
        int outPos = 0;
        ArrayStack<Integer> stack = new ArrayStack<Integer>(out.length);
        while (k < out.length) {
            if (k == out[outPos]) {
                k++;
                outPos++;
            } else {
                stack.push(k);
                k++;
                while (outPos < out.length && stack.peek() == out[outPos]) {
                    stack.pop();
                    outPos++;
                }
            }
        }
        if (stack.getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 看样子我是解答出来了，哈哈哈，还是需要再多多思考复习才行
     * @param out
     * @return
     */
    public static String[] wholeSequence(String[] out) {
        String[] all = new String[out.length * 2];
        ArrayStack<String> stack = new ArrayStack<String>(20);
        int N = 0;
        int pos = 0;
        all[pos] = String.valueOf(N);
        stack.push(String.valueOf(N));
        N++;
        pos++;
        for (int i = 0; i < out.length; i++) {
            if (null != stack.peek() && stack.peek().equals(out[i])) {
                stack.pop();
                all[pos++] = "-";
            } else {
                while (N <= Integer.parseInt(out[i])) {
                    all[pos++] = String.valueOf(N++);
                    stack.push(String.valueOf(N - 1));
                }
                i--;
//                all[pos++] = String.valueOf(N++);
            }
//            while (null !=stack.peek())
        }
        if (stack.getSize() != 0) {
            return null;
        }
        return all;

    }
}
