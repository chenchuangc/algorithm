package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.path;


import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;

/**
 * Created by chen on 2017/9/25.
 */
public interface PathsT {


    void dfs(Graph graph, int start);

    boolean hasPathTo(int V);

    Iterable<Integer> pathTo(int V);

    Iterable<Integer> pathToV2(int V);
}
