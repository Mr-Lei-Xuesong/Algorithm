package com.demo;

import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("push:  表示添加数据到栈(入栈)");
            System.out.println("pop:  表示从栈取出数据(出栈)");
            System.out.println("length:  表示栈有多少个数据");
            System.out.println("top:  表示栈顶的值");
            System.out.println("exit: 退出程序");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        Object res = stack.pop();
                        System.out.println("出栈的数据是:" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "length":
                    int length = stack.getLength();
                    System.out.println("栈有" + length + "个数据");
                    break;
                case "top":
                    try {
                        Object top = stack.top();
                        System.out.println("栈顶为:" + top);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class LinkedListStack {
    ListStack head = new ListStack(null);

    public boolean isEmpty() {
        return head.getData() == null;
    }

    public void push(Object value) {
        if (head.getData() == null) {
            head.setData(value);
        } else {
            ListStack newStack = new ListStack(value);
            newStack.setNext(head);
            head = newStack;
        }
    }

    public Object pop() {
        Object data;
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        data = head.getData();
        head = head.getNext();
        return data;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
        }
        ListStack data = head;
        while (data.getNext() != null) {
            System.out.println(data.getData());
            data = data.getNext();
        }
    }

    public Object top() {
        Object data;
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        data = head.getData();
        return data;
    }

    public int getLength() {
        int count = 0;
        ListStack temp = head;
        if (isEmpty() || temp.getData() == null) {
            return 0;
        } else {
            while (temp != null) {
                count++;
                temp = temp.getNext();
            }
        }
        return count;
    }

}

class ListStack {
    private Object data;
    private ListStack next;

    public ListStack(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ListStack getNext() {
        return next;
    }

    public void setNext(ListStack next) {
        this.next = next;
    }
}