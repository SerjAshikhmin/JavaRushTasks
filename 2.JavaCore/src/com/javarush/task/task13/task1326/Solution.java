package com.javarush.task.task13.task1326;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws Exception{
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileInputStream inStream = new FileInputStream(fileName);
        InputStreamReader buffer = new InputStreamReader(inStream);
        BufferedReader bReader = new BufferedReader(buffer);

        ArrayList<Integer> list = new ArrayList<Integer>();

        String line;

        while ((line = bReader.readLine()) != null) {
            int n = Integer.parseInt(line);
            if (n % 2 == 0) list.add(n);
        }

        Collections.sort(list);

        for (int n : list) {
            System.out.println(n);
        }

        inStream.close();
        reader.close();
    }
}
