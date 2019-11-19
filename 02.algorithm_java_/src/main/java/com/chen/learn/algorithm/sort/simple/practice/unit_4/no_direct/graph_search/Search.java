package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.graph_search;

/**
 * Created by chen on 2017/9/25.
 */
public interface Search {

    /**
     * is v  connected with s in this graph
     * @param v
     * @return
     */
    boolean marked(int v);

    /**
     * the size of the part s in this graph
     * @return
     */
    int count();

}
