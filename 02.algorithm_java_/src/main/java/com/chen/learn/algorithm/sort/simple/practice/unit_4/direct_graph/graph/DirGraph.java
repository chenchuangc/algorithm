package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph;

/**
 * Created by chencc on 2018/8/21.
 */
public interface DirGraph {

    int V();

    int E();

    void addEdge(int v, int w);

    Iterable<Integer> adj(int v);

    DirGraph reverse();

}
