package com.chen.learn.algorithm.fee_learn.design_pattern.self_practice.heap;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/10/31.
 * 可以用来求前k大
 */
public class MinHeap {

    int N;
    int[] ele;
    int using_len;

    public MinHeap(int n) {
        N = n;
        ele = new int[N];
        using_len = 0;
    }

    public void insert(int e) {
        if (using_len == N) {
            if (e < ele[0]) {
                return;
            } else {
                ele[0] = e;
//                Helper.exchange(ele, 0, using_len);
                sink(using_len);
            }
        } else {
            ele[using_len++] = e;
            swim(using_len-1);
        }
    }

    private void swim(int using_len) {
        int k=using_len;
        int p = (k-1)/2;
        while (k > 0) {
            if (ele[p] > ele[k]) {
                Helper.exchange(ele, p, k);
                k = p;
                p = (k - 1) / 2;
            } else {
                return;
            }
        }

    }

    private void sink(int using_len) {

        int k = 0;

        int l = 2 * k + 1;
        int r = 2 * k + 2;
        int target;
        while (l < using_len) {
            if (r < using_len && ele[l] > ele[r]) {
                target = r;
            } else {
                target = l;
            }
            if (ele[k] > ele[target]) {
                Helper.exchange(ele, k, target);
                k = l;
                l = 2 * k + 1;
                r = 2 * k + 2;
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] aa = new int[]{3, 4, 5, 23, 7, 8};
        MinHeap heap = new MinHeap(3);
        for (int a : aa) {
            heap.insert(a);
        }
        System.out.println("ok");

    }


}
