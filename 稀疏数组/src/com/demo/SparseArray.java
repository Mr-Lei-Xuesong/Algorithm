package com.demo;

public class SparseArray {
    public static void main(String[] args) {
        //创建原始二维数组
        int[][] chessArray1 = new int[6][7];
        chessArray1[0][3] = 22;
        chessArray1[0][6] = 15;
        chessArray1[1][1] = 11;
        chessArray1[1][5] = 17;
        chessArray1[2][3] = -6;
        chessArray1[3][5] = 39;
        chessArray1[4][0] = 91;
        chessArray1[5][2] = 28;
        System.out.println("原始二维数组的值为:");
        for (int[] row : chessArray1) {
            for (int value : row) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
        System.out.println("--------");
        int sum = 0;
        //计算有多少个不同数
        for (int[] ints : chessArray1) {
            for (int j = 0; j <= chessArray1.length; j++) {
                if (ints[j] != 0) {
                    sum++;
                }
            }
        }
        //创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArray1.length;
        sparseArr[0][1] = chessArray1[0].length;
        sparseArr[0][2] = sum;
        int count = 0;
        for (int i = 0; i < chessArray1.length; i++) {
            for (int j = 0; j <= chessArray1.length; j++) {
                if (chessArray1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray1[i][j];
                }
            }
        }
        System.out.println("稀疏数组的值为:");
        for (int[] row : sparseArr) {
            for (int value : row) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
        //创建新的二维数组
        int[][] newArray = new int[sparseArr[0][0]][sparseArr[0][1]];
        //将稀疏数组的值保存到新的二维数组中
        for (int i = 1; i < sparseArr.length; i++) {
            newArray[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("--------");
        System.out.println("恢复后的二维数组的值为:");
        for (int[] row : newArray) {
            for (int value : row) {
                System.out.printf("%d\t", value);
            }
            System.out.println();
        }
    }
}
