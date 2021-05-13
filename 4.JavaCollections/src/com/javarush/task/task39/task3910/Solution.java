package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(45));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 1) return true;
        if (n >= 3) {
            if (n % 3 == 0 && isPowerOfThree(n / 3)) {
                return true;
            }
        }

        return false;
    }
}
