package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = new int[15];
        for (int i = 0; i < 15; i++) {
            array[i] = Integer.parseInt(reader.readLine());
        }

        int even = 0, odd = 0;
        for (int i = 0; i < 15; i++) {
            if (i % 2 == 0 || i == 0) even = even + array[i];
            else odd = odd + array[i];
        }

        if (even > odd) System.out.println("В домах с четными номерами проживает больше жителей.");
        else System.out.println("В домах с нечетными номерами проживает больше жителей.");

    }
}
