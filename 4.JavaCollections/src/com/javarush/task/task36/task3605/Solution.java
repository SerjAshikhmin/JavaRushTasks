package com.javarush.task.task36.task3605;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Set<String> treeSet = new TreeSet<>();
        FileReader fileReader = new FileReader(args[0]);
        while (fileReader.ready()) {
            char symbol = (char)(fileReader.read());
            if (Character.isLetter(symbol)) {
                treeSet.add(String.valueOf(symbol).toLowerCase());
            }
        }

        int treeLength = 5;
        if (treeSet.size() < 5) {
            treeLength = treeSet.size();
        }
        Object[] array = treeSet.toArray();
        for (int i = 0; i < treeLength; i++) {
            System.out.print(array[i]);
        }
    }
}
