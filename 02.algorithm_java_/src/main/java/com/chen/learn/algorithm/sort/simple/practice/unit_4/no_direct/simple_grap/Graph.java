package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap;

/**
 * Created by chen on 2017/9/25.
 */
public abstract class Graph {



    public abstract void addEdge(int v, int w);

    public abstract int V();


    public abstract int E();

    public abstract Iterable<Integer> adj(int v);


}
