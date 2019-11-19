package com.chen.learn.algorithm.sort.simple.practice.unit_4.no_direct.symble_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by chencc on 2018/8/21.
 */
public class SymbleGrap {
    String[] nodeNumToName;
    Map<String,Integer> hashMap ;
    Set<Integer>[] container;

    private int v;

    public SymbleGrap(int v) {
        this.v = v;
        this.nodeNumToName = new String[v];
        this.hashMap = new HashMap<>();
        this.container = new HashSet[v];
        for (int i =0;i<v;i++) {
            container[i] = new HashSet<>();
        }
    }

    public void add(String one, String another) {
        Integer oneIndex = getIndex(one);
        Integer anotherIndex = getIndex(another);
        container[oneIndex].add(anotherIndex);
        container[anotherIndex].add(oneIndex);
    }

    private Integer getIndex(String one) {
        Integer index= hashMap.get(one);
        if (null == index) {
            index=hashMap.size();
            hashMap.put(one, index);
            nodeNumToName[index]=one;
        }
        return index;
    }

}
