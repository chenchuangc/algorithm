package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.graph_search;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;

import java.util.Iterator;

/**
 * Created by chencc on 2018/8/20.
 * 用来判断图中的某个点 v 相关的一系列操作
 */
public class SimpleSearch implements Search {

    private Graph graph;
    private int s ;
    private boolean[] marked;

    public SimpleSearch(Graph graph, int s) {
        this.graph=graph;
        this.s=s;
        marked=new boolean[graph.V()];
    }

    /**
     *
     * @param v
     * @return
     */
    public boolean marked(int v) {
        return search(s, v);
    }

    private boolean search(int s, int v) {
        marked[s]=true;
        Iterator<Integer> nebor = graph.adj(s).iterator();
        while (nebor.hasNext()) {
            int temp = nebor.next();
            if (temp == v) {
                System.out.println(" s:" + s + " v:" + v);
                return true;
            }
            if (!marked[temp]) {
                boolean isconnected = search(temp, v);
                if (isconnected) {
                    return true;
                }
            }

        }
        return false;

    }

    public int count() {
        return 0;
    }

    public static void main(String[] args) {
        Graph g = new SimpleGraph(7);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(4,6);
        g.addEdge(5,6);

        g.adj(2).forEach(System.out::println);
        System.out.println("-------");

        SimpleSearch simpleSearch = new SimpleSearch(g, 0);
        System.out.println(simpleSearch.marked(6));

    }
}
