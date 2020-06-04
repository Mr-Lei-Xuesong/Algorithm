package com.demo;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {
        //得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大位数是几位数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int value : arr) {
                int digitOfElement = value / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，就放入数组中
                if (bucketElementCounts[k] != 0) {
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        arr[index] = bucket[k][j];
                        index++;
                    }
                }
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮对个位排序处理" + Arrays.toString(arr));
        }
    }

    //基数排序详细方法
    public static void radixSort1(int[] arr) {
        //定义一个二维，表示10个桶，每个桶就是一个一维数组
        //为了防止在放置数的时候，数据溢出，则每个一维数组，大小定义为arr.length
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        //第一轮(针对每个元素的个位进行排序处理)
        for (int value : arr) {
            int digitOfElement = value % 10;
            //第一次:digitOfElement=3
            // bucket[3][0]=53
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        //遍历每一桶，并将桶中的数据，放入到原来数组
        for (int i = 0; i < bucket.length; i++) {
            //如果桶中有数据，就放入数组中
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("第一轮对个位排序处理" + Arrays.toString(arr));

        //第二轮(针对每个元素的十位进行排序处理)
        for (int value : arr) {
            int digitOfElement = value / 10 % 10;
            //第一次:digitOfElement=3
            // bucket[3][0]=53
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中的数据，放入到原来数组
        for (int i = 0; i < bucket.length; i++) {
            //如果桶中有数据，就放入数组中
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("第二轮对个位排序处理" + Arrays.toString(arr));
        //第三轮(针对每个元素的百位进行排序处理)
        for (int value : arr) {
            int digitOfElement = value / 100 % 10;
            //第一次:digitOfElement=3
            // bucket[3][0]=53
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中的数据，放入到原来数组
        for (int i = 0; i < bucket.length; i++) {
            //如果桶中有数据，就放入数组中
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }
            bucketElementCounts[i] = 0;
        }
        System.out.println("第三轮对个位排序处理" + Arrays.toString(arr));
    }
}
