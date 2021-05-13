package com.javarush.task.task04.task0417;

/* 
Существует ли пара?
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

        if ((a == b)&&(b == c)&&(a == c)) System.out.print(a + " " + b + " " + c);
        else {
            if (a == b) System.out.print(a + " " + b);
            if (a == c) System.out.print(a + " " + c);
            if (c == b) System.out.print(c + " " + b);
        }
    }
}