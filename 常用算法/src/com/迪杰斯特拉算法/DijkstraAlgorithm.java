package com.迪杰斯特拉算法;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);
        graph.dsj(6);
        graph.showDjs();
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //迪杰斯特拉算法
    public void dsj(int index) {//index表示出发顶点对应的下标
        vv = new VisitedVertex(vertex.length, index);
        //更新index顶点到周围顶点的距离和前驱顶点
        update(index);
        for (int i = 0; i < vertex.length; i++) {
            //选择并返回新的访问节点
            index = vv.updateArr();
            //更新index顶点到周围顶点的距离和前驱顶点
            update(index);
        }
    }

    //更新Index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    public void update(int index) {
        int len = 0;
        //根据遍历邻接矩阵从matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //出发顶点到Index顶点的距离+从index顶点到I顶点的距离和
            len = vv.getDis(index) + matrix[index][i];
            //如果i节点没有被访问过，并且len小于出发顶点到i顶点的距离，就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                //更新i顶点到前驱index顶点
                vv.updatePre(i, index);
                //更新出发顶点到i顶点的距离
                vv.updateDis(i, len);
            }
        }
    }

    //显示结果
    public void showDjs() {
        vv.show();
    }
}

class VisitedVertex {
    //记录各个顶点是否被访问，访问过为1，未访问为0
    public int[] already_arr;
    //每个下标对应的值的前一个顶点下标
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离
    public int[] dis;

    /**
     * @param length 顶点的个数
     * @param index  出发点的下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65534);
        //设置出发顶点已被访问过
        this.already_arr[index] = 1;
        //出发顶点的的访问距离
        this.dis[index] = 0;
    }

    //判断Index顶点是否访问过
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    //更新出发顶点到Index顶点的距离
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    //更新pre这个顶点顶点前驱为index节点
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    //返回出发顶点到Index顶点的距离
    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问节点
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    //显示最后结果
    public void show() {
        System.out.println("-------------");
        for (int i : already_arr) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(" " + i);
        }
        System.out.println();
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ")");
            } else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}