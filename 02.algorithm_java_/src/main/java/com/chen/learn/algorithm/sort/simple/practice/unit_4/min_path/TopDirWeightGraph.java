package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_path;

import java.util.*;

/**
 * Created by chencc on 2018/8/28.
 */
public class TopDirWeightGraph {

    Queue<Integer> prequeue = new ArrayDeque<>();
    Queue<Integer> reflectPreQueue = new ArrayDeque<>();
    Stack<Integer> reflectStack = new Stack<>();
    DirWeightGraph dirGraph;
    boolean[] marked;


    public TopDirWeightGraph(DirWeightGraph dirGraph) {
        this.dirGraph = dirGraph;
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
            if (!marked[e.getTo()]) {
                deepSearch(e.getTo());
            }
        });
        reflectPreQueue.offer(i);
        reflectStack.push(i);

    }

    public List<Integer> order() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        while (!reflectStack.isEmpty()) {
            linkedList.add(reflectStack.pop());
        }
        return linkedList;
    }

}
