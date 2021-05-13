package com.javarush.task.task39.task3908;

import java.util.HashMap;
import java.util.Map;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("asdfgasd"));
    }

    public static boolean isPalindromePermutation(String s) {
        s = s.toLowerCase();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, 0);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int k = map.get(c);
            map.put(c, ++k);
        }
        int numberOfOdds = 0;
        for (Integer value : map.values()) {
            if (value % 2 == 1) {
                numberOfOdds++;
            }
        }
        System.out.println(numberOfOdds);
        if (s.length() % 2 == 0) {
            return numberOfOdds == 0;
        } else {
            return numberOfOdds == 1;
        }

        /*int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (s.lastIndexOf(c) != i) {
                s = s.replaceFirst(String.valueOf(c), "");
                s = s.replaceFirst(String.valueOf(c), "");
            } else
                i++;
        }
        //System.out.println(s);
        if (s.length() <= 1) return true;
        return false;*/
    }
}
