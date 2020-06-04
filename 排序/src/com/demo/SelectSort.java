package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        /*int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ago = format.format(date1);
        System.out.println("排序前的时间=" + ago);

        //排序
        selectSort(arr);

        Date date2 = new Date();
        String now = format.format(date2);
        System.out.println("排序后的时间=" + now);*/
        int[] arr = {5, 9, 4, 3, 8, 7, 1, 0, 6};
        selectSort(arr);
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //记录最小索引值
            int minIndex = i;
            //假定arr[0]为最小
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
