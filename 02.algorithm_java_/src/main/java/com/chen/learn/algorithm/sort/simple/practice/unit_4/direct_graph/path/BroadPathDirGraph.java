package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.path;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by chencc on 2018/8/21.
 */
public class BroadPathDirGraph implements PathsT{

    DirGraph dirGraph;
    boolean[] marked;
    int[] stepRecord;
    int start;

    @Override
    public void dfs(DirGraph graph, int start) {

        dirGraph=graph;
        marked = new boolean[graph.V()];
        stepRecord = new int[graph.V()];
        this.start=start;
        broadSearch(start);
    }

    private void broadSearch(int start) {
        marked[start] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            dirGraph.adj(node).forEach(e->{
                if (!marked[e]) {
                    stepRecord[e]=node;
                    marked[e]=true;
                }
            });
        }

    }

    @Override
    public boolean hasPathTo(int V) {
        return false;
    }

    @Override
    public Iterable<Integer> pathTo(int V) {
        return null;
    }

    @Override
    public Iterable<Integer> pathToV2(int V) {
        return null;
    }
}
