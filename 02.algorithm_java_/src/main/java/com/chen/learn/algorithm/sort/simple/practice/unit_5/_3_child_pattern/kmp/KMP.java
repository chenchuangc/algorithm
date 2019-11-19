package com.chen.learn.algorithm.sort.simple.practice.unit_5._3_child_pattern.kmp;

/**
 * cankao https://blog.csdn.net/v_july_v/article/details/7041827
 */
public class KMP {


    public static void match(String src, String pat) {
        int[] next  = new int[pat.length() + 1];
        NextGet.getNextArray(pat, next);
        int slen = src.length();
        int plen = pat.length();
        int sb = 0;
        int pb = 0;
        while (sb<slen&&pb<plen) {
            if ( pb!=-1 &&src.charAt(sb) == pat.charAt(pb)) {
                sb++;
                pb++;
            } else {
                if (pb == -1) {
                    sb++;
                    pb++;
                } else {
                    pb = next[pb];
                }
            }
        }
        if (pb == plen) {
            System.out.println("find");
        } else {
            System.out.println("not find");

        }




    }

    public static void main(String[] args) {
        match("asddsfsdf","ass");
    }


}
