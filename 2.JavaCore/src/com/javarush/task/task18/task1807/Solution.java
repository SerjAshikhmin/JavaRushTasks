package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileInputStream inputStream = new FileInputStream(fileName);

        byte[] data = new byte[1000];
        int comma = 0;

        while (inputStream.available() > 0) {
            int count = inputStream.read(data);
            for (int i = 0; i < count; i++) {
                if (data[i] == 44) comma++;
            }
        }

        System.out.println(comma);

        inputStream.close();
        reader.close();
    }
}
