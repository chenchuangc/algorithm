package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.two_color;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;
import com.chen.learn.algorithm.util.Helper;


/**
 * Created by chencc on 2018/8/21.
 */
public class IsTwoColorGraph {
    String[] color;
    boolean[] marked;
    Graph g;
    boolean is_two_color=true;

    public IsTwoColorGraph(Graph g) {
        this.g = g;
        color = new String[g.V()];
        marked = new boolean[g.V()];

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                deepSearch(i, "red");
            }
        }
        Helper.print(marked);
        Helper.print(color);
    }

    private void deepSearch(int i, String col) {
        marked[i] = true;
        color[i] = col;
        g.adj(i).forEach(e -> {
            if (!marked[e]) {
                deepSearch(e, getColor(col));
            } else {
                if (color[e].equals(col)) {
                    is_two_color = false;
                }
            }
        });

    }

    private String getColor(String col) {
        if (col.equals("red")) {
            return "black";
        } else {
            return "red";
        }
    }


    public static void main(String[] args) {
        Graph g = new SimpleGraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
//        g.addEdge(0, 3);
//        g.addEdge(4, 6);
//        g.addEdge(1, 6);

        g.adj(2).forEach(System.out::println);
        System.out.println("-------");


        IsTwoColorGraph colorGraph = new IsTwoColorGraph(g);
        System.out.println(colorGraph.is_two_color);

    }
}
