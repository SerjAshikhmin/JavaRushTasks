package com.javarush.task.task19.task1925;
import java.io.*;
/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        String data;
        String longWords = "";
        while ((data = reader.readLine()) != null) {
            String[] aData = data.split("\\s");
            for (int i = 0; i < aData.length; i++) {
                System.out.println(aData[i]);
                if (aData[i].length() > 6) longWords += aData[i] + ",";
            }
        }
        reader.close();
        longWords =longWords.substring(0, longWords.length()-1);
        writer.write(longWords);
        writer.close();
    }
}
