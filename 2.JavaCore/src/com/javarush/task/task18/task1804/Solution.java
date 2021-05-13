package com.javarush.task.task18.task1804;

import java.io.FileInputStream;
import java.io.*;
import java.util.*;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);

        ArrayList<Integer> list = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            list.add(data);
            map.put(data, 0);
        }

        inputStream.close();
        reader.close();

        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                int count = map.get(list.get(i));
                count++;
                map.put(list.get(i), count);
            }
        }

        int minCount = map.get(list.get(0));

        for (Integer value: map.values()) {
            if (value < minCount) minCount = value;
        }

        for (Map.Entry<Integer, Integer> pair : map.entrySet())
            if (pair.getValue() == minCount)
                System.out.print(pair.getKey() + " ");
    }
}
