package com.chen.learn.algorithm.sort.simple.practice.p_1_5;

public class c_1_5_2_QuickUnion {

    int [] container;
    int count;
    public c_1_5_2_QuickUnion(int cap) {
        container = new int[cap];
        for (int i = 0; i < cap; i++) {
            container[i] = i;
        }
        count=cap;
    }

    public int find(int p) {
        while (container[p]!= p) {
            p=container[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int root_p = find(p);
        int root_q = find(q);
        if (root_p == root_q) {
            return;
        }
        container[root_p]=root_q;
        count--;
    }
}
