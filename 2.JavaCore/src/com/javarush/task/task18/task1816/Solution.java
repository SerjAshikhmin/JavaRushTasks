package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);

        int englishLetters = 0;
        while (inputStream.available() > 0) {
            int i = inputStream.read();
            char c = (char) i;
            if ((c >= 'a' && c <='z') || (c >= 'A' && c <='Z')) englishLetters++;
        }

        System.out.println(englishLetters);
        inputStream.close();
    }
}
