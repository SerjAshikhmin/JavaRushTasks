package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        BigInteger tenDigitValue = null;
        int minDigit = 0;
        for (int i = 2; i <= 36; i++) {
            try {
                tenDigitValue = new BigInteger(args[0], i);
            } catch (Exception e) {}
            if (tenDigitValue != null) {
                minDigit = i;
                break;
            }
        }
        if (minDigit == 0) {
            System.out.println("incorrect");
        } else {
            System.out.println(minDigit);
        }
    }
}