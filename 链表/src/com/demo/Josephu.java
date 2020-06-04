package com.demo;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.addBoy(5);
        linkedList.show();

        linkedList.countBoy(1, 2, 5);
    }
}

class CircleSingleLinkedList {
    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("输入的值不符合");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                //构成一个环形
                first = boy;
                first.setNext(first);
                //暂存
                curBoy = first;
            } else {
                //连接到下一个boy
                curBoy.setNext(boy);
                //下一个boy又连接到first，构成环形
                boy.setNext(first);
                //暂存
                curBoy = boy;
            }
        }
    }

    public void show() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * @param startNo  表示从第几个开始数
     * @param countNum 表示数几次
     * @param nums     表示最初有多少个
     */
    //根据用户的输入，计算出圈的的顺序
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入的参数有误");
            return;
        }
        //创建辅助指针
        Boy helper = first;
        //要让辅助指针事先指向环形链表的最后节点
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        //让first和helper定位到从那个开始报数的位置
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始报数时，让first和helper同时移动countNum-1次,找到该节点，出圈
        while (helper != first) {//相等的话，说明圈中只有一个节点
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d\n", first.getNo());
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}