package com.javarush.task.task34.task3403;

import java.math.BigInteger;

/*
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        for (int i = 2; i <= n; i++) {
            Integer integer = i;
            // Простое число проверка
            BigInteger bigInteger = BigInteger.valueOf(integer);
            boolean probablePrime = bigInteger.isProbablePrime((int) Math.log(integer));
            if (probablePrime && n % i == 0) {
                System.out.print(i + " ");
                if (n / i != 1) {
                    recurse(n / i);
                }
                i = n + 1;
            }
        }
    }
}
