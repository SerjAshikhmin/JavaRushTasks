package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        FileInputStream inputStream = new FileInputStream(fileName);
        FileOutputStream outputStream1 = new FileOutputStream(fileName1);
        FileOutputStream outputStream2 = new FileOutputStream(fileName2);

        int halfFile = 0;
        if (inputStream.available() % 2 == 0) halfFile = inputStream.available() / 2;
        else halfFile = inputStream.available() / 2 + 1;

        for (int i = 1; i <= halfFile; i++) {
            int data = inputStream.read();
            outputStream1.write(data);
        }

        outputStream1.close();

        while (inputStream.available() > 0) {
            int data = inputStream.read();
            outputStream2.write(data);
        }

        inputStream.close();
        outputStream2.close();
    }
}
