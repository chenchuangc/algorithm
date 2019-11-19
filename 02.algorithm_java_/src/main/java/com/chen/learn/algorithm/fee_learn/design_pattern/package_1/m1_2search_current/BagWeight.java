package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_2search_current;


import java.util.*;

/**
 * Created by chencc on 2018/9/21.
 * 背包问题
 * 边界是 bagsize < goods.weight 记住，不带等号
 */
public class BagWeight {

    static class Goods implements Comparable<Goods>{
        int weight;
        int price;
        int status;//1 choose; 2 cannot choose ; 0 have not check;

        public Goods(int weight, int price, int status) {
            this.weight = weight;
            this.price = price;
            this.status = status;
        }

        /**
         * 这里只是实现了一种
         * @param o
         * @return
         */
        @Override
        public int compareTo(Goods o) {
            return this.price * 1.0f / this.weight - o.price * 1.0f / o.weight > 0 ? 1 : -1;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "weight=" + weight +
                    ", price=" + price +
                    ", status=" + status +
                    '}';
        }
    }

    public static Goods[] search_current(Map.Entry<Integer, Integer>[] allGoods,int bagsize) {
        List<Goods> goodsList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> agoods : allGoods) {
            goodsList.add(new Goods(agoods.getKey(), agoods.getValue(), 0));
        }
        Collections.sort(goodsList);
        for (int i =  goodsList.size()-1;i>=0; i--) {
            Goods goods = goodsList.get(i);
            //刚开始这个地方的等号没有注意到，就出问题了，回头写代码的时候还是要先分析，建模，写出逻辑公式和边界，然后再开始写
            if (bagsize >= goods.weight) {
                goods.status = 1;
                bagsize -= goods.weight;
            } else {
                goods.status = 2;
            }

        }
       return goodsList.stream().filter(e -> e.status == 1).collect(ArrayList::new, ArrayList::add, ArrayList::addAll).toArray(new Goods[]{});

    }


    public static void main(String[] args) {
        Map.Entry<Integer, Integer> ele  = new HashMap.SimpleEntry<>(35, 10);
        Map.Entry<Integer, Integer> ele1 = new HashMap.SimpleEntry<>(30, 40);
        Map.Entry<Integer, Integer> ele2 = new HashMap.SimpleEntry<>(60, 30);
        Map.Entry<Integer, Integer> ele3 = new HashMap.SimpleEntry<>(50, 50);
        Map.Entry<Integer, Integer> ele4 = new HashMap.SimpleEntry<>(40, 35);
        Map.Entry<Integer, Integer> ele5 = new HashMap.SimpleEntry<>(10, 40);
        Map.Entry<Integer, Integer> ele6 = new HashMap.SimpleEntry<>(25, 30);
        Map.Entry<Integer, Integer>[] src = new  Map.Entry[]{ele,ele1,ele2,ele3,ele4,ele5,ele6};
        Goods[] res = search_current(src,150);

        for (Goods g : res) {
            System.out.println(g);
        }

    }

}
