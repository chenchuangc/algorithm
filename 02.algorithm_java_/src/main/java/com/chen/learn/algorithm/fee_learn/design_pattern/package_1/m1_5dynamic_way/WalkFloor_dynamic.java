package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;


/**
 * Created by chencc on 2018/9/23.
 */
public class WalkFloor_dynamic {

    /**
     * 这个方法没有使用备忘录，和下面的备忘录的算法完全没有办法比较，这个方法无法求解的（>50s），使用备忘录则是ns级别,0ms
     *
     * @param floorLeft
     * @return
     */
    public static long get(int floorLeft) {
        if (floorLeft == 1) {
            return 1;
        } else if (floorLeft == 2) {
            return 2;
        } else if (floorLeft < 1) {
            return 0;
        }
        return get(floorLeft - 1) + get(floorLeft - 2);
    }

    /**
     * 使用备忘录算法
     *
     * @param floorLeft
     * @param mem
     * @return
     */
    public static long getMem(int floorLeft, long[] mem) {
        if (floorLeft == 1) {
            return 1;
        } else if (floorLeft == 2) {
            return 2;
        } else if (floorLeft < 1) {
            return 0;
        }
        long ways = mem[floorLeft];
        if (ways == 0) {
            ways = getMem(floorLeft - 1, mem) + getMem(floorLeft - 2, mem);
            mem[floorLeft] = ways;
        }
        return ways;
    }


    /**
     * 思路逆过来看
     *
     * @param floor
     * @return
     */
    public static long getReflect(int floor) {

        long[] cur = new long[floor + 1];
        cur[1] = 1;
        cur[2] = 2;
        if (floor <= 0) return 0;

        if (floor <= 2) return cur[floor];

        for (int f = 3; f <= floor; f++) {
            cur[f] = cur[f - 1] + cur[f - 2];
        }
        return cur[floor];
    }

    /**
     * 思路逆过来看
     * 根据特征进一步优化空间复杂度
     * @param floor
     * @return
     */
    public static long getReflectBetter(int floor) {


        if (floor <= 0) return 0;

        if (floor <= 1) return 1;
        if (floor == 2) return 2;
        long a = 1;
        long b = 2;

        for (int f = 3; f <= floor; f++) {
            long cur = a + b;
            a = b;
            b = cur;
        }
        return b;
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        System.out.println(get(48));
        System.out.println("time:" + (System.currentTimeMillis() - start));

        long startA = System.currentTimeMillis();
        System.out.println(getMem(1000, new long[3110]));
        System.out.println("time:" + (System.currentTimeMillis() - startA));

        long startB = System.currentTimeMillis();
        System.out.println(getReflect(1000));
        System.out.println("time:" + (System.currentTimeMillis() - startB));

        long startC = System.currentTimeMillis();
        System.out.println(getReflectBetter(1000));
        System.out.println("time:" + (System.currentTimeMillis() - startC));
    }

}
