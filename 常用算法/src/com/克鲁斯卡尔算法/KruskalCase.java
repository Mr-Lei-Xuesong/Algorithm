package com.克鲁斯卡尔算法;

import java.util.Arrays;

public class KruskalCase {
    //顶点数组
    private char[] vertexs;
    //边的个数
    private int edgeNum;
    //邻接矩阵
    private int[][] matrix;
    //表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/{0, 12, INF, INF, INF, 16, 14},
                /*B*/{12, 0, 10, INF, INF, 7, INF},
                /*C*/{INF, 10, 0, 3, 5, 6, INF},
                /*D*/{INF, INF, 3, 0, 4, INF, INF},
                /*E*/{INF, INF, 5, 4, 0, 2, 8},
                /*F*/{16, 7, 6, INF, 2, 0, 9},
                /*G*/{14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.kruskal();
    }

    public KruskalCase(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        //统计边
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        //表示最后结果数组索引
        int index = 0;
        //用于保存‘已有最小生成树’中的每个顶点在最小生成树的终点
        int[] end = new int[edgeNum];
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];
        //获取图中所有边的集合
        EData[] eData = getEdges();
        //按照边的权值大小进行从小到大排序
        sortEdges(eData);
        //遍历edges数组，将边添加到最小生成树中时，判断是准备加入的边是否形成了回路，如果没有，就加入rets，否则就不加入
        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(eData[i].start);
            int p2 = getPosition(eData[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(end, p1);
            int n = getEnd(end, p2);
            //判断是否构成回路
            if (m != n) {//没有构成回路
                end[m] = n;
                rets[index++] = eData[i];
            }
        }
        //统计并打印最小生成树
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    //输出
    public void print() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //排序
    public void sortEdges(EData[] eData) {
        for (int i = 0; i < eData.length; i++) {
            for (int j = 0; j < eData.length - 1 - i; j++) {
                if (eData[j].weight > eData[j + 1].weight) {
                    EData tmp = eData[j];
                    eData[j] = eData[j + 1];
                    eData[j + 1] = tmp;
                }
            }
        }
    }

    //返回顶点值对应的下标，否则返回-1
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    //获取图中的边，放入数组中
    private EData[] getEdges() {
        int index = 0;
        EData[] eData = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    eData[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return eData;
    }

    //获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}