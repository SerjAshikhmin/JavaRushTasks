package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if (reader == null) {
            return "";
        }
        char[] data = new char[reader.toString().length()];
        reader.read(data);
        String result = "";
        for (int i = 0; i < data.length; i++) {
            if (data[i] != '\u0000') {
                result += (char) (data[i] + key);
            }
        }

        return result;
    }
}
