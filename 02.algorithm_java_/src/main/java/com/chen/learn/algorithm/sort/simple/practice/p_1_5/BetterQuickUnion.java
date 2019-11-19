package com.chen.learn.algorithm.sort.simple.practice.p_1_5;

import com.chen.learn.algorithm.sort.simple.practice.queue.QueueInList;

import java.nio.file.Path;

public class BetterQuickUnion {
    int[] container;
    int count;
    int[] len;

    public BetterQuickUnion(int count) {
        container = new int[count];
        len = new int[count];
        this.count = count;
        for (int i = 0; i < count; i++) {
            container[i] = i;
            len[i]=1;
        }
    }

    public int find(int p) {
        while (p!= container[p]) p=container[p];
        return p;
    }

    public void union(int p, int q) {
        int pEnd = find(p);
        int qEnd = find(q);
        if (pEnd == qEnd) {
            return;
        }
        if (len[pEnd] > len[qEnd]) {
            container[qEnd] = pEnd;
            len[qEnd]+=len[pEnd];
        } else {
            container[pEnd]=qEnd;
            len[pEnd] += len[qEnd];
        }
    }

    public boolean isConnected(int p, int q) {
         p = find(p);
         q = find(q);
         return p==q;

    }

    public static void main(String[] args) {
        BetterQuickUnion betterQuickUnion = new BetterQuickUnion(5);
        System.out.println(betterQuickUnion.find(3));
        betterQuickUnion.union(3, 1);
        System.out.println(betterQuickUnion.find(3));
        System.out.println(betterQuickUnion.find(1));
        System.out.println(betterQuickUnion.isConnected(3, 1));
    }
}
