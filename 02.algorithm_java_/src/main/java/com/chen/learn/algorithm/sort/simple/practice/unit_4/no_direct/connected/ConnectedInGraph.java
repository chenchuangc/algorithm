package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.connected;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;


/**
 * Created by chencc on 2018/8/21.
 */
public class ConnectedInGraph {

    int[] connectedSymble;
    boolean[] marked;
    int count;

    Graph g;

    public ConnectedInGraph(Graph g) {
        this.g = g;
        this.connectedSymble = new int[g.V()];
        this.marked = new boolean[g.V()];
        for (int i=0;i<g.V();i++) {
            if (!marked[i]) {
                deepSearch(i, i);
                count++;
            }
        }
    }

    private void deepSearch(int i, int symble) {
        marked[i]=true;
        connectedSymble[i]=symble;
        g.adj(i).forEach(e->{
            if (!marked[e]) {
                deepSearch(e, symble);
            }
        });
    }

    public boolean isConnected(int i, int j) {
        return connectedSymble[i] == connectedSymble[j];
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

        ConnectedInGraph connectedInGraph = new ConnectedInGraph(g);
        System.out.println(connectedInGraph.isConnected(2, 6));
        System.out.println(connectedInGraph.count);
    }
}
