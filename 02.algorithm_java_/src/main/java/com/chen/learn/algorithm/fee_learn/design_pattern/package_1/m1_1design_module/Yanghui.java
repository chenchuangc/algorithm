package com.chen.learn.algorithm.fee_learn.design_pattern.package_1.m1_1design_module;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/9/20.
 * 杨辉三角 的求解模拟
 */
public class Yanghui {

    static class Node {
        int number;
        int x_number;
        int y_number;

        public Node(int number, int x_number, int y_number) {
            this.number = number;
            this.x_number = x_number;
            this.y_number = y_number;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "number=" + number +
                    ", x_number=" + x_number +
                    ", y_number=" + y_number +
                    '}';
        }
    }

    /**
     * 使用了两个数组来进行计算，空间消耗相对大一些，但是更加容易理解一些
     * @param n
     * @return
     */
    public static Node[] get(int n) {
        if (n ==0) {
            return new Node[]{new Node(1, 0, 0)};
        }
        Node[] pre = new Node[n+1];
        pre[0] = new Node(1, 0, 0);
        Node[] target = new Node[n+1];
        for (int i = 1; i <= n; i++) {
            target[0] = new Node(1, i, 0);
            target[i] = new Node(1, 0, i);
            for (int j = 1; j < i; j++) {
                int c=pre[j].number+pre[j-1].number;
                target[j] = new Node(c, i - j, j);
            }
            Node[] temp = pre;
            pre=target;
            target=temp;
        }
        return pre;
    }

    /**
     * 这个方法降低了空间复杂度
     * @param n
     * @return
     */
    public static Node[] getBetter(int n) {

        if (n == 0) {
            return new Node[]{new Node(1, 0, 0)};
        }
        Node[] data = new Node[n+1];
        for (int i=1;i<=n;i++) {
            data[0] = new Node(1, i, 0);
            data[i] = new Node(1, 0, i);
            for (int j=i-1;j>0;j--) {
                int c = data[j].number + data[j - 1].number;
                data[j] = new Node(c, i - j, j);
            }
            Helper.print(data);
        }
        return data;
    }

    public static void main(String[] args) {
//        Helper.println(get(10));
        System.out.println("----------");
        System.out.println("----------");
        System.out.println("----------");
        Helper.println(getBetter(4));
    }
}
