package com.javarush.task.task07.task0715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Продолжаем мыть раму
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        ArrayList<String> sList = new ArrayList<String>();

        sList.add("мама");
        sList.add("мыла");
        sList.add("раму");

        for (int i = 1; i <= 5; i = i+2){
            sList.add(i, "именно");
        }

        for (int i = 0; i <= 5; i++) {
            System.out.println(sList.get(i));
        }

    }
}
