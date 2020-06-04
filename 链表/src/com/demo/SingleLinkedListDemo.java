package com.demo;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        System.out.println("原链表");
        singleLinkedList.list();
        System.out.println("逆序打印");
        reversePrint(singleLinkedList.getHead());
/*
        System.out.println("原链表");
        singleLinkedList.list();
        System.out.println("反转后");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        //循序加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        //排序加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示
        singleLinkedList.list();

        //修改
        HeroNode newHeroNode = new HeroNode(4, "好汉", "豹子头啊");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的");
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(3);
        System.out.println("删除后的");
        singleLinkedList.list();

        //测试单链表的有效节点
        System.out.println("有效节点个数:" + getLength(singleLinkedList.getHead()));

        //测试得到单链表的倒数第Index节点
        HeroNode lastIndex = findLastIndex(singleLinkedList.getHead(), 1);
        System.out.println("result" + lastIndex);
 */
    }

    //逆序打印单链表
    public static void reversePrint(HeroNode heroNode) {
        if (heroNode.next == null) {
            return;
        }
        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = heroNode.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    //将单链表进行反转
    public static void reverseList(HeroNode heroNode) {
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        //去掉节点头
        HeroNode temp = heroNode.next;
        //定义辅助变量
        HeroNode next = null;
        //定义暂存head
        HeroNode reverseHead = new HeroNode(0, "", "");
        //判断是否为空
        while (temp != null) {
            //把当前节点的下一个next节点暂存到next里面
            next = temp.next;
            //将reverseHead.next给temp.next，形成了先进来的要排在后进来的后面
            temp.next = reverseHead.next;
            //在把整体赋值给reverseHead.next
            reverseHead.next = temp;
            //把暂存的值赋值给temp
            temp = next;
        }
        //替换
        heroNode.next = reverseHead.next;
    }

    //查找单链表倒数第index节点
    public static HeroNode findLastIndex(HeroNode heroNode, int index) {
        if (heroNode.next == null) {
            return null;
        }
        int size = getLength(heroNode);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode temp = heroNode.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //有效节点个数
    public static int getLength(HeroNode heroNode) {
        if (heroNode.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode temp = heroNode.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
}

class SingleLinkedList {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //增加节点
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    //排序
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，找的 temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        // flag 标志添加的编号是否存在，默认为 false
        boolean flag = false;
        while (true) {
            //说明 temp 已经在链表的最后
            if (temp.next == null) {
                break;
            }
            //位置找到，就在 temp 的后面插入
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的 heroNode 的编号已然存在
                flag = true;//说明编号存在
                break;
            }
            //后移，遍历当前链表
            temp = temp.next;
        }
        //判断 flag  的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d  已经存在了,  不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp 的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息，根据编号来修改，并且编号不能修改
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        //找到需要修改的编号
        HeroNode temp = head.next;
        //表示是否找到节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //遍历完链表
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到 编号 %d  的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点
    public void del(int no) {
        HeroNode temp = head;
        //标识是否找到删除节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//已经到链表的最后
                break;
            }
            if (temp.next.no == no) {//找到待删除的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d  节点不存在\n", no);
        }
    }

    //列出节点
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                '}';
    }
}