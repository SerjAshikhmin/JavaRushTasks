package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length != 0) {
            LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            int id = Integer.parseInt(args[1]);
            String sId;
            String lineToChange;
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                sId = line.substring(0, 8);
                int space = sId.indexOf(" ");

                if (space != -1) {
                    if (Integer.parseInt(sId.substring(0, space)) == id) lineToChange = line;
                    map.put(Integer.parseInt(sId.substring(0, space)), line);
                } else {
                    if (Integer.parseInt(sId) == id) lineToChange = line;
                    map.put(Integer.parseInt(sId), line);
                }
            }
            reader.close();

            if (args[0].equals("-d")) {
                map.remove(id);
            }

            if (args[0].equals("-u")) {
                String pId = args[1];
                if (pId.length() >= 8) pId = pId.substring(0, 8);
                else {
                    for (int i = args[1].length(); i < 8; i++) {
                        pId += " ";
                    }
                }

                String pName = args[2];
                if (pName.length() >= 30) pName = pName.substring(0, 30);
                else {
                    for (int i = args[2].length(); i < 30; i++) {
                        pName += " ";
                    }
                }

                String pPrice = args[3];
                if (pPrice.length() >= 8) pPrice = pPrice.substring(0, 8);
                else {
                    for (int i = args[3].length(); i < 8; i++) {
                        pPrice += " ";
                    }
                }

                String pQuantity = args[4];
                if (pQuantity.length() >= 4) pQuantity = pQuantity.substring(0, 4);
                else {
                    for (int i = args[4].length(); i < 4; i++) {
                        pQuantity += " ";
                    }
                }

                String lineToWrite = pId + pName + pPrice + pQuantity;
                map.put(id, lineToWrite);
            }

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            for (Map.Entry<Integer, String> pair : map.entrySet()) {
                System.out.println(pair.getValue());
                writer.write(pair.getValue());
                writer.write("\r\n");
            }
            writer.close();
        }
    }
}

