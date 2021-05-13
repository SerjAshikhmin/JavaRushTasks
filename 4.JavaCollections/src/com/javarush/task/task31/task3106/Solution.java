package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        FileOutputStream fos = new FileOutputStream(resultFileName);
        List<String> fileNamePartList = new ArrayList<>();
        List<InputStream> inputStreams = new ArrayList<>();

        for (int i = 1; i < args.length; i++) {
            fileNamePartList.add(args[i]);
        }

        Collections.sort(fileNamePartList);
        for (int i = 0; i < fileNamePartList.size(); i++) {
            inputStreams.add(new FileInputStream(fileNamePartList.get(i)));
        }

        SequenceInputStream sis = new SequenceInputStream(Collections.enumeration(inputStreams));
        ZipInputStream zis = new ZipInputStream(sis);
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            final int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            for(int readBytes = -1; (readBytes = zis.read(buffer, 0, bufferSize)) > -1; ) {
                fos.write(buffer, 0, readBytes);
            }
        }

        fos.close();
        zis.close();
    }
}
