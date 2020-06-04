package com.骑士周游;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {
    //棋盘的列
    private static int X;
    //棋盘的行
    private static int Y;
    //标记各个位置是否被访问过
    private static boolean[] visited;
    //标记棋盘所有位置是否都被访问过
    private static boolean finished;

    public static void main(String[] args) {
        //棋盘总数
        X = 8;
        Y = 8;
        int[][] chessboard = new int[X][Y];
        //马儿的初始位置
        int row = 1;
        int column = 1;
        //初始化访问记录
        visited = new boolean[X * Y];

        //记录时间
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时" + (end - start));

        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 骑士周游方法
     *
     * @param chessboard 棋盘
     * @param row        马儿当前的位置的行 从0开始
     * @param column     马儿当前位置的列 从0开始
     * @param step       是第几步，初始位置为第一步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        //标记该位置已被访问
        visited[row * X + column] = true;
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序
        sort(ps);
        //遍历能走通的路
        while (!ps.isEmpty()) {
            //取出下一个可走的位置
            Point p = ps.remove(0);
            //为真就代表还未访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断马儿是否全部走完棋盘(用step与棋盘总数比较)，如果没有走完就将整个棋盘置为0
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    //功能：根据当前位置(Point对象)，计算马儿还能走哪些位置(Point)，并放入到一个集合中，最多有八个位置
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //判断马儿能走的位置，并加入到集合
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根据当前这个一步的所有下一步的选择位置，进行非递减排序,减少回溯次数
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}
