package com.javarush.task.task04.task0441;

/* 
Как-то средненько
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

        if ((a >= b && a <= c)||(a <= b && a >= c)) m = a;
        else
        if ((b >= a && b <= c)||(b <= a && b >= c)) m = b;
        else m = c;

        System.out.println(m);

    }
}
