package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        String data;
        while ((data = reader.readLine()) != null) {
            String[] aData = data.split(" ");
            for (int i = 0; i < aData.length; i++) {
                if (aData[i].matches("\\b\\S*\\d+\\S*\\b")) {
                    writer.write(aData[i] + " ");
                }
            }
        }
        reader.close();
        writer.close();
    }
}
