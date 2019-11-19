package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_6search_all;

/**
 * Created by chencc on 2018/9/20.
 */
public class BuyChicken {

    public static int buy() {
        int count=0;
        int rootster=100/5;
        int hens = 100/3;
        for (int r=0;r<rootster;r++) {
            for (int h=0;h<hens;h++) {
                int chicks = 100-h-r;
                int leftMoney = 100-r*5-h*3;
                if (leftMoney * 3 == chicks) {
                    System.out.println("公鸡："+r+" 母鸡: "+h+" 小鸡："+chicks );
                    count++;
                }
            }
        }

        return count;
    }

    public static void anotherBuy(){
        int count = 0;

        for (int roosters = 0; roosters <= 20; roosters++)   //枚举大公鸡数量
        {
            for (int hens = 0; hens <= 33; hens++) //枚举母鸡数量
            {
                int chicks = 100 - roosters - hens;  //剩下的就是小鸡数量
                if (((chicks % 3) == 0) //小鸡个数应该是3的整数倍，算是个小小的剪枝
                        && ((5 * roosters + 3 * hens + chicks / 3) == 100)) //是否凑够100个钱
                {
                    count++;
//                    std::cout << "买法 " << count << "：公鸡 " << roosters
//                            << ", 母鸡 " << hens
//                            << ", 小鸡 " << chicks << std::endl;
                }
            }
        }
        System.out.println(count);

    }

    public static void main(String[] args) {
        System.out.println(buy());
        anotherBuy();
    }

}
