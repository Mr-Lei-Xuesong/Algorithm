package com.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //定义中缀表达式
        String expression = "1+((2+3)*4)-5";
        System.out.println("中缀表达式：" + expression);
        //将中缀表达式转为list
        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式转为List：" + list);
        //List转后缀表达式
        List<String> stringList = parseSuffixExpressionList(list);
        System.out.println("List转后缀表达式：" + stringList);
        //计算后缀表达式
        int calculate = calculate(stringList);
        //输出结果
        System.out.println("计算结果：" + calculate);

    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义List存放中缀表达式的内容
        List<String> ls = new ArrayList<>();
        //定义一个指针,用户遍历中缀表达式字符串
        int i = 0;
        //数字对多位数拼接
        String str;
        //每遍历到一个字符，就存入c中
        char c;
        do {
            //如果c不是数字，就加入到List中
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {//ASCII码对照表
                ls.add("" + c);
                i++;
            } else {//如果是数字，就要考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    //将List中缀表达式转后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //符号栈
        Stack<String> s1 = new Stack<>();
        //暂存
        List<String> s2 = new ArrayList<>();

        for (String item : ls) {
            if (item.matches("\\d+")) {//如果是数字，就直接加入到s2中
                s2.add(item);
            } else if (item.equals("(")) {//如果是"("号，直接加入s1栈中
                s1.push(item);
            } else if (item.equals(")")) {//如果是")"
                while (!s1.peek().equals("(")) {//直到遇到"("为止
                    s2.add(s1.pop());//则依次弹出 s1 栈顶的运算符，并加入到s2
                }
                s1.pop();//此时将这一对括号丢弃
            } else {//遇到运算符时，比较其与 s1 栈顶运算符的优先级
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //计算后缀表达式
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {//如果item是整数就直接存栈
                stack.push(item);
            } else {//如果不是整数就是运算符
                //从栈里面pop出两位数来进行运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                //运算的结果
                int res;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误！！");
                }
                //再把运算结果存到栈中
                stack.push("" + res);
            }
        }
        //最后弹出运算结果的值
        return Integer.parseInt(stack.pop());
    }
}

//返回运算优先级
class Operation {
    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 1;
    private final static int DIV = 1;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}