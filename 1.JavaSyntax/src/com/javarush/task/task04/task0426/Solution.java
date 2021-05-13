package com.javarush.task.task04.task0426;

/* 
Ярлыки и числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        String compareZero, parity;

        if (a > 0) {
            compareZero = "положительное";
        }
        else {
            compareZero = "отрицательное";
        }

        if (a % 2 == 0) {
            parity = "четное";
        }
        else {
            parity = "нечетное";
        }

        if (a == 0) {
            System.out.println("ноль");
        }
        else {
            System.out.println(compareZero + " " + parity + " число");
        }
    }
}
