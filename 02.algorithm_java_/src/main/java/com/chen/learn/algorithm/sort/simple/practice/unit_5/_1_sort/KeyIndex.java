package com.chen.learn.algorithm.sort.simple.practice.unit_5._1_sort;

/**
 * Created by chencc on 2018/8/30.
 * compare this way and quick sort
 */
public class KeyIndex {

    public static void sort(Ele[] src) {

        int len = src.length;
        Ele[] temp = new Ele[len];
        int[] count = new int[256];
        //1. count
        for (Ele ele : src) {
            count[ele.getKey()+1]++;
        }
        //2. index
        for (int i=1;i<256;i++) {
            count[i] += count[i - 1];
        }

        //3. sort
        for (int j=0;j<len;j++) {
            temp[count[src[j].getKey()]++] = src[j];
        }
        System.arraycopy(temp, 0, src, 0, len);

    }

    public static void main(String[] args) {
        Ele e1 = new Ele(3, "eee1");
        Ele e2 = new Ele(2, "eee1");
        Ele e3 = new Ele(6, "eee1");
        Ele e4 = new Ele(6, "eee1");
        Ele e5 = new Ele(6, "eee1");
        Ele e6 = new Ele(2, "ee41");
        Ele e7 = new Ele(6, "eee1");
        Ele e8 = new Ele(18, "eee188");

        Ele[] src = new Ele[]{e1, e2, e3, e4,e5,e6,e7,e8};
        sort(src);
        for (Ele e : src) {
            System.out.println(e);
        }
    }


   static class Ele{
        Integer key;
        String content;

        public Ele(Integer key, String content) {
            this.key = key;
            this.content = content;
        }

        public Integer getKey() {
            return key;
        }

       public void setKey(Integer key) {
           this.key = key;
       }

       public String getContent() {
           return content;
       }

       public void setContent(String content) {
           this.content = content;
       }

       @Override
        public String toString() {
            return "Ele{" +
                    "key=" + key +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
