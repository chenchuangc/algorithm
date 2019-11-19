package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_3_cross_river;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chencc on 2018/10/10.
 * 狼羊菜，农夫
 * 1,2,3
 */
public class CarrayCrossRiver {
    static class Stag {
        List<Integer> left;
        List<Integer> right;
        Integer withMan;
        Integer direct;//0,往左，1，往右

        public Stag(List<Integer> left, List<Integer> right, Integer withMan, Integer direct) {
            this.left = left;
            this.right = right;
            this.withMan = withMan;
            this.direct = direct;
        }

        boolean isDanger() {
            int leftCondition = left.stream().mapToInt(Integer::intValue).sum();
            int rightCondition = right.stream().mapToInt(Integer::intValue).sum();
            return leftCondition == 4 ||leftCondition == 8|| leftCondition == 9 || rightCondition == 4 || rightCondition == 8;
        }

        boolean isTarget() {
            return direct == 1 && right.stream().mapToInt(Integer::intValue).sum() + withMan == 9;
        }

        int sumLeft() {
            return left.stream().mapToInt(Integer::intValue).sum();
        }

        int sumRight() {
            return right.stream().mapToInt(Integer::intValue).sum();
        }

        @Override
        public String toString() {
            return "Stag{" +
                    "left=" + left +
                    ", right=" + right +
                    ", withMan=" + withMan +
                    ", direct=" + direct +
                    '}';
        }
    }

    static boolean[][][][] mark = new boolean[15][15][15][2];//left,right,withman
    static List<List<Stag>> container = new ArrayList<>();

//    static int[] eleToCarry = new int[]{1, 2, 3};

    static Stag initStag() {
        List<Integer> left = new ArrayList() {{
            add(0);
            add(1);
            add(3);
            add(5);
        }};
        Stag stag = new Stag(left, new ArrayList() {{
            add(0);
        }}, 0, -1);
        return stag;
    }

    static void get() {
        LinkedList<Stag> linkedList = new LinkedList<>();
        Stag initStag = initStag();
        int direct = 1;
        searchway(linkedList, initStag, direct);

    }

    private static void searchway(LinkedList<Stag> linkedList, Stag stag, int direct) {
        if (stag.isTarget()) {
            linkedList.addLast(stag);
            List<Stag> temp = new LinkedList<>();
            temp.addAll(linkedList);
            container.add(temp);
            linkedList.removeLast();
            return;
        } else {
            linkedList.addLast(stag);
            putDownWithMan(stag);
            if (direct == 0) {
                for (int aEle : stag.right) {
                    Stag next = carray(stag, direct, aEle);
                    dealNext(linkedList, direct, next);
                }
            } else {
                for (int aEle : stag.left) {
                    if (aEle == 5 && stag.sumLeft()==6) {
                        System.out.println();
                    }
                    Stag next = carray(stag, direct, aEle);
                    dealNext(linkedList, direct, next);
                }
            }
            linkedList.removeLast();
        }
    }

    private static void dealNext(LinkedList<Stag> linkedList, int direct, Stag next) {
        if (null != next) {
            int lsum =next.sumLeft(),rsum=next.sumRight(),with=next.withMan;
            mark[next.sumLeft()][next.sumRight()][next.withMan][direct] = true;
            searchway(linkedList, next, (direct + 1) % 2);
            mark[lsum][rsum][with][direct] = false;
        }
    }

    private static Stag carray(Stag stag, int direct, Integer aEle) {

        List<Integer> left = new LinkedList<>(stag.left);
        List<Integer> right = new LinkedList<>(stag.right);
        if (direct == 0) {
            if (aEle != 0) {
                right.remove(aEle);
            }
        } else {
            if (aEle != 0) {
                left.remove(aEle);
            }
        }
        Stag temp = new Stag(left, right, aEle, direct);
        if (!temp.isDanger() && !mark[temp.sumLeft()][temp.sumRight()][temp.withMan][direct]) {
            return temp;
        }
        return null;
    }

    private static void putDownWithMan(Stag stag) {
        if (stag.direct == 0) {
            if (stag.withMan != 0) {
                stag.left.add(stag.withMan);
            }
        } else if (stag.direct == 1) {
            if (stag.withMan != 0) {
                stag.right.add(stag.withMan);
            }
        }
    }

    public static void main(String[] args) {
        get();
        System.out.println("ok");
        printBest();
    }


    private static void printBest() {
        System.out.println("total:"+container.size());
        container.stream().forEachOrdered(e -> {e.stream().forEachOrdered(System.out::println);
                    System.out.println("++++++++++++++++++++++++++++:"+e.size());
                }
        );
    }
}
