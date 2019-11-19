package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.path;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.SimpleDirGraph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chencc on 2018/8/21.
 */
public class DeepPathDirGraph implements PathsT {

    DirGraph g;
    int[] stepRecord;
    boolean[] marked;
    int start;



    @Override
    public void dfs(DirGraph graph, int start) {
        this.g=graph;
        this.start=start;
        stepRecord = new int[g.V()];
        marked = new boolean[g.V()];
        deepSearch(start,start);
    }

    private void deepSearch(int start,int parent) {
            marked[start]=true;
            stepRecord[start] = parent;
            g.adj(start).forEach(e->{
                if (!marked[e]) {
                    deepSearch(e, start);
                }
            });

    }

    @Override
    public boolean hasPathTo(int V) {
        return marked[V];
    }

    @Override
    public Iterable<Integer> pathTo(int V) {
        List<Integer> list = new LinkedList<>();
        if (marked[V]) {
            int pre = V;
            while (pre != start) {
                list.add(pre);
                pre = stepRecord[pre];
            }
            list.add(pre);
        }
        Collections.reverse(list);
        return list;
    }

    @Override
    public Iterable<Integer> pathToV2(int V) {
        return null;
    }


    public static void main(String[] args) {
        DirGraph dg = new SimpleDirGraph(4);
        dg.addEdge(0, 1);
        dg.addEdge(1, 2);
        dg.addEdge(2, 3);
        dg.addEdge(1, 3);
        DeepPathDirGraph dpath = new DeepPathDirGraph();
        dpath.dfs(dg, 1);
        dpath.pathTo(3).forEach(System.out::println);

    }
}
