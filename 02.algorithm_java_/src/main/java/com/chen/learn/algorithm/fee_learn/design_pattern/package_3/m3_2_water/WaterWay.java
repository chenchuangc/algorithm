package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_2_water;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chencc on 2018/10/9.
 * 这个问题的建模，想了好久不知道怎么建模
 * <p>
 * 其实可以把问题的求解看成得到想要的最终状态
 * 然后不停的在状态上施加动作进行驱动改变
 * 如何对动作进行枚举，本身是一个问题，要去思考和分析出来具体的逻辑
 * bucket 对象描述-->描述 一个桶的当前的水和总容量
 * stage 描述经历一次倒水以后的三个桶的
 */
public class WaterWay {

    static class Bucket {
        int cap;
        int already;

        public Bucket(int cap, int already) {
            this.cap = cap;
            this.already = already;

        }

        boolean isfull() {
            return cap == already;
        }

        int stay() {
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
        ActionWay action;

        public Stag(Bucket[] buckets, ActionWay action) {
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

    static class ActionWay {
        int from;
        int to;
        int weight;

        public ActionWay(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public ActionWay(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "ActionWay{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    static ActionWay[] totalAction = new ActionWay[]{
            new ActionWay(0, 1),
            new ActionWay(0, 2),
            new ActionWay(1, 0),
            new ActionWay(1, 2),
            new ActionWay(2, 0),
            new ActionWay(2, 1)
    };

    static boolean[][][] mark = new boolean[8+1][3+1][5+1];

    public static Stag initStag() {
        Bucket first = new Bucket(8, 8);
        Bucket sec = new Bucket(3, 0);
        Bucket third = new Bucket(5, 0);
        Bucket[] stageBucket = new Bucket[]{first, sec, third};
        mark[8][0][0]=true;
        return new Stag(stageBucket, null);
    }

    static List<List<Stag>> container = new ArrayList<>();

    static void get() {
        LinkedList<Stag> linkedList = new LinkedList<>();
        Stag stag = initStag();
        getIterator(stag, linkedList);
    }

    private static void getIterator(Stag stag, LinkedList<Stag> linkedList) {
        if (isTarget(stag)) {
            linkedList.addLast(stag);
            List<Stag> temp = new ArrayList<>();
            temp.addAll(linkedList);
            container.add(temp);
            linkedList.removeLast();
            return;
        } else {
            linkedList.addLast(stag);
            for (ActionWay aAct : totalAction) {
                Stag ostag = doAction(stag, aAct);
                if (ostag != null) {
                    mark[ostag.buckets[0].already][ostag.buckets[1].already][ostag.buckets[2].already] = true;
                    getIterator(ostag, linkedList);
                    mark[ostag.buckets[0].already][ostag.buckets[1].already][ostag.buckets[2].already] = false;
                }
            }
            linkedList.removeLast();
        }
    }

    private static boolean isTarget(Stag stag) {
        return stag.buckets[0].already == 4 && stag.buckets[2].already == 4;
    }

    private static Stag doAction(Stag stag, ActionWay aAct) {
        if (stag.buckets[aAct.from].already == 0 || stag.buckets[aAct.to].isfull()) {
            return null;
        }
        int weight = stag.buckets[aAct.from].already >= stag.buckets[aAct.to].stay() ? stag.buckets[aAct.to].stay() : stag.buckets[aAct.from].already;
        Bucket first = new Bucket(stag.buckets[0].cap, stag.buckets[0].already);
        Bucket sec = new Bucket(stag.buckets[1].cap, stag.buckets[1].already);
        Bucket third = new Bucket(stag.buckets[2].cap, stag.buckets[2].already);
        ActionWay actionWay = new ActionWay(aAct.from, aAct.to, weight);
        Bucket[] stagBucket = new Bucket[]{first, sec, third};
        stagBucket[aAct.from].already -= weight;
        stagBucket[aAct.to].already += weight;
        //已经到达过
        if (mark[stagBucket[0].already][stagBucket[1].already][stagBucket[2].already]) {
            return null;
        }
        return new Stag(stagBucket, actionWay);
    }

    public static void main(String[] args) {
        get();
        printBest();
        System.out.println("ok,total:"+container.size());
    }

    private static void printBest() {
        container.stream().forEachOrdered(e -> {e.stream().forEachOrdered(System.out::println);
                    System.out.println("++++++++++++++++++++++++++++");
        }
        );
    }

}
