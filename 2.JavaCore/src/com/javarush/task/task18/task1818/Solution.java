package com.javarush.task.task18.task1818;

import java.awt.image.BufferedImageFilter;
import java.io.*;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();

        reader.close();

        BufferedInputStream bufferedInputStreaminputStream = new BufferedInputStream
                (new FileInputStream(fileName1));

        BufferedInputStream bufferedInputStreaminputStream1 = new BufferedInputStream
                (new FileInputStream(fileName2));

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        while (bufferedInputStreaminputStream.available() > 0) {
            fileOutputStream.write(bufferedInputStreaminputStream.read());
        }

        bufferedInputStreaminputStream.close();
        fileOutputStream.close();

        fileOutputStream = new FileOutputStream(fileName, true);


        while (bufferedInputStreaminputStream1.available() > 0) {
            fileOutputStream.write(bufferedInputStreaminputStream1.read());
        }

        bufferedInputStreaminputStream1.close();
        fileOutputStream.close();
    }
}
