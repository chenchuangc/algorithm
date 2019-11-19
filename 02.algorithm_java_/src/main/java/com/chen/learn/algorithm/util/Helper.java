package com.chen.learn.algorithm.util;

import com.chen.learn.algorithm.sort.simple.exam.ListNode;

import java.util.Random;

public class Helper {

    public static int[] seed(int num) {
        Random random = new Random();
        int[] temp = new int[num];
        for (int index = 0; index < num; index++) {
            temp[index] = random.nextInt(num * 50);
        }
        return temp;
    }

    public static int[] seedOrder(int num) {
        Random random = new Random();
        int[] temp = new int[num];
        int last=0;
        for (int index = 0; index < num; index++) {
            temp[index] = random.nextInt(num * 100)+last;
            last = temp[index];
        }
        int repeatIndex = random.nextInt(num - 4);
        for (; repeatIndex < num-2; repeatIndex++) {
            temp[repeatIndex + 1] = temp[repeatIndex];
        }
        return temp;
    }

    public static void print(int[] source) {
        for (int temp : source) {
            mprint(temp );
        }
        System.out.println();
    }


    private static void mprint(int i) {
        String tre = String.valueOf(i);
        if (tre.length() < 2) {
            tre = tre + " ";
        }
        System.out.print(tre + ",");
    }
    public static void print(boolean[] source) {
        for (Object temp : source) {
            System.out.print(temp + ",");
        }
        System.out.println();
    }
    public static void print(Object[] source) {
        for (Object temp : source) {
            System.out.print(temp + ",");
        }
        System.out.println();
    }
    public static void print(double[] source) {
        for (Object temp : source) {
            System.out.print(temp + ",");
        }
        System.out.println();
    }
    public static void println(Object[] source) {
        for (Object temp : source) {
            System.out.println(temp + ",");
        }
        System.out.println();
    }

    public static void exchange(int[] source, int indexa, int indexb) {

        int temp = source[indexa];
        source[indexa] = source[indexb];
        source[indexb] = temp;
    }
    public static void exchange(Comparable[] source, int indexa, int indexb) {

        Comparable temp = source[indexa];
        source[indexa] = source[indexb];
        source[indexb] = temp;
    }

    public static int random() {
        Random random = new Random();
        return random.nextInt(5000);
    }

    public static ListNode creatListNode(int len) {
        ListNode first = new ListNode(random());
        ListNode temp = first;
        ListNode newNode = null;
        for (int i=0;i<len-1;i++) {
            newNode = new ListNode(random());
            temp.next = newNode;
            temp=newNode;
        }
        return first;
    }

    public static void main(String[] args) {
        ListNode listNode = creatListNode(8);
        System.out.println(len(null));

    }

    public static void print(ListNode listNode) {

        StringBuffer buffer = new StringBuffer();
        ListNode current=listNode;
        buffer.append(current.getVal()).append(",");

        while ( (current=current.next) != null) {
            buffer.append(current.getVal()).append(",");
        }
        System.out.println(buffer.toString());
    }

    public static int len(ListNode node) {

        if (node == null) {
            return 0;
        }
        int len =1;
        ListNode current = node;

        while ((current = current.next)!=null) {
            len++;
        }
        return len;
    }

    public static void exchange(char[] chars, int begin, int i) {
        char temp = chars[begin];
        chars[begin] = chars[i];
        chars[i] = temp;

    }

    public static double toPositive(double v) {

        return v<0?-v:v;
    }
}
