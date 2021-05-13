package com.javarush.task.task07.task0719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Вывести числа в обратном порядке
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> sList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            sList.add(Integer.parseInt(reader.readLine()));
        }

        for (int i = 0; i <= sList.size()-1; i++) {
            System.out.println(sList.get(9-i));
        }

        //напишите тут ваш код
    }
}
