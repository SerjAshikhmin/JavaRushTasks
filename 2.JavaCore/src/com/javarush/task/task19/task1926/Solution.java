package com.javarush.task.task19.task1926;
import java.io.*;
/* 
Перевертыши
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        BufferedReader fReader = new BufferedReader(new FileReader(file));
        String data;
        while ((data = fReader.readLine()) != null) {
            data = new StringBuffer(data).reverse().toString();
            System.out.println(data);
        }
        fReader.close();
    }
}
