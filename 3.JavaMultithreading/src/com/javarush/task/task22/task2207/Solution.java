package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();
    public static HashSet<String> set= new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        reader = new BufferedReader(new FileReader(filename));
        String line;
        String file = "";
        while ((line = reader.readLine()) != null) {
            file += line + " ";
        }

        String[] words = file.split(" ");

        for (int i = 0; i < words.length; i++) {
            StringBuilder word = new StringBuilder(words[i]);
            for (int j = 0; j < words.length; j++) {
                StringBuilder word2 = new StringBuilder(words[j]);
                if ((i != j) && (word.toString().equals(word2.reverse().toString()))) {
                    Pair pair1 = new Pair(word.toString(), word2.reverse().toString());
                    Pair pair2 = new Pair(word2.toString(), word.toString());
                    if (!result.contains(pair1) && !result.contains(pair2)) {
                        result.add(pair1);
                    }
                }
            }
        }

        for (Pair pair: result) {
            System.out.println(pair.first + " " + pair.second);
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        public Pair() {
        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
