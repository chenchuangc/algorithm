package com.simple;

/**
 * Created by chencc on 2018/4/2.
 */
public class Fu {


    String name = "lili";

    public void getName() {
        System.out.println(name);

    }

    public Fu() {
        System.out.println(name);
    }


    public static void main(String[] args) {
        Fu zi = new Zi();
        zi.getName();
    }
}
class Zi extends Fu{

    String name;

    public Zi() {
        System.out.println("aaaa");
    }
}



