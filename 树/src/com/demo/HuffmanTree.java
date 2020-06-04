package com.demo;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node tree = createHuffmanTree(arr);

        preOrder(tree);

    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);
            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建一个新的二叉树，权值为左右子节点之和
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到ArrayList中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //前序遍历方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }
}

//创建节点类
class Node implements Comparable<Node> {
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}