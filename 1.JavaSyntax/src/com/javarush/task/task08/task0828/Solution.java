package com.javarush.task.task08.task0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Номер месяца
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("January", 0);
        map.put("February", 1);
        map.put("March", 2);
        map.put("April", 3);
        map.put("May", 4);
        map.put("June", 5);
        map.put("July", 6);
        map.put("August", 7);
        map.put("September", 8);
        map.put("October", 9);
        map.put("November", 10);
        map.put("December", 11);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();

        System.out.println(month + " is the " + (map.get(month)+1) + " month");
    }
}
