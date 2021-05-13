package com.javarush.task.task08.task0824;

import java.util.ArrayList;
import java.util.List;

/* 
Собираем семейство
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        ArrayList children1 = new ArrayList<Human>();
        ArrayList children2 = new ArrayList<Human>();
        ArrayList children3 = new ArrayList<Human>();
        ArrayList children4 = new ArrayList<Human>();

        Human c1 = new Human("Петя", true, 3, children1);
        Human c2 = new Human("Хобот", true, 5, children1);
        Human c3 = new Human("Челси", false, 6, children1);

        children2.add(c1);
        children2.add(c2);
        children2.add(c3);

        Human mother = new Human("Кобра", false, 56, children2);

        Human father = new Human("Мухтар", true, 65, children2);

        children3.add(mother);

        Human gm1 = new Human("Байка", false, 88, children3);
        Human gf1 = new Human("Йохан", true, 88, children3);

        children4.add(father);

        Human gm2 = new Human("Ивва", false, 74, children4);
        Human gf2 = new Human("Экхилл", true, 77, children4);

        System.out.println(gf1);
        System.out.println(gf2);
        System.out.println(gm1);
        System.out.println(gm2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }

    public static class Human {
        public String name;
        public Boolean sex;
        public int age;
        public ArrayList<Human> children;
        //напишите тут ваш код

        public Human (String name, Boolean sex, int age, ArrayList<Human> children){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }
}
