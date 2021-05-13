package com.javarush.task.task09.task0926;

import java.util.ArrayList;
import java.util.List;

/* 
Список из массивов чисел
*/

public class Solution {
    public static void main(String[] args) {
        List<int[]> list = createList();
        printList(list);
    }

    public static List<int[]> createList() {
        //напишите тут ваш код
        List<int[]> list = new ArrayList();
            list.add(new int[5]);
            list.add(new int[2]);
            list.add(new int[4]);
            list.add(new int[7]);
            list.add(new int[0]);

            for (int[] array : list){
                for (int i = 0; i < array.length; i++) {
                    array[i] = (int) (Math.random() * 100);
                }
            }

        return list;
    }

    public static void printList(List<int[]> list) {
        for (int[] array : list) {
            for (int x : array) {
                System.out.println(x);
            }
        }
    }
}
