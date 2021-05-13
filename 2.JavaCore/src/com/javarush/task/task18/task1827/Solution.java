package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        if (args.length != 0) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            int id = 0;
            String sId;
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                sId = line.substring(0, 8);
                int space = sId.indexOf(" ");

                if (space != -1) {
                    if (Integer.parseInt(sId.substring(0, space)) > id) id = Integer.parseInt(sId.substring(0, space));
                } else {
                    if (Integer.parseInt(sId) > id) id = Integer.parseInt(sId);
                }
            }
            reader.close();
            id++;

            String pName = args[1];
            if (pName.length() >= 30) pName = pName.substring(0, 30);
            else {
                for (int i = args[1].length(); i < 30 ; i++) {
                    pName += " ";
                }
            }
            System.out.println(pName);

            String pPrice = args[2];
            if (pPrice.length() >= 8) pPrice = pPrice.substring(0, 8);
            else {
                for (int i = args[2].length(); i < 8 ; i++) {
                    pPrice += " ";
                }
            }

            String pQuantity = args[3];
            if (pQuantity.length() >= 4) pQuantity = pQuantity.substring(0, 4);
            else {
                for (int i = args[3].length(); i < 4 ; i++) {
                    pQuantity += " ";
                }
            }

            String lineToWrite = String.valueOf(id) + pName + pPrice + pQuantity;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
            writer.write("\r\n");
            writer.write(lineToWrite);
            writer.close();
        }
    }
}
