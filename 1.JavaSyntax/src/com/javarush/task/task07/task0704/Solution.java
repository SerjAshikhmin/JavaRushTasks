package com.javarush.task.task07.task0704;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Переверни массив
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] aInt = new int[10];
        for (int i = 0; i < 10; i++) {
            aInt[i] = Integer.parseInt(reader.readLine());
        }

        int[] bInt = new int[10];
        for (int i = 0; i < 10; i++) {
            bInt[i] = aInt[9-i];
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(bInt[i]);
        }

    }
}

