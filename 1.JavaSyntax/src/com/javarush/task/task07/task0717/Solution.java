package com.javarush.task.task07.task0717;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Удваиваем слова
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        // Считать строки с консоли и объявить ArrayList list тут

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> sList = new ArrayList();

        for (int i = 0; i < 10; i++) {
            sList.add(reader.readLine());
        }

        List<String> doubleList;
        doubleList = doubleValues(sList);

        for (int i = 0; i <= doubleList.size()-1; i++) {
            System.out.println(doubleList.get(i));
        }

        // Вывести на экран result
    }

    public static List<String> doubleValues(List<String> list) {
        //напишите тут ваш код
        for (int i = 0; i <= list.size()-1; i = i + 2) {
            list.add(i, list.get(i));
        }
        return list;
    }

}
