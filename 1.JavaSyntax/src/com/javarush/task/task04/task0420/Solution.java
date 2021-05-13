package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        int m;

        if (a <= b) {
            m = a;
            a = b;
            b = m;
        }
        if (a <= c) {
            m = a;
            a = c;
            c = m;
        }
        if (b <= c) {
            m = b;
            b = c;
            c = m;
        }
        System.out.println(a + " "+ b + " " + c);

    }
}
