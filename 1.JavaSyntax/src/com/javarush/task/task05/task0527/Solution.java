package com.javarush.task.task05.task0527;

/* 
Том и Джерри
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Cat tom = new Cat ("Том", 8, "Голубой");
        Mouse jerry = new Mouse ("Джерри", 1, "Коричневый");
        Dog spike = new Dog ("Спайк", 11, "Серый");
    }

    public static class Dog {
        private String name, color;
        private int weight;

        public Dog (String n, int w, String c){
            name = n;
            weight = w;
            color = c;
        }
    }

    public static class Cat {
        private String name, color;
        private int weight;

        public Cat (String n, int w, String c){
            name = n;
            weight = w;
            color = c;
        }
    }

    public static class Mouse {
        private String name, color;
        private int weight;

        public Mouse (String n, int w, String c){
            name = n;
            weight = w;
            color = c;
        }
    }

    //напишите тут ваш код
}
