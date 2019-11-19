package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_path;


/**
 * Created by chencc on 2018/8/27.
 */
public class MinPath {

     private DirEdge[] minRootTree;
     private double[] weightTo;
     private boolean[] marked;
     private DirWeightGraph dirGraph;

    public MinPath(DirWeightGraph dirGraph, int s) {
        this.dirGraph=dirGraph;
        minRootTree = new DirEdge[dirGraph.V()];
        weightTo = new double[dirGraph.V()];
        marked = new boolean[dirGraph.V()];
        for (int i = 0; i < weightTo.length; i++) {
            weightTo[i] = Double.POSITIVE_INFINITY;
        }
        weightTo[s]=0;
        deepFind(s);
    }

    private void deepFind(int s) {
        marked[s]=true;
        dirGraph.adj(s).forEach(e->{
            int to = e.getTo();
            int from = e.getFrom();

            if (weightTo[to] > (weightTo[from] + e.getWeight())) {
                weightTo[to]=weightTo[from]+e.getWeight();
                minRootTree[to]=e;
//                if (!marked[to]) {
                    deepFind(to);
//                }
            }
        });
    }

    public static void main(String[] args) {
        DirWeightGraph dirWeightGraph = new DirWeightGraph(5);
        dirWeightGraph.addEdge(new DirEdge(0,1,5.5));
        dirWeightGraph.addEdge(new DirEdge(0,2,3.5));
        dirWeightGraph.addEdge(new DirEdge(0,3,3.1));
        dirWeightGraph.addEdge(new DirEdge(3,2,1.5));
        dirWeightGraph.addEdge(new DirEdge(2,3,0.5));
        dirWeightGraph.addEdge(new DirEdge(1,2,0.2));
        dirWeightGraph.addEdge(new DirEdge(3,4,0.2));
        dirWeightGraph.addEdge(new DirEdge(2,4,0.1));
        dirWeightGraph.addEdge(new DirEdge(2,1,0.3));

        MinPath minPath = new MinPath(dirWeightGraph, 0);
        for (DirEdge dirEdge : minPath.minRootTree) {
            System.out.println(dirEdge);

        }
    }
}
