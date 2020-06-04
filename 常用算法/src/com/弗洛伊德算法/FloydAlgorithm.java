package com.弗洛伊德算法;

import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    //顶点集合
    private char[] vertex;
    //从各个顶点出发到其他顶点的距离
    private int[][] dis;
    //到达目的地顶点的前驱顶点
    private int[][] pre;

    /**
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "到" + vertex[j] + "：" + dis[i][j] + ") \t");
            }
            System.out.println();
        }
    }

    //弗洛伊德算法
    public void floyd() {
        //保存距离
        int len = 0;
        //对中间顶点遍历,K就是中间顶点下标[A,B,C,D,E,F,G]
        for (int k = 0; k < dis.length; k++) {
            //从i顶点开始出发[A,B,C,D,E,F,G]
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    //从顶点i出发，经过k中间地点，到达j顶点距离
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        //更新距离
                        dis[i][j] = len;
                        //更新前驱顶点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }

    }
}