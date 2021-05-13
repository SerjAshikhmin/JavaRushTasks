package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();
    static{
        labels.put(2.5, "zsdgf");
        labels.put(2.6, "zsddgf");
        labels.put(2.7, "zsfsf");
        labels.put(2.0, "zsdfx");
        labels.put(2.1, "zsgf");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
