package com.javarush.task.task39.task3902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Биты были биты
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long l = Long.parseLong(reader.readLine());
        String result = isWeightEven(l) ? "even" : "odd";
        System.out.println("The entered number has " + result + "ones");

    }

    public static boolean isWeightEven(long number) {
        int onesCount = 0;
        for (int i = 0; i < 64; i++) {
            long n = number & 1;
            if (n == 1) {
                onesCount++;
            }
            number = number >> 1;
        }
        return onesCount % 2 == 0;
        /*long invNumber = ~number;
        long numbOfOnes = number | invNumber;
        String s = Long.toBinaryString(number & numbOfOnes);
        System.out.println(s);
        return false;*/
    }
}
