package com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.array;

import com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.SortedSymbleT;
import com.chen.learn.algorithm.util.Helper;

/**
 * Created by chencc on 2018/8/18.
 * 2018.08.18 于北林学研大厦
 */
public class SortedSybleByArray implements SortedSymbleT<Integer, String> {

    Integer[] keyArray;
    String[] valArray;
    Integer ele_size;


    public SortedSybleByArray(Integer size) {
        keyArray = new Integer[size];
        valArray = new String[size];
        ele_size=0;
    }

    public void put(Integer integer, String s) {
        int rank = rank(integer);
        if (integer.equals(keyArray[rank])) {
            valArray[rank] = s;
        } else {
            ele_size++;
            int end = ele_size;
            while (end > rank) {
                keyArray[end] = keyArray[end - 1];
                valArray[end]=valArray[end-1];
                end--;
            }
            keyArray[rank]=integer;
            valArray[rank]=s;
        }
        Helper.print(keyArray);
        Helper.print(valArray);
        System.out.println("----------------");
        System.out.println("----------------");

    }

    public String get(Integer integer) {
        return null;
    }

    public void delete(Integer integer) {

    }

    public boolean contains(Integer integer) {
        return false;
    }

    public boolean isEnpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public Integer min() {
        return null;
    }

    public Integer max() {
        return null;
    }

    public Integer floor(Integer integer) {
        return null;
    }

    public Integer ceiling(Integer integer) {
        return null;
    }

    /**
     * 小于key的键的数量，不包含等于的
     * @param key
     * @return
     */
    public int rank(Integer key) {
        int low=0;
        int hi = ele_size-1;

        while (low <= hi) {
            int mid = (low+hi)/2;
            if (keyArray[mid] > key) {
                hi=mid-1;
            } else if (keyArray[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;//这一句很关键，也就是要考虑的情况，应该是return lo 才行的。
    }

    public Integer select(int k) {
        return null;
    }

    public static void main(String[] args) {
        SortedSymbleT<Integer, String> testSort = new SortedSybleByArray(10);
        testSort.put(5,"5s");
        testSort.put(23,"23s");
        testSort.put(9,"9s");
        testSort.put(35,"35s");
        testSort.put(1,"315s");
        testSort.put(3,"335s");
    }
}
