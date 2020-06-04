package com.sum;

public class Calculator {
    public static void main(String[] args) {
        //表达式
        String expression = "70+20*6-4";
        //数栈
        ArrayStack numStack = new ArrayStack(10);
        //符号栈
        ArrayStack operStack = new ArrayStack(10);

        int index = 0;
        int num1,num2,oper,res;
        //将每次扫描expression得到得char保存到ch中
        char ch = ' ';
        //用于拼接多位数
        String keepNum = "";

        do {
            //依次得到expression得每个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，做相应的处理
            if (operStack.isOper(ch)) {//判断是否为运算字符
                if (!operStack.isEmpty()) {//判断栈不为空
                    //如果符号栈有操作符，就进行比较，如果当前的操作符优先级小于或等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个运算符，与数栈中pop出的两个数进行运算出结果，再存入数栈中，然后再将当前操作符入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算结果push到数栈
                        numStack.push(res);
                        //把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈
                        operStack.push(ch);
                    }
                } else {//栈为空,直接进符号栈
                    operStack.push(ch);
                }
            } else {//如果是数字
                keepNum += ch;
                //如果ch已经是expression的最后一位
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符就直接入符号栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
        } while (index < expression.length());
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个结果
            if (operStack.isEmpty()) {
                break;
            } else {
                num1 = numStack.pop();
                num2 = numStack.pop();
                oper = operStack.pop();
                res = numStack.cal(num1, num2, oper);
                numStack.push(res);
            }
        }
        System.out.printf("表达式%s=%d", expression, numStack.pop());
    }
}

class ArrayStack {
    //栈的大小
    private int maxStack;
    //数组模拟栈
    private int[] stack;
    //表示栈顶，初始化为-1
    private int top = -1;

    public ArrayStack(int maxStack) {
        this.maxStack = maxStack;
        stack = new int[this.maxStack];
    }

    //栈满
    public boolean isFull() {
        return top == maxStack - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        //从栈顶开始遍历数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //查找当前栈顶的值
    public int peek() {
        return stack[top];
    }

    //返回算数得优先级，数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}