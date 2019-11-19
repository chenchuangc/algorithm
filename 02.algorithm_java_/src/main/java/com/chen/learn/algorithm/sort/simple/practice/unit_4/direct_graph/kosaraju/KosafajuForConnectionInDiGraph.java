package com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.kosaraju;

import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.DirGraph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.graph.SimpleDirGraph;
import com.chen.learn.algorithm.sort.simple.practice.unit_4.direct_graph.tuo_pu_sort.ThreeIteratorDirgraph;

import java.util.Stack;

/**
 * Created by chencc on 2018/8/22.
 * this algorithm `s code if few ,
 * but how to really know the heart of this is difficult
 * 联通分量的查找，
 * 参考这个讲的挺好 https://www.cnblogs.com/nullzx/p/6437926.html
 * 这个应该就是这个算法的原理，感觉好厉害的样子，带懂不懂的吧
 * 实际上就是先遍历拓扑结构中依赖最多的元素，然后一层一层的往下面更基础的去推
 */
public class KosafajuForConnectionInDiGraph {

        int[] connect;

        int count;
        boolean[] marked;

        DirGraph dirGraph;

    public KosafajuForConnectionInDiGraph(DirGraph dirGraph) {

        this.dirGraph = dirGraph;
        this.connect = new int[dirGraph.V()];
        this.marked = new boolean[dirGraph.V()];
        ThreeIteratorDirgraph threeGraph = new ThreeIteratorDirgraph(dirGraph.reverse());
        Stack<Integer> stck = threeGraph.getReflectStack();
        while (!stck.isEmpty()) {
            int ele = stck.pop();
            if (!marked[ele]) {
                deepSearch(ele, count);
                count++;
            }

        }
    }

    private void deepSearch(int ele, int count) {
        marked[ele]=true;
        connect[ele]=count;
        dirGraph.adj(ele).forEach(e->{
            if (!marked[e]) {
                deepSearch(e, count);
            }
        });
    }


    public static void main(String[] args) {
        DirGraph dg = new SimpleDirGraph(4);
        dg.addEdge(0, 1);
        dg.addEdge(1, 2);
        dg.addEdge(2, 3);
        dg.addEdge(3, 0);
        KosafajuForConnectionInDiGraph dpath = new KosafajuForConnectionInDiGraph(dg);
        System.out.println(dpath.count);

    }
}
