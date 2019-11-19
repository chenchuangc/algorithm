package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.path;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;
import com.chen.learn.algorithm.util.Helper;

import java.util.Stack;

/**
 * Created by chencc on 2018/8/20.
 * 即使有环的话也不用担心
 */
public class BetterDeepPath implements PathsT {

    private Graph g;
    private int[] stepRecord;
    private boolean[] marked;
    int start ;



    @Override
    public void dfs(Graph graph, int start) {
        g=graph;
        this.start=start;
        stepRecord = new int[g.V()];
        marked = new boolean[g.V()];
        deepFind(start, 0);
        Helper.print(stepRecord);
    }

    private void deepFind(int start, int pre) {
        marked[start]=true;
        stepRecord[start]=pre;
        g.adj(start).forEach(e->{
            if (!marked[e]) {
                deepFind(e, start);
            }

        });
    }

    @Override
    public boolean hasPathTo(int V) {
        return marked[V];
    }

    @Override
    public Iterable<Integer> pathTo(int V) {
        if (!hasPathTo(V)) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        int pre = V;
        while (pre != start) {
            stack.push(pre);
            pre = stepRecord[pre];
        }
        stack.push(pre);
        return stack;
    }

    @Override
    public Iterable<Integer> pathToV2(int V) {
        return null;
    }

    public static void main(String[] args) {
        Graph g = new SimpleGraph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(1, 5);
        g.addEdge(0, 5);
//        g.addEdge(2,3);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(5, 6);

        g.adj(2).forEach(System.out::println);
        System.out.println("-------");

        BetterDeepPath deepPath = new BetterDeepPath();
        deepPath.dfs(g, 0);

        deepPath.pathTo(6).forEach(System.out::println);

    }
}
