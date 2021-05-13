package com.javarush.task.task04.task0442;

/* 
Суммирование
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0, a = 0;

        while (a != -1) {
            a = Integer.parseInt(reader.readLine());
            sum = sum + a;
        }

        System.out.println(sum);

    }
}
