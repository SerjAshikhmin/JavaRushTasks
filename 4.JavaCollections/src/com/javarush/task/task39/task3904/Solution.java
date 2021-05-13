package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/
public class Solution {
    private static int n = 10;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n == 0) return 1;
        if (n < 0) return 0;
        int i = 4;
        long a = 1;
        long b = 2;
        long c = 4;
        long sum_fib = 0;
        while(i++ <= n){
            sum_fib = a + b + c;
            a = b;
            b = c;
            c = sum_fib;
        }
        return sum_fib;
        /*numbOfWays++;
        numberOfPossibleAscents(n - 1);
        if (n > 1) numberOfPossibleAscents(n - 2);
        if (n > 2) numberOfPossibleAscents(n - 3);
        return numbOfWays;*/
    }
}

