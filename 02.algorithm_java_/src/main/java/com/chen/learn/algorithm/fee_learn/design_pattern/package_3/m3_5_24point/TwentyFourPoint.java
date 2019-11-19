package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_5_24point;

import com.chen.learn.algorithm.util.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by chencc on 2018/10/31.
 * 24点计数求解
 */
public class TwentyFourPoint {

    static class Ele {
        double val;
        String exp;

        public Ele(double val, String exp) {
            this.val = val;
            this.exp = exp;
        }
    }

    static    BiFunction<Ele, Ele, Ele> add = (f, s) -> new Ele(f.val + s.val, "("+f.exp + " + " + s.exp+")");
    static    BiFunction<Ele, Ele, Ele> mutiply = (f, s) -> new Ele(f.val * s.val, "("+f.exp + " * " + s.exp+")");
    static   BiFunction<Ele, Ele, Ele> div = (f, s) -> new Ele(f.val / s.val, "("+f.exp + " / " + s.exp+")");
    static   BiFunction<Ele, Ele, Ele> sub = (f, s) -> new Ele(f.val - s.val, "("+f.exp + " - " + s.exp+")");

   static BiFunction<Ele, Ele, Ele>[] operations = new BiFunction[]{add,mutiply,div,sub};

    public static List<String> getCal(ArrayList<Integer> arrays,double target) {

        List<String> collector = new ArrayList<>();
        ArrayList<Ele> eles = new ArrayList<>();
        arrays.forEach(e -> eles.add(new Ele(e, e.toString())));
        tryCalculate(eles, collector,target);
        return collector;
    }

    private static void tryCalculate(ArrayList<Ele> eles, List<String> collector,double target) {

        if (eles.size() == 1) {
            if (Helper.toPositive(eles.get(0).val - target) < 0.01) {
                collector.add(eles.get(0).exp);
            }
            return;
        }
        int size = eles.size();
        for (int i=0;i<size;i++) {
            Ele first = eles.get(i);
            for (int j=0;j<size;j++) {
                if (j == i) {
                    continue;
                }
                Ele sec = eles.get(j);
                for (BiFunction<Ele, Ele, Ele> op : operations) {
                    Ele res = op.apply(first, sec);
                    ArrayList<Ele> list = new ArrayList<>();
                    list.add(res);
                    for(int k=0;k<size;k++) {
                        if (k != j && k != i) {
                            list.add(eles.get(k));
                        }
                    }
                    tryCalculate(list, collector, target);
                }
            }
        }



    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 3, 7, 1));
        getCal(list,24).forEach(System.out::println);
    }

}
