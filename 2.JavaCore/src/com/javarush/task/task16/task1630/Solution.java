package com.javarush.task.task16.task1630;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {}
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        //add your code here - добавьте код тут
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements ReadFileInterface {
        private String fileName;
        private ArrayList<String> content = new ArrayList<String>();

        public void setFileName (String name) {
            fileName = name;
        }

        public String getFileContent() {
            if (content.size() == 0) return ""; else {
                String contentToString = "";
                for (String string : content) {
                    contentToString = contentToString + string + " ";
                }
                return contentToString;
            }
            //return content.size() == 0 ? "" : content.toString();
        }

        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String text = "";

                while ((text = reader.readLine()) != null) {
                    content.add(text);
                }
            } catch (IOException e) {}
        }
    }
}
