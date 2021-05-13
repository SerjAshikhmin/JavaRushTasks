package com.javarush.task.task05.task0519;

/* 
Ходим по кругу
*/

public class Circle {
    //напишите тут ваш код
    private int centerX, centerY, radius, width, color;

    public Circle (int x, int y, int r, int w, int c){
        centerX = x;
        centerY = y;
        radius = r;
        width = w;
        color = c;
    }

    public Circle (int x, int y, int r, int w){
        centerX = x;
        centerY = y;
        radius = r;
        width = w;
    }

    public Circle (int x, int y, int r){
        centerX = x;
        centerY = y;
        radius = r;
    }


    public static void main(String[] args) {

    }
}
