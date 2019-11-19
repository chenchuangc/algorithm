package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.graph_search;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;
import com.chen.learn.algorithm.util.Helper;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by chencc on 2018/8/20.
 */
public class PreparedSearch implements Search {

    private int v;
    private Graph g;
    private boolean[] marked;
    private int count = 0;

    public PreparedSearch(int v, Graph g) {
        this.v = v;
        this.g = g;
        this.marked = new boolean[g.V()];
        mark();
        Helper.print(marked);

    }

    private void mark() {
//        deep_search(v);
        broad_search(v);
    }

    private void deep_search(int v) {
        marked[v] = true;
        count++;
        g.adj(v).forEach(e -> {
            if (!marked[e]) {
                deep_search(e);
            }
        });
    }

    private void broad_search(int v) {
        Queue<Integer> child = new ArrayDeque<>();
        child.offer(v);
        broad_search(child);
    }


    private void broad_search(Queue<Integer> child) {

        while (!child.isEmpty()) {
            int ele = child.poll();
            if (!marked[ele]) {
                marked[ele] = true;
                count++;
                g.adj(ele).forEach(e -> {
                    if (!marked[e]) {
                        child.offer(e);
                    }
                });
            }
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Graph g = new SimpleGraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(2, 5);
        g.addEdge(2, 4);
        g.addEdge(4, 6);
        g.addEdge(5, 6);

        g.adj(2).forEach(System.out::println);
        System.out.println("-------");

        PreparedSearch psearch = new PreparedSearch(0, g);

        System.out.println(psearch.marked(6));

    }
}
