package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_tree;

import com.chen.learn.algorithm.sort.simple.practice.unit_2.chapter_2_3.MinPriority;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by chencc on 2018/8/23.
 * one base is that this graph must connected;
 */
public class MyMinTree {

    List<Edge> minTree;
    boolean[] marked;//if already in the mintree

    WeightGraph weightGraph;

    public MyMinTree(WeightGraph weightGraph) {
        this.weightGraph = weightGraph;
        minTree = new LinkedList<>();
        marked = new boolean[weightGraph.v()];
        MinPriority<Edge> minPriority = new MinPriority<>(50);
        marked[0] = true;
        visit(0, minPriority);
        findMin(minPriority);
    }

    private void visit(int i, MinPriority<Edge> minPriority) {
        weightGraph.adj(i).forEach(edge -> {
            if (!marked[edge.another(i)]) {
                minPriority.insert(edge);
            }
        });
    }

    private void findMin(MinPriority<Edge> minPriority) {
        while (!minPriority.isEmpty()) {
            Edge edge = minPriority.delete();
            int node = edge.either();
            if (!marked[node]) {
                node = edge.another(node);
            }
            if (!marked[edge.another(node)]) {
                minTree.add(edge);
                marked[edge.another(node)] = true;
                visit(edge.another(node), minPriority);
            }
        }
    }

    public List<Edge> getMinTree() {
        return minTree;
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

        MyMinTree myMinTree = new MyMinTree(weightGraph);
        myMinTree.getMinTree().forEach(System.out::println);
    }
}

