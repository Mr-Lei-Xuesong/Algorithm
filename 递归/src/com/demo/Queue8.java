package com.demo;

public class Queue8 {
    int max = 8;
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有" + count + "解法");
    }

    //放置第n个皇后
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行的第一列
            array[n] = i;
            //判断当前放置第n个皇后到i列时，是否冲突
            if (judge(n)) {//不冲突
                //接着放n+1个皇后
                check(n + 1);
            }
            //如果冲突，就继续执行array[n]=i,即将第n个皇后，放置在本行的后移一个位置
        }
    }

    /**
     * @param n 代表当前行数
     * @return 不冲突就返回true，冲突就返回false
     */
    //查看当我们放置第n个皇后，就去检测该皇后是否与前面已摆放的皇后冲突
    //不冲突的条件是：不能存在同一行(因为行存在自增，所以不考虑冲突问题)，同一列，同一斜线
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //array[i]==array[n] 表示的是不能存在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i])  表示判断第 n 个皇后是否和第 i 皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
