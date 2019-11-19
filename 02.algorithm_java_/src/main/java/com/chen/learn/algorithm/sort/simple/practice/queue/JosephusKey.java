package com.chen.learn.algorithm.sort.simple.practice.queue;

public class JosephusKey {
    public static void main(String[] args) {
        SimpleCircleQueue<String> queue = new SimpleCircleQueue<String>();
        queue.enQueue("aa");
        queue.enQueue("aa1");
        queue.enQueue("aa2");
        queue.enQueue("aa3");
        queue.enQueue("aa5");
        queue.enQueue("aa6");
        queue.enQueue("aa7");
        SimpleCircleQueue.Node last = queue.getLast();

        System.out.println("last:"+last.item);
        josephu(last.next,3);
//        josephus(queue,3);

    }

    /**
     * 你这个解法直接深入里面了，
     * 直接是环形的node了，但是，实际上有通用的做法，看看下面
     * @param head
     * @param nextRemove
     */
    private static void josephu(SimpleCircleQueue.Node head,int nextRemove) {
        int index=2;
        if (head.next == head) {
            System.out.println("real last:"+head.item);
            return;
        }
        SimpleCircleQueue.Node  temp=head ;
        for (; index < nextRemove; index++) {
            temp=temp.next;
        }
        SimpleCircleQueue.Node remove = temp.next;
        System.out.println(remove.item);
        SimpleCircleQueue.Node third = temp.next.next;
        temp.next=third;
        josephu(temp.next, nextRemove);
    }

    /**
     * 这个操作看起来则是酷炫的多啊
     * @param queue
     * @param nextRemove
     */
    private static void josephus(SimpleCircleQueue<String> queue,int nextRemove) {
        while (true) {
            for (int i = 0; i < nextRemove-1; i++) {
                queue.enQueue(queue.deQueue());
            }
            String val = queue.deQueue();
            if (null == val) {
                break;
            }
            System.out.println(val);
        }
    }
}
