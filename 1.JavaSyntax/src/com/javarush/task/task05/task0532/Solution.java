package com.javarush.task.task05.task0532;

import java.io.*;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int max = 0;

        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                int a = Integer.parseInt(reader.readLine());
                if (i == 1) max = a;
                if (a > max) max = a;
            }

            System.out.println(max);
        }
    }
}
