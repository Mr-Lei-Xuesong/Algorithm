package com.普里姆算法;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };
        MGraph graph = new MGraph(verxs);
        MinTree tree = new MinTree();
        tree.createGraph(graph, verxs, data, weight);
        tree.showGraph(graph);
        tree.prim(graph,0);
    }
}

class MGraph {
    //存放节点数据
    char[] data;
    //表示图的节点个数
    int verxs;
    //存放边，邻接矩阵
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}

//创建最小生成树
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图对应的顶点
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }


    /**
     * Prim算法，得到最小生成树
     * @param graph 图
     * @param v 表示从图的第几个顶点开始
     */
    public void prim(MGraph graph, int v) {
        //标记顶点是否被访问过
        int[] visited = new int[graph.verxs];
        //标记当前顶点已访问过
        visited[v] = 1;
        //记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println(graph.data[h1] + "," + graph.data[h2] + ":" + minWeight);

            visited[h2] = 1;

            minWeight = 10000;
        }

    }
}