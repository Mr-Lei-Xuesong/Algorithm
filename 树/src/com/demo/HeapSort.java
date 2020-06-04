package com.demo;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //升序排列
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        System.out.println(Arrays.toString(arr));
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将数组(二叉树)，调整成一个大顶堆
     * @param arr    待调整的数组
     * @param i      表示非叶子节点的索引
     * @param length 对多少个元素继续调整，再逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        //j=i*2+1是i节点的左子节点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {//说明左子节点小于右子节点
                j++;//指向右子节点
            }
            if (arr[j] > temp) {//如果子节点大于父节点
                arr[i] = arr[j];//就把较大的值赋给当前节点
                i = j;//i指向j，继续循环比较
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
