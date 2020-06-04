package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DubbleSort {
    public static void main(String[] args) {
        //创建一个80000的数组
        /*int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ago = format.format(date1);
        System.out.println("排序前的时间=" + ago);

        //排序
        bubbleSort(arr);

        Date date2 = new Date();
        String now = format.format(date2);
        System.out.println("排序后的时间=" + now);*/
        int[] arr = {5, 9, 4, 3, 8, 7, 1, 0, 6};
        bubbleSort(arr);

    }

    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
