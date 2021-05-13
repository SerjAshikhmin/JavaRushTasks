package com.javarush.task.task05.task0518;

/* 
Регистрируем собачек
*/


public class Dog {
    //напишите тут ваш код
    private String name, color;
    private int height;

    public Dog (String n){
        name = n;
    }

    public Dog (String n, int h){
        name = n;
        height = h;
    }

    public Dog (String n, int h, String c){
        name = n;
        height = h;
        color = c;
    }


    public static void main(String[] args) {

    }
}
