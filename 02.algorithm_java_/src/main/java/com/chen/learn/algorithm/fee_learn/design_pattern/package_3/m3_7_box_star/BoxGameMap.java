package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star;

/**
 * Created by chencc on 2018/11/14.
 */


public class BoxGameMap {

    public static int MAX_MAP_ROW = 16;
    public static int MAX_MAP_COL = 16;

    public static char MAP_V_OUT = 'A';
    public static char MAP_V_BOARD = 'X';
    public static char MAP_V_BOX = 'B';
    public static char MAP_V_DEST = '#';
    public static char MAP_V_SPACE = ' ';
    public static char MAP_V_MAN = '@';

    MapNode[][] m_map = new MapNode[MAX_MAP_ROW][MAX_MAP_COL];

    int m_rowCount;   //��ͼ��ʵ������
    int m_colCount;

    /*
    const char N_MASK_DEAD = 0x02;
    const char N_MASK_DEST = 0x04;
    const char N_MASK_DEST = 0x04;
    */
    public static class MapNode {
        char value;   //'X'��'O'��' '
        boolean isDead;  //�Ƿ��������
        boolean isDest;  //�Ƿ���Ŀ���

        public MapNode(char value, boolean isDead, boolean isDest) {
            this.value = value;
            this.isDead = isDead;
            this.isDest = isDest;
        }

        public MapNode() {
        }
    }

    public static class MapPos {
        int row;
        int col;

        public MapPos(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    ;

    public static class Direction {
        int row_offset;
        int col_offset;

        public Direction(int row_offset, int col_offset) {
            this.row_offset = row_offset;
            this.col_offset = col_offset;
        }
    }


    boolean IsWall(int row, int col) {
        return (m_map[row][col].value == MAP_V_BOARD);
    }


    int GetRowCount() {
        return m_rowCount;
    }

    int GetColCount() {
        return m_colCount;
    }

    MapNode Get(int row, int col) {
        assert ((row >= 0) && (row < m_rowCount));
        assert ((col >= 0) && (col < m_colCount));
        return m_map[row][col];
    }

    void Set(int row, int col, MapNode value) {
        assert ((row >= 0) && (row < m_rowCount));
        assert ((col >= 0) && (col < m_colCount));
        m_map[row][col] = value;
    }


    BoxGameMap(char[] map, int row, int col) {
        assert ((row >= 0) && (row < MAX_MAP_ROW));
        assert ((col >= 0) && (col < MAX_MAP_COL));

        for (int i = 0; i < MAX_MAP_ROW; i++) {
            for (int j = 0; j < MAX_MAP_COL; j++) {
                m_map[i][j] = new MapNode();
            }
        }
        Init(map, row, col);
    }


    void PrintMap() {
        for (int i = 0; i < m_rowCount; i++) {
            for (int j = 0; j < m_colCount; j++) {
                if (m_map[i][j].isDead) {
                    System.out.print("O" + ", ");
                } else {
                    System.out.print(m_map[i][j].value + ", ");
                }
            }

            System.out.println();
        }

        System.out.println();
    }

    boolean Init(char[] map, int row, int col) {
        m_rowCount = row;
        m_colCount = col;
        char[] pos = map;
        for (int i = 0; i < m_rowCount; i++) {
            for (int j = 0; j < m_colCount; j++) {
                m_map[i][j].value = pos[m_colCount * i + j];
                m_map[i][j].isDest = (m_map[i][j].value == MAP_V_DEST);
                m_map[i][j].isDead = false;
            }
        }

        ProcessDead();
        return true;
    }

    void ProcessDead() {
        for (int i = 0; i < m_rowCount; i++) {
            for (int j = 0; j < m_colCount; j++) {
                //是空位置，并且不是目标点的情况，需要判断是否是死亡点
                if (!m_map[i][j].isDest && (m_map[i][j].value == MAP_V_SPACE)) {
                    m_map[i][j].isDead = IsDeadPos(i, j);
                } else {
                    m_map[i][j].isDead = false;
                }
            }
        }
    }

    boolean IsDeadPos(int row, int col) {
//        Direction dirs[] = { { -1, 0 },{ 0, 1 },{ 1, 0 },{ 0, -1 } };


        BoxGameMap.Direction dirs[] = {
                new BoxGameMap.Direction(-1, 0),
                new BoxGameMap.Direction(0, 1),
                new BoxGameMap.Direction(1, 0),
                new BoxGameMap.Direction(0, -1)};

        boolean isCorner = false;
        for (BoxGameMap.Direction dir : dirs) {
            MapPos newPos = new MapPos(row + dir.row_offset, col + dir.col_offset);

            if (m_map[newPos.row][newPos.col].value == MAP_V_BOARD) {
                if (isCorner) {
                    return true; //已经连续两个方向是墙了，说明是个corner
                }

                isCorner = true;
                if (dir.col_offset == 0) //沿行方向找墙
                {
                    int[] col_s_container = new int[1];
                    int[] col_e_container = new int[1];
                    int col_s, col_e;
                    if (GetRowWall(newPos.row, newPos.col, col_s_container, col_e_container)) {
                        col_s = col_s_container[0];
                        col_e = col_e_container[0];
                        if (!IsRowHasDestPos(row, col_s, col_e))
                            return true;
                    }
                } else //否则沿着列方向找墙
                {

                    int[] row_s_container = new int[1];
                    int[] row_e_container = new int[1];
                    int row_s, row_e;
                    if (GetColWall(newPos.row, newPos.col, row_s_container, row_e_container)) {
                        row_s = row_s_container[0];
                        row_e = row_e_container[0];
                        if (!IsColHasDestPos(col, row_s, row_e))
                            return true;
                    }
                }

            } else {
                isCorner = false; //这个方向不是墙，将标记清除
            }
        }

        return false;
    }

    boolean GetRowWall(int row, int col, int[] cs, int[] ce) {
        cs[0] = col;
        while (m_map[row][cs[0]].value == MAP_V_BOARD) {
            if (cs[0] == 0)
                break;

            cs[0]--;
        }
        if ((cs[0] > 0) && (m_map[row][cs[0]].value != MAP_V_OUT))
            return false;

        ce[0] = col;
        while (m_map[row][cs[0]].value == MAP_V_BOARD) {
            if (ce[0] == (m_colCount - 1))
                break;

            ce[0]++;
        }

        if ((ce[0] < (m_colCount - 1)) && (m_map[row][cs[0]].value != MAP_V_OUT))
            return false;

        return true;
    }

    boolean IsRowHasDestPos(int row, int cs, int ce) {
        for (int col = cs; col <= ce; col++) {
            if (m_map[row][col].isDest)
                return true;
        }

        return false;
    }

    boolean GetColWall(int row, int col, int[] rs, int[] re) {
        rs[0] = row;
        while (m_map[rs[0]][col].value == MAP_V_BOARD) {
            if (rs[0] == 0)
                break;

            rs[0]--;
        }
        if ((rs[0] > 0) && (m_map[rs[0]][col].value != MAP_V_OUT))
            return false;

        re[0] = row;
        while (m_map[re[0]][col].value == MAP_V_BOARD) {
            if (re[0] == (m_rowCount - 1))
                break;

            re[0]++;
        }

        if ((re[0] < (m_rowCount - 1)) && (m_map[re[0]][col].value != MAP_V_OUT))
            return false;

        return true;
    }

    boolean IsColHasDestPos(int col, int rs, int re) {
        for (int row = rs; row <= re; row++) {
            if (m_map[row][col].isDest)
                return true;
        }

        return false;
    }

}
