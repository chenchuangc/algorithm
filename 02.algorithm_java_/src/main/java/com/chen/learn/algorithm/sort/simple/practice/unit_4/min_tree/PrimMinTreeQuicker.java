package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_tree;

import com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3.IndexMinPriority;
import com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3.StanderdIndexMinPriority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencc on 2018/8/25.
 * 这个是即时版的prim min  tree 相对于延迟版的优化就是小顶堆里面不会存储太多数据了，只会每个顶点存储一个，这样的话，访问的次数会减少很多
 */
public class PrimMinTreeQuicker {
    private Edge[] edgeTo;//记录该顶点到达树的最小顶点
    private boolean[] marked;
    private double[] weightTo;

    private List<Edge> minTree;

    private WeightGraph weightGraph;

    public PrimMinTreeQuicker(WeightGraph weightGraph) {
        this.weightGraph = weightGraph;
        weightTo = new double[weightGraph.v()];
        for (int i = 0; i < weightTo.length; i++) {
            weightTo[i] = Double.MAX_VALUE;
        }
        edgeTo = new Edge[weightGraph.v()];
        marked = new boolean[weightGraph.v()];
        minTree = new ArrayList<>();
        StanderdIndexMinPriority<Double> minPriority = new StanderdIndexMinPriority<>(10);
//        edgeTo[0]=0;
        weightTo[0] = 0;
        marked[0] = true;
        minPriority.insert(0, 0d);
        visit(minPriority);
    }

    private void visit(StanderdIndexMinPriority<Double> minPriority) {
        while (!minPriority.isEmpty()) {
            int v = minPriority.delete();
            marked[v] = true;
            weightGraph.adj(v).forEach(edge -> {
                int another = edge.another(v);
                if (marked[another]) {

                } else {
                    if (edge.getWeight() < weightTo[another]) {
                        edgeTo[another] = edge;
                        weightTo[another] = edge.getWeight();
                        if (minPriority.contains(another)) {
                            minPriority.change(another, edge.getWeight());
                        } else {
                            minPriority.insert(another, edge.getWeight());
                        }
                    }
                }
            });
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

        PrimMinTreeQuicker quicker = new PrimMinTreeQuicker(weightGraph);
        for (Edge edge : quicker.edgeTo) {
            System.out.println(edge);

        }
    }
}
