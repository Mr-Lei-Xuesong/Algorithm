package com.分治法;

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(6,'A','B','C');
    }

    //汉诺塔移动，使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {//如果只有一个盘
            System.out.println("第一个盘：" + a + "->" + c);
        } else {//如果有>=2的盘，可以总是看成两个盘，最下边的一个盘，和上面所有的盘
            //1.先把上面所有盘移动到B塔
            hanoiTower(num - 1, a, c, b);
            //2.把最下面的盘移动到C塔
            System.out.println("第" + num + "个盘从：" + a + "->" + c);
            //把B塔上所有的盘移动到C塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}