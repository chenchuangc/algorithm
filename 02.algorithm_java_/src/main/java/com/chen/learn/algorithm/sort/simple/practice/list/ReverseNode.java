package com.chen.learn.algorithm.sort.simple.practice.list;


/**
 * Created by chencc on 2018/5/12.
 */
public class ReverseNode {
    public static void main(String[] args) {
        Node<String> aa_1 = new Node<String>("aab", null);
        Node<String> aa = new Node<String>("aa", aa_1);
        Node<String> bb = new Node<String>("bb", aa);
        Node<String> cc = new Node<String>("cc", bb);
        printN(cc);
//        Node reverse = reverse(cc);
        Node reverse = reverse(cc);
        System.out.println("----------");
        printN(reverse);

    }

    private static void printN(Node<String> cc) {
        for (Node n=cc;n!=null;n=n.next) {
            System.out.println(n.item);
        }
    }

    /**
     * 关于链表翻转之前自己也是听过很多次
     * 都没有具体的实践，昨天尝试写了一下，没有写出来
     * 还是对这种迭代性的东西不够敏感
     * 完全没有想到定义一个变量来指向已经翻转的链表
     * @param head
     * @return
     */
    private static Node reverse(Node<String> head) {

        Node reverse =null;
        Node node = head;
        while (node != null) {
            Node sec = node.next;
            node.next=reverse;
            reverse=node;
            node=sec;
        }
        return reverse;
    }


    /**
     * 使用迭代的方式来实现链表的翻转操作
     * 这个链表翻转的效果不是很好，从这里可以看到，
     * 每个嵌套中都要进行一次遍历来拿到最后一个节点，这样的话效率是很低的，n*n级别的时间复杂度
     * 可以看看 {@link #reverseIterateFast}
     * @param head
     * @return
     */
    private static Node reverseIterate(Node<String> head) {

        if (null == head) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node reversed = reverseIterate(head.next);
        head.next=null;
        /**
         * 这里只是想把当前节点接到已经翻转的链表后面，你这里用了遍历，但是你想想，你只要在当前的栈中能够拿到对应的sec就行了啊
         */
        Node last = reversed;
        for (; last.next!=null; last=last.next) {

        }
        last.next=head;
        return reversed;
    }

    /**
     * 这个的迭代更快
     * 只是修改了一点点，使时间复杂度降低到了 n
     * @param head
     * @return
     */
    private static Node reverseIterateFast(Node<String> head) {
        if (null == head) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        /**
         * 只是把second多保存了一个指针指向就获得了这么巨大的算法性能提升，
         * 这个还是很有意义的
         */
        Node second = head.next;
        Node reversed = reverseIterateFast(second);
        head.next=null;
        second.next=head;
        return reversed;
    }

}
