package com.javarush.task.task18.task1824;
import java.io.*;
import java.util.ArrayList;
/* 
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<FileInputStream> streams = new ArrayList<FileInputStream>();
        while (true) {
            String fileName = null;
            FileInputStream inputStream = null;
            try {
                fileName = reader.readLine();
                streams.add(new FileInputStream (fileName));
            } catch (FileNotFoundException e) {
                System.out.println(fileName);
                for (int i = 0; i < streams.size(); i++) {
                    streams.get(i).close();
                }
                break;
            }
             catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
