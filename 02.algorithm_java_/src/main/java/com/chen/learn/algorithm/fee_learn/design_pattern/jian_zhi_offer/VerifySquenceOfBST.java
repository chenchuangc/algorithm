package com.chen.learn.algorithm.fee_learn.design_pattern.jian_zhi_offer;

/**
 * Created by chencc on 2018/10/15.
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 这个要注意特征，最后一个数字是根，然后以根为标记，分别检查两边的，然后进行递归操作就可以了
 */
public class VerifySquenceOfBST {

    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length<1) return false;

        int start =0;
        int end=sequence.length-1;
        return verify(sequence, start, end);
    }

    private boolean verify(int[] sequence, int start, int end) {

        if (start >= end) {
            return true;
        }

        int rootVal = sequence[end];
        int leftStart=start;
        for(;leftStart<end;leftStart++) {
            if (sequence[leftStart] > rootVal) {
                break;
            }
        }
        int leftEnd = leftStart-1;
        int rightStart = leftStart;
        for(int i=rightStart;i<end;i++) {
            if (sequence[i] < rootVal) {
                return false;
            }
        }
        boolean childOk = verify(sequence, start, leftEnd);
        if (!childOk) {
            return false;
        }
        return verify(sequence, rightStart, end - 1);

    }

    public static void main(String[] args) {
        int[] se= new int[]{3};
        System.out.println(new VerifySquenceOfBST().VerifySquenceOfBST(se));
    }

}
