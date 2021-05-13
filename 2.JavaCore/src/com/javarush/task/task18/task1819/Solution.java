package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String fileName1 = reader.readLine();

        reader.close();

        BufferedInputStream bufferedInputStreaminputStream = new BufferedInputStream
                (new FileInputStream(fileName));

        BufferedInputStream bufferedInputStreaminputStream1 = new BufferedInputStream
                (new FileInputStream(fileName1));

        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list1 = new ArrayList<Integer>();

        while (bufferedInputStreaminputStream.available() > 0) {
            list.add(bufferedInputStreaminputStream.read());
        }
        bufferedInputStreaminputStream.close();

        while (bufferedInputStreaminputStream1.available() > 0) {
            list1.add(bufferedInputStreaminputStream1.read());
        }
        bufferedInputStreaminputStream1.close();

        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        for (int i = 0; i < list1.size(); i++) {
            fileOutputStream.write(list1.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            fileOutputStream.write(list.get(i));
        }

        fileOutputStream.close();
    }
}
