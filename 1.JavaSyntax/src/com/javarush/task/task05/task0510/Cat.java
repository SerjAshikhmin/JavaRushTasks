package com.javarush.task.task05.task0510;

/* 
Кошкоинициация
*/

public class Cat {
    //напишите тут ваш код
    private String name, address, color;
    private int age, weight;

    public void initialize (String n){
        name = n;
        weight = 5;
        age = 7;
        color = "Серый";
    }

    public void initialize (String n, int w, int a){
        name = n;
        weight = w;
        age = a;
        color = "Серый";
    }

    public void initialize (int w, String c){
        weight = w;
        color = c;
        age = 7;
    }

    public void initialize (String n, int a){
        name = n;
        age = a;
        weight = 5;
        color = "Серый";
    }

    public void initialize (int w, String c, String a){
        weight = w;
        color = c;
        address = a;
        age = 7;
    }


    public static void main(String[] args) {

    }
}
