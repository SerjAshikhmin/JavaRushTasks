package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        if (string == null) throw new TooShortStringException();
        int firstTab = string.indexOf("\t");
        if (firstTab == -1) throw new TooShortStringException();
        int secondTab = string.indexOf("\t", firstTab + 1);
        if (secondTab == -1) throw new TooShortStringException();
        return string.substring(firstTab + 1, secondTab);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
