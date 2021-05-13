package com.javarush.task.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Выражаемся покороче
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> sList = new ArrayList();

        for (int i = 0; i < 5; i++) {
            sList.add(reader.readLine());
        }

        int minL = sList.get(0).length();

        for (int i = 1; i < 5; i++) {
            if (sList.get(i).length() < minL) minL = sList.get(i).length();
        }

        for (int i = 0; i < 5; i++) {
            if (sList.get(i).length() == minL) System.out.println(sList.get(i));
        }

    }
}
