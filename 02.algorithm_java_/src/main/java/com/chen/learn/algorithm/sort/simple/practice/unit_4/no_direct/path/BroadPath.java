package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.path;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;
import com.chen.learn.algorithm.util.Helper;

import java.util.*;

/**
 * Created by chencc on 2018/8/20.
 */
public class BroadPath implements PathsT {

    private Graph g;
    private boolean[] marked;
    private int[] stepRecord;
    private int start;

    @Override
    public void dfs(Graph graph, int start) {
        this.g = graph;
        this.start = start;
        marked = new boolean[g.V()];
        stepRecord = new int[g.V()];
        broadSearch(start);
        Helper.print(stepRecord);
        Helper.print(marked);
    }

    private void broadSearch(int start) {
        Queue<Integer> child = new ArrayDeque<>();
        child.offer(start);
        marked[start]=true;
        broadSearch(child);
    }

    /**
     * 这个地方的思维逻辑性不够严密，导致有环路的时候出错，需要进行加强训练
     * @param child
     * @param
     */
    private void broadSearch(Queue<Integer> child) {
        while (!child.isEmpty()) {
            int temp = child.poll();
//            stepRecord[temp]=pre;
//            marked[temp] = true;
            g.adj(temp).forEach(e -> {
                if (!marked[e]) {
                    child.offer(e);
                    stepRecord[e] = temp;
                    marked[e]=true;
                }
            });
        }
    }

    @Override
    public boolean hasPathTo(int V) {
        return marked[V];
    }

    @Override
    public Iterable<Integer> pathTo(int V) {
        List<Integer> list = new ArrayList<>();
        int pre = V;
        while (pre != start) {
            list.add(pre);
            pre = stepRecord[pre];
        }
        list.add(pre);
        Collections.reverse(list);
        return list;

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
        g.addEdge(4, 6);
        g.addEdge(3, 6);
        g.addEdge(2, 6);
//        g.addEdge(2,3);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(5, 6);

        g.adj(2).forEach(System.out::println);
        System.out.println("-------");

        BroadPath broadPath = new BroadPath();
        broadPath.dfs(g, 0);

        broadPath.pathTo(2).forEach(System.out::println);

    }
}
