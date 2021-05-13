package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private String name, address;
        private int age, weight, height;
        private boolean sex;

        public Human(String name, String address, int age, int weight, int height, boolean sex){
            this.name = name;
            this.address = address;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }

        public Human(String name, String address, int age, int weight, int height){
            this.name = name;
            this.address = address;
            this.age = age;
            this.weight = weight;
            this.height = height;
        }

        public Human(String name, String address, int age, int weight){
            this.name = name;
            this.address = address;
            this.age = age;
            this.weight = weight;
        }

        public Human(String name, String address, int age){
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public Human(String name, String address){
            this.name = name;
            this.address = address;
        }

        public Human(String name){
            this.name = name;
        }

        public Human(String name, int age, int weight, int height, boolean sex){
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }

        public Human(String name, String address, int weight, int height, boolean sex){
            this.name = name;
            this.address = address;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }

        public Human(String name, int weight, int height){
            this.name = name;
            this.weight = weight;
            this.height = height;
        }

        public Human(String name, int weight){
            this.name = name;
            this.weight = weight;
        }


        // Напишите тут ваши переменные и конструкторы
    }
}
