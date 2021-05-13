package com.javarush.task.task10.task1015;

import java.util.ArrayList;

/* 
Массив списков строк
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String>[] arrayOfStringList = createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList() {
        //напишите тут ваш код
        ArrayList<String>[] arrayOfStringList = new ArrayList[5];

        for (int j = 0; j < 5; j++){
            arrayOfStringList[j] = new ArrayList<String>();
            for (int i = 0; i < 5; i++){
                arrayOfStringList[j].add("Строка " + j + "-" + i);
            }
        }

        return arrayOfStringList;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList) {
        for (ArrayList<String> list : arrayOfStringList) {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }
}