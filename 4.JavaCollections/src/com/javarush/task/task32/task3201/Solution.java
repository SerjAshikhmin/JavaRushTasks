package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0], "w");
        int number = Integer.parseInt(args[1]);
        if (number < raf.length()) {
            raf.seek(number);
        } else {
            raf.seek(raf.length());
        }
        raf.write(args[2].getBytes());
        raf.close();
    }
}
