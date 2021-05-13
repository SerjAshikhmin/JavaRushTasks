package com.javarush.task.task20.task2025;

import java.util.*;

/*
Алгоритмы-числа
*/

public class Solution {

    static long[][] degreeMatrix = new long[10][20];

    static {
        for (int i = 0; i < degreeMatrix[0].length; i++) {
            degreeMatrix[0][i] = 0;
        }
        for (int i = 0; i < degreeMatrix[0].length; i++) {
            degreeMatrix[1][i] = 1;
        }
        for (int i = 2; i < degreeMatrix.length; i++) {
            for (int j = 1; j < degreeMatrix[0].length; j++) {
                degreeMatrix[i][j] = i;
                for (int k = 1; k < j; k++) {
                    degreeMatrix[i][j] *= i;
                }
            }
        }
    }

    public static long[] getNumbers(long N) {
        long[] result;
        Set<Long> armstrongNumbers = new TreeSet<>();
        long defaultN = N;
        if (N > 0 && N <= Long.MAX_VALUE ) {
            int degree = String.valueOf(N).length();
            int[] nextNumber = new int[degree];
            for (int i = 0; i < nextNumber.length; i++) {
                nextNumber[i] = 9;
            }
            while (true) {
                boolean isContinue = true;
                int i = 0;
                while (isContinue && i < nextNumber.length) {
                    nextNumber[i]--;
                    if (nextNumber[0] == 0) {
                        int k = 0;
                        do {
                            int[] numberWithZeros = new int[nextNumber.length - k];
                            for (int j = 0; j < numberWithZeros.length; j++) {
                                numberWithZeros[j] = nextNumber[j + k];
                            }
                            checkDegreeSummAsArmstrongNumber(armstrongNumbers, defaultN, numberWithZeros);
                            k++;
                        } while (k < nextNumber.length && nextNumber[k - 1] == 0);
                        for (int j = 0; j < nextNumber.length - 1; j++) {
                            if (nextNumber[j] == 0 && nextNumber[j + 1] != 0) {
                                if (nextNumber[j + 1] != 1) {
                                    isContinue = false;
                                    break;
                                } else {
                                    i++;
                                    break;
                                }
                            }
                        }
                        if (nextNumber[nextNumber.length - 1] == 0) isContinue = false;
                    } else {
                        checkDegreeSummAsArmstrongNumber(armstrongNumbers, defaultN, nextNumber);
                    }
                }
                if (nextNumber[nextNumber.length - 1] != 0) {
                    for (int k = 0; k < nextNumber.length; k++) {
                        if (nextNumber[k] != 0) {
                            nextNumber[k]--;
                            for (int j = 0; j <= k; j++) {
                                nextNumber[j] = nextNumber[k];
                            }
                            i = 0;
                            nextNumber[0]++;
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        result = new long[armstrongNumbers.size()];
        int i = 0;
        for (Long armstrongNumber : armstrongNumbers) {
            result[i] = armstrongNumber;
            i++;
        }
        return result;
    }

    private static void checkDegreeSummAsArmstrongNumber(Set<Long> armstrongNumbers, long defaultN, int[] nextNumberDigits) {
        long currentDegreeSumm = calculateDegreeSumm(nextNumberDigits);
        if (currentDegreeSumm > 0 && currentDegreeSumm < defaultN && isArmstrongNumber(currentDegreeSumm)) {
            armstrongNumbers.add(currentDegreeSumm);
        }
    }

    private static boolean isArmstrongNumber(long n) {
        int degree = String.valueOf(n).length();
        long summ = 0;
        long defaultN = n;
        while (n > 0) {
            int digit = (int) (n % 10);
            summ += degreeMatrix[digit][degree];
            if (summ > defaultN || summ < 0) return false;
            n /= 10;
        }
        if (defaultN == summ) {
            return true;
        } else {
            return false;
        }
    }

    private static long calculateDegreeSumm(int[] number) {
        int degree = number.length;
        long summ = 0;
        for (int i = 0; i < number.length; i++) {
            summ += degreeMatrix[number[i]][degree];
        }
        return summ;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(1000)));
        long b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);

        a = System.currentTimeMillis();
        System.out.println(Arrays.toString(getNumbers(Long.MAX_VALUE)));
        b = System.currentTimeMillis();
        System.out.println("memory " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (8 * 1024));
        System.out.println("time = " + (b - a) / 1000);
    }
}
