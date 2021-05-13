package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileWriter fileWriter= new FileWriter(new File(fileName));
        BufferedWriter writer = new BufferedWriter(fileWriter);

        while (true) {
            String text = reader.readLine();
            writer.write(text);
            writer.newLine();
            writer.flush();
            if (text.equals("exit")) break;
        }
        writer.close();
        fileWriter.close();
    }
}
