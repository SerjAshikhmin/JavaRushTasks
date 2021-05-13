package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<Character, Integer> map = new TreeMap<>();
        String fileToString = "";
        FileInputStream inputStream = new FileInputStream(args[0]);

        while (inputStream.available() > 0) {
            int i = inputStream.read();
            char c = (char) i;
            map.put(c, 0);
            fileToString += c;
        }

        char[] charArray = fileToString.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            int j = map.get(charArray[i]);
            map.put(charArray[i], ++j);
        }

        for (Map.Entry<Character, Integer> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }

        inputStream.close();

    }
}
