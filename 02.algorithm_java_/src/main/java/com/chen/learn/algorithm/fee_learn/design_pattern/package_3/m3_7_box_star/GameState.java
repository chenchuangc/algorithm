package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star;

import java.util.Collections;
import java.util.LinkedList;

import static com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star.BoxGameMap.MAP_V_BOX;
import static com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star.BoxGameMap.MAP_V_MAN;

/**
 * Created by chencc on 2018/11/14.
 */
public class GameState {

    LinkedList<BoxGameMap.MapPos> m_boxes = new LinkedList<>();
    BoxGameMap.MapPos m_man;

    Action m_action=new Action();
    GameState m_parent;

    class Action {
        LinkedList<BoxGameMap.MapPos> path=new LinkedList<>();
        int push_x;
        int push_y;
    }



    BoxGameMap.MapPos GetMan() {
        return m_man;
    }

    void SetActionPath(LinkedList<BoxGameMap.MapPos> path) {
        m_action.path = path;
    }


    LinkedList<BoxGameMap.MapPos> GetActionPath() {
        return m_action.path;
    }

    int GetBoxCount() {
        return m_boxes.size();
    }

    BoxGameMap.MapPos GetBox(int index) {
        return m_boxes.get(index);
    }

    GameState GetParent() {
        return m_parent;
    }

    boolean IsBox(int row, int col) {
        for (BoxGameMap.MapPos box : m_boxes) {
            if ((box.row == row) && (box.col == col)) {
                return true;
            }
        }

        return false;
    }


    GameState(char[] map, int row, int col) {
        Init(map, row, col);
        m_parent = null;
    }


    GameState(GameState parent) {
        m_man = new BoxGameMap.MapPos(parent.m_man.row, parent.m_man.col);
        m_boxes = copy(parent.m_boxes);
//        m_action = parent.m_action;
        m_parent = parent;
    }

    private LinkedList<BoxGameMap.MapPos> copy(LinkedList<BoxGameMap.MapPos> m_boxes) {
        LinkedList<BoxGameMap.MapPos> copy = new LinkedList<>();
        for (BoxGameMap.MapPos pos : m_boxes) {
            copy.add(new BoxGameMap.MapPos(pos.row, pos.col));
        }
        return copy;

    }

    void MoveMan(int row, int col, int row_offset, int col_offset) {
        BoxGameMap.MapPos newPos = new BoxGameMap.MapPos(row + row_offset, col + col_offset);
        for (BoxGameMap.MapPos box : m_boxes) {
            if ((box.row == newPos.row) && (box.col == newPos.col)) {
                box.row += row_offset;
                box.col += col_offset;
            }
        }

        m_action.push_x = row_offset;
        m_action.push_y = col_offset;
        m_man = newPos;
    }
//
//     boolean IsBox(int row, int col)   
//    {
//        for (BoxGameMap.MapPos box : m_boxes)
//        {
//            if ((box.row == row) && (box.col == col))
//            {
//                return true;
//            }
//        }
//
//        return false;
//    }

    boolean IsSameState(GameState state) {
        if ((m_man.row != state.m_man.row) || (m_man.col != state.m_man.col))
            return false;

        if (!isSame(m_boxes,state.m_boxes))
            return false;

        return true;
    }

    private boolean isSame(LinkedList<BoxGameMap.MapPos> m_boxes, LinkedList<BoxGameMap.MapPos> other) {

        int len = m_boxes.size();
        for (int i=0;i<len;i++) {
            BoxGameMap.MapPos this_box = m_boxes.get(i);
            BoxGameMap.MapPos other_box = other.get(i);

            if ((this_box.col != other_box.col) || (this_box.row != other_box.row)) {
                return false;
            }
        }
        return true;

    }

    void Init(char[] map, int row, int col) {
        char[] pos = map;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pos[i * col + j] == MAP_V_MAN) {
                    m_man = new BoxGameMap.MapPos(i, j);
                }
                if (pos[i * col + j] == MAP_V_BOX) {
                    m_boxes.add(new BoxGameMap.MapPos(i, j));
                }

            }
        }
    }
}
