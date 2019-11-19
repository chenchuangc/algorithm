package com.chen.learn.algorithm.sort.simple.practice.unit_5._2_find;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chencc on 2018/9/4.
 * 要点是头结点是空节点，所有的都是从头连接后面的链接开始计算的
 *
 */
public class TriST {
    Node root;
    int r = 256;

    public void put(String str, Object val) {
        root = put(root, str, val, 0);
    }

    /**
     * 这里 index 对应的是当前的node+1   也就是index在str中对应的字符 = node 在他父亲中的位置
     * 对比你的 另一个实现可以看出他是 index 在str中对应的字符 对应的是当前 node.child的位置
     * @param node
     * @param str
     * @param val
     * @param index
     * @return
     */
    private Node put(Node node, String str, Object val, int index) {

        if (null == node) {
            node = new Node(r);
        }
        if (index == str.length()) {
            node.setValue(val);
        } else {
            node.child[str.charAt(index)] = put(node.child[str.charAt(index)], str, val, index + 1);
        }
        return node;
    }

    public Object get(String str) {

        Node node = get(root, str, 0);
        if (null == node) {
            return null;
        } else {
            return node.getValue();
        }

    }

    private Node get(Node node, String str, int index) {
        if (node == null) {
            return null;
        }
        if (index == str.length()) {
            return node;
        } else {
            return get(node.child[str.charAt(index)], str, index + 1);
        }

    }

    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        collect(root, "", list);
        return list;
    }

    private void collect(Node node, String s, List<String> list) {
        if (node == null) {
            return;
        }
        if (node.getValue() != null) {
            list.add(s);
        }
        for (int i=0;i<node.child.length;i++) {
            if (null != node.child[i]) {
                collect(node.child[i], s + (char) i, list);
            }
        }

    }

    private void delete(String s) {

        delete(root, s, 0);

    }

    private boolean delete(Node node, String s, int i) {
        if (null == node) {
            return false;
        }
        if (i == s.length()) {
            if (node.getValue() != null) {
                node.setValue(null);
                boolean mark = true;
                for (int j = 0; j < node.child.length; j++) {
                    if (node.child[j] != null) {
                        mark = false;
                        break;
                    }
                }
                return mark;
            } else {
                return false;
            }
        } else {
            boolean ifDelete = delete(node.child[s.charAt(i)], s, i + 1);
            if (!ifDelete) {
                return ifDelete;
            }else {
                node.child[s.charAt(i)] = null;
                if (null != node.getValue()) {
                    return false;
                }
                boolean mark=true;
                for (int j=0;j<node.child.length;j++) {
                    if (node.child[j] != null) {
                        mark=false;
                        break;
                    }
                }
                return mark;
            }
        }
    }

    private List<String> match(String reg) {
        List<String> list = new ArrayList<>();
        collectMatch(root,reg, 0, list, "");
        return list;
    }

    private void collectMatch(Node node,String reg, int index, List<String> list, String s) {
        if (null == node) {
            return;
        }
        if (index == reg.length()&&node.getValue() != null) {
            list.add(s);
        }
        if (index == reg.length()) {
            return;
        } else {
            if (reg.charAt(index) == '.') {
                for (int i = 0; i < node.child.length; i++) {
                    collectMatch(node.child[i], reg, index + 1, list, s + (char) i);
                }
            } else {
                collectMatch(node.child[reg.charAt(index)], reg, index + 1, list, s + reg.charAt(index));

            }
        }
    }

    private int size() {
        return size(root);
    }

    private int size(Node node) {
        int cnt = 0;
        if (null == node) {
            return 0;
        }

        if (node.getValue() != null) {
            cnt++;
        }
        for (int i = 0; i < r; i++) {
            cnt += size(node.child[i]);
        }
        return cnt;
    }

    public String longestKey(String prefix) {
        LinkedList<String> list = new LinkedList<>();
        getlong(root, prefix, "", list);
        return list.getLast();
    }

    private void getlong(Node node, String prefix, String s, List<String> list) {
        if (null == node) {
            return;
        }
        if (node.getValue() != null) {
            list.add(s);
        }
        if (s.length() == prefix.length()) {
            return;
        }
        char c = prefix.charAt(s.length());
        getlong(node.child[c], prefix, s + c, list);

    }


    public static void main(String[] args) {
        TriST triST = new TriST();
        triST.put("abc", 1);
        triST.put("acd", 2);
        triST.put("abcda", 3);
        triST.put("abcdasfsf", 3);
        triST.put("aecdasfsn", 3);
        triST.put("agc", 4);
//        System.out.println(triST.get("def"));
//        System.out.println(triST.get("agc"));
//        System.out.println(triST.get("abc"));
//        System.out.println(triST.get("abcdasfsd"));
//        System.out.println(triST.get("abcdasfsd"));
//        System.out.println(triST.get("abcdasfsd"));
//        System.out.println(triST.size());
        System.out.println("--------------");
        triST.getAll().forEach(System.out::println);
//        System.out.println("****************************");
//        triST.match("abc").forEach(System.out::println);
//
//        System.out.println("****************************");
//        triST.match("a.c").forEach(System.out::println);
//        System.out.println("&&&&&&&&&&&&&&&&");
//        System.out.println(triST.longestKey("aecdasfsn"));

        System.out.println("+++++++++++++++");
        triST.delete("abcdasfsf");
        triST.delete("abc");
        triST.delete("agc");
        triST.getAll().forEach(System.out::println);



    }
}

// 查找所有键，通配符匹配，查找指定字符串的最长前缀
// 冷静分析问题