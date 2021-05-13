package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        Human grandma1 = new Human("Клава", false, 84);
        Human grandma2 = new Human("Дуня", false, 87);
        Human grandfa1 = new Human("Семьон", true, 79);
        Human grandfa2 = new Human("Кириллий", true, 82);
        Human father = new Human("Иванний", true, 45, grandfa1, grandma1);
        Human mother = new Human("Олька", false, 27, grandfa2, grandma2);
        Human dot1 = new Human("Эльза", false, 15, father, mother);
        Human dot2 = new Human("Василиса", false, 12, father, mother);
        Human son = new Human("Эллиот", true, 9, father, mother);

        System.out.println(grandma1);
        System.out.println(grandma2);
        System.out.println(grandfa1);
        System.out.println(grandfa2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(dot1);
        System.out.println(dot2);
        System.out.println(son);

    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        public Human(String n, boolean s, int a, Human f, Human m){
            name = n;
            sex = s;
            age = a;
            father = f;
            mother = m;
        }

        public Human(String n, boolean s, int a){
            name = n;
            sex = s;
            age = a;
        }

        public String toString(){
            String out = "Имя: " + name;
            out += ", пол: " + (sex ? "мужской" : "женский");

            if (father != null)
                out += ", отец: " + father.name;

            if (mother != null)
                out += ", мать: " + mother.name;
            return out;
        }
    }

}