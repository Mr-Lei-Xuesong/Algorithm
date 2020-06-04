package com.demo;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 8, 10, 14};

        ArrBinaryTree tree = new ArrBinaryTree(arr);
        tree.preOrder(0);

        System.out.println("--------");

        tree.infixOrder(0);

        System.out.println("--------");

        tree.postOrder(0);
    }
}

//顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //顺序存储二叉树前序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
            return;
        }
        System.out.println(arr[index]);
        //向左递归
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //向右递归
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    //顺序存储二叉树中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
            return;
        }
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //顺序存储二叉树后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历");
            return;
        }
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);
    }
}