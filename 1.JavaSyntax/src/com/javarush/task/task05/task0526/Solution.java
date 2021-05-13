package com.javarush.task.task05.task0526;

/* 
Мужчина и женщина
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Man man1 = new Man("Кирилл", 7, "Орел");
        Man man2 = new Man("Кирилл2", 9, "Орел");
        Woman woman1 = new Woman ("Ира", 24, "Москва");
        Woman woman2 = new Woman ("Кира", 26, "Москва");
        System.out.println(man1);
        System.out.println(man2);
        System.out.println(woman1);
        System.out.println(woman2);

    }
    public static class Man {
        private String name, address;
        private int age;

        public Man (String n, int a, String ad){
            name = n;
            age = a;
            address = ad;
        }

        public String toString(){
            return name + " " + age + " " + address;
        }
    }

    public static class Woman {
        private String name, address;
        private int age;

        public Woman (String n, int a, String ad){
            name = n;
            age = a;
            address = ad;
        }

        public String toString(){
            return name + " " + age + " " + address;
        }
    }

    //напишите тут ваш код
}
