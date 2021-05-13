package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        int spacePos = 0;
        int firstSpace = 0;
        String[] words = string.split(" ");
        if (words.length < 5) throw new TooShortStringException();
        String partString = "";
        for (int i = 1; i < 5; i++) {
            partString += words[i];
            if (i != 4) partString += " ";
        }
        return partString;
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
