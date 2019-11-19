package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_2_water;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * good you have done it
 * Created by chencc on 2018/10/10.
 * 这个题目几乎和上一个类似，就是数据建模的时候想明白了就ok了，如何正确的抽象数据模型是最重要的工作
 */
public class PeterWater {

    static class Bucket {
        int cap;
        int already;

        boolean isFull() {
            return cap == already;
        }

        public Bucket(int cap, int already) {
            this.cap = cap;
            this.already = already;
        }

        public int stay() {
            return cap - already;
        }

        @Override
        public String toString() {
            return "Bucket{" +
                    "cap=" + cap +
                    ", already=" + already +
                    '}';
        }
    }

    static class Stag {
        Bucket[] buckets;
        WaterAction action;

        public Stag(Bucket[] buckets, WaterAction action) {
            this.buckets = buckets;
            this.action = action;
        }

        @Override
        public String toString() {
            return "Stag{" +
                    "buckets=" + Arrays.toString(buckets) +
                    ", action=" + action +
                    '}';
        }
    }

    static class WaterAction {
        int from;
        int to;
        int weight;

        public WaterAction(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public WaterAction(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "WaterAction{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    static Bucket outbuck() {
        return new Bucket(200, 100);
    }

    static boolean[][] marked = new boolean[4][6];

    static Stag initStag() {
        Bucket first = new Bucket(3, 0);
        Bucket sec = new Bucket(5, 0);
        Bucket[] buckets = new Bucket[]{
                first, sec, outbuck()
        };
        return new Stag(buckets, null);
    }

    static WaterAction[] totalAction = new WaterAction[]{
            new WaterAction(0, 1),
            new WaterAction(0, 2),
            new WaterAction(1, 0),
            new WaterAction(1, 2),
            new WaterAction(2, 1),
            new WaterAction(2, 0)
    };

    static List<List<Stag>> container = new ArrayList<>();

    static void get() {

        LinkedList<Stag> linkedList = new LinkedList<>();
        Stag stag = initStag();
        marked[0][0] = true;
        searchWay(linkedList, stag);

    }

    private static void searchWay(LinkedList<Stag> linkedList, Stag stag) {

        if (isTarget(stag)) {
            linkedList.addLast(stag);
            List<Stag> temp = new ArrayList<>();
            temp.addAll(linkedList);
            container.add(temp);
            linkedList.removeLast();
        } else {
            linkedList.add(stag);
            stag.buckets[2] = outbuck();
            for (WaterAction aAction : totalAction) {
                Stag next = tryStag(stag, aAction);
                if (null != next) {

                    marked[next.buckets[0].already][next.buckets[1].already] = true;
                    searchWay(linkedList, next);
                    marked[next.buckets[0].already][next.buckets[1].already] = false;
                }
            }
        }
    }

    private static boolean isTarget(Stag stag) {

        return stag.buckets[1].already + stag.buckets[0].already == 4 || stag.buckets[1].already == 4;
    }

    private static Stag tryStag(Stag stag, WaterAction aAction) {
        if (stag.buckets[aAction.from].already == 0 || stag.buckets[aAction.to].isFull()) {
            return null;
        }
        int weight = stag.buckets[aAction.from].already >= stag.buckets[aAction.to].stay() ? stag.buckets[aAction.to].stay() : stag.buckets[aAction.from].already;
        Bucket first = new Bucket(stag.buckets[0].cap, stag.buckets[0].already);
        Bucket sec = new Bucket(stag.buckets[1].cap, stag.buckets[1].already);
        Bucket[] buckets = new Bucket[]{first, sec, outbuck()};
        buckets[aAction.from].already -= weight;
        buckets[aAction.to].already += weight;
        if (marked[buckets[0].already][buckets[1].already]) {
            return null;
        }
        WaterAction waterAction = new WaterAction(aAction.from, aAction.to, weight);
        return new Stag(buckets, waterAction);

    }

    public static void main(String[] args) {
        get();
        System.out.println("ok");
        printBest();
    }

    private static void printBest() {
        container.stream().forEachOrdered(e -> {
                    e.stream().forEachOrdered(System.out::println);
                    System.out.println("++++++++++++++++++++++++++++");
                }
        );
    }

}
