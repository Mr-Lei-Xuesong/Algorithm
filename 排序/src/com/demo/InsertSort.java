package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        /*int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ago = format.format(date1);
        System.out.println("排序前的时间=" + ago);

        insertSort(arr);

        Date date2 = new Date();
        String now = format.format(date2);
        System.out.println("排序后的时间=" + now);*/
        int[] arr = {5, 9, 4, 3, 8, 7, 1, 0, 6};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        int insertVal, insertIndex;
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            insertVal = arr[i];
            //待插入数的前一位
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
