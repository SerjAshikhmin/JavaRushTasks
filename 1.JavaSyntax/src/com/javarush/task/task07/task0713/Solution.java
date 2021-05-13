package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> sList = new ArrayList<>();
        List<Integer> m2List = new ArrayList<>();
        List<Integer> m3List = new ArrayList<>();
        List<Integer> noneList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            sList.add(Integer.parseInt(reader.readLine()));
        }

        for (int i = 0; i < 20; i++) {
            if (sList.get(i) % 3 == 0) m2List.add(sList.get(i));
            if (sList.get(i) % 2 == 0) m3List.add(sList.get(i));
            if (sList.get(i) % 2 != 0 && sList.get(i) % 3 != 0) noneList.add(sList.get(i));
        }

        printList(m2List);
        printList(m3List);
        printList(noneList);
    }

    public static void printList(List<Integer> list) {
        //напишите тут ваш код
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

}
