package com.chen.learn.algorithm.sort.simple.practice.stack;

/**
 * Created by chencc on 2018/5/17.
 * 栈的可生成性判断
 * 就是给一个数组 string[] 其中整数为 入栈， 为"-"是出栈，整数有k个，"-"也有k个
 * 1.看看是否会出现出栈溢出的情况
 * 2.假如不存在的话，这个栈是什么样的，就是求"对应的出栈顺序"
 *
 */
public class StackGenerateWay {

    /**
     * 问题1的求解
     * @param src
     * @return
     */
    public static boolean erverBeStack(String[] src) {
        int i=0;
        for (String ele : src) {
            if ("-".equals(ele)) {
                i--;
            }else {
                i++;
            }
            if (i < 0) {
                return false;
            }
        }
        return true;
    }

    public static String[] outStack(String[] src) {
        return null;
    }

    /**
     * 这个解法感觉有点复杂啊
     * @param seq
     * @return
     */
    public static String[] problem2(String[] seq){ // 返回一系列操作顺序， seq是出栈顺序
        ArrayStack<String> s = new ArrayStack<String>(30);
        String[] ans = new String[2*seq.length];
        int N=0,p=0;
        s.push(String.valueOf(N));
        ans[p++] = String.valueOf(N);
        N++;
        for(int i=0;i<seq.length;i++){
            while(N<seq.length && s.peek().compareTo(seq[i])!=0){
                s.push(String.valueOf(N));
                ans[p++]=String.valueOf(N);
                N++;
            }
            if(s.peek().compareTo(seq[i])!=0) return null;
            else {
                s.pop();
                ans[p++]="-";
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] seq1 = new String[]{"2", "5", "6", "7", "4", "8", "9", "3", "1", "0"};
        for(String i:problem2(seq1))
            System.out.print(i+" ");
        System.out.println();
        System.out.println("--------------");
        String[] seq2 = new String[]{"4", "6", "8", "7", "5", "3", "2", "9", "0", "1"};
        System.out.println(problem2(seq2));
    }
}
