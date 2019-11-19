package com.chen.learn.algorithm.sort.simple.practice.unit_3.inter;

/**
 * Created by chencc on 2018/8/18.
 */
public interface SortedSymbleT<Key extends Comparable, Value> {

    void put(Key key, Value value);

    Value get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEnpty();

    int size();

    Key min();

    Key max();

    /**
     * <= key 的最大键
     * @return
     */
    Key floor(Key key);//

    /**
     * >= key的最最小键
     * @return
     */
    Key ceiling(Key key);

    /**
     * 小于key的键的数量,这个是不包含等于的
     * @param key
     * @return
     */
    int rank(Key key);

    /**
     * 排名为key的键
     * @param k
     * @return
     */
    Key select(int k);
}
