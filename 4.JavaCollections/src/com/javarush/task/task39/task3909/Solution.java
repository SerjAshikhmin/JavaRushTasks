package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("afsd", "asd"));
    }

    public static boolean isOneEditAway(String first, String second) {
        int diff = 0;

        for (int i = 0, j = 0; i < first.length() && j < second.length(); i++, j++) {
            if (first.charAt(i) != second.charAt(j)) {
                diff++;
                if (first.length() < second.length()) {
                    i--;
                } else {
                    if (first.length() > second.length()) {
                        j--;
                    }
                }
            }
        }

        return diff < 2;
        /*if (first == "" && second == "") return true;
        for (int i = 0; i < first.length(); i++) {
            String s = first.replace(first.charAt(i), Character.MIN_VALUE).trim();
            if (second.equals(s)) {
                return true;
            }
        }
        for (int i = 0; i < second.length(); i++) {
            String s = second.replace(second.charAt(i), Character.MIN_VALUE).trim();
            if (first.equals(s)) {
                return true;
            }
        }
        return false;*/
    }
}
