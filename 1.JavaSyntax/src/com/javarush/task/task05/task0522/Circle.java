package com.javarush.task.task05.task0522;

/* 
Максимум конструкторов
*/

public class Circle {
    public double x;
    public double y;
    public double radius;

    public Circle (){
        this.x = 5;
        this.y = 5;
        this.radius = 2;
    }

    public Circle (double x, double y, double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle (double x, double y){
        this.x = x;
        this.y = y;
        this.radius = 2;
    }

    public Circle (double radius){
        this.x = 5;
        this.y = 5;
        this.radius = radius;
    }

    //напишите тут ваш код

    public static void main(String[] args) {

    }
}