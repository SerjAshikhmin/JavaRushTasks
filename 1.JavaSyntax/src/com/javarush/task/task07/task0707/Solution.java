package com.javarush.task.task07.task0707;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Что за список такой?
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> sList = new ArrayList();

        sList.add("s1");
        sList.add("s2");
        sList.add("s3");
        sList.add("s4");
        sList.add("s5");

        System.out.println(sList.size());
        for (int i = 0; i < 5; i++) {
            System.out.println(sList.get(i));
        }

    }
}
