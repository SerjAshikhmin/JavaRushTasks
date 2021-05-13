package com.javarush.task.task05.task0511;

/* 
Создать класс Dog
*/

public class Dog {
    //напишите тут ваш код
    private String name, color;
    private int height;

    public void initialize (String n){
        name = n;
    }

    public void initialize (String n, int h){
        name = n;
        height = h;
    }

    public void initialize (String n, int h, String c){
        name = n;
        height = h;
        color = c;
    }


    public static void main(String[] args) {

    }
}
