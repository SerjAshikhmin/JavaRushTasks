package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> sList = new ArrayList();

        for (int i = 0; i < 10; i++) {
            sList.add(reader.readLine());
        }

        int currentLength = sList.get(0).length();

        boolean isOrdered = true;
        for (int i = 1; i < 10; i++) {
            if (sList.get(i).length() >= currentLength) currentLength = sList.get(i).length();
            else {
                System.out.println(i);
                isOrdered = false;
                break;
            }
        }
    }

}


