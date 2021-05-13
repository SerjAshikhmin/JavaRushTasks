package com.javarush.task.task39.task3903;

import com.google.common.primitives.Chars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.BitSet;

/* 
Неравноценный обмен
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a number: ");

        long number = Long.parseLong(reader.readLine());
        System.out.println("Please enter the first index: ");
        int i = Integer.parseInt(reader.readLine());
        System.out.println("Please enter the second index: ");
        int j = Integer.parseInt(reader.readLine());

        System.out.println("The result of swapping bits is " + swapBits(number, i, j));
    }

    public static long swapBits(long number, int i, int j) {
        //System.out.println(Long.toBinaryString(number));
        /*char[] chars = Long.toBinaryString(number).toCharArray();
        char[] zeros = new char[Math.max(i, j)];
        for (int k = 0; k < zeros.length; k++) {
            zeros[k] = '0';
        }
        chars = Chars.concat(zeros, chars);
        char c = chars[i];
        chars[i] = chars[j];
        chars[j] = c;
        //System.out.println(String.copyValueOf(chars));
        return Long.parseLong(String.copyValueOf(chars), 2);*/
        long bit1 = (number >> i) & 1;
        long bit2 = (number >> j) & 1;
        if (bit1 == bit2) {
            return number;
        }
        int mask = (1 << i) | (1 << j);

        return number ^ mask;
        //number = (new BigInteger(String.copyValueOf(chars), 2)).longValue();
        /*//i = Long.toBinaryString(number).length() - i;
        //j = Long.toBinaryString(number).length() - j;
        BitSet bs = BitSet.valueOf(new long[] {number});
        bs.set();
        long invNumber = ~number;
        long ones = invNumber | number;
        long iDigit = (number >> i + 1l) & ones;
        long jDigit = (number >> j + 1l) & ones;
        if (iDigit != jDigit) {

        }
        number = (number & iDigit) | (number & jDigit);*/
        //System.out.println(Long.toBinaryString(number));
        //return number;
    }
}
