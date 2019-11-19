package com.chen.learn.algorithm.sort.simple.practice.unit_5._2_find;

/**
 * Created by chencc on 2018/9/4.
 */
public class MyTriST {

    Node root;
    int R=256;

    public MyTriST() {
        root = new Node(null, R);
    }

    public void put(String str, Object val) {
        put(root, str, 0, val);
    }

    private void put(Node node, String str, int index, Object val) {

        char ch = str.charAt(index);
        Node child = node.child[ch];

        if (index == str.length() - 1) {
            if (child == null) {
                node.child[ch] = new Node(val, R);
            } else {
                child.setValue(val);
            }
        } else {
            if (child == null) {
                node.child[ch] = new Node(R);
            }
            put(node.child[ch], str, index + 1, val);
        }
    }

    private Object get(String str) {
        return get(root.child[str.charAt(0)], str, 0);

    }

    private Object get(Node node, String str, int i) {
        if (null == node) {
            return null;
        }
        if (i >= str.length()) {
            return null;
        }

        if (i == str.length() - 1) {
            return node.getValue();
        } else {
            return get(node.child[str.charAt(i+1)], str, i + 1);
        }
    }


    public static void main(String[] args) {
        MyTriST triST = new MyTriST();
        triST.put("abc", 1);
        triST.put("acd", 2);
        triST.put("abcdasfsd", 3);
        triST.put("agc", 4);
        System.out.println(triST.get("def"));
        System.out.println(triST.get("agc"));
        System.out.println(triST.get("abc"));
        System.out.println(triST.get("abcdasfsd"));
    }
}
