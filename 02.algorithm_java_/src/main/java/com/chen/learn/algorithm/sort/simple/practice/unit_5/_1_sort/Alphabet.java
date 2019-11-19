package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

/**
 * Created by chencc on 2018/8/29.
 */
public class Alphabet {


    private char[] container;
    private int[] revers;
    private int len;

    public Alphabet(String src) {
        container= src.toCharArray();
        len=container.length;
        revers = new int[Character.MAX_VALUE];
        for (int i=0;i<Character.MAX_VALUE;i++) {
            revers[i]=-1;
        }
        for (int j = 0; j < container.length; j++) {
            char c = container[j];
            revers[c]=j;
        }

    }

    public int indxer(char c) {
        if (c > revers.length || revers[c] == -1) {
            throw new RuntimeException("bad param :" + c);
        }
        return revers[c];
    }

    public char toChar(int index) {
        if (index >= 0 && index < container.length) {
            return container[index];
        } else {
            throw new RuntimeException("error param:" + index);
        }
    }

    public int len() {
        return len;
    }

    public static void main(String[] args) {
        Alphabet alphabet = new Alphabet("lsjdfk");
        System.out.println(alphabet.indxer('k'));

    }


}
