package com.javarush.task.task07.task0720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Перестановочка подоспела
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> sList = new ArrayList<String>();
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            sList.add(reader.readLine());
        }

        for (int i = 0; i < m; i++) {
            String s = sList.get(i);
            sList.add(s);
        }

        int delCount = 0;

        while (delCount < m) {
            sList.remove(0);
            delCount++;
        }

        for (int i = 0; i <= sList.size()-1; i++) {
            System.out.println(sList.get(i));
        }

        //напишите тут ваш код
    }
}
