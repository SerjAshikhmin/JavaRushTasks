package com.javarush.task.task05.task0517;

/* 
Конструируем котиков
*/

public class Cat {
    //напишите тут ваш код
    private String name, color, address;
    private int age, weight;

    public Cat  (String n){
        name = n;
        weight = 5;
        age = 7;
        color = "Серый";
    }

    public Cat  (String n, int w, int a){
        name = n;
        weight = w;
        age = a;
        color = "Серый";
    }

    public Cat  (int w, String c){
        weight = w;
        color = c;
        age = 7;
    }

    public Cat  (String n, int a){
        name = n;
        age = a;
        weight = 5;
        color = "Серый";
    }

    public Cat (int w, String c, String a){
        weight = w;
        color = c;
        address = a;
        age = 7;
    }



    public static void main(String[] args) {

    }
}
