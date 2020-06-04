package com.demo;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        /*doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        System.out.println("原双向表数据");
        doubleLinkedList.list();
        HeroNode2 heroNode2=new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(heroNode2);
        System.out.println("修改后的双向表数据");
        doubleLinkedList.list();
        doubleLinkedList.del(3);
        System.out.println("删除双向表3号后的数据");
        doubleLinkedList.list();*/

        System.out.println("按编号进行插入排序");
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();
    }
}

class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        //找到需要修改的编号
        HeroNode2 temp = head.next;
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

    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
        }
        HeroNode2 temp = head.next;
        //标识是否找到删除节点
        boolean flag = false;
        while (true) {
            if (temp == null) {//已经到链表的最后
                break;
            }
            if (temp.no == no) {//找到待删除的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d  节点不存在\n", no);
        }
    }

    public void addByOrder(HeroNode2 heroNode2) {
        HeroNode2 temp = head;
        boolean flag = false;
        while (true) {
            //说明 temp 已经在链表的最后
            if (temp.next == null) {
                break;
            }
            //位置找到，就在 temp 的后面插入
            if (temp.next.no > heroNode2.no) {
                break;
            } else if (temp.next.no == heroNode2.no) {//说明希望添加的 heroNode 的编号已然存在
                flag = true;//说明编号存在
                break;
            }
            //后移，遍历当前链表
            temp = temp.next;
        }
        //判断 flag  的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d  已经存在了,  不能加入\n", heroNode2.no);
        } else {//heroNode2插入到temp与temp.next之间
            //heroNode2的前缀pre连接到temp
            heroNode2.pre=temp;
            //把temp.next赋值到heroNode2.next
            heroNode2.next = temp.next;
            temp.next = heroNode2;
            temp.next.pre=heroNode2;
        }
    }

}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                '}';
    }
}