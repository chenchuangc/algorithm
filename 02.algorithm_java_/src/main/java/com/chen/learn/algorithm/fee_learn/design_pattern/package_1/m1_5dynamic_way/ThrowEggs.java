package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_5dynamic_way;


import static java.lang.Integer.max;

/**
 * Created by chencc on 2018/9/26.
 * 2egg,36floor
 * 这个动态规划问题相对来说复杂的多
 * 状态为 w(egg,floor) 就是有egg个鸡蛋，floor个楼层的最优解
 * <p>
 * 状态转移方程式（最优子结构）
 * 为了确保能够求解 在k处扔下鸡蛋 w(egg,floor)= 1+ max{ w(egg-1,k-1), w(egg,floor-k)}
 * 因为为了确保能够找出合适的楼层，所以做 max 这样的处理
 * 同样，因为k可以为 1 -> floor 的范围
 * 所以 保证总数最低的 k 为 min { 1+max{ w(egg-1,k-1), w(egg,floor-k)} }  k->(1-->floor)
 * <p>
 * 边界条件
 * w(0,k)=无穷大，理论上不应该发生
 * w(1,k)=k
 * w(x,0)=0
 * <p>
 * 这里的计算结果也很有意思，比如 w(2,100)=14 而最优解 k=9  而不是预想中的总次数 14
 */
public class ThrowEggs {

    public static int get(int egg, int floor) {
        if (egg == 1) {
            return floor;
        }
        if (floor == 0) {
            return 0;
        }
        int temp = Integer.MAX_VALUE;
        int tempk = 0;
        for (int i = 1; i <= floor; i++) {
            int cur = max(get(egg - 1, i - 1), get(egg, floor - i));
            if (cur < temp) {
                temp = cur;
                tempk = i;
            }
        }
        if (egg == 2 && floor == 100) {
            System.out.println("tempk:" + tempk);
        }
        return 1 + temp;
    }

    public static int getMemory(int egg, int floor, int[][] mermory) {
        if (egg == 1) {
            return floor;
        }
        if (egg == 0 || floor == 0) {
            return 0;
        }
        int temp = Integer.MAX_VALUE;
        int tempk = 0;

        for (int i = 1; i <= floor; i++) {
            int egg_over = mermory[egg - 1][i - 1];
            if (0 == egg_over) {
                egg_over = getMemory(egg - 1, i - 1, mermory);
                mermory[egg - 1][i - 1] = egg_over;
            }
            int egg_stay = mermory[egg][floor - i];
            if (0 == egg_stay) {
                egg_stay = getMemory(egg, floor - i, mermory);
                mermory[egg][floor - i] = egg_stay;
            }
            int cur = max(egg_over, egg_stay);
            if (cur < temp) {
                temp = cur;
                tempk = i;
            }
        }

        if (egg == 2 && floor == 104) {
            System.out.println("startk:" + tempk + "  and total step:" + (temp + 1));
        }
        return 1 + temp;
    }


    public static int getBetterWay(int egg, int floor) {
        int[][] map = new int[egg + 1][floor + 1];
        for (int i = 0; i <= floor; i++) {
            map[1][i] = i;
        }
        for (int j = 0; j <= egg; j++) {
            map[j][0] = 0;
        }
        for (int e = 2; e <= egg; e++) {
            for (int f = 1; f <= floor; f++) {
//                map[e][floor]=getMin(map)
            }
        }

        return 0;

    }

    public static void main(String[] args) {
        System.out.println(get(3, 11));
        System.out.println(get(2, 11));
        System.out.println("-----------");
        System.out.println(getMemory(2,104,new int[4][120]));
    }
}
