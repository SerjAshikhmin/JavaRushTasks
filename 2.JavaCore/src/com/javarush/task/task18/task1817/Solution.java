package com.javarush.task.task18.task1817;
import java.io.*;
/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);

        String s = "";
        int countGaps = 0;

        while (inputStream.available() > 0) {
            int i = inputStream.read();
            char c = (char) i;
            s += c;
            if (c == ' ') countGaps++;
        }
        inputStream.close();

        int count = s.length();

        System.out.printf("%.2f", (double) 100 * countGaps / count );

    }
}
