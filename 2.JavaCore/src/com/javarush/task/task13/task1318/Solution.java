package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) throws Exception{
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        InputStream inStream = new FileInputStream(fileName);
        BufferedInputStream buffer = new BufferedInputStream(inStream);

        while (buffer.available() > 0) {
            char c = (char) buffer.read();
            System.out.print(c);
        }

        inStream.close();
        reader.close();
    }
}