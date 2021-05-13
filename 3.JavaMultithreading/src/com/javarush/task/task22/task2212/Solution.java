package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        boolean result = false;
        if (telNumber == null || telNumber.isEmpty()) return false;
        result = telNumber.matches("(?:\\+\\d{2})?\\(?\\d{3}\\)?\\d{3}-?\\d{2}-?\\d{2}");

        return result;
    }

    public static void main(String[] args) {

    }
}
