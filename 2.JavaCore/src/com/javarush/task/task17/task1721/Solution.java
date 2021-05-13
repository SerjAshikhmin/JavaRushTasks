package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1 = reader.readLine();
            String file2 = reader.readLine();
            reader.close();
             BufferedReader fileReader = new BufferedReader(new FileReader(file1));

            String text = "";
            while ((text = fileReader.readLine()) != null) {
                allLines.add(text);
                System.out.println(text);
            }
            fileReader.close();

            BufferedReader file2Reader = new BufferedReader(new FileReader(file2));
            while ((text = file2Reader.readLine()) != null) {
                forRemoveLines.add(text);
                System.out.println(text);
            }
            file2Reader.close();
        } catch (IOException e) {}


        Solution solution = new Solution();
        try {
            solution.joinData();
        } catch (CorruptedDataException e) {}
    }

    public void joinData() throws CorruptedDataException {
        int removeCount = 0;
        for (int i = 0; i < forRemoveLines.size(); i++)
            if (allLines.contains(forRemoveLines.get(i))) removeCount++;

        if (removeCount == forRemoveLines.size() && removeCount != 0) {
            for (int i = 0; i < allLines.size(); i++) {
                if (forRemoveLines.contains(allLines.get(i))) allLines.remove(i);
            }
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
