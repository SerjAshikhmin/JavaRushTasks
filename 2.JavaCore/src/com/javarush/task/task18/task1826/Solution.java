package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        InputStream input = new FileInputStream(args[1]);
        OutputStream output = new FileOutputStream(args[2]);

        if (args[0].equals("-e")) {
            while (input.available() > 0) {
                int i = input.read();
                output.write(i + 1);
            }

            input.close();
            output.close();
        }

        if (args[0].equals("-d")) {
            while (input.available() > 0) {
                int i = input.read();
                output.write(i - 1);
            }

            input.close();
            output.close();
        }
    }

}
