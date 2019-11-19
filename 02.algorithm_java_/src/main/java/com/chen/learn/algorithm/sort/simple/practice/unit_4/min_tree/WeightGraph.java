package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by chencc on 2018/8/23.
 */
public class WeightGraph {

    private int V;
    private Set<Edge>[] container;

    public WeightGraph(int v) {
        V = v;
        container = new HashSet[v];
        for (int index = 0; index < v; index++) {
            container[index] = new HashSet<>();
        }
    }

    public void addEdge(Edge edge) {
        int one = edge.either();
        int another = edge.another(one);
        container[one].add(edge);
        container[another].add(edge);
    }

    public Iterable<Edge> adj(int i) {
        return container[i];
    }

    public int v() {
        return V;
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph(3);
        weightGraph.addEdge(new Edge(0, 1, 1.1));
        weightGraph.addEdge(new Edge(0, 2, 2.3));
        weightGraph.addEdge(new Edge(2, 1, 3.5));
    }

    public List< Edge> edges() {
        List<Edge> edgeList = new ArrayList<>();
        for (int i=0;i<container.length;i++) {
            edgeList.addAll(container[i]);
        }
        return edgeList;
    }
}
