package com.threaded;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "mi");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes(root);

        HeroNode left = node5.getLeft();
        System.out.println(left);
        HeroNode right = node5.getRight();
        System.out.println(right);

        System.out.println("--------");

        tree.threadedList();
    }
}

class HeroNode {
    private int no;
    private String name;
    //左子树
    private HeroNode left;
    //右子树
    private HeroNode right;
    //如果leftType==0表示左子树，等于 1 表示前驱
    private int leftType;
    //如果rightType==0表示右子树，等于 1 表示后继
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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
            HeroNode temp = null;
            if (this.left.left != null) {
                if (this.left.right != null) {
                    temp = this.left.right;
                }
                this.left = this.left.left;
                this.left.left = temp;
            } else {
                this.left = null;
                return;
            }
        }
        if (this.right != null && this.right.no == no) {
            HeroNode temp = null;
            if (this.right.left != null) {
                if (this.right.right != null) {
                    temp = this.right.right;
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

class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归执行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //遍历线索化二叉树
    public void threadedList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    //对二叉树进行中序线索化
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前节点

        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //处理每个节点后，让当前节点是下一个节点的前驱节点
        pre = node;
        //再线索化右子树
        threadedNodes(node.getRight());
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