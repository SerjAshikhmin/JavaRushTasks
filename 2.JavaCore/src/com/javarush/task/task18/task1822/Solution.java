package com.javarush.task.task18.task1822;

import java.io.*;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName = reader.readLine();
        String string;
        String id = args[0] + " ";

        reader = new BufferedReader(new FileReader(fileName));

        while ((string = reader.readLine()) != null) {
            int i = string.indexOf(id);
            if (string.startsWith(id)) {
                System.out.println(string);
            }
        }

        reader.close();
    }
}
