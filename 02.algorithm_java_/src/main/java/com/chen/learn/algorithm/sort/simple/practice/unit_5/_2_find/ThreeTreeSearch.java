package com.chen.learn.algorithm.sort.simple.practice.unit_5._2_find;

/**
 * Created by chencc on 2018/9/5.
 */
public class ThreeTreeSearch {
    ThreeNode root;

    public void put(String key, Object val) {
        root = put(root, key, val, 0);
    }

    private ThreeNode put(ThreeNode node, String key, Object val, int index) {

        if (index >= key.length()) {
            return null;
        }
        if (null == node) {
            node = new ThreeNode();
            node.setKey(key.charAt(index));
        }
//        if (index == key.length() - 1) {
//            node.setVal(val);
//        }
        if (node.getKey() == key.charAt(index)) {

            if (index == key.length() - 1) {
                node.setVal(val);
            } else {
                node.mid = put(node.mid, key, val, index + 1);
            }
        } else if (node.getKey() > key.charAt(index)) {
            node.left = put(node.left, key, val, index);
        } else {
            node.right = put(node.right, key, val, index);
        }
        return node;

    }

    public Object get(String key) {
        ThreeNode node = get(root, key, 0);
        if (node == null) {
            return null;
        }
        return node.getVal();
    }

    private ThreeNode get(ThreeNode node, String key, int index) {
        if (node == null) {
            return null;
        }
        if (index == key.length()) {
            return null;
        }
        if (node.key == key.charAt(index)) {
            if (index == key.length() - 1) {
                return node;
            } else {
                return get(node.getMid(), key, index + 1);
            }
        } else if (node.key > key.charAt(index)) {
            return get(node.left, key, index);
        } else {
            return get(node.right, key, index);
        }

    }

    public static void main(String[] args) {
        ThreeTreeSearch threeTreeSearch = new ThreeTreeSearch();

        threeTreeSearch.put("123", 123);
        threeTreeSearch.put("abc", 111);
        threeTreeSearch.put("abe", 333);
        threeTreeSearch.put("aba",444);
        System.out.println(threeTreeSearch.get("abc"));
        System.out.println(threeTreeSearch.get("abe"));
        System.out.println(threeTreeSearch.get("123"));
        System.out.println(threeTreeSearch.get("aba"));

    }


}


// some times how i wish this is not really true ,this is just a dream
// how i wish come back to time ago
// i wish happened nothing
// i am feeling tired