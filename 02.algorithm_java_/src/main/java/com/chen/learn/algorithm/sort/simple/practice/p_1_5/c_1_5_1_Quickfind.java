package com.chen.learn.algorithm.sort.simple.practice.p_1_5;

public class c_1_5_1_Quickfind {
    int[] container;
    int N;

    public c_1_5_1_Quickfind(int cap) {

        N = cap;
        container = new int[cap];
        for (int i = 0; i < cap; i++) {
            container[i] = i;
        }
    }

    public void union(int p, int q) {
        int p_score = container[p];
        int q_score = container[q];
        if(p_score==q_score) return;

        for (int i = 0; i < N; i++) {
            if (container[i] == q_score) {
                container[i]=p_score;
            }
        }
    }

    public int find(int p) {
        return container[p];
    }

    public boolean connected(int p, int q) {
        return container[p] == container[q];
    }

    int count() {
        return N;
    }


}
