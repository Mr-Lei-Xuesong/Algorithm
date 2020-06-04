package com.动态算法;

import java.util.Arrays;

public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] w = {1, 4, 3};
        //物品的价值
        int[] val = {1500, 3000, 2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = w.length;

        //创建二维数组，表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //记录商品放入情况
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行第一列设置为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        Arrays.fill(v[0], 0);

        //根据公式来动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                if (w[i - 1] > j) {//当重量大于了当前背包容量
                    v[i][j] = v[i - 1][j];//就继续使用上一个的策略
                } else {//如果不大于就比较上一个策略的物价是否小于新增加的策略的物价
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出二维数组
        for (int[] ints : v) {
            System.out.println(Arrays.toString(ints));
        }

        //输出放入的商品
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
