package com.javarush.task.task07.task0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Минимаксы в массивах
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] sList = new int[20];

        for (int i = 0; i < 20; i++) {
            sList[i] = Integer.parseInt(reader.readLine());
        }

        int min = sList[0];
        int max = sList[0];
        for (int i = 1; i < 20; i++) {
            if (sList[i] > max) max = sList[i];
            if (sList[i] < min) min = sList[i];
        }

        System.out.println(max + " " + min);
    }
}
