package com.javarush.task.task05.task0509;

/* 
Создать класс Friend
*/

public class Friend {
    private String name;
    private int age;
    private char sex;

    //напишите тут ваш код

    public static void main(String[] args) {

    }

    public void initialize (String n, int a, char s){
        name = n;
        age = a;
        sex = s;
    }

    public void initialize (String n, int a){
        name = n;
        age = a;
    }

    public void initialize (String n){
        name = n;
    }

}
