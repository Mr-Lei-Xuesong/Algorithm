package com.贪心算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadCasts.put("K1", hashSet1);
        broadCasts.put("K2", hashSet2);
        broadCasts.put("K3", hashSet3);
        broadCasts.put("K4", hashSet4);
        broadCasts.put("K5", hashSet5);

        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //临时集合，在遍历过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义一个maxKey,保存一次遍历过程中能够覆盖最多未覆盖地区的对应电台的key
        //如果maxKry不为空，则会加入到selects中
        String maxKey;
        while (allAreas.size() != 0) {
            maxKey = null;
            for (String key : broadCasts.keySet()) {
                tempSet.clear();
                HashSet<String> areas = broadCasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet与allAreas的交集
                tempSet.retainAll(allAreas);
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadCasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台的覆盖地区去掉
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果："+selects);
    }
}
