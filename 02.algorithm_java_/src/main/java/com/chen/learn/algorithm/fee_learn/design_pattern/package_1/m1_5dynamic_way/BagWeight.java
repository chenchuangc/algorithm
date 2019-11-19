package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by chencc on 2018/9/17.
 * 感觉动态规划的过程实际上有点类似于穷举，就是思路不太一样
 */
public class BagWeight {
    static Map<String, Bean> mapaaa = new HashMap();
    static Map<String, Bean> path = new HashMap<>();

    static class Bean{
        int val;
        int weight;
        int bagsize;
        int index;
        boolean hold;

        @Override
        public String toString() {
            return "Bean{" +
                    "val=" + val +
                    ", weight=" + weight +
                    ", bagsize=" + bagsize +
                    ", index=" + index +
                    ", hold=" + hold +
                    '}';
        }

        public Bean(int val, int bagsize, int index, boolean hold , int weight) {
            this.weight = weight;
            this.bagsize = bagsize;
            this.index = index;
            this.hold = hold;
            this.val=val;

        }
    }


    public static int getMax(int bagSize, Map.Entry<Integer, Integer>[] goods) {

        int max = get(bagSize, goods, goods.length - 1);
        dealWith(bagSize,goods.length-1);
        return max;

    }

    /**
     * 倒着追溯可以找到对应的数据，只有知道最后的才能递推之前的
     * @param bagSize
     * @param index
     */
    private static void dealWith(int bagSize, int index) {
        if (index < 0||bagSize<=0) {
            return;
        }
        String key = bagSize+"_"+index;
        Bean max = mapaaa.get(key);
        path.put(key, max);
        if (max.hold) {
            dealWith(bagSize - max.weight, index - 1);
        } else {
            dealWith(bagSize, index - 1);
        }
    }


    private static int get(int bagSize, Map.Entry<Integer, Integer>[] goods, int index) {

        //边界：1.包里没空  2.东西装完了，没有东西可装了 3.包里有空，但是装不进去当前物品了
        if (index < 0) {
            return 0;
        }
        boolean canHoldAny = false;
        for (int i = 0; i <= index; i++) {
            if (goods[i].getKey() <= bagSize) {
                canHoldAny = true;
                break;
            }
        }
        if (!canHoldAny) {
            return 0;
        }
        String key = bagSize + "_" + index;
        if (null != mapaaa.get(key)) {
            return mapaaa.get(key).val;
        }
        int notTake = get(bagSize, goods, index - 1);
        if (index == 1 && bagSize == 1) {
            System.out.println("ok");

        }
        int take =0;
        //刚开始漏掉了这里的边界处理，坑死自己了
        if (goods[index].getKey() <= bagSize) {
            take = get((bagSize - goods[index].getKey()), goods, index - 1) + goods[index].getValue();
        }
        System.out.print("index:" + index + " bagsize: " + bagSize + "  val:" + (take > notTake ? take : notTake));
        if (take > notTake) {
            System.out.println(" token: yes");
            mapaaa.put(key, new Bean(take,bagSize,index,true,goods[index].getKey()));
            return take;
        } else {
            System.out.println(" token: no");
//            mapaaa.put(key, notTake);
            mapaaa.put(key, new Bean(notTake,bagSize,index,false,0));

            return notTake;
        }


    }

    public static void main(String[] args) {
        Map.Entry<Integer, Integer>[] kk = new Map.Entry[]{
                new HashMap.SimpleEntry<>(7, 10),
                new HashMap.SimpleEntry<>(2, 4),
                new HashMap.SimpleEntry<>(3, 3),
                new HashMap.SimpleEntry<>(5, 3),
                new HashMap.SimpleEntry<>(3, 5),
                new HashMap.SimpleEntry<>(4, 6),
                new HashMap.SimpleEntry<>(5, 8),
                new HashMap.SimpleEntry<>(1, 1),
        };

        System.out.println(getMax(21, kk));
        path.entrySet().stream().filter(e->e.getValue().hold).forEach((e) -> System.out.println(e.getValue().toString()));
    }

}
