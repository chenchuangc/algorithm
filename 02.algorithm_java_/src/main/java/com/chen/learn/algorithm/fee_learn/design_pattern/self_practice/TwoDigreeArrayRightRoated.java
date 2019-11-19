package com.chen.learn.algorithm.fee_learn.design_pattern.self_practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chencc on 2018/9/24.
 * 试试二维数组的顺时针遍历
 *
 */
public class TwoDigreeArrayRightRoated {

     interface  Boundary{
       boolean  isBoundary(int cur_row, int cur_col);
    }

    static class Direct{
         int x_offset ;
         int y_offset;
         Boundary boundary;

        public Direct(int x_offset, int y_offset, Boundary boundary) {
            this.x_offset = x_offset;
            this.y_offset = y_offset;
            this.boundary = boundary;
        }
    }

    /**
     * 这种是引入了一些标志变量，并且使用方向数组做了一致性处理，
     * 这个代码是你自己做出来的，写的挺好的，简洁有力
     * @param src
     * @return
     */
    public static List<Object> see(Object[][] src) {
        int row = src.length-1;
        int col = src[0].length-1;
        int total = (row + 1) * (col + 1);
        List<Object> list = new ArrayList<>();
        int count=0;

        Boundary toRight = (cur_row, cur_col) -> col-cur_row==cur_col;
        Boundary toButton = (cur_row,cur_col)->row-(col-cur_col)==cur_row;
        Boundary toLeft = (cur_row,cur_clo)-> (row-cur_row)==cur_clo;
        Boundary toTop = (cur_row,cur_clo)-> cur_clo+1==cur_row;

        Direct[] directs = new Direct[]{
                new Direct(1, 0, toRight),
                new Direct(0, 1, toButton),
                new Direct(-1, 0, toLeft),
                new Direct(0, -1, toTop)
        };
        int r=0;
        int c=0;
        int dir=0;
        while (count < total) {

            list.add(src[r][c]);
            count++;
            if (directs[dir].boundary.isBoundary(r, c)) {
                dir++;
                dir=dir%4;//这个地方也是为了做一致性处理
            }
            c+=directs[dir].x_offset;
            r+=directs[dir].y_offset;

        }
        return list;

    }

    public static void main(String[] args) {
        Integer[][] aa = {
                {3,2,1,8},{2,3,4,7},{4,5,6,9}
        };
        see(aa).forEach(System.out::println);

    }
}
