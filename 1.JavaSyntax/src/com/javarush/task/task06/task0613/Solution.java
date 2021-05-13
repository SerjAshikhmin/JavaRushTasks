package com.javarush.task.task06.task0613;

import java.util.ArrayList;

/* 
Кот и статика
*/

public class Solution {
    public static void main(String[] args) {
        // Создай 10 котов
        for (int i = 0; i < 10; i++){
            new Cat();
        }
        System.out.println(Cat.catCount);
        // Выведи значение переменной catCount
    }

    public static class Cat {
        public static int catCount;
        public static ArrayList<Cat> cats = new ArrayList();

        public Cat(){
            catCount++;
            cats.add(this);
        }

        public static void printCats(){
            System.out.println(cats);
        }

    }
}
