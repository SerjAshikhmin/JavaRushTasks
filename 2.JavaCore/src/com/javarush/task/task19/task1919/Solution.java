package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<String, Double> map = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String data;
        while ((data = reader.readLine()) != null) {
            String[] dataS = data.split(" ");
            if (map.get(dataS[0]) == null) map.put(dataS[0], Double.parseDouble(dataS[1]));
            else {
                map.put(dataS[0], map.get(dataS[0]) + Double.parseDouble(dataS[1]));
            }
        }
        reader.close();

        for (Map.Entry<String, Double> pair: map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}
