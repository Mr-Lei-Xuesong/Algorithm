package com.demo;

import java.util.ArrayList;
import java.util.List;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89, 11};

        List<Integer> list = seqSearch(arr, 11);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static List<Integer> seqSearch(int[] arr, int value) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                list.add(i);
            }
        }
        return list;
    }
}
