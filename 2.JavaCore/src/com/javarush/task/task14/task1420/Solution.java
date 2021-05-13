package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseUnsignedInt(reader.readLine());
        int n = Integer.parseUnsignedInt(reader.readLine());

        if (m <= 0 || n <= 0) throw new Exception();

        int nod = 1;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0 && m % i == 0) nod = i;
        }

        System.out.println(nod);
    }
}
