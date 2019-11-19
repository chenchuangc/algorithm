package com.chen.learn.algorithm.sort.simple.practice.P_1_4;


import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
/**
 * Created by chencc on 2018/6/17.
 */
public class Test {

    public static void main(String args[]) {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }
    }
}
