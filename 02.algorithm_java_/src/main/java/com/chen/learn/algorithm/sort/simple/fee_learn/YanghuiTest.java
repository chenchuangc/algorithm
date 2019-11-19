package com.chen.learn.algorithm.sort.simple.fee_learn;

import java.util.ArrayList;
import java.util.List;

public class YanghuiTest {
        public static List<Integer> getRow(int rowIndex) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int i=0; i<rowIndex+1; i++){
                row.add(0,1);
                for(int j=1; j<row.size()-1;j++){
                    row.set(j, row.get(j)+row.get(j+1));
                }
            }
            return row;
        }

    public static void main(String[] args) {
        getRow(4).forEach(System.out::println);
    }
}
