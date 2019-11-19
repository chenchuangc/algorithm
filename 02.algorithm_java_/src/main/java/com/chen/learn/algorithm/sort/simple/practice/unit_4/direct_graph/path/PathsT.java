package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.path;


import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;

/**
 * Created by chen on 2017/9/25.
 */
public interface PathsT {


    void dfs(DirGraph graph, int start);

    boolean hasPathTo(int V);

    Iterable<Integer> pathTo(int V);

    Iterable<Integer> pathToV2(int V);
}
