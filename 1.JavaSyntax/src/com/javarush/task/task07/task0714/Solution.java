package com.javarush.task.task07.task0714;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Слова в обратном порядке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> sList = new ArrayList();

        for (int i = 0; i < 5; i++) {
            sList.add(reader.readLine());
        }

        sList.remove(2);

        for (int i = 3; i >= 0; i--) {
            System.out.println(sList.get(i));
        }
    }

}
