package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chencc on 2018/8/21.
 */
public class SimpleDirGraph implements DirGraph{

    private int e;
    private int v;
    private Set<Integer>[] container;

    public SimpleDirGraph(int v) {
        this.v = v;
        this.container = new HashSet[v];
        for (int i=0;i<v;i++) {
            container[i] = new HashSet<>();
        }
    }

    @Override
    public int V() {
        return v;
    }

    @Override
    public int E() {
        return e;
    }

    @Override
    public void addEdge(int v, int w) {
        container[v].add(w);
        e++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return container[v];
    }

    @Override
    public DirGraph reverse() {
        DirGraph g = new SimpleDirGraph(v);
        for (int i=0;i<v;i++) {
            int j=i;
            this.adj(i).forEach(e->{
                g.addEdge(e, j);
            });
        }
        return g;
    }
}
