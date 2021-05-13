package com.javarush.task.task06.task0611;

/* 
Класс StringHelper
*/

public class StringHelper {
    public static String multiply(String s, int count) {
        String st = "";
        for (int i = 1; i <= count; i++){
            st = st + s;
        }
        return st;
    }

    public static String multiply(String s) {
        return s+s+s+s+s;
    }


    public static void main(String[] args) {

    }
}
