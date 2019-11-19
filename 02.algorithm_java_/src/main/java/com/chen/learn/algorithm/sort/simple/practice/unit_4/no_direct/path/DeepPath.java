package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.path;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.Graph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.simple_grap.SimpleGraph;

import java.util.LinkedList;

/**
 * Created by chencc on 2018/8/20.
 * 这个地方的数据结构设计的很不合理，导致后面的遍历也是特别的复杂，所以良好的数据结构能够大大减小算法的复杂度
 * 没有想到用一个数组来记录步子
 */
public class DeepPath implements PathsT {

    private Graph g;
    private int start;
    private boolean marked[];

    private boolean steped[];


    @Override
    public void dfs(Graph graph, int start) {
        this.g = graph;
        this.start = start;
        marked = new boolean[g.V()];
        searchDeep(start);
    }

    private void searchDeep(int ele) {
        marked[ele] = true;
        g.adj(ele).forEach(e -> {
            if (!marked[e]) {
                searchDeep(e);
            }
        });
    }

    @Override
    public boolean hasPathTo(int V) {
        return marked[V];
    }

    /**
     * 这个地方的数据结构设计的很不合理，导致后面的遍历也是特别的复杂，所以良好的数据结构能够大大减小算法的复杂度
     * @param V
     * @return
     */
    @Override
    public Iterable<Integer> pathTo(int V) {
        steped = new boolean[g.V()];
        LinkedList<Integer> path = new LinkedList<>();
        findPath(path, start, V);
        return path;

    }

    private boolean findPath(LinkedList<Integer> path, int start, int v) {

        path.add(start);
        steped[start]=true;
        if (start == v) {
            return true;
        }
        boolean exit = false;
        for (int i : g.adj(start)) {
            if (!steped[i]) {
                exit |= findPath(path, i, v);
            }
        }
        if (!exit) {
            path.removeLast();
        }
        return exit;

    }

    @Override
    public Iterable<Integer> pathToV2(int V) {
        return null;
    }


    public static void main(String[] args) {
        Graph g = new SimpleGraph(7);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(2,5);
//        g.addEdge(2,3);
        g.addEdge(3,5);
        g.addEdge(4,5);
        g.addEdge(5,6);

        g.adj(2).forEach(System.out::println);
        System.out.println("-------");

        DeepPath deepPath = new DeepPath();
        deepPath.dfs(g, 3);
//        System.out.println(simpleSearch.marked(6));
        deepPath.pathTo(6).forEach(System.out::println);

    }
}
