package com.KMP算法;

public class ViolenceMath {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int index = violenceMath(str1, str2);
        System.out.println(index);
    }

    //暴力匹配算法
    public static int violenceMath(String str1, String str2) {
        //将字符串转为字符数组
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        //获取字符数组长度
        int s1Len = s1.length;
        int s2Len = s2.length;

        //指向s1索引
        int i = 0;
        //指向s2索引
        int j = 0;

        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {//匹配成功，就往后继续匹配
                i++;
                j++;
            } else {//没有匹配成功
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
