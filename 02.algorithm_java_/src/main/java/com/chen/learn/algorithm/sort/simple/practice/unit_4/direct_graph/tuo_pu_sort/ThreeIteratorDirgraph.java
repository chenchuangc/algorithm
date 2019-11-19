package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.tuo_pu_sort;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.SimpleDirGraph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by chencc on 2018/8/22.
 */
public class ThreeIteratorDirgraph {

    Queue<Integer> prequeue = new ArrayDeque<>();
    Queue<Integer> reflectPreQueue = new ArrayDeque<>();
    Stack<Integer> reflectStack = new Stack<>();
    DirGraph dirGraph;
    boolean[] marked;
    public ThreeIteratorDirgraph(DirGraph dirGraph) {
        this.dirGraph=dirGraph;
        marked = new boolean[dirGraph.V()];
        for (int i=0;i<dirGraph.V();i++) {
            if (!marked[i]) {

                deepSearch(i);
            }
        }
    }

    private void deepSearch(int i) {
        marked[i]=true;
        prequeue.offer(i);
        dirGraph.adj(i).forEach(e->{
            if (!marked[e]) {
                deepSearch(e);
            }
        });
        reflectPreQueue.offer(i);
        reflectStack.push(i);

    }

    public Queue<Integer> getPrequeue() {
        return prequeue;
    }

    public Queue<Integer> getReflectPreQueue() {
        return reflectPreQueue;
    }

    public Stack<Integer> getReflectStack() {
        return reflectStack;
    }


    public static void main(String[] args) {
        DirGraph dg = new SimpleDirGraph(4);
        dg.addEdge(0, 1);
        dg.addEdge(1, 2);
        dg.addEdge(3, 2);
        dg.addEdge(1, 3);
        ThreeIteratorDirgraph dpath = new ThreeIteratorDirgraph(dg);
        Stack<Integer> stack = dpath.getReflectStack();
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }
}
