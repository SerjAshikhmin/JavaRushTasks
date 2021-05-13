package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();
        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        reader.close();

        String[] words = line.split(" ");
        //...
        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        List<List<String>> sequences = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        if (words.length == 0) return result;
        HashSet out;
        List<String> list;


        for (int k = 0; k < words.length; k++) {
            list = new LinkedList<>();
            out = new HashSet<>();
            list.addAll(Arrays.asList(words));
            Collections.shuffle(list);
            boolean firstWordFinded = false;

            sequences.add(k, new ArrayList());
            sequences.get(k).add(list.get(k));
            out.add(list.get(k));
            list.remove(k);

            boolean isNoWords = false;
            while (!isNoWords) {
                for (int i = 0; i < list.size(); i++) {
                    String lastWord = sequences.get(k).get(sequences.get(k).size() - 1);
                    if (!out.contains(list.get(i)) && (Character.toLowerCase(lastWord.charAt(lastWord.length() - 1)) == Character.toLowerCase(list.get(i).charAt(0)))) {
                        sequences.get(k).add(list.get(i));
                        out.add(list.get(i));
                        list.remove(i);
                        break;
                    }
                    if (i == (list.size()) - 1) isNoWords = true;
                }
                if (out.size() == words.length) isNoWords = true;
            }

            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (!out.contains(list.get(i))) sequences.get(k).add(list.get(i));
                }
            }

        }

        int maxLetnth = 0;
        int maxPos = 0;

        for (int i = 0; i < sequences.size(); i++) {
            if (sequences.get(i).size() > maxLetnth) {
                maxLetnth = sequences.get(i).size();
                maxPos = i;
            }
        }

        for (int i = 0; i < sequences.get(maxPos).size(); i++) {
            result.append(sequences.get(maxPos).get(i));
            if (i != sequences.get(maxPos).size() - 1) result.append(" ");
            //System.out.println(sequences.get(i));
        }

        return result;
    }
}
