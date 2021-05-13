package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) {
                break;
            }
            list.add(s);
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        //for (String x : array) {
            //System.out.println(x);
        //}
    }

    public static void sort(String[] array) {
        // напишите тут ваш код
        ArrayList<String> sArray = new ArrayList<String>();
        ArrayList<Integer> nArray = new ArrayList<Integer>();

        for (int i = 0; i < array.length; i++){
            if (isNumber(array[i])) nArray.add(Integer.parseInt(array[i]));
            else sArray.add(array[i]);
        }

        for (int i = 0; i < sArray.size() - 1; i++){
            for (int j = 0; j < sArray.size() - 1 - i; j++) {
                if (isGreaterThan(sArray.get(j), sArray.get(j+1))){
                    String s = sArray.get(j);
                    sArray.set(j, sArray.get(j+1));
                    sArray.set(j + 1, s);
                }
            }
        }

        for (int i = 0; i < nArray.size() - 1; i++){
            for (int j = 0; j < nArray.size() - 1 - i; j++) {
                if (nArray.get(j) < nArray.get(j+1)){
                    int m = nArray.get(j);
                    nArray.set(j, nArray.get(j+1));
                    nArray.set(j + 1, m);
                }
            }
        }

        for (String s : sArray){
            System.out.println(s);
        }

        for (int n : nArray){
            System.out.println(n);
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // Строка содержит '-'
                    || (!Character.isDigit(c) && c != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && c == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
