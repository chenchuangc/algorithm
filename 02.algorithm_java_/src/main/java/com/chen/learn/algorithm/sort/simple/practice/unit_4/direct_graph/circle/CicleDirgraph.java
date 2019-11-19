package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.circle;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.SimpleDirGraph;

import java.util.Stack;

/**
 * Created by chencc on 2018/8/22.
 */
public class CicleDirgraph {

    DirGraph dirGraph;
    boolean hasCircle;
    boolean[] marked;
    boolean[] inOneLine ;
    int[] stepRecord;
    Stack<Integer> stack = new Stack<>();

    public CicleDirgraph(DirGraph dirGraph) {
        this.dirGraph = dirGraph;
        marked = new boolean[dirGraph.V()];
        stepRecord = new int[dirGraph.V()];
        inOneLine = new boolean[dirGraph.V()];
        for (int i=0;i<dirGraph.V();i++) {
            if (!marked[i]) {
                deepSearch(i,i);
            }
        }
    }

    private void deepSearch(int i, int pre) {

        marked[i]=true;
        inOneLine[i]=true;
        stepRecord[i]=pre;
        dirGraph.adj(i).forEach(e->  {
            if (!marked[e]) {
                deepSearch(e, i);
            } else if (inOneLine[e]) {
                hasCircle=true;
                int index = i;
                while (index != e) {
                    stack.push(index);
                    index = stepRecord[index];
                }
                stack.push(index);
            }
        });
        inOneLine[i]=false;
    }


    public static void main(String[] args) {
        DirGraph dg = new SimpleDirGraph(4);
        dg.addEdge(0, 1);
        dg.addEdge(1, 2);
        dg.addEdge(2, 3);
        dg.addEdge(3, 1);
        CicleDirgraph dpath = new CicleDirgraph(dg);
        System.out.println(dpath.hasCircle);
        while (!dpath.stack.isEmpty()) {
            System.out.println(dpath.stack.pop());

        }

    }


    public boolean isHasCircle() {
        return hasCircle;
    }

    public Stack<Integer> getStack() {
        return stack;
    }
}
