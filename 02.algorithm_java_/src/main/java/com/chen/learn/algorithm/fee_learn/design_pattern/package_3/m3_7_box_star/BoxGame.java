package com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star;

import java.util.LinkedList;

import static com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star.BoxGameMap.MAP_V_BOARD;
import static com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star.BoxGameMap.MAX_MAP_COL;
import static com.chen.learn.algorithm.fee_learn.design_pattern.package_3.m3_7_box_star.BoxGameMap.MAX_MAP_ROW;

public class BoxGame {

    BoxGameMap m_map;
    LinkedList<GameState> m_states = new LinkedList<>();
    int m_result;

    boolean InitMap(char[] map, int row, int col)
    {
        if ((row < 0) || (row >= MAX_MAP_ROW))
        {
            return false;
        }

        if ((col < 0) & (col >= MAX_MAP_COL))
        {
            return false;
        }

        m_map = new BoxGameMap(map, row, col);
        GameState state = new GameState(map, row, col);
        m_states.add(state);
        m_map.PrintMap();
        return (m_map != null);
    }

    boolean ResolveGame()
    {
        int index = 0;
        while (index < m_states.size())
        {
            GameState state = m_states.get(index);
            if (IsFinalState(state))
            {
                m_result++;
                PrintMoveRecords(state);
            }
            else
            {
                SearchNewGameStates(state);
            }
            index++;
            if ((index % 1000) == 0)
                System.out.println( "index " + index );
        }

        return (m_result > 0);
    }

    boolean CanPushBox(GameState state, BoxGameMap.MapPos man, int row_offset, int col_offset)
    {
        boolean canMove = false;


        BoxGameMap.MapPos box =
                new BoxGameMap.MapPos(man.row + row_offset, man.col + col_offset);  //小人的推动方向是箱子
        BoxGameMap.MapPos boxTo =
                new BoxGameMap.MapPos(box.row + row_offset, box.col + col_offset);  //箱子的新位置坐标

        BoxGameMap.MapNode node = m_map.Get(boxTo.row, boxTo.col);
        if (node.isDead)
        {
            return false; //这个位置是死点
        }
        if ((node.value == MAP_V_BOARD) || state.IsBox(boxTo.row, boxTo.col))
        {
            return false; //这个位置是障碍物或其他箱子
        }

        return true;
    }

    void SearchNewGameStates(GameState state)
    {
//        BoxGameMap.Direction dirs[] = { { -1, 0 },{ 0, 1 },{ 1, 0 },{ 0, -1 } };
        BoxGameMap.Direction dirs[] = {
                new BoxGameMap.Direction(-1, 0),
                new BoxGameMap.Direction(0, 1),
                new BoxGameMap.Direction(1, 0),
                new BoxGameMap.Direction(0, -1)};

        for (int i = 0; i < state.GetBoxCount(); i++)
        {
            //检查每一个箱子周围四个方向是否有空位置，可以将小人移动过来
            BoxGameMap.MapPos box = state.GetBox(i);
            for (BoxGameMap.Direction dir : dirs)
            {
                BoxGameMap.MapPos newMan = new BoxGameMap.MapPos(box.row + dir.row_offset, box.col + dir.col_offset);
                //只要不是墙或箱子，就尝试能否将小人移动到箱子边上
                if (!m_map.IsWall(newMan.row, newMan.col) & !state.IsBox(newMan.row, newMan.col))
                {
                    AStar astar = new AStar(m_map, state);
                    LinkedList<BoxGameMap.MapPos> path = new LinkedList<>();
                    if (astar.FindPath(state.GetMan(), newMan, path)) //有路径可以到这个地方
                    {
                        //推的方向是小人的与箱子位置的反方向
                        BoxGameMap.Direction push = new BoxGameMap.Direction(-dir.row_offset, -dir.col_offset);
//                        System.out.println();
                        MoveToNewState(state, newMan, path, push);//尝试在这个位置推箱子
                    }
                }
            }
        }
    }

    void MoveToNewState(GameState parent, BoxGameMap.MapPos man, LinkedList<BoxGameMap.MapPos> path, BoxGameMap.Direction push)
    {
        if (CanPushBox(parent, man, push.row_offset, push.col_offset))
        {
            GameState state = new GameState(parent);
            state.SetActionPath(path);
//            System.out.println("man origin : [ "+man.row+","+man.col+" ]");
            state.MoveMan(man.row, man.col, push.row_offset, push.col_offset);
//            System.out.println("man : [ "+state.m_man.row+","+state.m_man.col+" ]");
            if (!AddNewState(state))
            {

            }
        }
    }

    boolean IsFinalState( GameState state)
    {
        for (int i = 0; i < state.GetBoxCount(); i++)
        {
            BoxGameMap.MapPos box = state.GetBox(i);
            BoxGameMap.MapNode node = m_map.Get(box.row, box.col);
            if (!node.isDest)
                return false;
        }

        return true;
    }

    boolean AddNewState(GameState state)
    {

        for (GameState item : m_states)
        {
            if (item.IsSameState(state))
            {
                return false;
            }
        }

        m_states.add(state);
        return true;
    }

    void PrintMoveRecords(GameState state)
    {
        System.out.println("Find result "+ m_result);

        GameState parent = state;
        while (parent.GetParent() != null)
        {
         LinkedList<BoxGameMap.MapPos> path = parent.GetActionPath();

            System.out.println("Step x:" );
            if (!path.isEmpty())
            {
                System.out.print("Man Move :");
                for (BoxGameMap.MapPos item : path)
                {
                    System.out.print( "[" + item.row + " , " + item.col + "] ");
                }
                System.out.println();
            }
            BoxGameMap.MapPos man = parent.GetMan();

            System.out.print( "Man push [ "+ man.row + " , "+ man.col + " ]" );

            parent = parent.GetParent();
        }

        System.out.println();
    }

}
