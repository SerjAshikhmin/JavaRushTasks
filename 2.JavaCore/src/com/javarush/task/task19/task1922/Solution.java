package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        BufferedReader fReader = new BufferedReader(new FileReader(file));
        String data;
        while ((data = fReader.readLine()) != null) {
            int wCount = 0;
            String[] aData = data.split(" ");
            for (int i = 0; i < words.size(); i++) {
                for (int j = 0; j < aData.length; j++)
                    if (aData[j].equals(words.get(i))) wCount++;
            }
            if (wCount == 2) System.out.println(data);
        }
        fReader.close();
    }
}
