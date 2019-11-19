package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by chencc on 2018/8/20.
 */
public class SimpleGraph extends Graph {
    int V;
    int E;
    Set<Integer>[] edges;

    public SimpleGraph(int v) {
        V = v;
        E = 0;
        edges = new Set[v];
        for (int index = 0; index < edges.length; index++) {
            edges[index] = new HashSet<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        Set<Integer> vEdge = edges[v];
        vEdge.add(w);
        Set<Integer> wEdge = edges[w];
        wEdge.add(v);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return edges[v];
    }
}
