package com.demo;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

        quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            //设置基准数
            int x = arr[left];
            while (i < j) {
                //从后往前找比基准数(x)小的数
                while (i < j && arr[j] >= x) {
                    j--;
                }
                //找到后跟基准数交换位置，留下一个空位arr[j]
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }
                //从前往后找比基准数(x)大的数
                while (i < j && arr[i] < x) {
                    i++;
                }
                //找到后填入到arr[j]中，留下一个空位arr[i]
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            //把基准数填入到arr[i]
            arr[i] = x;
            //以上操作完以基准数为准，右边比基准数大，左边比基准数小
            //继续以上操作
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    public static void quickSort1(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        //左边索引
        int i = left;
        //右边索引
        int j = right;
        //基准数
        int index = arr[left];

        while (i < j) {
            //先看右边，依次往左递减
            while (arr[j] >= index && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (arr[i] <= index && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[left] = arr[i];
        arr[i] = index;
        //递归调用左半数组
        quickSort1(arr, left, j - 1);
        //递归调用右半数组
        quickSort1(arr, j + 1, right);
    }

    public static void quickSort2(int[] arr, int left, int right) {
        //左下标
        int l = left;
        //右下标
        int r = right;
        //中轴值
        int pivot = arr[(left + right) / 2];
        //临时变量
        int temp = 0;
        //while循环的目的是让比pivot值小放到左边
        //比pivot值大放到右边
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r说明pivot的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后，发现这个arr[l]==pivot值相等r--，前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r]==pivot值相等l++，后移
            if (arr[r] == pivot) {
                l += 1;
            }
            //如果l==r,必须l++,r--,否则为出现栈溢出
            if (l == r) {
                l += 1;
                r -= 1;
            }
            //向左递归
            if (left < r) {
                quickSort2(arr, left, r);
            }
            //向右递归
            if (right > l) {
                quickSort2(arr, l, right);
            }
        }
    }
}
