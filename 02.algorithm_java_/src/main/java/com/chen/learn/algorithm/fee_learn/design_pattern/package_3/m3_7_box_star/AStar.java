package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star;


import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by chencc on 2018/11/14.
 */
public class AStar {


    static class ANODE implements Comparable<ANODE> {

        int row;
        int col;
        double g;
        double h;
        int prev_row;
        int prev_col;

        boolean eq(ANODE anode) {
            if ((row == anode.row) && (col == anode.col)) {
                return true;
            }

            return false;
        }

        public ANODE(int row, int col, double g, double h, int prev_row, int prev_col) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.h = h;
            this.prev_row = prev_row;
            this.prev_col = prev_col;
        }

        public ANODE(int row, int col, double g, double h) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.h = h;
        }

        @Override
        public int compareTo(ANODE o) {
            double ts = this.g + this.h;
            double os = o.g + o.h;

            if (ts == os) return 0;
            if (ts > os) {
                return 1;
            } else {
                return -1;
            }
        }
    }


    double ManhattanDistance(ANODE n1, ANODE n2) {
        return (Math.abs(n1.row - n2.row) + Math.abs(n1.col - n2.col));
    }

    BoxGameMap m_map;
    GameState m_state;
    TreeSet<ANODE> m_open = new TreeSet<>();
    LinkedList<ANODE> m_close = new LinkedList<>();


    public AStar(BoxGameMap map, GameState state) {
        this.m_map = map;
        this.m_state = state;
    }


    boolean FindPath(BoxGameMap.MapPos from, BoxGameMap.MapPos to, LinkedList<BoxGameMap.MapPos> path) {
        ANODE source = new ANODE(from.row, from.col, 0.0, 0.0, -1, -1);
        ANODE target = new ANODE(to.row, to.col, 0.0, 0.0, -1, -1);

        //步骤(1)
        m_open.add(source);

        //步骤(2)
        ANODE[] cur_nodeContainer = new ANODE[1];
        ANODE cur_node;
        while (ExtractMiniFromOpen(cur_nodeContainer)) {
            //graph->open.erase(cur_node);
            cur_node = cur_nodeContainer[0];
            m_close.add(cur_node);
            if (cur_node.eq(target)) {
                GetPath(path);
                return true;
            }

            BoxGameMap.Direction dirs[] = {
                    new BoxGameMap.Direction(-1, 0),
                    new BoxGameMap.Direction(0, 1),
                    new BoxGameMap.Direction(1, 0),
                    new BoxGameMap.Direction(0, -1)};
            //步骤(3)
            for (BoxGameMap.Direction dir : dirs) {
                ANODE nn = new ANODE(cur_node.row + dir.row_offset, cur_node.col + dir.col_offset, 0.0, 0.0);

                if ((nn.row >= 0) && (nn.row < m_map.GetRowCount()) && (nn.col >= 0) && (nn.col < m_map.GetColCount()) &&
                        IsEmpty(nn.row, nn.col) && !IsNodeExistInClose(nn.row, nn.col)) {

                    ANODE it = find(m_open, nn);
                    if (it == null) /*nn不在open列表*/ {
                        nn.g = cur_node.g + 1; //将g始终赋值为0可得到BFS算法的效果
                        nn.h = ManhattanDistance(nn, target);
                        nn.prev_row = cur_node.row;
                        nn.prev_col = cur_node.col;
                        m_open.add(nn);
                    } else  /*nn在open列表中*/ {
                        if ((cur_node.g + 1.0) < it.g) {
                            nn.g = cur_node.g + 1.0;
                            nn.h = it.h;
                            nn.prev_row = cur_node.row;
                            nn.prev_col = cur_node.col;
                            m_open.remove(it);
                            m_open.add(nn);
                        }
                    }
                }
            }
        }

        return false;
    }

    private ANODE find(TreeSet<ANODE> m_open, ANODE nn) {
        for (ANODE anode : m_open) {
            if (anode.eq(nn)) {
                return anode;
            }
        }
        return null;
    }

    boolean ExtractMiniFromOpen(ANODE[] node) {
        if (m_open.isEmpty()) {
            return false;
        }
        node[0] = m_open.first();
        boolean ifD = m_open.remove(node[0]);
        return true;
    }

    boolean IsNodeExistInClose(int row, int col) {

        for (ANODE anode : m_close) {
            if (anode.col == col && anode.row == row) {
                return true;
            }
        }
        return false;
    }

    boolean IsEmpty(int row, int col) {
        if (m_map.IsWall(row, col))
            return false;

        if (m_state.IsBox(row, col))
            return false;

        return true;
    }

    void GetPath(LinkedList<BoxGameMap.MapPos> path) {

        Collections.reverse(m_close);
        ANODE target = m_close.removeFirst();
        int preRow = target.prev_row;
        int preCol = target.prev_col;
        path.add(new BoxGameMap.MapPos(target.row, target.col));

        while (!m_close.isEmpty()) {
            ANODE temp = m_close.removeFirst();
            if (preRow == temp.row && preCol == temp.col) {
                path.addFirst(new BoxGameMap.MapPos(temp.row, temp.col));
                preCol = temp.prev_col;
                preRow = temp.prev_row;
            }
        }

      /*  List<ANODE>::reverse_iterator it = m_close.rbegin();
        int ri = it -> row;
        int rj = it -> col;
        while ((it != m_close.rend()) (ri != -1) (rj != -1))
        {
            if ((ri == it -> row) (rj == it -> col))
            {
                //path.push_back({ it->i, it->j });
                path.insert(path.begin(), {it -> row, it -> col});
                ri = it -> prev_row;
                rj = it -> prev_col;
            }

            ++it;
        }*/
    }
}