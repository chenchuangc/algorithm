package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_path;


import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chencc on 2018/8/27.
 */
public class DirWeightGraph  {

    private int V;
    private Set<DirEdge>[] container;

    public DirWeightGraph(int v) {
        V = v;
        this.container = new HashSet[v];
        for (int i = 0; i < v; i++) {
            container[i] = new HashSet<>();
        }
    }

    public void addEdge(DirEdge dirEdge) {
        container[dirEdge.getFrom()].add(dirEdge);
    }

    public Iterable<DirEdge> adj(int from) {
        return container[from];
    }

    public int V() {
        return V;
    }

    public List<DirEdge> edges(){
        List<DirEdge> list = new ArrayList<>();
        for (int i=0;i<container.length;i++) {
            list.addAll(container[i]);
        }
        return list;
    }



}
