package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = "";
        String obj = "";
        try {
            url = reader.readLine();
            url = url.substring(url.indexOf('?') + 1, url.length());

            String[] list = url.split("&");

            for (String sPar : list) {
                String[] par = sPar.split("=");
                if (par[0].equals("obj")) obj = par[1];
                System.out.print(par[0] + " ");
            }
            System.out.println();

            if (obj != "") {
                alert(Double.parseDouble(obj));
            }

        } catch (Exception e) {
            alert(obj);
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}