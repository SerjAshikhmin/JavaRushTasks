package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if (line.equals("exit")) break;
            try {
                if (line.contains(".")) Solution.print(Double.parseDouble(line));
                else {
                    int n = Integer.parseInt(line);
                    if (n > 0 && n < 128) Solution.print((short) n);
                    else if (n <= 0 || n >= 128) Solution.print(n);
                }
            } catch (Exception e) {Solution.print(line);}
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
