package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream reader = new FileInputStream(new File(args[0]));
        OutputStream writer = new FileOutputStream(new File(args[1]));
        byte[] buffer;
        String line;
        Charset utf8 = Charset.forName("UTF-8");
        Charset windows1251 = Charset.forName("Windows-1251");
        while (reader.available() > 0) {
            buffer = new byte[reader.available()];
            reader.read(buffer);
            line = new String(buffer, windows1251);
            buffer = line.getBytes(utf8);
            writer.write(buffer);
        }
    }
}
