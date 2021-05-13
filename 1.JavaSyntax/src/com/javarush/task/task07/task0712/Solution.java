package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> sList = new ArrayList();

        for (int i = 0; i < 10; i++) {
            sList.add(reader.readLine());
        }

        int minI = 9;
        int maxI = 9;

        int minL = sList.get(9).length();
        int maxL = sList.get(9).length();
        for (int i = 8; i >= 0; i--) {
            if (sList.get(i).length() >= maxL) {
                maxL = sList.get(i).length();
                maxI = i;
            }
            if (sList.get(i).length() <= minL) {
                minL = sList.get(i).length();
                minI = i;
            }
        }

        if (minI < maxI) System.out.println(sList.get(minI));
        else System.out.println(sList.get(maxI));

    }
}
