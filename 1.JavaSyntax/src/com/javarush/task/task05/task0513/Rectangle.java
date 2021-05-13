package com.javarush.task.task05.task0513;

/* 
Собираем прямоугольник
*/

public class Rectangle {
    //напишите тут ваш код
    private int left, top, width, height;

    public void initialize (int l, int t, int w, int h){
        left = l;
        top = t;
        width = w;
        height = h;
    }

    public void initialize (int l, int t, int w){
        left = l;
        top = t;
        width = w;
        height = w;
    }

    public void initialize (int l, int t){
        left = l;
        top = t;
        width = 0;
        height = 0;
    }

    public void initialize (Rectangle r){
        left = r.left;
        top = r.top;
        width = r.width;
        height = r.height;
    }


    public static void main(String[] args) {

    }
}
