package com.simple;

/**
 * Created by chencc on 2018/3/18.
 */
public class Test {
    public static void main(String args[]) {

        System.out.println('b' + 'c');
        System.out.println('b' );
        System.out.println("-------");
        int i;
        int num = 0xFFFFFFFE;
        System.out.println(num);
        for(i=0; i<8; i++) {
            num = num >>> 1;
            System.out.println(num);
        }
    }
}
