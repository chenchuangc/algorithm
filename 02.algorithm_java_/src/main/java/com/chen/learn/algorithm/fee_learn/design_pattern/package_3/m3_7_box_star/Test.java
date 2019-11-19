package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star;

import java.math.BigInteger;

/**
 * Created by chencc on 2018/11/15.
 */
public class Test {

/*
    static int MAP_ROW = 8;
    static int MAP_COL = 8;

    static char map[64] =
    {
        { ' ',' ','X','X','X',' ',' ',' ' },
        { ' ',' ','X','?','X',' ',' ',' ' },
        { ' ',' ','X',' ','X','X','X','X' },
        { 'X','X','X','O',' ','O','?','X' },
        { 'X','?',' ','O','*','X','X','X' },
        { 'X','X','X','X','O','X',' ',' ' },
        { ' ',' ',' ','X','?','X',' ',' ' },
        { ' ',' ',' ','X','X','X',' ',' ' }
    };*/
/*
static int MAP_ROW = 9;
    static int MAP_COL = 9;

    static char map[] =
{
     'A','A','A','A','X','X','X','X','X',
     'A','A','A','A','X',' ',' ','@','X',
     'X','X','X','A','X','B','B',' ','X',
     'X','#','X','A','X',' ','B',' ','X',
     'X','#','X','X','X',' ','X','X','A',
     'X','#',' ',' ',' ',' ','X','X','A',
     'X',' ',' ','X',' ',' ',' ','X','X',
     'X','X','X','X',' ',' ',' ','X','X',
     'A','A','A','X','X','X','X','X','A'
};
*/
/*
const int MAP_ROW = 11;
const int MAP_COL = 13;

char map[] =
{
    'A','A','A','X','X','X','X','X','X','X','A','A','A',
    'X','X','X','X',' ',' ',' ',' ',' ','X','A','A','A',
    'X',' ',' ',' ','#','X','X','X',' ','X','A','A','A',
    'X',' ','X',' ','X',' ',' ',' ',' ','X','X','A','A',
    'X',' ','X',' ','B',' ','B','X','#',' ','X','A','A',
    'X',' ','X',' ',' ','X',' ',' ','X',' ','X','A','A',
    'X',' ','#','X','B',' ','B',' ','X',' ','X','A','A',
    'X','X',' ',' ',' ',' ','X',' ','X',' ','X','X','X',
    'A','X',' ','X','X','X','#',' ',' ',' ',' ','@','X',
    'A','X',' ',' ',' ',' ',' ','X','X',' ',' ',' ','X',
    'A','X','X','X','X','X','X','X','X','X','X','X','X'
};
*/

/*
    static int MAP_ROW = 5;
    static int MAP_COL = 5;

    static char map[] =
            {
                    'X', 'X', 'X', 'X', 'X',
                    'X', '#', ' ', ' ', 'X',
                    'X', ' ', 'B', ' ', 'X',
                    'X', ' ', ' ', '@', 'X',
                    'X', 'X', 'X', 'X', 'X'
            };*/

    /*

    static int MAP_ROW = 8;
    static int MAP_COL = 10;

    static char map[] =
            {
                    'A', 'A', 'X', 'X', 'X', 'X', 'A', 'A', 'A', 'A',
                    'A', 'A', 'X', '@', ' ', 'X', 'X', 'X', 'A', 'A',
                    'A', 'A', 'X', ' ', 'B', ' ', ' ', 'X', 'A', 'A',
                    'A', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'A',
                    'A', 'X', '#', 'X', ' ', 'X', ' ', ' ', 'X', 'A',
                    'A', 'X', '#', 'B', ' ', ' ', 'X', ' ', 'X', 'A',
                    'A', 'X', '#', ' ', ' ', ' ', 'B', ' ', 'X', 'A',
                    'A', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A',
            };
*/

    static int MAP_ROW = 8;
    static int MAP_COL = 10;

    static char map[] =
            {
                    'A', 'A', 'X', 'X', 'X', 'X', 'A', 'A', 'A', 'A',
                    'A', 'A', 'X', '@', ' ', 'X', 'X', 'X', 'A', 'A',
                    'A', 'A', 'X', ' ', 'B', ' ', ' ', 'X', 'A', 'A',
                    'A', 'X', 'X', 'X', ' ', 'X', ' ', 'X', 'X', 'A',
                    'A', 'X', 'X', 'X', ' ', 'X', ' ', ' ', 'X', 'A',
                    'A', 'X', '#', 'B', ' ', ' ', 'X', ' ', 'X', 'A',
                    'A', 'X', '#', ' ', ' ', ' ', ' ', ' ', 'X', 'A',
                    'A', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'A',
            };

    public static void main(String[] args) {

        BoxGame game = new BoxGame();
        BigInteger t1, t2, tc;
//            QueryPerformanceFrequency(&tc);
//            QueryPerformanceCounter(&t1);
        if (game.InitMap(map, MAP_ROW, MAP_COL)) {
            game.ResolveGame();
        }
//            QueryPerformanceCounter(&t2);
//            std::cout << "Use Time: " << (t2.QuadPart - t1.QuadPart)*1.0 / tc.QuadPart << std::endl;


    }
}
