package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        ArrayList<String> fileParts = new ArrayList<>();
        String fileName;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            fileName = reader.readLine();
            if (fileName.equals("end")) break;
            fileParts.add(fileName);
        }

        reader.close();

        Collections.sort(fileParts);

        int i = fileParts.get(fileParts.size() - 1).indexOf(".part");
        fileName = fileParts.get(fileParts.size() - 1).substring(0, i);

        System.out.println(fileName);
        System.out.println(fileParts);

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        for (int j = 0; j < fileParts.size(); j++) {
            reader = new BufferedReader(new FileReader(fileParts.get(j)));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
            reader.close();
        }
        writer.close();

    }
}
