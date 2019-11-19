package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_tree;

/**
 * Created by chencc on 2018/8/23.
 */
public class Edge implements Comparable<Edge> {
    private int one;
    private int another;
    private double weight;

    public Edge(int one, int another, double weight) {
        this.one = one;
        this.another = another;
        this.weight = weight;
    }

    public int either() {
        return one;
    }

    public int another(int node) {
        if (one == node) {
            return another;
        } else {
            return one;
        }
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight > o.getWeight() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "one=" + one +
                ", another=" + another +
                ", weight=" + weight +
                '}';
    }
}
