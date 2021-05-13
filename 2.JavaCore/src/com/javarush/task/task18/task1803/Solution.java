package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые частые байты
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

        for (int i = 0; i < list.size(); i++) {
            if (map.containsKey(list.get(i))) {
                int count = map.get(list.get(i));
                count++;
                map.put(list.get(i), count);
            }
        }

        int maxCount = 0;

        for (Integer value: map.values()) {
            if (value > maxCount) maxCount = value;
        }

        for (Map.Entry<Integer, Integer> pair : map.entrySet())
            if (pair.getValue() == maxCount)
                System.out.print(pair.getKey() + " ");

        reader.close();
        inputStream.close();

    }
}
