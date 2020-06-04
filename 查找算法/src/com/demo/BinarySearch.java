package com.demo;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        List<Integer> list = binarySearch(arr, 0, arr.length - 1, 10);
        System.out.println(list);
    }

    /**
     * @param arr     原数组  从小到大排列
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 需要查找的值
     * @return 找到就返回值的下标，没有找到就返回空
     */
    public static List<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                list.add(temp);
                temp--;
            }
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                list.add(temp);
                temp++;
            }
            list.add(mid);

            return list;
        }
    }
}
