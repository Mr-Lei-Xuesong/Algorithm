package com.huffmancode;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        /*List<Node> nodes = getNodes(bytes);
        System.out.println("统计后的字符串：" + nodes);

        Node root = createHuffmanTree(nodes);
        preOrder(root);

        Map<Byte, String> codes = getCodes(root);
        System.out.println(codes);

        byte[] zip = zip(bytes, codes);
        System.out.println(Arrays.toString(zip));*/

        //编码
        byte[] zip = huffmanZip(bytes);
        System.out.println(Arrays.toString(zip));

        //解码
        byte[] sourceByte = decode(huffmanCodes, zip);
        System.out.println(new String(sourceByte));

    }

    //压缩封装
    public static byte[] huffmanZip(byte[] bytes) {
        //将字符串转为byte,并存入List集合中
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建哈夫曼树
        Node root = createHuffmanTree(nodes);
        //根据哈夫曼树生成对应的哈夫曼编码
        Map<Byte, String> codes = getCodes(root);
        //根据哈夫曼编码对原始byte数组压缩
        byte[] zips = zip(bytes, codes);
        //返回
        return zips;
    }

    //将byte转成一个二进制的字符串
    public static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //将压缩数据进行解码
    /**
     * @param huffmanCodes 哈夫曼编码
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 返回原来的字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //将字符串转为byte,并存入List集合中
    public static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();
        //遍历字节数组，并将数据和权值存入Map中
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }
        //遍历Map集合，存入List集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //根据nodes创建哈夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //按从小到大排序
            Collections.sort(nodes);
            //获取List集合最小的前两位数
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //以最小的前两位数之和创建新的节点
            Node prent = new Node(null, leftNode.weight + rightNode.weight);
            prent.left = leftNode;
            prent.right = rightNode;
            //移除前两位最小的数
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //新的节点添加到List集合中
            nodes.add(prent);
        }
        return nodes.get(0);
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树");
        }
    }

    //生成哈夫曼树对应的哈夫曼编码
    static Map<Byte, String> huffmanCodes = new HashMap<>();//将哈夫曼编码表放在Map中,32->01 97->100 100->11000
    static StringBuilder stringBuilder = new StringBuilder();//在生成哈夫曼编码时，需要去拼接路径，存储某个叶子节点

    /**
     * 功能：将传入的Node节点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入的节点
     * @param code          路径：左子节点是0，右子节点是1
     * @param stringBuilder 拼接路径
     */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    //将字符串对应的byte[] 数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]

    /**
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成的哈夫曼编码表
     * @return 返回哈夫曼编码处理后的byte[]数组
     */
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        int len = (stringBuilder.length() + 7) / 8;
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }
}

class Node implements Comparable<Node> {
    Byte data;//表示数据本身
    int weight;//权值，表示数据出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
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
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}