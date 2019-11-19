package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.circle;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;
import com.chen.learn.algorithm.util.Helper;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chencc on 2018/8/21.
 */
public class FindCircleInGraph implements FindCircle{

    private Graph g;
    private boolean hasCircle;
    private int[] stepRecord;
    private boolean[] marked;
    private int circle_end;
    private int circle_begin;

    public FindCircleInGraph(Graph g) {
        this.g = g;
        stepRecord = new int[g.V()];
        marked = new boolean[g.V()];
        for (int i=0;i<g.V();i++) {
            if (hasCircle()) {
                break;
            }
            if (!marked[i]) {
                deepSearch(i, i);
            }
        }
        Helper.print(stepRecord);

    }

    private void deepSearch(int i, int parent) {

        marked[i] = true;
        stepRecord[i]=parent;
        for (int j : g.adj(i)) {
            //因为使用了递归，所以在调用的时候有些时候会出一些问题。必须在处理前判断是否已经找到了环，如果已经找到了就不再找了
            if (hasCircle) {
                break;
            }
            if (!marked[j]) {
                    deepSearch(j, i);
            } else {
                if (j != parent) {
                    hasCircle=true;
                    circle_end = i;
                    circle_begin=j;
                    break;
                }
            }
        }

    }

    @Override
    public boolean hasCircle() {
        return hasCircle;
    }

    @Override
    public Iterable<Integer> circle() {
        List<Integer> list = new LinkedList<>();
        int pre = circle_end;
        while (pre != circle_begin) {
            list.add(pre);
            pre = stepRecord[pre];
        }
        list.add(pre);
        Collections.reverse(list);
        return list;
    }


    public static void main(String[] args) {
        Graph g = new SimpleGraph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(3, 5);
        g.addEdge(2, 4);
        g.addEdge(4, 6);
        g.addEdge(1, 6);

        g.adj(2).forEach(System.out::println);
        System.out.println("-------");

        FindCircleInGraph circleInGraph = new FindCircleInGraph( g);
        System.out.println(circleInGraph.hasCircle);
        for (Integer integer : circleInGraph.circle()) {
            System.out.println(integer);

        }

//        System.out.println(psearch.marked(6));

    }

}
