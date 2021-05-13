package com.javarush.task.task18.task1805;

import java.io.FileInputStream;
import java.io.*;
import java.util.*;


/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream inputStream = new FileInputStream(fileName);

        TreeSet<Integer> set = new TreeSet<Integer>();
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            set.add(data);
        }

        for (int a: set) {
            System.out.print(a + " ");
        }

        inputStream.close();
        reader.close();
    }
}
