package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String fileName1 = reader.readLine();
        reader.close();

        FileInputStream inputStream = new FileInputStream(fileName);
        FileOutputStream outputStream1 = new FileOutputStream(fileName1);

        ArrayList<Integer> list = new ArrayList<Integer>();

        while (inputStream.available() > 0) {
            int data = inputStream.read();
            list.add(data);
        }

        for (int i = list.size() - 1; i >= 0; i--)
            outputStream1.write(list.get(i));

        inputStream.close();
        outputStream1.close();
    }
}
