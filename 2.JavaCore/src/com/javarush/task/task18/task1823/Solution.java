package com.javarush.task.task18.task1823;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.TreeMap;

/* 
Нити и байты
*/

public class Solution {
    public volatile static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName;
        while (true) {
            fileName = reader.readLine();
            if (fileName.equals("exit")) break;
            ReadThread file = new ReadThread(fileName);
            file.start();
        }

        reader.close();

    }

    public static class ReadThread extends Thread {
        private String filename;

        public ReadThread(String fileName) {
            //implement constructor body
            this.filename = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void run() {
            TreeMap<Character, Integer> map = new TreeMap<>();
            String fileToString = "";
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(filename);

                while (inputStream.available() > 0) {
                    int i = inputStream.read();
                    char c = (char) i;
                    map.put(c, 0);
                    fileToString += c;
                }

                inputStream.close();

                char[] charArray = fileToString.toCharArray();

                for (int i = 0; i < charArray.length; i++) {
                    int j = map.get(charArray[i]);
                    map.put(charArray[i], ++j);
                }

                int max = map.get(map.firstKey());
                char maxC = map.firstKey();

                for (Map.Entry<Character, Integer> pair : map.entrySet()) {
                    if (pair.getValue() > max) {
                        max = pair.getValue();
                        maxC = pair.getKey();
                    }
                }

                resultMap.put(filename, (int) maxC);

            } catch (Exception e) {e.printStackTrace();}

        }
    }
}
