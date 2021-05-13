package com.javarush.task.task08.task0814;

import java.util.HashSet;
import java.util.Set;

/* 
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static Set<Integer> createSet() {
        // напишите тут ваш код
        Set<Integer> set = new HashSet<Integer>();
        set.add(10);
        set.add(0);
        set.add(155);
        set.add(26);
        set.add(4);
        set.add(5);
        set.add(55);
        set.add(78);
        set.add(7);
        set.add(9);
        set.add(2);
        set.add(17);
        set.add(21);
        set.add(44);
        set.add(45);
        set.add(46);
        set.add(738);
        set.add(12);
        set.add(19);
        set.add(86);

        return set;
    }

    public static Set<Integer> removeAllNumbersGreaterThan10(Set<Integer> set) {
        // напишите тут ваш код
        Set<Integer> set1 = new HashSet<Integer>();
        set1.addAll(set);
        for (int number : set1){
            if (number > 10) set.remove(number);
        }

        return set;
    }

    public static void main(String[] args) {
        removeAllNumbersGreaterThan10(createSet());
    }
}