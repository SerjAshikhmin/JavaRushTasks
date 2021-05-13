package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("d:/file.txt"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        String data = "";
        byte[] bytes;
        if (is == null) {
            return writer;
        }
        while (is.available() > 0) {
            /*if (is.available() > 1000) {
                bytes = new byte[1000];
            } else {
                bytes = new byte[is.available()];
            }*/
            bytes = new byte[is.available()];
            is.read(bytes);
            data += new String(bytes);
        }
        is.close();
        writer.write(data);
        writer.close();
        return writer;
    }
}