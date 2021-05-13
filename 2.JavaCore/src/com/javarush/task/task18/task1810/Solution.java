package com.javarush.task.task18.task1810;
import java.io.*;
/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            FileInputStream inputStream = new FileInputStream(fileName);
            if (inputStream.available() < 1000) {
                inputStream.close();
                throw new DownloadException();
            }
        }
    }

    public static class DownloadException extends Exception {

    }
}
