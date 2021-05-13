package com.javarush.task.task19.task1910;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        int symbolsCount = 0;
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));

        StringBuilder stringBuilder = new StringBuilder();

        while (fileReader.ready()) {
            int data = fileReader.read();
            stringBuilder.append((char) data);
        }
        fileReader.close();

        String string = stringBuilder.toString();
        Pattern pattern = Pattern.compile("(\\p{Punct}|\\n)");
        Matcher matcher = pattern.matcher(string);
        string = matcher.replaceAll("");

        fileWriter.write(string);
        fileWriter.close();

    }
}
