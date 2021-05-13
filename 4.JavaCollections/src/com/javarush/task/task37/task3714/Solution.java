package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int[] intArray = new int[s.length()];
        int sumDigits = 0;
        for (int i = 0; i < s.length(); i++) {
            int digit = 0;
            switch (s.charAt(i)) {
                case 'I':
                    digit = 1;
                    break;
                case 'V':
                    digit = 5;
                    break;
                case 'X':
                    digit = 10;
                    break;
                case 'L':
                    digit = 50;
                    break;
                case 'C':
                    digit = 100;
                    break;
                case 'D':
                    digit = 500;
                    break;
                case 'M':
                    digit = 1000;
                    break;
            }
            intArray[i] = digit;
        }

        for (int i = 0; i < intArray.length; i++) {
            if ((i != intArray.length - 1) && (intArray[i] < intArray[i +1])) {
                sumDigits -= intArray[i];
            } else {
                sumDigits += intArray[i];
            }
        }
        return sumDigits;
    }
}
