package com.javarush.task.task07.task0711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Удалить и вставить
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> sList = new ArrayList();

        for (int i = 0; i < 5; i++) {
            sList.add(reader.readLine());
        }

        for (int i = 0; i < 13; i++) {
            sList.add(0, sList.get(4));
            sList.remove(5);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(sList.get(i));
        }

    }
}
