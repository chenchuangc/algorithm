package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.search;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.graph_search.Search;

/**
 * Created by chencc on 2018/8/21.
 */
public class SearchDirGraph implements Search {

    DirGraph g;
    boolean[] marked;
    int start;
    int count;


    public SearchDirGraph(DirGraph g, int start) {
        this.g = g;
        this.start = start;
        marked = new boolean[g.V()];
        deepSearch(start);
    }

    private void deepSearch(int start) {
        marked[start]=true;
        count++;
        g.adj(start).forEach(e->{
            if (!marked[e]) {
                deepSearch(e);
            }
        });
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }
}
