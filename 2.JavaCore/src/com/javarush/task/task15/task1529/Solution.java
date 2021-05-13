package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static CanFly result;

    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static void reset() {
        //add your code here - добавьте код тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String text = reader.readLine();
            if (text.equals("helicopter")) result = new Helicopter();
            if (text.equals("plane")) result = new Plane(45);
        } catch (Exception e) {e.printStackTrace();}
    }
}
