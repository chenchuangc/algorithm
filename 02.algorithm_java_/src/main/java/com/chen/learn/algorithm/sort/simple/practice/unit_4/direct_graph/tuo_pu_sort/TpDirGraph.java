package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.tuo_pu_sort;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.circle.CicleDirgraph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by chencc on 2018/8/22.
 */
public class TpDirGraph {

    ThreeIteratorDirgraph threeGraph ;
    CicleDirgraph cicleDirgraph;

    public TpDirGraph(DirGraph dirGraph) {
        cicleDirgraph = new CicleDirgraph(dirGraph);
        threeGraph = new ThreeIteratorDirgraph(dirGraph);
    }

    public Iterable getSort() {
        if (cicleDirgraph.isHasCircle()) {
            return null;
        } else {
            List<Integer> list = new LinkedList<>();
            Stack<Integer> stack = threeGraph.getReflectStack();
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
            return list;
        }
    }



}
