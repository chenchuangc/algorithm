package com.chen.learn.algorithm.fee_learn.design_pattern.self_practice;

import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/12/4.
 * 所谓的鸡排，感觉和字符串的计数排序是一样的
 * try some of vim use
 */
public class RadixSort {

    public static int[] radixSort(int[] src) {
        String[] srcStr = new String[src.length];
        int max=0;
        for(int i=0;i<src.length;i++) {
            srcStr[i] = String.valueOf(src[i]);
            if (src[i] > max) {
                max = src[i];
            }
        }
        int len= String.valueOf(max).length();
        /*for (int i=0;i<srcStr.length;i++) {
            if (srcStr[i].length() < len) {
                srcStr[i] = "00000"+ srcStr[i];
            }
        }*/
        for (int j=len-1;j>=0;j--) {
            sortOne(srcStr, j,len-1);
        }

        Helper.print(srcStr);
        return null;
    }

    private static void sortOne(String[] srcStr, int index,int endIndex) {
        int[] count = new int[11];
        for (int i=0;i<srcStr.length;i++) {
            int num = Integer.valueOf(indexOf(srcStr[i], index,endIndex));
            count[num+1]++;
        }
        for (int k=1;k<count.length;k++) {
            count[k]+=count[k-1];
        }
        String[] temp = new String [srcStr.length+1];
        for(int j=0;j<srcStr.length;j++) {
            temp[count[indexOf(srcStr[j], index, endIndex)]++] = srcStr[j];
        }
        System.arraycopy(temp, 0, srcStr, 0, srcStr.length);

    }

    private static int indexOf(String str, int index,int endIndex) {

        int lastIndex=endIndex-index;

        if (lastIndex > str.length() - 1) {
            return 0;
        }
        int len=str.length();
        return Integer.valueOf(String.valueOf(str.charAt(len - 1 - lastIndex)));
    }

    public static void main(String[] args) {
        int[] src= new int[]{123,132,11,110};

        radixSort(src);    }
}
