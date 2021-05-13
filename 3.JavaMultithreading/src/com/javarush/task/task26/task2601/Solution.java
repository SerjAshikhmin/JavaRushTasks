package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{13, 8, 15, 5, 17, 7};
        array = sort(array);
        for (int i = 0; i < array.length; i++) {
            //System.out.print(array[i] + " ");
        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        //Находим медиану
        //Сортируем массив из параметра
        int median;
        Arrays.sort(array);
        //Если массив с четным колличеством элементов
        if (array.length % 2 == 0) median = (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
            //И если с нечетным
        else median = array[array.length / 2];

        //Сортируем по условию
        Comparator<Integer> medSort = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(median - o1), Math.abs(median - o2));
            }
        };
        Arrays.sort(array, medSort);
        //System.out.println(median);
        return array;
    }
}
