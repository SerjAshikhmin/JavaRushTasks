package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> fileTree = new ArrayList<>();
        File fileDirectory = new File(root);
        Queue<File> fileQueue = new PriorityQueue<>();
        Collections.addAll(fileQueue, fileDirectory.listFiles());

        while(!fileQueue.isEmpty()) {
            File file = fileQueue.remove();
            if (file.isDirectory()) {
                Collections.addAll(fileQueue, file.listFiles());
            } else {
                fileTree.add(file.getAbsolutePath());
            }
        }

        return fileTree;
    }

    public static void main(String[] args) throws IOException {
        for (String fileName : getFileTree("E:/Фильмы")) {
            System.out.println(fileName);
        }
    }
}
