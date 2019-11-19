package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_tree;

import com.chen.learn.algorithm.sort.simple.practice.p_1_5.BetterQuickUnion;
import com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3.MinPriority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencc on 2018/8/27.
 */
public class MinTreeAnother {

    private BetterQuickUnion union;
    List<Edge> edges = new ArrayList<>();
    boolean[] marked ;
    MinPriority<Edge> minPriority ;

    public MinTreeAnother(WeightGraph weightGraph) {
        union = new BetterQuickUnion(weightGraph.v());
        minPriority = new MinPriority<>(weightGraph.v());
        marked = new boolean[weightGraph.v()];
        minPriority = new MinPriority<>(weightGraph.edges());

        while (!minPriority.isEmpty() && !(edges.size() == weightGraph.v()-1)) {
            Edge min = minPriority.delete();
            int one = min.either();
            int another = min.another(one);
            if (!union.isConnected(one, another)) {
                edges.add(min);
                union.union(one, another);
            }
        }
    }

    public static void main(String[] args) {
        WeightGraph weightGraph = new WeightGraph(6);
        weightGraph.addEdge(new Edge(0, 1, 1.1));
        weightGraph.addEdge(new Edge(0, 2, 2.3));
        weightGraph.addEdge(new Edge(2, 1, 3.5));
        weightGraph.addEdge(new Edge(2, 3, 1.5));
        weightGraph.addEdge(new Edge(0, 3, 0.5));
        weightGraph.addEdge(new Edge(2, 5, 0.6));
        weightGraph.addEdge(new Edge(3, 5, 3.7));
        weightGraph.addEdge(new Edge(4, 5, 3.9));
        weightGraph.addEdge(new Edge(4, 3, 1.9));

        MinTreeAnother another = new MinTreeAnother(weightGraph);
        another.edges.forEach(System.out::println);
    }
}
