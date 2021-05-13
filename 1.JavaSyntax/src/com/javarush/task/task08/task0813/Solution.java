package com.javarush.task.task08.task0813;

import java.util.HashSet;
import java.util.Set;

/* 
20 слов на букву «Л»
*/

public class Solution {
    public static Set<String> createSet() {
        //напишите тут ваш код
        Set<String> set = new HashSet<String>();
        set.add("Лодка");
        set.add("Лекарь");
        set.add("Лапша");
        set.add("Лекарство");
        set.add("Лейка");
        set.add("Лиана");
        set.add("Лапта");
        set.add("Лепешка");
        set.add("Ларек");
        set.add("Лакей");
        set.add("Лепка");
        set.add("Лилия");
        set.add("Лебедь");
        set.add("Лактобактерия");
        set.add("Лекция");
        set.add("Лимон");
        set.add("Лакшери");
        set.add("Лоцман");
        set.add("Лудоман");
        set.add("Лювовь");

        return set;
    }

    public static void main(String[] args) {

        createSet();
    }
}
