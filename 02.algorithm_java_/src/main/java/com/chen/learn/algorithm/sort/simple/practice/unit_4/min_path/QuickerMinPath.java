package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_path;

/**
 * Created by chencc on 2018/8/28.
 * 只适用于无环
 */
public class QuickerMinPath {
    private DirEdge[] edges;
    private DirWeightGraph dirWeightGraph;
    private double[] weightTo;

    public QuickerMinPath(DirWeightGraph dirWeightGraph, int s) {

        this.dirWeightGraph = dirWeightGraph;
        edges = new DirEdge[dirWeightGraph.V()];
        weightTo = new double[dirWeightGraph.V()];
        for (int i = 0; i < dirWeightGraph.V(); i++) {
            weightTo[i] = Double.POSITIVE_INFINITY;
        }
        weightTo[s] = 0;
        TopDirWeightGraph topDirWeightGraph = new TopDirWeightGraph(dirWeightGraph);
//        topDirWeightGraph.order().forEach(System.out::println);
        topDirWeightGraph.order().forEach(e -> {
            relax(e);


        });
    }

    private void relax(Integer e) {
        dirWeightGraph.adj(e).forEach(edge -> {
            int to = edge.getTo();
            if (weightTo[to] > weightTo[e] + edge.getWeight()) {
                weightTo[to] = weightTo[e] + edge.getWeight();
                edges[to] = edge;
            }
        });
    }


    public static void main(String[] args) {
        DirWeightGraph dirWeightGraph = new DirWeightGraph(5);
        dirWeightGraph.addEdge(new DirEdge(0, 1, 5.5));
        dirWeightGraph.addEdge(new DirEdge(1, 2, 1.5));
        dirWeightGraph.addEdge(new DirEdge(1, 3, 3.1));
        dirWeightGraph.addEdge(new DirEdge(1, 4, 3.1));
        dirWeightGraph.addEdge(new DirEdge(4, 2, 3.1));

        QuickerMinPath quickerMinPath = new QuickerMinPath(dirWeightGraph, 1);
        for (DirEdge edge : quickerMinPath.edges) {
            System.out.println(edge);
        }

    }
}
