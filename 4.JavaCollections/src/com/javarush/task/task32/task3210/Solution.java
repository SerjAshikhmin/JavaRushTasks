package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        byte[] byteText = new byte[text.length()];
        raf.seek(number);
        raf.read(byteText, 0, text.length());
        String fileText = new String(byteText);
        raf.seek(raf.length());
        if (fileText.equals(text)) {
            raf.write("true".getBytes());
        } else {
            raf.write("false".getBytes());
        }

        raf.close();
    }
}
