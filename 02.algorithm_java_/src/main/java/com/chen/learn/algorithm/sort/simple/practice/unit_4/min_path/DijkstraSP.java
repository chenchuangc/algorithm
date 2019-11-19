package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_path;

import com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3.StanderdIndexMinPriority;

/**
 * Created by chencc on 2018/8/28.
 * https://github.com/jimmysuncpt/Algorithms/tree/master/src/com/jimmysun/algorithms/chapter4_4
 */
public class DijkstraSP {
    private DirWeightGraph dirWeightGraph;
    private DirEdge[] edges;
    private double[] weightTo;
//    private


    public DijkstraSP(DirWeightGraph dirWeightGraph, int s) {

        this.dirWeightGraph = dirWeightGraph;
        edges = new DirEdge[dirWeightGraph.V()];
        weightTo = new double[dirWeightGraph.V()];
        for (int i = 0; i < dirWeightGraph.V(); i++) {
            weightTo[i] = Double.POSITIVE_INFINITY;
        }
        weightTo[s] = 0;
        StanderdIndexMinPriority<Double> minPriority = new StanderdIndexMinPriority<>(20);
        minPriority.insert(0, 0d);
        deepfind(minPriority);
    }

    private void deepfind(StanderdIndexMinPriority<Double> queue) {
        while (!queue.isEmpty()) {
            Integer v = queue.delete();
            dirWeightGraph.adj(v).forEach(e -> {
                int to = e.getTo();
                if (weightTo[to] > weightTo[v] + e.getWeight()) {
                    weightTo[to] = weightTo[v] + e.getWeight();
                    edges[to] = e;
                    if (queue.contains(to)) {
                        queue.change(to, weightTo[to]);
                    } else {
                        queue.insert(to, weightTo[to]);
                    }
                }
            });
        }
    }

    public boolean hapathTo(int end) {
        return weightTo[end]<Double.POSITIVE_INFINITY;
    }


    public static void main(String[] args) {
        DirWeightGraph dirWeightGraph = new DirWeightGraph(4);
        dirWeightGraph.addEdge(new DirEdge(0, 1, 5.5));
        dirWeightGraph.addEdge(new DirEdge(0, 2, 3.5));
        dirWeightGraph.addEdge(new DirEdge(0, 3, 3.1));
        dirWeightGraph.addEdge(new DirEdge(2, 1, -3.1));


        DijkstraSP sp = new DijkstraSP(dirWeightGraph, 0);
        for (DirEdge edge : sp.edges) {
            System.out.println(edge);

        }
//        System.out.println(sp.hapathTo(4));
//        System.out.println(sp.hapathTo(5));

    }
}
