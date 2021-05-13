package com.javarush.task.task05.task0520;

/* 
Создать класс прямоугольник (Rectangle)
*/


public class Rectangle {
    //напишите тут ваш код
    private int left, top, width, height;

    public Rectangle (int l, int t, int w, int h){
        left = l;
        top = t;
        width = w;
        height = h;
    }

    public Rectangle (int l, int t){
        left = l;
        top = t;
        width = 0;
        height = 0;
    }

    public Rectangle (int l, int t, int w){
        left = l;
        top = t;
        width = w;
        height = w;
    }

    public Rectangle (Rectangle aRect){
        left = aRect.left;
        top = aRect.top;
        width = aRect.width;
        height = aRect.height;
    }


    public static void main(String[] args) {

    }
}
