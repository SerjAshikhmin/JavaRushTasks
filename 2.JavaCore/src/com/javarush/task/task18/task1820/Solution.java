package com.javarush.task.task18.task1820;
import java.io.*;
import java.util.ArrayList;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String fileName1 = reader.readLine();

        reader.close();

        BufferedInputStream bufferedInputStreaminputStream = new BufferedInputStream
                (new FileInputStream(fileName));

        String data = "";

        while (bufferedInputStreaminputStream.available() > 0) {
            char c = (char) bufferedInputStreaminputStream.read();
            data += c;
        }
        bufferedInputStreaminputStream.close();

        String[] dataArray = data.split(" ");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < dataArray.length; i++) {
            list.add((int) Math.round(Double.parseDouble(dataArray[i])));
        }

        data = "";
        for (int i = 0; i < list.size(); i++) {
            data += list.get(i) + " ";
        }
        System.out.println(data);

        FileOutputStream fileOutputStream = new FileOutputStream(fileName1);
        fileOutputStream.write(data.getBytes());
        fileOutputStream.close();
    }
}
