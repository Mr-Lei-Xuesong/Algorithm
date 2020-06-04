package com.demo;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "雷雪松");
        HeroNode node7 = new HeroNode(7, "曾予桸");

        //设置根节点
        tree.setRoot(root);

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        node3.setRight(node7);

/*
        System.out.println("前序遍历");
        tree.preOrder();

        System.out.println("中序遍历");
        tree.infixOrder();

        System.out.println("后序遍历");
        tree.postOrder();

        System.out.println("前序遍历查找");
        HeroNode resNode1 = tree.preOrderSearch(5);
        if (resNode1 != null) {
            System.out.printf("找到信息为：no=%d name=%s", resNode1.getNo(), resNode1.getName());
        } else {
            System.out.println("没有找到");
        }

        System.out.println();
        System.out.println("中序遍历查找");
        HeroNode resNode2 = tree.infixOrderSearch(5);
        if (resNode2 != null) {
            System.out.printf("找到信息为：no=%d name=%s", resNode2.getNo(), resNode2.getName());
        } else {
            System.out.println("没有找到");
        }
        System.out.println();
        System.out.println("后序遍历查找");
        HeroNode resNode3 = tree.postOrderSearch(5);
        if (resNode3 != null) {
            System.out.printf("找到信息为：no=%d name=%s", resNode3.getNo(), resNode3.getName());
        } else {
            System.out.println("没有找到");
        }
*/

/*        //删除节点
        System.out.println("删除前的前序遍历");
        tree.preOrder();

        tree.delNode(5);
        System.out.println("删除后的前序遍历");
        tree.preOrder();
 */
        System.out.println("删除前的前序遍历");
        tree.preOrder();

        tree.deleteNode(3);

        System.out.println("删除后的前序遍历");
        tree.preOrder();
    }
}

//创建节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    //如果是叶子节点，就直接删除
    //如果非叶子节点，就删除该节点，并把左子树移动删除节点位置
    public void deleteNode(int no) {
        if (this.left != null && this.left.no == no) {
            HeroNode temp=null;
            if (this.left.left != null) {
                if (this.left.right != null) {
                    temp=this.left.right;
                }
                this.left = this.left.left;
                this.left.left=temp;
            } else {
                this.left = null;
                return;
            }
        }
        if (this.right != null && this.right.no == no) {
            HeroNode temp=null;
            if (this.right.left != null) {
                if (this.right.right != null) {
                    temp=this.right.right;
                }
                this.right = this.right.left;
                this.right.left = temp;
            } else {
                this.right = null;
                return;
            }
        }
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

    //递归删除节点
    //如果是叶子节点，就直接删除
    //如果非叶子节点，就直接删除该子树
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder() {
        //先输出父节点
        System.out.println(this);
        //遍历左节点
        if (this.left != null) {
            this.left.preOrder();
        }
        //遍历右节点
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历
    public void infixOrder() {
        //先遍历左节点
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //遍历右节点
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历
    public void postOrder() {
        //先遍历左节点
        if (this.left != null) {
            this.left.postOrder();
        }
        //遍历右节点
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return null;
    }
}

//定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void deleteNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.deleteNode(no);
            }
        } else {
            System.out.println("空树，不能删除");
        }
    }

    //递归删除节点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除");

        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}