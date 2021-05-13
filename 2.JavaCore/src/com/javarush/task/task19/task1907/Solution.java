package com.javarush.task.task19.task1907;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        FileReader fileReader = new FileReader(file);
        StringBuilder stringBuilder = new StringBuilder();

        while (fileReader.ready()) {
            int data = fileReader.read();
            stringBuilder.append((char) data);
        }
        fileReader.close();

        String string = stringBuilder.toString();

        Pattern pattern = Pattern.compile("\\bworld\\b");
        Matcher matcher = pattern.matcher(string);
        int count = 0;
        while (matcher.find())
            count++;

        System.out.println(count);
    }
}
