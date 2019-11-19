package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_4_aiyinsitan;

import com.chen.learn.algorithm.util.Helper;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by chencc on 2018/10/11.
 * aiyinsitan问题
 * 问题的建模方式很重要啊，如果是选用你最开始想的一位数组的方式，估计很难进行算法实现了，
 * 选择了合理的数学模型则能够很快的得到问题的求解
 * 还是要培养合理的建模思维，多用基本的数学模型
 */
public class AyinsTan {

    static class Item{
        int type;
        int val;

        public Item(int type, int val) {
            this.type = type;
            this.val = val;
        }
    }

    public static void iterator(int[][] groups, int col_type, int row_index) {
        if (row_index == 4) {
            if (col_type != SMOKE) {
                iterator(groups, col_type + 1, 0);
            } else {
                filter(groups);
            }
        } else {
            for (int type_val=row_index;type_val<=4;type_val++) {
                exchage(groups, col_type, row_index, type_val);
                iterator(groups, col_type, row_index + 1);
                exchage(groups, col_type,  type_val,row_index);
            }

        }

    }

    private static void exchage(int[][] groups, int col_type, int end, int start) {
        int temp = groups[end][col_type];
        groups[end][col_type] = groups[start][col_type];
        groups[start][col_type]=temp;
    }


    static int HOUSE = 0;
    static int DRINK = 1;
    static int PET= 2;
    static int COUNTRY= 3;
    static int SMOKE = 4;


    static int RED = 0;
    static int GREEN = 1;
    static int WHITE = 2;
    static int BLUE = 3;
    static int YELLOW = 4;

    static int ENGLAND = 0;
    static int RUIDIAN = 1;
    static int NUOWEI = 2;
    static int DANMAI = 3;
    static int DEGUO = 4;

    static int DOG = 0;
    static int BIRD = 1;
    static int HOURSE = 2;
    static int FISH = 3;
    static int CAT = 4;

    static int TEA = 0;
    static int MILK = 1;
    static int WATER = 2;
    static int COFFE = 3;
    static int BEER = 4;

    static int Pall_Mall = 0;
    static int Dunhill = 1;
    static int Blends = 2;
    static int BlueMaster = 3;
    static int Prince = 4;


    static Item[] englandWithRedHouse = new Item[]{new Item(COUNTRY, ENGLAND), new Item(HOUSE, RED)};
    static Item[] redianWithDog = new Item[]{new Item(COUNTRY, RUIDIAN), new Item(PET, DOG)};
    static Item[] danmaiWithTea = new Item[]{new Item(COUNTRY, DANMAI), new Item(DRINK, TEA)};
    static Item[] greenHouseWithCoffe = new Item[]{new Item(HOUSE, GREEN), new Item(DRINK, COFFE)};
    static Item[] pallMallWithBird = new Item[]{new Item(SMOKE, Pall_Mall), new Item(PET, BIRD)};
    static Item[] yellowWithDirll = new Item[]{new Item(HOUSE, YELLOW), new Item(SMOKE, Dunhill)};
    static Item[] BlueMasterWithBeer = new Item[]{new Item(SMOKE, BlueMaster), new Item(DRINK, BEER)};
    static Item[] germanWithPrince = new Item[]{new Item(COUNTRY, DEGUO), new Item(SMOKE, Prince)};
    static List<Item[]> atomicRuleList = Arrays.asList(englandWithRedHouse,redianWithDog,danmaiWithTea,
            greenHouseWithCoffe,pallMallWithBird,
            yellowWithDirll,BlueMasterWithBeer,germanWithPrince);



    static Item[] greenBeforeWhiteHouse = new Item[]{new Item(HOUSE,GREEN),new Item(HOUSE,WHITE)};

    static Item[] BlendsNearCat = new Item[]{new Item(SMOKE, Blends), new Item(PET, CAT)};
    static Item[] DunhillNearHourse = new Item[]{new Item(SMOKE, Dunhill), new Item(PET, HOURSE)};
    static Item[] nuoweiNearBlueHouse = new Item[]{new Item(COUNTRY, NUOWEI), new Item(HOUSE, BLUE)};
    static Item[] BlendsWithWater = new Item[]{new Item(SMOKE, Blends), new Item(DRINK, WATER)};

    static List<Item[]> nearRuleList = Arrays.asList(BlendsNearCat, DunhillNearHourse, nuoweiNearBlueHouse, BlendsWithWater);


    static   Function<int[][],Boolean> atumicRule = groups->{
        boolean mark=true;
        for (Item[] rule : atomicRuleList) {
            for (int i=0;i<=4;i++) {
                if (groups[i][rule[0].type] == rule[0].val) {
                    mark = (groups[i][rule[1].type] == rule[1].val);
                    if (!mark) {
                        return false;
                    }
                }
            }
        }
        return mark;
    };

    static Function<int[][],Boolean> nearRule = groups->{
        boolean mark=true;
        for (Item[] rule : nearRuleList) {
            for (int i=0;i<=4;i++) {
                if (groups[i][rule[0].type] == rule[0].val) {
                    if (i == 0) {
                        mark = (groups[i + 1][rule[1].type] == rule[1].val);
                    } else if (i == 4) {
                        mark = (groups[i - 1][rule[1].type] == rule[1].val);
                    } else {
                        mark = ((groups[i + 1][rule[1].type] == rule[1].val) || (groups[i - 1][rule[1].type] == rule[1].val));
                    }
                    if (!mark) {
                        return false;
                    }
                }
            }
        }
        for (int i=0;i<=4;i++) {
            if (groups[i][greenBeforeWhiteHouse[0].type] == greenBeforeWhiteHouse[0].val) {
                if (i != 4 && groups[i + 1][greenBeforeWhiteHouse[1].type] == greenBeforeWhiteHouse[1].val) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        return mark;
    };


    private static void filter(int[][] groups) {

        boolean judge = atumicRule.apply(groups);
        if (!judge) {
            return;
        }
        judge = nearRule.apply(groups);
        if (!judge) {
            return;
        }
        if (groups[2][DRINK] != MILK) {
            return;
        }
        if (groups[0][COUNTRY] != NUOWEI) {
            return;
        }

        System.out.println("ok");
        for (int i=0;i<=4;i++) {
            Helper.print(groups[i]);
        }

    }

    static int[][] initGroupd() {

     return   new int[][]{
             {YELLOW,WATER,CAT,NUOWEI,Dunhill},
             {BLUE,TEA,HOURSE,DANMAI,Blends},
             {RED,MILK,BIRD,ENGLAND,Pall_Mall},
             {GREEN,COFFE,FISH,DEGUO,Prince},
             {WHITE,BEER,DOG,RUIDIAN,BlueMaster}
        };
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[][] groups = new int[5][5];
        for (int i=0;i<=4;i++) {
            for (int j=0;j<=4;j++) {
                groups[j][i]=j;
            }
        }
        iterator(groups, 0, 0);
        System.out.println("use time:" + (System.currentTimeMillis() - start));
    }


}
