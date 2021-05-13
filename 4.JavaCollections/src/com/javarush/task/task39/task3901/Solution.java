package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s == "") {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int curLength = 0;
        for (char c : s.toCharArray()) {
            if (!set.contains(c)) {
                set.add(c);
                curLength++;
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
            } else {
                set = new HashSet<>();
                curLength = 1;
                set.add(c);
            }
        }
        return maxLength;
        /*String[] strings = s.split(" ");
        int maxLength = 0;
        String maxString = "";
        for (String string : strings) {
            if (maxLength < string.length()) {
                maxLength = string.length();
                maxString = string;
            }
        }
        *//*maxString = maxString.replaceAll("[0-9]", "");
        Set<Character> set = new HashSet<>();
        for (char c : maxString.toCharArray()) {
            set.add(c);
        }
        return set.size();*//*
        return maxString.chars().distinct().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().length();*/
    }
}
