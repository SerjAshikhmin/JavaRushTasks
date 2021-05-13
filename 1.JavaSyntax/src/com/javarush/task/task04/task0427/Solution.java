package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        String numbers="", parity="";

        if (a >= 1 && a <= 999) {
            if (a % 2 == 0) {
                parity = "четное";
            }
            else {
                parity = "нечетное";
            }

            if (a / 100 != 0) {
                numbers = "трехзначное";
            }
            else {
                if (a / 10 != 0) {
                    numbers = "двузначное";
                }
                else {
                    numbers = "однозначное";
                }
            }

            System.out.println(parity + " " + numbers + " число");
        }
    }

}
