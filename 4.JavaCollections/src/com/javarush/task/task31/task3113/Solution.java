package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {

    private static int directoriesCount = 0;
    private static int filesCount = 0;
    private static int totalSize = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String stringPath = reader.readLine();
        Path directory = Paths.get(stringPath);

        if (!(Files.isDirectory(directory))) {
            System.out.println(String.format("%s - не папка", directory));
        } else {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    directoriesCount++;
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    filesCount++;
                    totalSize +=+ attrs.size();
                    return super.visitFile(file, attrs);
                }
            });

            System.out.println("Всего папок - " + --directoriesCount);
            System.out.println("Всего файлов - " + filesCount);
            System.out.println("Общий размер - " + totalSize);
        }

    }

}
