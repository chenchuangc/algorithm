package com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.simple_tree;

import com.chen.learn.algorithm.sort.simple.practice.unit_3.inter.SortedSymbleT;

/**
 * Created by chencc on 2018/8/18.
 */

public class SortedSimpleTree implements SortedSymbleT<Integer,String>{

    Node root;


    public void put(Integer integer, String s) {
        if (null == root) {
            root = new Node(1,integer, s, null, null);
        } else {
            put(root, integer, s);
        }
    }

    private Node put(Node parent, Integer integer, String s) {
        if (null == parent) {
            return new Node(1, integer,s, null, null);
        } else {
            if (parent.getKey().equals(integer)) {
                parent.setValue(s);
            } else {
                if (parent.getKey().compareTo(integer) > 0) {
               parent.left=     put(parent.left, integer, s);
                } else {
                    parent.right = put(parent.right, integer, s);
                }
                parent.setN(1+size(parent.left) + size(parent.right));
            }
            return parent;
        }
    }

    private int size(Node node) {
        if (null == node) {
            return 0;
        } else {
            return node.getN();
        }
    }

    public String get(Integer integer) {
        return get(root, integer);
    }

    private String get(Node node, Integer integer) {
        if (null == root) {
            return null;
        } else {
            if (node.getKey().compareTo(integer) > 0) {
                return get(node.left, integer);
            } else if(node.getKey().compareTo(integer)<0){
                return get(node.right, integer);
            }else {
                return node.getValue().toString();
            }
        }

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

    public int rank(Integer integer) {
        return 0;
    }

    public Integer select(int k) {
        return null;
    }

    class Node{
        int N;
        Node left;
        Node right;
        Integer key;
        Object value;

        public Node(int n,Integer key,Object val, Node left, Node right) {
            N = n;
            this.key=key;
            value=val;
            this.left = left;
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public int getN() {
            return N;
        }

        public void setN(int n) {
            N = n;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
