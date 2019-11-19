package com.chen.learn.algorithm.sort.simple.practice.unit_4.min_path;

/**
 * Created by chencc on 2018/8/27.
 */
public class DirEdge {
    private int from;
    private int to;
    private double weight;

    public DirEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "DirEdge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
