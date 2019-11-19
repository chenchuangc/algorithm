package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

/**
 * Created by chencc on 2018/8/29.
 */
public class Count {

    public static void count(String dic, String target) {
        Alphabet dictionary = new Alphabet(dic);
        int[] count = new int[dictionary.len()];
        for (char c : target.toCharArray()) {
            count[dictionary.indxer(c)]++;
        }
        for (int i = 0; i < count.length; i++) {
            int temp = count[i];
            if (temp > 0) {
                System.out.println("" + dictionary.toChar(i)+" :"+temp);
            }
        }
    }


    public static void main(String[] args) {
        count("qwertyu","qwqweewqqeqwewrwrtytrteetruuruueqr");
    }
}
