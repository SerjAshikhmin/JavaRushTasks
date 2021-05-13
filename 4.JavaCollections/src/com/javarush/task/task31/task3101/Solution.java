package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println(args[0]);
        System.out.println(args[1]);
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        System.out.println(allFilesContent.getAbsolutePath());

        if (FileUtils.isExist(resultFileAbsolutePath)) {
            FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);
        }

        FileOutputStream fos = new FileOutputStream(allFilesContent);
        fos.flush();
        fos.close();
        List<File> fileList = new ArrayList<>();
        getContent(path, fileList);

        fileList.sort(Comparator.comparing(File::getName));
        fos = new FileOutputStream(allFilesContent);
        for (File file : fileList) {
            FileInputStream fis = new FileInputStream(file);
            while(fis.available() > 0) {
                fos.write(fis.read());
            }
            fos.write(System.lineSeparator().getBytes());
        }

        fos.flush();
        fos.close();
    }

    public static void getContent(File path, List fileList) {

        for (File file : path.listFiles()) {
            if (file.isDirectory()) {
                getContent(file, fileList);
            } else {
                if (file.length() <= 50) {
                    fileList.add(file);
                }
            }
        }
    }
}
