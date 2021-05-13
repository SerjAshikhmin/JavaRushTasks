package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arrayInt = new int[20];
        for (int i = 0; i < 20; i++) {
            arrayInt[i] = Integer.parseInt(reader.readLine());
        }

        int[] a1Int = new int[10];
        int[] a2Int = new int[10];

        for (int i = 0; i < 10; i++) {
            a1Int[i] = arrayInt[i];
        }

        for (int i = 0; i < 10; i++) {
            a2Int[i] = arrayInt[i+10];
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(a2Int[i]);
        }

    }
}
