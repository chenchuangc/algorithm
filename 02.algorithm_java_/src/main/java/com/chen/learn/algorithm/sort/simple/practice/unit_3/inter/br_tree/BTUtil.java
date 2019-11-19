package com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.br_tree;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by chencc on 2018/8/20.
 */
public class BTUtil {
    public static void print(BTNode node) {

        ArrayBlockingQueue<BTNode> queue = new ArrayBlockingQueue(50);
        queue.offer(node);
        while (!queue.isEmpty()) {
            ArrayBlockingQueue<BTNode> tempQueue = new ArrayBlockingQueue<BTNode>(50);
            while (!queue.isEmpty()) {

                BTNode tempNode = queue.poll();
                System.out.print(tempNode.getKey() + ":" + tempNode.color + "       ");

                addChild(tempQueue, tempNode);
            }
            System.out.println();

            queue.addAll(tempQueue);
        }

    }

    private static void addChild(ArrayBlockingQueue<BTNode> tempQueue, BTNode tempNode) {
        if (null != tempNode.left) {
            tempQueue.add(tempNode.left);
        }
        if (null != tempNode.right) {
            tempQueue.add(tempNode.right);
        }
    }

}
