package com.chen.learn.algorithm.sort.simple.practice.ring_buff;

/**
 * Created by chencc on 2018/5/15.
 * 这个ringbuffer主要对应的是一个线程写，一个线程读的情况
 * 实际上在多线程写或者读中并不具备线程安全性
 *
 * 1.注意什么时候不能读，也就是读的临界条件 当 readHeadPos=writeTailPos的时候，说明读的临界达到了
 * 2.写的临界条件，也就是 writeTailPos+1=readHeadPos说明是满了的状态，当然这里用的是数组存储，所以还有稍微做一个转换
 * (writeTailPos+1)%size=readHeadPos
 * 3.需要注意是一个线程写，一个线程读，要抓住赋值操作是原子操作的特性来让线程之间协同
 * 4.这就需要tail所指向的位置是不存储元素的才行
 */
public class RingBuffer<T> {
    private T[] elementList;
    private int readHeadPos;
    private int writeTailPos;

    public RingBuffer(int cap) {
        elementList = (T[]) new Object[cap];
        readHeadPos =0;
        writeTailPos=0;
    }

    public int size() {
        return elementList.length;
    }

    public int available() {
        int num = writeTailPos%size()-readHeadPos;
        if (num < 0) {
            num+=size();
        }
        return num;
    }

    public boolean write(T ele) {
        if ((writeTailPos+1)%size()==readHeadPos) {
            System.out.println("写满了-------");
            return false;
        }
        elementList[writeTailPos]=ele;
        writeTailPos=(writeTailPos+1)%size();
        return true;
    }

    public T read() {
        if (writeTailPos == readHeadPos) {
            System.out.println("空了");
            return null;
        }
        T e = elementList[readHeadPos];
        readHeadPos = (readHeadPos + 1) % size();
        return e;
    }

    public static void main(String[] args) {
        RingBuffer<String> ringBuffer = new RingBuffer<String>(4);
        ringBuffer.write("aa");
        ringBuffer.write("aa1");
        ringBuffer.write("aa2");
        ringBuffer.write("aa3");
        ringBuffer.write("aa4");
        System.out.println(ringBuffer.read());
        System.out.println(ringBuffer.read());
        System.out.println(ringBuffer.read());
        System.out.println(ringBuffer.read());
        ringBuffer.write("bb");
        ringBuffer.write("bb3");
        ringBuffer.write("bb4");
        System.out.println(ringBuffer.read());
        System.out.println(ringBuffer.read());
        System.out.println(ringBuffer.read());
        System.out.println(ringBuffer.read());

    }

}
